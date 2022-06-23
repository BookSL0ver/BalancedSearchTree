// Name: Jaclyn Windorff
// Project: p2
// Files: TestSearchTree, BalancedSearchTree, SearchTreeADT
// Instructor: Deb Deppeler (deppeler@cs.wisc.edu)
// Implemented Red-Black trees
// Bugs: delete and black property balancing don't work

//this class implements a balanced search tree
public class BalancedSearchTree<T extends Comparable<T>> implements SearchTreeADT<T> 
{

	// inner node class used to store key items and links to other nodes
	protected class Treenode<K extends Comparable<K>> 
	{
		public Treenode(K item) 
		{
			this(item,null,null);
		}
		public Treenode(K item, Treenode<K> left, Treenode<K> right) 
		{
			key = item;
			this.left = left;
			this.right = right;
			color = "red";
		}
		K key;
		Treenode<K> left;
		Treenode<K> right;
		String color;
	}

	protected Treenode<T> root;

	//returns the tree in ascending order, from lowest to greatest
	//@return String -> the items in the list in order from lowest to greatest
	public String inAscendingOrder() 
	{
		//TODO : must return comma separated list of keys in ascending order
		String ret = "";
		ret = ascendingHelper(ret, root);
		return ret;
	}
	
	//recursive method that helps the inAscendingOrder method
	//@param String ret -> the current string of letters and commas
	//@param Treenode<T> node -> the node to go off from
	//@return String -> the information in the tree in ascending order
	private String ascendingHelper(String ret, Treenode<T> node)
	{
		if(node == null)
			return ret;
		if(!(node.left == null))
			ret = ascendingHelper(ret, node.left);
		ret = ret + node.key + ",";
		if(!(node.right == null))
			ret = ascendingHelper(ret, node.right);
		return ret;
	}

	//tests if the tree is empty
	//@return boolean -> true if the list is empty, false otherwise
	public boolean isEmpty() 
	{
		//TODO return empty if there are no keys in structure
		if(root == null)
			return true;
		else
			return false;
	}

	//returns the height of the tree
	//@return int -> the height of the tree
	public int height()
	{
		//TODO return the height of this tree
		return branchHeight(root); 
	}
	
	//helps the height method by recursively finding the height of each branch
	//@param Treenode<T> node -> the root of the current branch
	//@return int -> the height of the branch
	private int branchHeight(Treenode<T> node)
	{
		if(node.right == null && node.left == null)
			return 1;
		else if(node.right == null)
			return branchHeight(node.left) + 1;
		else if(node.left == null)
			return branchHeight(node.right) + 1;
		else
		{
			if(branchHeight(node.left) > branchHeight(node.right))
				return branchHeight(node.left) + 1;
			else
				return branchHeight(node.right) + 1;
		}
	}

	//checks if the item given is in the tree
	//@param T item -> the item that you want to know if is in the tree
	//@return boolean -> true if the item is in the tree
	public boolean lookup(T item) 
	{
		//TODO must return true if item is in tree, otherwise false
		return lookupHelper(item, root);
	}
	
	//recursively helps the lookup method find the item
	//@param T item -> the item to find
	//@param Treenode<T> node -> the node to be checked against the item
	//@return boolean -> true if the item is in the list
	private boolean lookupHelper(T item, Treenode<T> node)
	{
		if(item.equals(node.key))
			return true;
		else
		{
			if(item.compareTo(node.key) < 0)
			{
				if(node.left == null)
					return false;
				else
					return lookupHelper(item, node.left);
			}
			else
			{
				if(node.right == null)
					return false;
				else
					return lookupHelper(item, node.right);
			}
		}
	}

	//inserts the given item into the list
	//@param T item -> the item to be inserted into the list
	public void insert(T item)
	{
		//TODO if item is null throw IllegalArgumentException, 
		// otherwise insert into balanced search tree
		if(item == null)
			throw new IllegalArgumentException();
		else if(isEmpty())
			root = new Treenode<T>(item);
		else
		{
			if(item.compareTo(root.key) < 0)
				insertHelper(item, root.left, root, "L");
			else
				insertHelper(item, root.right, root, "R");
		}
		balance(new Treenode<T>(item));
	}
	
	//helps the insert method put the item in the right place
	//@param T item -> the item to be inserted
	//@param Treenode<T> node -> the current place that the method is testing against
	//@param Treenode<T> par -> the parent of the node
	//@param String side -> left or right, the side of the parent node the current node is on
	private void insertHelper(T item, Treenode<T> node, Treenode<T> par, String side)
	{
		if(node == null)
		{
			if(side.equals("L"))
				par.left = new Treenode<T>(item);
			else
				par.right = new Treenode<T>(item);
		}
		else if(item.compareTo(node.key) < 0)
			insertHelper(item, node.left, node, "L");
		else
			insertHelper(item, node.right, node, "R");
	}

	//deletes the item given
	//@param T item -> the item to be deleted
	public void delete(T item) 											//Doesn't work
	{
		//TODO if item is null or not found in tree, return without error
		// else remove this item key from the tree and re-balance

		// NOTE: if you are unable to get delete implemented
		// it will be at most 5% of the score for this program assignment
		if(height() == 1 && root.key.equals(item))
			root = null;
		
		balance(new Treenode<T>(item));
	}


