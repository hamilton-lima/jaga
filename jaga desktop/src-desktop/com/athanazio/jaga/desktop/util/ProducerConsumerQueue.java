package com.athanazio.jaga.desktop.util;

/**
 * Handles a pair of producer/consumer in a double queue
 * @author hlima
 *
 */
public class ProducerConsumerQueue {

	private int size = 10;

	private int read = 0;

	private int write = 0;

	private Object[] buffer;

	/**
	 * build the queue defining the size
	 * @param size
	 */
	public ProducerConsumerQueue(int size) {
		this.size = size;
		this.buffer = new Object[size];
		this.read = 0;
		this.write = 0;
	}	
	
	/**
	 * add data to the queue
	 */
	public boolean push(Object data) {
		int next = (write + 1) % size;
		if (next != read) {
			buffer[write] = data;
			write = next;
			return true;
		}
		return false;
	}

	/**
	 * read data from the current position of the read
	 */
	public Object pop() {
		if (read == write)
			return null;
		int next = (read + 1) % size;
		Object result = buffer[read];
		read = next;
		return result;
	}
	
}
