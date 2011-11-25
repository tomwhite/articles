package org.tiling.s3map;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * A {@link Serializer} that uses standard Java serialization.
 */
class DefaultSerializer implements Serializer {
	
	public static final DefaultSerializer INSTANCE = new DefaultSerializer();

	public byte[] serialize(Object obj) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(out);
		oos.writeObject(obj);
		oos.close();
		return out.toByteArray();
	}
	
	public Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		ObjectInputStream ois = new ObjectInputStream(in);
		return ois.readObject();
	}

}
