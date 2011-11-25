package org.tiling.s3map;

import java.util.Map;
import java.util.Map.Entry;

// Copied from java.util.AbstractMap
class SimpleEntry<K, V> implements Entry<K, V> {
	K key;
	V value;

	public SimpleEntry(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public SimpleEntry(Entry<K, V> e) {
		this.key = e.getKey();
		this.value = e.getValue();
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}

	public V setValue(V value) {
		V oldValue = this.value;
		this.value = value;
		return oldValue;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Map.Entry))
			return false;
		Map.Entry e = (Map.Entry) o;
		return eq(key, e.getKey()) && eq(value, e.getValue());
	}

	@Override
	public int hashCode() {
		return ((key == null) ? 0 : key.hashCode())
				^ ((value == null) ? 0 : value.hashCode());
	}

	@Override
	public String toString() {
		return key + "=" + value;
	}

	private static boolean eq(Object o1, Object o2) {
		return (o1 == null ? o2 == null : o1.equals(o2));
	}
}