package org.tiling.s3map;

/**
 * Thrown if there is a problem communicating with Amazon S3.
 */
public class S3Exception extends RuntimeException {

	public S3Exception(String message, Object... args) {
		super(String.format(message, args));
	}

	public S3Exception(Throwable t) {
		super(t);
	}

}
