package com.sortcompare;

/*
Ivan Craddock
CSCD 300 Armstrong
Assignment 3

*Doubly-Linked LinkedList class with a dummy head Node. Node class is nested within.
Nodes will contain comparable elements, and will be linked their previous and next nodes
Contains methods for bubble-sort, selection-sort, insertion-sort, and array insertion-
sort for Nodes contained within LinkedList.

Export to CSV file implemented for extra credit.

*/

public class LinkedList {
	int dataCompare = 0;
	int loopCompare = 0;
	int dataAssign = 0;
	int loopAssign = 0;
	int other = 0;

	/**
	 * Nested node class
	 */
	protected static class Node {
		Comparable data;
		Node next, prev;

		/**
		 * EVC for Node.
		 * 
		 * @param Comparable
		 * @param Node
		 * @param Node
		 */
		public Node(Comparable data, Node next, Node prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}

		/**
		 * DVC for Node.
		 * 
		 * @param Comparable
		 */
		public Node(Comparable data) {
			this(data, null, null);
		}

		/**
		 * DVC for dummy head Node.
		 */
		public Node() {
			this(null, null, null);
		}
	}

	private Node head;
	private int size;

	/**
	 * DVC for LinkedList with dummy head node.
	 */
	public LinkedList() {
		this.head = new Node();
		this.size = 0;
	}

	/**
	 * Method for accessing head for exterior programs.
	 * 
	 * @return Node
	 */
	public Node returnHead() {
		return this.head;
	}

	/**
	 * Returns the total number of sorting opperations done by this LinkedList
	 * 
	 * @return int
	 */
	public int total() {
		int prime = (this.dataCompare + this.loopCompare + this.dataAssign + this.loopAssign + other);
		return prime;
	}

	/**
	 * Returns the size of the array for outside programs.
	 * 
	 * @return int
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Method for sorting LinkedList contents using a selection-sort.
	 */
	public void selectionSort() {
		if (this.size < 2) {
			System.out.println("List is too small");
			return;
		}
		Node first = this.head.next;
		// ^^^^^^^^^^^^
		loopAssign++;
		// ^^^^^^^^^^^^
		while (first.next != this.head) {
			// **************
			loopCompare++;
			// **************
			Node max = first;
			Node mover = first.next;
			boolean swap = false;
			// ^^^^^^^^^^^^
			loopAssign += 2;
			other++;
			// ^^^^^^^^^^^^
			while (mover != this.head) {
				// ***********
				loopCompare++;
				// ***********
				// %%%%%%%%%%%%%%%
				dataCompare++;
				// %%%%%%%%%%%%%%%
				if (mover.data.compareTo(max.data) < 0) {
					// ^^^^^^^^^^^
					loopAssign++;

					other++;
					// ^^^^^^^^^^^
					max = mover;
					swap = true;
				}
				// ^^^
				loopAssign++;
				// ^^^
				mover = mover.next;
			}

			other++;
			//
			if (swap == true) {
				Node temp = first.prev;
				nodeSwap(first, max);
				first = temp.next;
				//////////////////
				dataAssign += 2;
				//////////////////

			}
			first = first.next;
			// ^^^^^
			loopAssign++;
			// ^^^^^
		}
	}

	/**
	 * Method for sorting LinkedList contents using an array-insertion-sort.
	 */

	public void arraySort() {
		if (this.size < 2) {
			System.out.println("List is too small");
			return;
		}
		Node lead = this.head.next.next;
		// ^^^^^
		loopAssign++;
		// ^^^^^
		while (lead != this.head.next) {
			// **************
			loopCompare++;
			// **************
			Node mover = lead.prev;
			Boolean swap = true;

			// ^^^^^
			loopAssign++;
			other++;
			// ^^^^^
			while (mover.prev != this.head && swap != false) {
				// **************
				loopCompare += 2;
				// **************
				// %%%%%%%%%%%%%%%
				dataCompare++;
				// %%%%%%%%%%%%%%%
				swap = false;
				// ^^^^^
				other++;
				// ^^^^^
				if (mover.data.compareTo(mover.prev.data) < 0) {
					// ++++++++++++
					dataAssign += 2;
					// ++++++++++++
					Node temp = mover.prev;
					nodeSwap(mover, mover.prev);
					mover = temp;
					swap = true;
					//
					other++;
					//
				}
				// ^^^^^
				loopAssign++;
				// ^^^^^
				mover = mover.prev;
			}
			// ^^^^
			loopAssign++;
			// ^^^^^
			lead = lead.next;
		}
		//
		// Node lead = this.head.next;
		// while(lead != this.head){
		// lead = lead.next;
		// Node mover = lead.prev.prev;
		// boolean swap = false;
		// while(mover.next != lead && swap == false){
		// if(lead.prev.data.compareTo(mover.next.data) < 0){
		// insertNode(mover, lead.prev);
		// swap = true;
		// }
		// mover = mover.next;
		//
		// }
		//
		// }
	}

