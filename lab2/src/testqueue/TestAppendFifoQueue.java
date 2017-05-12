package testqueue;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import queue.FifoQueue;

public class TestAppendFifoQueue {

	private FifoQueue<Integer> empty1;
	private FifoQueue<Integer> empty2;

	@Before
	public void setUp() throws Exception {
		empty1 = new FifoQueue<Integer>();
		empty2 = new FifoQueue<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		empty1 = null;
		empty2 = null;
	}

	@Test
	public void testTwoEmptyQueues() {
		empty1.append(empty2);
		assertEquals("Empty que added to empty queue", 0, empty1.size());
		assertEquals("The added queue is empty", 0, empty2.size());
	}

	@Test
	public void testEmptyToQueue() {
		empty1.add(1);
		empty1.add(2);
		empty1.append(empty2);
		assertEquals("Empty queue added to queue", 2, empty1.size());
		assertEquals("The added queue is empty", 0, empty2.size());
	}

	@Test
	public void testQueuetoEmpty() {
		empty2.add(1);
		empty2.add(2);
		empty1.append(empty2);
		assertEquals("Queue added to empty queue", 2, empty1.size());
		assertEquals("The added queue is empty", 0, empty2.size());
	}

	@Test
	public void testTwoQueues() {
		empty1.add(1);
		empty1.add(2);
		empty2.add(3);
		empty2.add(4);
		empty1.append(empty2);
		assertEquals("Queue added to queue", 4, empty1.size());
		assertEquals("The added queue is empty", 0, empty2.size());
	}

}
