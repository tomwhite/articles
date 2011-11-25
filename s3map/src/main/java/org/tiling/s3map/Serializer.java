package org.tiling.s3map;

import java.io.IOException;

/**
 * Defines how objects are serialized to byte streams, and vice versa.
 */
public interface Serializer {
	public byte[] serialize(Object obj) throws IOException;
	public Object deserialize(byte[] data) throws IOException, ClassNotFoundException;
}
