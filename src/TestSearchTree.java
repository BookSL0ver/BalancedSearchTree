import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/////////////////////////////////////////////////////////////////////////////
// Semester:         CS400 Spring 2018
// PROJECT:          cs400_p2_201801
// FILES:            TestSearchTree.java
//                   SearchTreeADT.java
//                   BalancedSearchTree.java
//
// USER:             deppeler
//
// Instructor:       Deb Deppeler (deppeler@cs.wisc.edu)
// Bugs:             delete and black property balancing don't work
//
// 2018 Feb 8, 2018 5:13:18 PM TestSearchTree.java 
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * @author Jaclyn Windorff 
 * jwindorff@wisc.edu
 */
//This class tests the BalancedSearchTree class using JUnit4
public class TestSearchTree 
{

	SearchTreeADT<String> tree = null;
	String expected = null;
	String actual = null;

	/**
	 * @throws java.lang.Exception
	 * sets up anything that needs to be set up before all of the tests
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
	}

	/**
	 * @throws java.lang.Exception
	 * finishes anything that needs to be closed after all the tests
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception 
	{
	}

	/**
	 * @throws java.lang.Exception
	 * sets up anything that needs to be set up before each test
	 */
	@Before
	public void setUp() throws Exception 
	{
		tree = new BalancedSearchTree<String>();
	}

	/**
	 * @throws java.lang.Exception
	 * resets anything that needs to be reset after every test
	 */
	@After
	public void tearDown() throws Exception 
	{
		tree = null;
	}

