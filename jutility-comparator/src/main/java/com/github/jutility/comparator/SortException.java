package com.github.jutility.comparator;

/**
 * The sort exception class, that caused when execute sort method.
 */
public class SortException extends RuntimeException {

	/**
	 * Constructs a new runtime exception with <code>null</code> as its detail
	 * message.
	 */
	public SortException() {
		super();
	}

	/**
	 * Constructs a new runtime exception with the specified detail message.
	 * 
	 * @param message
	 *            the detail message. The detail message is saved for later
	 *            retrieval by the {@link #getMessage()} method.
	 */
	public SortException(String message) {
		super(message);
	}

	/**
	 * Constructs a new runtime exception with the specified cause and a detail
	 * message of <tt>(cause==null ? null : cause.toString())</tt> (which
	 * typically contains the class and detail message of <tt>cause</tt>). This
	 * constructor is useful for runtime exceptions that are little more than
	 * wrappers for other throwables.
	 * 
	 * @param cause
	 *            the cause (which is saved for later retrieval by the
	 *            {@link #getCause()} method). (A <tt>null</tt> value is
	 *            permitted, and indicates that the cause is nonexistent or
	 *            unknown.)
	 */
	public SortException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new runtime exception with the specified detail message and
	 * cause.
	 * 
	 * @param message
	 *            the detail message (which is saved for later retrieval by the
	 *            {@link #getMessage()} method).
	 * @param cause
	 *            the cause (which is saved for later retrieval by the
	 *            {@link #getCause()} method). (A <tt>null</tt> value is
	 *            permitted, and indicates that the cause is nonexistent or
	 *            unknown.)
	 */
	public SortException(String message, Throwable cause) {
		super(message, cause);
	}
}
