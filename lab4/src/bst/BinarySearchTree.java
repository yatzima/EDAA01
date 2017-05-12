package bst;

import java.util.Random;

public class BinarySearchTree<E extends Comparable<? super E>> {
	BinaryNode<E> root;
	int size;

	/**
	 * Constructs an empty binary searchtree.
	 */
	public BinarySearchTree() {

	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * 
	 * @param x
	 *            element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		if (root == null) {
			root = new BinaryNode<E>(x);
			size = 1;
			return true;
		} else {
			return traverse(root, x);
		}
	}

	private boolean traverse(BinaryNode<E> n, E x) {

		if (n.element.equals(x)) {
			return false;

		} else {

			if (n.element.compareTo(x) > 0 && n.left == null) {
				n.left = new BinaryNode<E>(x);
				size++;
				return true;
			} else if (n.element.compareTo(x) < 0 && n.right == null) {
				n.right = new BinaryNode<E>(x);
				size++;
				return true;
			}

			if (n.element.compareTo(x) > 0) {
				traverse(n.left, x);
			} else {
				traverse(n.right, x);
			}
		}
		return false;

	}

	/**
	 * Computes the height of tree.
	 * 
	 * @return the height of the tree
	 */
	public int height() {
		return count(root, 0);
	}

	private int count(BinaryNode<E> n, int level) {
		if (n != null) {
			int left = count(n.left, level);
			int right = count(n.right, level);
			level = Math.max(left, right) + 1;
		}
		return level;
	}

	/**
	 * Returns the number of elements in this tree.
	 * 
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}

	/**
	 * Print tree contents in inorder.
	 */
	public void printTree(BinaryNode<E> n) {
		StringBuilder sb = new StringBuilder();
		System.out.println(print(n, sb));
	}

	private StringBuilder print(BinaryNode<E> n, StringBuilder sb) {
		if (n.left != null) {
			print(n.left, sb);
		}
		sb.append(n.element + ", ");

		if (n.right != null) {
			print(n.right, sb);
		}
		return sb;

	}

	/**
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {

		E[] a = (E[]) new Comparable[size];
		int index = toArray(root, a, 0);
		root = buildTree(a, 0, index - 1);

	}

	/*
	 * Adds all elements from the tree rooted at n in inorder to the array a
	 * starting at a[index]. Returns the index of the last inserted element + 1
	 * (the first empty position in a).
	 */
	private int toArray(BinaryNode<E> n, E[] a, int index) {
		if (n.left != null) {
			index = toArray(n.left, a, index);
		}
		a[index] = n.element;
		index++;

		if (n.right != null) {
			index = toArray(n.right, a, index);
		}
		return index;
	}

	/*
	 * Builds a complete tree from the elements a[first]..a[last]. Elements in
	 * the array a are assumed to be in ascending order. Returns the root of
	 * tree.
	 */
	private BinaryNode<E> buildTree(E[] a, int first, int last) {
		int mid = ((first + last) / 2) > 0 ? (first + last) / 2 : 0 ;
		int midl = ((first + last) % 2) == 0 ? mid : mid - 1;
		int midr = ((first + last) % 2) == 0 ? mid : mid + 1;

		BinaryNode<E> root = new BinaryNode<E>(a[mid]);

		if (first < last) {
			root.left = buildTree(a, first, midl - 1);
			root.right = buildTree(a, midr + 1, last);
		}
		return root;
	}

	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}
	}

	public static void main(String[] args) {
		Random rand = new Random();
		BinarySearchTree bst = new BinarySearchTree<Integer>();
		BSTVisualizer bstv1 = new BSTVisualizer("Träd", 600, 600);
		BSTVisualizer bstv2 = new BSTVisualizer("Sorterad träd", 600, 600);
		for (int i = 0; i < 15; i++) {
			bst.add(rand.nextInt(100));
		}
		bstv1.drawTree(bst);
		bst.printTree(bst.root);
		System.out.println("\n" + "Height: " + bst.height());
		System.out.println("Size: " + bst.size() + "\n");
		Integer[] lol1 = new Integer[bst.size];

		// bst.toArray(bst.root, lol1, 0);
		// for (int i = 0; i < lol1.length; i++) {
		// System.out.println(lol1[i]);
		// }

		bst.rebuild();
		bstv2.drawTree(bst);
		System.out.println("\n" + "Height: " + bst.height());
		System.out.println("Size: " + bst.size() + "\n");
		Integer[] lol2 = new Integer[bst.size];

		// bst.toArray(bst.root, lol2, 0);
		// for (int i = 0; i < lol2.length; i++) {
		// System.out.println(lol2[i]);
		// }

	}

}
