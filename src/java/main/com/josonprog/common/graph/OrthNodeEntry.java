/**
 * 
 */
package com.josonprog.common.graph;


/**
 * Orthogonal list entry for node.
 * 
 * @author Joson
 *
 */
public class OrthNodeEntry<E extends Node> {
	
	private E node = null;
	
	private OrthArcEntry<E> firstInLink = null;
	
	private OrthArcEntry<E> firstOutLink = null;
	
	public OrthNodeEntry() { }
	
	public OrthNodeEntry(E node) {
		this.setNode(node);
	}
	
	public E getNode() {
		return node;
	}
	
	public void setNode(E node) {
		this.node = node;
	}

	protected OrthArcEntry<E> getFirstInLink() {
		return firstInLink;
	}

	protected void setFirstInLink(OrthArcEntry<E> firstInLink) {
		this.firstInLink = firstInLink;
	}

	protected OrthArcEntry<E> getFirstOutLink() {
		return firstOutLink;
	}

	protected void setFirstOutLink(OrthArcEntry<E> firstOutLink) {
		this.firstOutLink = firstOutLink;
	}

	public boolean isNode(E node) {
		if (this.node == null) 
			return node == null;
		else
			return this.node.equals(node);
		
	}
	
	/**
	 * 
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;

		E objNode = null;
		try {
			if (obj instanceof OrthNodeEntry) {
				objNode = ((OrthNodeEntry<E>) obj).getNode();
				
			} else if (obj instanceof Node){
				objNode = (E) obj;
				
			} else {
				return false;
			}
		} catch (ClassCastException ex) {
			return false;
		}
		

		if (this.node == null) {
			return objNode == null;
		} else {
			return this.node.equals(objNode);
		}
		
	}

	@Override
	public int hashCode() {
		if (this.node == null) {
			return 0;
		}
		return this.node.hashCode();
	}
	
	
}

