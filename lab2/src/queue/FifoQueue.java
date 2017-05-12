package queue;

import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E>implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {

	}

	/**
	 * Returns an iterator over the elements in this queue
	 * 
	 * @return an iterator over the elements in this queue
	 */
	public Iterator<E> iterator() {
		return new QueueIterator();
	}

	/**
	 * Returns the number of elements in this queue
	 * 
	 * @return the number of elements in this queue
	 */
	public int size() {
		return size;
	}

	/**
	 * Inserts the specified element into this queue, if possible post: The
	 * specified element is added to the rear of this queue
	 * 
	 * @param x
	 *            the element to insert
	 * @return true if it was possible to add the element to this queue, else
	 *         false
	 */
	public boolean offer(E x) {
		size++;
		if (size > 1) {
			QueueNode<E> temp = last;
			last = new QueueNode<E>(x);
			last.next = temp.next;
			temp.next = last;

		} else {
			last = new QueueNode<E>(x);
			last.next = last;
		}
		return true;
	}

	/**
	 * Retrieves and removes the head of this queue, or null if this queue is
	 * empty. post: the head of the queue is removed if it was not empty
	 * 
	 * @return the head of this queue, or null if the queue is empty
	 */
	public E poll() {
		if (!this.isEmpty()) {
			QueueNode<E> temp = last.next;
			last.next = last.next.next;
			size--;
			return temp.element;
		}

		return null;
	}

	/**
	 * Retrieves, but does not remove, the head of this queue, returning null if
	 * this queue is empty
	 * 
	 * @return the head element of this queue, or null if this queue is empty
	 */
	public E peek() {
		if (!super.isEmpty()) {
			return last.next.element;
		}
		return null;

	}

	/**
	 * Appends the specified queue to this queue post: all elements from the
	 * specified queue are appended to this queue. The specified queue (q) is
	 * empty
	 * 
	 * @param q
	 *            the queue to append
	 */
	public void append(FifoQueue<E> q) {
		 
		if (!this.isEmpty() && !q.isEmpty()) {
			QueueNode<E> temp = last.next;
			last.next = q.last.next;
			q.last.next = temp;
		} else if (this.isEmpty() && !q.isEmpty()) {
			last = q.last;
			last.next = q.last.next;
		}
		this.size += q.size();
		q.size = 0;
		q.last = null;
		

		// if(!this.isEmpty() || !q.isEmpty()){
		// Iterator<E> tempq = q.iterator();
		// while(tempq.hasNext()){
		// this.offer(tempq.next());
		// q.poll();
		// }
		// }
	}

	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}

	}

	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;
		private int counter;

		private QueueIterator() {
			pos = size() > 0 ? last.next : null;
			counter = size();
		}

		public boolean hasNext() {
			return counter > 0;
		}

		public E next() {
			if (counter > 0) {
				E temp = pos.element;
				pos = pos.next;
				counter--;
				return temp;
			}
			throw new NoSuchElementException();

		}

		public void remove() {
			if (size > 1) {
				poll();
			} else {
				throw new UnsupportedOperationException();
			}
		}
	}
}
