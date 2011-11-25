package org.tiling.s3map;

import com.amazon.s3.AWSAuthConnection;
import com.amazon.s3.GetResponse;
import com.amazon.s3.ListBucketResponse;
import com.amazon.s3.ListEntry;
import com.amazon.s3.Response;
import com.amazon.s3.S3Object;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * <p>
 * An implementation of {@link Map} which uses <a href="http://aws.amazon.com/s3">Amazon S3</a> as its backing store.
 * All data is stored in a single S3 bucket, which is specified on construction of the map. The bucket is created
 * if it doesn't already exist. (Note pre-existing buckets must only contain S3Map entries - other entries
 * will corrupt the map.)
 * This implementation provides all of the optional map operations, but does not permit null values and the null key
 * (like {@link Hashtable}, and unlike {@link HashMap}).
 * Furthermore, this implementation does not provide the two "standard" constructors that
 * {@link Map} recommends since Amazon S3 connection details must always be supplied.
 * </p>
 * <p>
 * All keys are of type {@link String}, with the added restriction that their length (in UTF-8 encoding) must be between 1 and 1024
 * bytes (inclusive). If this latter condition is not met then a {@link InvalidKeyException} will be thrown.
 * </p>
 * <p>
 * All methods that interact with S3 (documented below) may throw the unchecked exception
 * {@link S3Exception} if there is a problem communicating with S3. The exception contains diagnostic
 * information.
 * </p>
 * <p>
 * Since Amazon S3 is a paid-for service it is important to have a feel for potentially how costly each map operation will be.
 * The following table lists the cost of each public method call, in terms of the number of S3 operations,
 * assuming there are <i>n</i> entries in the map. The cost is the worst-case cost, in some cases the cost may be lower - where
 * this is the case it is noted. Notice that {@link #containsValue(Object)}, {@link AbstractMap#equals(Object) equals(Object)}, and
 * {@link AbstractMap#hashCode() hashCode()} are surprisingly expensive since they are bound by their contract to operate on all their values.
 * </p>
 * <table border="1">
 * <tr>
 * <td>Method</td>
 * <td>List Keys Operations<br/>(Keys retrieved)</td>
 * <td>Read Object Operations</td>
 * <td>Write Object Operations</td>
 * <td>Delete Object Operations</td>
 * <td>Notes</td>
 * </tr>
 * <tr>
 * <td>{@link #clear()}</td><td><b>1 (<i>n</i>)</b></td><td>0</td><td>0</td><td><b><i>n</i></b></td>
 * <td>&nbsp;</td>
 * </tr>
 * <tr>
 * <td>{@link #containsKey(Object)}</td><td>0</td><td><b>1</b></td><td>0</td><td>0</td>
 * <td>&nbsp;</td>
 * </tr>
 * <tr>
 * <td>{@link #containsValue(Object)}</td><td><b>1 (<i>n</i>)</b></td><td><b><i>n</i></b></td><td>0</td><td>0</td>
 * <td>Likely fewer read operations if the map contains the object.</td>
 * </tr>
 * <tr>
 * <td>{@link #entrySet()}</td><td>0</td><td>0</td><td>0</td><td>0</td>
 * <td>The iterator for the set returned by this method invokes one List Keys operation (retrieving a single key each time) and one Read Object operation for each element iterated over.</td>
 * </tr>
 * <tr>
 * <td>{@link AbstractMap#equals(Object) equals(Object)}</td><td><b>1 (<i>n</i>)</b> + <b><i>n</i> (1)</b></td><td><i><b>n</b></i></td><td>0</td><td>0</td>
 * <td>Fewer operations if maps are not equal.</td>
 * </tr>
 * <tr>
 * <td>{@link #get(Object)}</td><td>0</td><td><b>1</b></td><td>0</td><td>0</td>
 * <td>&nbsp;</td>
 * </tr>
 * <tr>
 * <td>{@link #keySet()}</td><td>0</td><td>0</td><td>0</td><td>0</td>
 * <td>The iterator for the set returned by this method invokes one List Keys operation (retrieving a single key each time) for each element iterated over.</td>
 * </tr>
 * <tr>
 * <td>{@link AbstractMap#hashCode() hashCode()}</td><td><b>1 (<i>n</i>)</b></td><td><b><i>n</i></b></td><td>0</td><td>0</td>
 * <td>&nbsp;</td>
 * </tr>
 * <tr>
 * <td>{@link AbstractMap#isEmpty() isEmpty()}</td><td><b>1 (<i>n</i>)</b></td><td>0</td><td>0</td><td>0</td>
 * <td>&nbsp;</td>
 * </tr> 
 * <tr>
 * <td>{@link #put(String, Object)}</td><td>0</td><td><b>1</b></td><td><b>1</b></td><td>0</td>
 * <td>&nbsp;</td>
 * </tr>
 * <tr>
 * <td>{@link #putAll(Map) putAll(Map)}</td><td>0</td><td>0</td><td><b><i>k</i></b></td><td>0</td>
 * <td>Where <i>k</i> is the number of entries in map being added. Note when <i>k</i> is 1, using <code>map.putAll(Collections.singletonMap(key, value))</code>
 * may be preferable to <code>map.put(key, value)</code> if the return value is not of interest.</code></td>
 * </tr>
 * <tr>
 * <td>{@link #remove(Object)}</td><td>0</td><td><b>1</b></td><td>0</td><td><b>1</b></td>
 * <td>&nbsp;</td>
 * </tr>
 * <tr>
 * <td>{@link #size()}</td><td><b>1 (<i>n</i>)</b></td><td>0</td><td>0</td><td>0</td>
 * <td>&nbsp;</td>
 * </tr>
 * <tr>
 * <td>{@link #values() values()}</td><td>0</td><td>0</td><td>0</td><td>0</td>
 * <td>The iterator for the set returned by this method invokes one List Keys operation (retrieving a single key each time) and one Read Object operation for each element iterated over.</td>
 * </tr>
 * </table>
 * <h3>Concurrency</h3>
 * <p>
 * <b>This implementation is not synchronized.</b> Like {@link HashMap}, if multiple threads access this map concurrently,
 * and at least one of the threads modifies the map structurally, it must be synchronized externally.
 * If multiple instances - possibly in separate Java Virtual Machines - access the same S3 bucket,
 * then they need to take care to synchronize accesses that modify the map structurally. Note that this implementation,
 * unlike {@link HashMap}, is <i>not fail-fast</i>.
 * </p>
 */
public class S3Map<V> extends AbstractMap<String,V> implements Map<String,V> {
	
    private String bucket;
    private AWSAuthConnection connection;
    private Serializer serializer;
    
    private Set<String> keySet = new KeySet();
    private Set<Map.Entry<String,V>> entrySet = new EntrySet();
    private Collection<V> values = new Values();
    
    public S3Map(String bucket, String awsAccessKeyId, String awsSecretAccessKey) {
    	this(bucket, awsAccessKeyId, awsSecretAccessKey, DefaultSerializer.INSTANCE);
    }
    
    public S3Map(String bucket, String awsAccessKeyId, String awsSecretAccessKey, Map<? extends String, ? extends V> map) {
    	this(bucket, awsAccessKeyId, awsSecretAccessKey);
    	for (Map.Entry<? extends String, ? extends V> e : map.entrySet()) {
    		put(e.getKey(), e.getValue());
    	}
    }

    public S3Map(String bucket, String awsAccessKeyId, String awsSecretAccessKey, Serializer serializer) {
    	this.bucket = bucket;
    	this.connection = new AWSAuthConnection(awsAccessKeyId, awsSecretAccessKey);
    	this.serializer = serializer;
    	s3CreateBucket(bucket);
    }
    
    // Map interface
    
	@Override
	public void clear() {
		for (ListEntry listEntry : s3ListBucket()) {
			s3Delete(listEntry.key);
		}
	}

	@Override
	public boolean containsKey(Object key) {
		// TODO: Re-write to avoid retrieving value data (which may be large): use HEAD or use listBucket call with marker set
		//		 tolerant
		//		 tol\uFFFF
		//		 tom
		return get(key) != null;
	}
	
	@Override
	public boolean containsValue(Object value) {
		for (ListEntry listEntry : s3ListBucket()) {
			if (get(listEntry.key).equals(value)) {
				return true;
			}
		}		
		return false;
	}

	@Override
	public Set<Map.Entry<String,V>> entrySet() {
		return entrySet;
	}
	
	@Override
	public Set<String> keySet() {
        return keySet;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public V get(Object key) {
		S3Object s3Object = s3Get(key.toString());
		if (s3Object == null) {
			return null;
		}
		try {
			return (V) serializer.deserialize(s3Object.data);
		} catch (ClassNotFoundException e) {
			throw new S3Exception(e);
		} catch (IOException e) {
			throw new S3Exception(e);
		}
	}

	@Override
	public V put(String key, V value) {
		if (value == null) {
			throw new NullPointerException("Value must not be null.");
		}
		V old = get(key);
        try {
        	s3Put(key, new S3Object(serializer.serialize(value), null));
		} catch (IOException e) {
			throw new S3Exception(e);
		}
		return old;
	}
	
    @Override
	public void putAll(Map<? extends String, ? extends V> t) {
    	Iterator<? extends Entry<? extends String, ? extends V>> i = t.entrySet().iterator();
    	while (i.hasNext()) {
    	    Entry<? extends String, ? extends V> entry = i.next();
    		if (entry.getValue() == null) {
    			throw new NullPointerException("Value must not be null.");
    		}
            try {
            	s3Put(entry.getKey(), new S3Object(serializer.serialize(entry.getValue()), null));
    		} catch (IOException e) {
    			throw new S3Exception(e);
    		}
    	}
   }	

	@Override
	public V remove(Object key) {
		V old = get(key);
		s3Delete(key.toString());
		return old;
	}

	@Override
	public int size() {
		return s3ListBucket().size();
	}
	
	@Override
	public Collection<V> values() {
		return values;
	}
	
	// S3 methods
	
	void s3CreateBucket(String bucketName) {
		try {
			Response response = connection.createBucket(bucketName, null);
			checkResponse(response.connection);
		} catch (MalformedURLException e) {
			throw new S3Exception(e);
		} catch (IOException e) {
			throw new S3Exception(e);
		}
	}

	void s3Delete(String key) {
		checkKey(key);
		try {
			Response response = connection.delete(bucket, key, null);
			checkResponse(response.connection);
		} catch (MalformedURLException e) {
			throw new S3Exception(e);
		} catch (IOException e) {
			throw new S3Exception(e);
		}		
	}
	
	S3Object s3Get(String key) {
		checkKey(key);
		try {
			GetResponse response = connection.get(bucket, key, null);
			if (response.connection.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
				return null;
			}
			checkResponse(response.connection);
			return response.object;
		} catch (MalformedURLException e) {
			throw new S3Exception(e);
		} catch (IOException e) {
			throw new S3Exception(e);
		}		
	}
	
	List<ListEntry> s3ListBucket() {
		return s3ListBucket(null, null);
	}
	
	List<ListEntry> s3ListBucket(Integer maxKeys) {
		return s3ListBucket(null, maxKeys);
	}	
	
	@SuppressWarnings("unchecked")
	List<ListEntry> s3ListBucket(String marker, Integer maxKeys) {
		try {
			ListBucketResponse response = connection.listBucket(bucket, null, marker, maxKeys, null);
			checkResponse(response.connection);
			return response.entries;
		} catch (MalformedURLException e) {
			throw new S3Exception(e);
		} catch (IOException e) {
			throw new S3Exception(e);
		}
	}	
	
	void s3Put(String key, S3Object s3Object) {
		checkKey(key);
        try {
			Response response = connection.put(bucket, key, s3Object, null);
			checkResponse(response.connection);
		} catch (MalformedURLException e) {
			throw new S3Exception(e);
		} catch (IOException e) {
			throw new S3Exception(e);
		}		
	}
	
	// Utility methods
	
	private void checkKey(String key) {
		try {
			int length = key.getBytes("UTF-8").length;
			if (length < 1 || length > 1024) {
				throw new InvalidKeyException("Key length must be between 1 and 1024 bytes in UTF-8 encoding.");
			}
		} catch (UnsupportedEncodingException e) {
			// should never happen
		}
	}
	
	private void checkResponse(HttpURLConnection httpUrlConnection) throws IOException {
		//System.out.println(httpUrlConnection.getResponseCode() + " " + httpUrlConnection.getResponseMessage() + " " + httpUrlConnection.getURL());
		if (httpUrlConnection.getResponseCode() >= 400) {
			throw new S3Exception("Server responded with error code %s %s for request %s.",
					Integer.toString(httpUrlConnection.getResponseCode()),
					httpUrlConnection.getResponseMessage(),
					httpUrlConnection.getURL());
		}		
	}
	
	// Views
	
	// TODO: Override removeAll in view sets to do a set difference between map keys and arg keys
	// (this is the boolean to return), before calling s3Delete for each overlap.
	// So Set overlap = mapKeys.retainAll(argKeys);
	
    class KeySet extends AbstractSet<String> {
        @Override
		public Iterator<String> iterator() {
    		return new KeyIterator();        	
        }
        @Override
        public int size() {
            return S3Map.this.size();
        }
        @Override
        public boolean contains(Object o) {
            return S3Map.this.containsKey(o);
        }
        @Override
        public boolean remove(Object o) {
            return S3Map.this.remove(o) != null;
        }
        @Override
        public void clear() {
        	S3Map.this.clear();
        }
    }
    
    class Values extends AbstractCollection<V> {
        @Override
		public Iterator<V> iterator() {
        	return new ValueIterator();
        }
        @Override
		public int size() {
        	return S3Map.this.size();
        }
        @Override
		public boolean contains(Object o) {
            return S3Map.this.containsValue(o);
        }
        @Override
		public void clear() {
        	S3Map.this.clear();
        }
    }  

    class EntrySet extends AbstractSet<Map.Entry<String,V>> {
        @Override
		public Iterator<Map.Entry<String,V>> iterator() {
        	return new EntryIterator();
        }
        @Override
		public int size() {
            return S3Map.this.size();
        }        
        @SuppressWarnings("unchecked")
		@Override
		public boolean contains(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<String,V> e = (Map.Entry<String,V>) o;
            Entry<String,V> candidate = S3Map.this.getEntry(e.getKey());
            return candidate != null && candidate.equals(e);
        }
        @Override
		public boolean remove(Object o) {
            return S3Map.this.removeMapping(o) != null;
        }
        @Override
		public void clear() {
        	S3Map.this.clear();
        }
        
    }    
    
    class KeyIterator implements Iterator<String> {
    	
    	private String current;
    	private String next;
    	
    	public KeyIterator() {
    		next = getNextKey(next);
    	}
    	
    	private String getNextKey(String previous) {
    		Iterator<ListEntry> i = S3Map.this.s3ListBucket(previous, Integer.valueOf(1)).iterator();
    		if (i.hasNext()) {
    			return i.next().key;
    		}
    		return null;
    	}

		public boolean hasNext() {
			return next != null;
		}

		public String next() {
			if (next == null) {
				throw new NoSuchElementException();
			}
			String k = next;
			next = getNextKey(next);
			return current = k;
		}

		public void remove() {
            if (current == null) {
            	throw new IllegalStateException();
            }
            String k = current;
            current = null;
            S3Map.this.s3Delete(k);
		}
    	
    }

    class ValueIterator extends HashIterator<V> {
        public V next() {
            return nextEntry().getValue();
        }
    }

    class EntryIterator extends HashIterator<Map.Entry<String,V>> {
        public Map.Entry<String,V> next() {
            return nextEntry();
        }
    } 
    
    private abstract class HashIterator<E> implements Iterator<E> {
        private Entry<String,V> current;	// current entry    
        private Entry<String,V> next;	// next entry to return
    	
    	public HashIterator() {
    		next = getNextEntry(next);
    	}
    	
    	private Entry<String,V> getNextEntry(Entry<String,V> previous) {
    		String previousKey = (previous == null ? null : previous.getKey());
    		Iterator<ListEntry> i = S3Map.this.s3ListBucket(previousKey, Integer.valueOf(1)).iterator();
    		if (i.hasNext()) {
    			return S3Map.this.getEntry(i.next().key);
    		}
    		return null;
    	}

		Entry<String,V> nextEntry() {
			if (next == null) {
				throw new NoSuchElementException();
			}
			Map.Entry<String,V> e = next;
			next = getNextEntry(next);
			return current = e;
		}

		public boolean hasNext() {
			return next != null;
		}

		public void remove() {
            if (current == null) {
            	throw new IllegalStateException();
            }
            Map.Entry<String,V> e = current;
            current = null;
            S3Map.this.removeMapping(e);
		}
    	
    }   
    
    Entry<String, V> getEntry(String key) {
    	V v = S3Map.this.get(key);
    	return v == null ? null : new SimpleEntry<String, V>(key, v);
    }
    
    @SuppressWarnings("unchecked")
	Entry<String, V> removeMapping(Object o) {
        if (!(o instanceof Map.Entry)) {
            return null;
        }
        Map.Entry<String,V> e = (Map.Entry<String,V>) o;
        V v = S3Map.this.remove(e.getKey());
        return v == null ? null : new SimpleEntry<String, V>(e.getKey(), v);
    }
    
}