	/**
	 * Method for sorting LinkedList contents using a insertion-sort.
	 */
	public void insertionSort() {
		if (this.size < 2) {
			System.out.println("List is too small");
			return;
		}
		Node lead = this.head.next;
		// ^^^^^
		loopAssign++;
		// ^^^^^
		while (lead != this.head) {
			// **************
			loopCompare++;
			// **************
			lead = lead.next;
			Node mover = this.head;
			boolean swap = false;
			// ^^^^^
			loopAssign += 2;
			other++;
			// ^^^^^
			while (mover.next != lead && swap == false) {
				// **************
				loopCompare += 2;
				// **************
				// %%%%%%%%%%%%%%%
				dataCompare++;
				// %%%%%%%%%%%%%%%

				if (lead.prev.data.compareTo(mover.next.data) < 0) {
					insertNode(mover, lead.prev);
					swap = true;
					//
					other++;
					//
				}
				mover = mover.next;

			}
		}
	}

	/**
	 * Method for sorting LinkedList contents using a bubble-sort.
	 * 
	 * @param Node
	 * @param Node//
	 */
	public void insertNode(Node first, Node ins) {
		ins.prev.next = ins.next;
		ins.next.prev = ins.prev;
		ins.next = first.next;
		first.next.prev = ins;
		first.next = ins;
		ins.prev = first;
		// ++++++++++++++
		dataAssign += 6;
		// ++++++++++++++
	}

	/**
	 * Method swapping the positions of two nodes within LinkedLists.
	 * 
	 * @param Node
	 * @param Node
	 */
	public void nodeSwap(Node first, Node ins) {
		//
		other++;
		//
		Node temp1, temp2;
		if (first.next == ins) {
			temp1 = first.prev;
			temp2 = ins.next;
			first.next = temp2;
			temp2.prev = first;
			ins.next = first;
			first.prev = ins;
			temp1.next = ins;
			ins.prev = temp1;
			// ++++++++++++++
			dataAssign += 8;
			// ++++++++++++++
		} else if (ins.next == first) {
			temp1 = ins.prev;
			temp2 = first.next;
			ins.next = temp2;
			temp2.prev = ins;
			first.next = ins;
			ins.prev = first;
			temp1.next = first;
			first.prev = temp1;
			// ++++++++++++++
			dataAssign += 8;

			// ++++++++++++++
		} else {
			temp1 = first.prev;
			temp2 = first.next;
			first.prev = ins.prev;
			first.next = ins.next;
			ins.prev.next = first;
			ins.next.prev = first;
			ins.prev = temp1;
			ins.next = temp2;
			temp1.next = ins;
			temp2.prev = ins;
			// ++++++++++++++
			dataAssign += 8;
			// ++++++++++++++
		}
	}

	/**
	 * Method for sorting LinkedList contents using a bubble-sort.
	 */
	public void bubbleSort() {
		if (this.size < 2) {
			System.out.println("List is too small");
			return;
		} else {
			int count = 0;
			boolean sorted = false;
			// ^^^^^
			loopAssign += 2;
			// ^^^^^
			while (sorted == false && count != this.size) {
				// **************
				loopCompare += 2;
				// **************
				sorted = true;
				Node mover = this.head.next;
				while (mover.next != this.head && mover != this.head) {
					// **************
					loopCompare += 2;
					// **************

					// %%%%%%%%%%%%%%%
					dataCompare++;
					// %%%%%%%%%%%%%%%
					if (mover.data.compareTo(mover.next.data) > 0) {
						// Node temp = mover.prev;
						nodeSwap(mover, mover.next);
						// temp.next = mover.prev;

						sorted = false;
						//
						other++;
						//
					}
					// ^^^^
					loopAssign++;
					// ^^^^
					mover = mover.next;
				}
				// ^^^^
				loopAssign++;
				// ^^^^
				count++;

			}
		}
	}

	/**
	 * Method for adding Nodes to end of LinkedList
	 * 
	 * @param Comparable
	 */
	public void add(Comparable e)// adds element to the end
	{
		Node prime = new Node(e, this.head, null);
		if (this.size < 1) {
			this.head.next = prime;
			this.head.prev = prime;
			prime.prev = this.head;
			this.size++;
		} else {
			Node mover = this.head.next;
			while (mover.next != this.head)
				mover = mover.next;
			mover.next = prime;
			prime.prev = mover;
			this.head.prev = prime;
			this.size++;
		}
	}

	/**
	 * Returns String of LinkedList contents
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		String p = "";
		if (this.size < 1)
			return ("" + this + " is empty");
		else {
			Node mover = this.head.next;
			int count = 1;
			p = ("1.) " + mover.data + "\n");
			while (mover.next != this.head) {
				count++;
				mover = mover.next;
				p = (p + count + ".) " + mover.data + "\n");
			}
		}
		return p;
	}
}// end
