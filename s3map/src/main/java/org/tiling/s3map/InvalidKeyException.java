package org.tiling.s3map;

/**
 * Throw if a key does not conform to the specification in {@link S3Map}.
 */
public class InvalidKeyException extends RuntimeException {
	public InvalidKeyException(String message) {
		super(message);
	}
}
