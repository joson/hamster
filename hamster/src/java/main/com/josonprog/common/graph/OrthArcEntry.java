/**
 * 
 */
package com.josonprog.common.graph;

/**
 * @author Joson
 *
 */

public class OrthArcEntry<N extends Node> {

	private OrthNodeEntry<N> tail = null;
	
	private OrthNodeEntry<N> head = null;
	
	private OrthArcEntry<N> tailLink = null;
	
	private OrthArcEntry<N> headLink = null;
	
	public OrthArcEntry() {}
	
	public OrthArcEntry(OrthNodeEntry<N> tail, OrthNodeEntry<N> head) {
		this.setTail(tail);
		this.setHead(head);
	}

	public OrthNodeEntry<N> getTail() {
		return tail;
	}

	public void setTail(OrthNodeEntry<N> tail) {
		this.tail = tail;
	}

	public OrthNodeEntry<N> getHead() {
		return head;
	}

	public void setHead(OrthNodeEntry<N> head) {
		this.head = head;
	}

	public OrthArcEntry<N> getTailLink() {
		return tailLink;
	}

	public void setTailLink(OrthArcEntry<N> tailLink) {
		this.tailLink = tailLink;
	}

	public OrthArcEntry<N> getHeadLink() {
		return headLink;
	}

	public void setHeadLink(OrthArcEntry<N> headLink) {
		this.headLink = headLink;
	}
}
