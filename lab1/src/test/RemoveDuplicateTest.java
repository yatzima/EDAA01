package test;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import set.ArraySet;
import set.RemoveDuplicates;

public class RemoveDuplicateTest {
	private RemoveDuplicates RD;
	@Before
	public void setUp() throws Exception {
		RD = new RemoveDuplicates();
	}
	@After
	public void tearDown() throws Exception {
		RD = null;
	}
	@Test
	public final void testNullPointer(){
		try{
			int[] i = RD.uniqueElements(null);
			fail("uniqueElements should catch NullPointerException");
		} catch(NullPointerException e){
			
		}
		
	}
	@Test
	public final void testEmptySet(){
		int[] ints = new int[10];
		int[] i = RD.uniqueElements(ints);
		assertEquals("uniqueElements should return length 1", 1, i.length);
	}
	@Test
	public final void testSingleElements(){
		int[] ints = new int[1];
		ints[0] = 1;
		int[] i = RD.uniqueElements(ints);
		assertEquals("uniqueElements should return one element", 1, i[0]);
	}
	@Test
	public final void testManyDuplicateElements(){
		int[] ints = new int[100];
		for(int i = 0; i < ints.length; i++){
			ints[i] = 1;
		}
		int[] j = RD.uniqueElements(ints);
		assertEquals("uniqueElements should return one element", 1, j[0]);
	}
	@Test
	public final void testManyDifferentElements(){
		int[] ints = new int[10];
		for(int i = 0; i < ints.length; i++){
			ints[i] = i + 1;
		}
		int[] j = RD.uniqueElements(ints);
		assertArrayEquals("uniqueElements should return a sorted list", ints ,j );
	}
	

	

}