	//tests if the isEmpty method works on an empty tree
	@Test
	public void test01_isEmpty_on_empty_tree() 
	{
		expected = "true";
		actual = "" + tree.isEmpty();
		if (! expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
	}

	//tests if the ascendingOrder method returns the propper thing when the tree is empty
	@Test
	public void test02_ascending_order_on_empty_tree() 
	{
		expected = "";
		actual = tree.inAscendingOrder();
		if (! expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
	}

	@Test
	/** tests that the height of an empty tree is 0 */
	public void test03_height_on_empty_tree() 
	{
		expected = "0";
		actual = "" + tree.height();
		if (! expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
	}

	//tests the isEmpty method after adding one object 
	@Test
	public void test04_isEmpty_after_one_insert() 
	{
		tree.insert("1");
		expected = "false";
		actual = "" + tree.isEmpty();
		if (! expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
	}
	
	@Test
	/** tests that the ascending order after inserting A item is "A," */
	public void test05_ascending_order_after_one_insert() 
	{
		tree.insert("A");
		expected = "A,";
		actual = tree.inAscendingOrder();
		if (! expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
	}

	@Test
	/** tests that the height after inserting A is 1 */
	public void test06_height_after_one_insert() 
	{
		tree.insert("A");
		expected = "1";
		actual = "" + tree.height();
		if (! expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
	}

	@Test
	/** tests that the height after inserting A and deleting it is 0 */
	public void test07_height_after_one_insert_and_delete() 
	{
		tree.insert("A");
		tree.delete("A");
		expected = "0";
		actual = "" + tree.height();
		if (! expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
	}
	
	//tests that the ascendingOrder method works after multiple objects are put in the tree
	@Test
	public void test08_ascending_order_after_many_add()
	{
		tree.insert("A");
		tree.insert("S");
		tree.insert("D");
		tree.insert("F");
		tree.insert("Q");
		tree.insert("W");
		tree.insert("E");
		tree.insert("R");
		tree.insert("Z");
		tree.insert("X");
		tree.insert("C");
		tree.insert("V");
		tree.insert("T");
		tree.insert("Y");
		tree.insert("U");
		tree.insert("I");
		tree.insert("G");
		tree.insert("J");
		tree.insert("H");
		tree.insert("K");
		expected = "A,C,D,E,F,G,H,I,J,K,Q,R,S,T,U,V,W,X,Y,Z,";
		actual = tree.inAscendingOrder();
		if (! expected.equals(actual))
			fail("expected: " + expected + " actual: " + actual);
	}
	
	//tests that the height method works for a larger amount of objects
	//can also test whether the tree is balanced
	@Test
	public void test09_height_after_many_add()
	{
		tree.insert("A");
		tree.insert("S");
		tree.insert("D");
		tree.insert("F");
		tree.insert("Q");
		tree.insert("W");
		tree.insert("E");
		tree.insert("R");
		tree.insert("Z");
		tree.insert("X");
		tree.insert("C");
		tree.insert("V");
		tree.insert("T");
		tree.insert("Y");
		tree.insert("U");
		expected = "7";											//maybe
		actual = "" + tree.height();
		if (! expected.equals(actual))
			fail("expected: " + expected + " actual: " + actual);
	}
	
	//tests if the lookup method works when the item is in a larger tree
	@Test
	public void test10_lookup_item_in()
	{
		tree.insert("A");
		tree.insert("S");
		tree.insert("D");
		tree.insert("F");
		tree.insert("Q");
		tree.insert("W");
		tree.insert("E");
		tree.insert("R");
		tree.insert("Z");
		tree.insert("X");
		tree.insert("C");
		tree.insert("V");
		tree.insert("T");
		tree.insert("Y");
		tree.insert("U");
		tree.insert("I");
		tree.insert("G");
		tree.insert("J");
		tree.insert("H");
		tree.insert("K");
		expected = "true";
		actual = "" + tree.lookup("X");
		if (! expected.equals(actual))
			fail("expected: " + expected + " actual: " + actual);
	}
	
	//tests if the lookup method works when the object is not in the tree
	@Test
	public void test11_lookup_item_not_in()
	{
		tree.insert("A");
		tree.insert("H");
		tree.insert("Q");
		expected = "false";
		actual = "" + tree.lookup("B");
		if (! expected.equals(actual))
			fail("expected: " + expected + " actual: " + actual);
	}
	
	//tests if the list stays in order after deleting the first item in the tree (not the root)
	@Test
	public void test12_insert_many_delete_first()
	{
		tree.insert("A");
		tree.insert("S");
		tree.insert("D");
		tree.insert("F");
		tree.insert("Q");
		tree.insert("W");
		tree.insert("E");
		tree.insert("R");
		tree.insert("Z");
		tree.insert("X");
		tree.insert("C");
		tree.insert("V");
		tree.insert("T");
		tree.insert("Y");
		tree.insert("U");
		tree.insert("I");
		tree.insert("G");
		tree.insert("J");
		tree.insert("H");
		tree.insert("K");
		tree.delete("A");
		expected = "C,D,E,F,G,H,I,J,K,Q,R,S,T,U,V,W,X,Y,Z,";
		actual = tree.inAscendingOrder();
		if (! expected.equals(actual))
			fail("expected: " + expected + " actual: " + actual);
	}
	
	//tests if the tree remains in order after deleting an object from the middle of the tree
	@Test
	public void test12_insert_many_delete_mid()
	{
		tree.insert("A");
		tree.insert("S");
		tree.insert("D");
		tree.insert("F");
		tree.insert("Q");
		tree.insert("W");
		tree.insert("E");
		tree.insert("R");
		tree.insert("Z");
		tree.insert("X");
		tree.insert("C");
		tree.insert("V");
		tree.insert("T");
		tree.insert("Y");
		tree.insert("U");
		tree.insert("I");
		tree.insert("G");
		tree.insert("J");
		tree.insert("H");
		tree.insert("K");
		tree.delete("I");
		expected = "A,C,D,E,F,G,H,J,K,Q,R,S,T,U,V,W,X,Y,Z,";
		actual = tree.inAscendingOrder();
		if (! expected.equals(actual))
			fail("expected: " + expected + " actual: " + actual);
	}
	
	//tests if the list remains in order after many insertions and multiple deletions
	@Test
	public void test13_insert_many_delete_many()
	{
		tree.insert("A");
		tree.insert("S");
		tree.insert("D");
		tree.insert("F");
		tree.insert("Q");
		tree.insert("W");
		tree.insert("E");
		tree.insert("R");
		tree.insert("Z");
		tree.insert("X");
		tree.insert("C");
		tree.insert("V");
		tree.insert("T");
		tree.insert("Y");
		tree.insert("U");
		tree.insert("I");
		tree.insert("G");
		tree.insert("J");
		tree.insert("H");
		tree.insert("K");
		tree.delete("I");
		tree.delete("J");
		tree.delete("A");
		tree.delete("G");
		tree.delete("K");
		tree.delete("V");
		tree.delete("C");
		tree.delete("Z");
		expected = "D,E,F,H,Q,R,S,T,U,W,X,Y,";
		actual = tree.inAscendingOrder();
		if (! expected.equals(actual))
			fail("expected: " + expected + " actual: " + actual);
	}

}