	// HINT: define this helper method that can find the smallest key 
	// in a sub-tree with "node" as its root
	// PRE-CONDITION: node is not null
	private T leftMost(Treenode<T> node) 
	{
		// TODO return the key value of the left most node in this subtree
		// or return node's key if node does not have a left child
		if(!(node.left == null))
			return leftMost(node.left);
		else
			return node.key;
	}
	
	//changes the root to black to make sure the root property is met
	private void rootProperty()
	{
		if(root.color.equals("red"))
		{
			root.color = "black";
		}
	}
	
	//makes sure the red property is met
	//@param Treenode<T> ggpar -> the great grandparent of the node
	//@param Treenode<T> gpar -> the grandparent of the node
	//@param Treenode<T> par -> the parent of the node
	//@param Treenode<T> node -> the node to see if the ones above it follow the red property
	private void redProperty(Treenode<T> ggpar, Treenode<T> gpar, Treenode<T> par, Treenode<T> node)
	{
		//compare node to parent, if both red, either resort or re-color
//		Treenode<T> sib;
//		if(par.left.equals(node))
//			sib = par.right;
//		else
//			sib = par.left;
		Treenode<T> parSib;
		if(gpar.left.equals(par))
			parSib = gpar.right;
		else
			parSib = gpar.left;
		if(node.color.equals("red") && node.color.equals(par.color))
		{
			if(gpar.left.equals(par))
			{
				if(gpar.right == null)
				{
					trinoderestructure(ggpar, gpar, par, node);
				}
				else
				{
					recolorForRedProp(gpar, par, node, parSib);
				}
			}
			else
			{
				if(gpar.left == null)
				{
					trinoderestructure(ggpar, gpar, par, node);
				}
				else
				{
					recolorForRedProp(gpar, par, node, parSib);
				}
			}
		}
	}
	
	//helps the redProperty method by restructuring the nodes if it is possible
	//@param Treenode<T> ggpar -> the great grandparent of the node
	//@param Treenode<T> gpar -> the grandparent of the node
	//@param Treenode<T> par -> the parent of the node
	//@param Treenode<T> node -> the child node to be restructured
	private void trinoderestructure(Treenode<T> ggpar, Treenode<T> gpar, Treenode<T> par, Treenode<T> node)		//need to deal with siblings????
	{
		Treenode<T> max = par;
		if(node.key.compareTo(max.key) < 1)
			max = node;
		if(gpar.key.compareTo(max.key) < 1)
			max = gpar;
		Treenode<T> min = par;
		if(node.key.compareTo(min.key) > 1)
			min = node;
		if(gpar.key.compareTo(min.key) > 1)
			min = gpar;
		Treenode<T> mid;
		if(!max.equals(node) && !min.equals(node))
			mid = node;
		else if(!max.equals(par) && !min.equals(par))
			mid = par;
		else
			mid = gpar;
		mid.color = "black";
		max.color = "red";
		min.color = "red";
		if(ggpar.left.equals(gpar))
		{
			ggpar.left = mid;
			mid.left = min;
			mid.right = max;
		}
		else
		{
			ggpar.right = mid;
			mid.left = min;
			mid.right = max;
		}
	}
	
	//recolors the nodes to make them fit the red property
	//@param Treenode<T> gpar -> the grandparent of the node
	//@param Treenode<T> par -> the parent of the node
	//@param Treenode<T> node -> the child node to reference when re-coloring
	//@param Treenode<T> parSib -> the sibling of the parent node, that also needs to be re-colored
	private void recolorForRedProp(Treenode<T> gpar, Treenode<T> par, Treenode<T> node, Treenode<T> parSib)
	{
		gpar.color = "red";
		par.color = "black";
		parSib.color = "black";
	}
	
	//fixes any violations of the black property
	//@param Treenode<T> node -> the node to start at
	private void blackProperty(Treenode<T> node)
	{
		//make sure every path has the same number of black nodes in it
		//then move one black node to the heavier side, shifting branches as necessary
	}
	
	//moves up the tree to make sure the tree follows all properties
	//@param Treenode<T> node -> the place to start
	private void cascadeUp(Treenode<T> node)
	{
		while(!(getParent(node) == null))
		{
			redProperty(getParent(getParent(getParent(node))), getParent(getParent(node)), getParent(node), node);
		}
	}
	
	//gets the parent of the given node
	//@param Treenode<T> node -> the node to get the parent for
	//@return Treenode<T> -> the parent of the given node
	private Treenode<T> getParent(Treenode<T> node)
	{
		Treenode<T> parent = root;
		if(node == null || parent == null || parent.left == null || parent.right == null)
			return null;
		while(!(parent == null) && (!(parent.left == null) || !(parent.right == null)) && !(parent.left.equals(node)) && !(parent.right.equals(node)))
		{
			if(parent.key.compareTo(node.key) < 1)
				parent = parent.left;
			else
				parent = parent.right;
		}
		if(parent == null || (parent.left == null) && (parent.right == null))
			return null;
		return parent;
	}
	
	//balances the tree after an insertion or deletion
	//@param Treenode<T> node -> where to start balancing
	private void balance(Treenode<T> node)
	{
		cascadeUp(node);
		rootProperty();
	}

}

