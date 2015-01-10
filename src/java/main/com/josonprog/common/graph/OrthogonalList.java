/**
 * 
 */
package com.josonprog.common.graph;

import java.util.LinkedList;

/**
 * @author Joson
 *
 */
public class OrthogonalList<N extends Node> extends LinkedList<OrthNodeEntry<N>> {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7920577341036460228L;
	
	public OrthNodeEntry<N> getEntry(N node) {
		int i = this.indexOf(node);
		
		return i >= 0 ? this.get(i) : null;
	}
	
	/**
	 * Append a node if it's not existed.
	 * 
	 * @param node
	 * @return if node's added, a new entry which wraps this node will be returned, otherwise null returned.
	 */
	public OrthNodeEntry<N> addNode(N node) {
		if (this.contains(node)) {
			return null;
		} else {
			OrthNodeEntry<N> entry = new OrthNodeEntry<N>(node);
			return this.add(entry) ? entry : null;
		}
	}
	
	public boolean addArc(N nodeA, N nodeB) {
		if (this.hasArc(nodeA, nodeB)) return false;
		

		OrthNodeEntry<N> entryA, entryB;

		if (this.contains(nodeA)) {
			entryA = this.getEntry(nodeA);
		} else {
			entryA = this.addNode(nodeA);
		}

		if (this.contains(nodeB)) {
			entryB = this.getEntry(nodeB);
		} else {
			entryB = this.addNode(nodeB);
		}

		OrthArcEntry<N> arcEntry = new OrthArcEntry<N>(entryA, entryB);
		
		
		OrthArcEntry<N> tailArcEntry = entryA.getFirstOutLink();
		if (tailArcEntry == null) {
			entryA.setFirstOutLink(arcEntry);
		} else {
			do {
				tailArcEntry = tailArcEntry.getTailLink();
				
			} while (tailArcEntry.getTailLink() != null);
			
			tailArcEntry.setTailLink(arcEntry);
		}
		

		OrthArcEntry<N> headArcEntry = entryB.getFirstInLink();
		if (headArcEntry == null) {
			entryB.setFirstInLink(arcEntry);
		} else {
			do {
				headArcEntry = headArcEntry.getHeadLink();
				
			} while (headArcEntry.getHeadLink() != null);
			
			headArcEntry.setHeadLink(arcEntry);
		}
		
		return true;
	}
	
	public boolean hasArc(N nodeA, N nodeB) {
		boolean res = false;

		OrthNodeEntry<N> fromEntry = this.getEntry(nodeA);
		
		if (fromEntry == null || !this.contains(nodeB)) {
			return false;
		}
		
		OrthArcEntry<N> arcEntry = fromEntry.getFirstOutLink();
		while (arcEntry != null) {
			if (arcEntry.getHead().equals(nodeB)) {
				res = true;
				break;
			}
			
			arcEntry = arcEntry.getTailLink();
		}
		
		return res;
	}
	
	public boolean removeArc(N nodeA, N nodeB) {
		if (!this.contains(nodeA) || !this.contains(nodeB)) return false;

		OrthNodeEntry<N> entryA = this.getEntry(nodeA);

		OrthNodeEntry<N> entryB = this.getEntry(nodeB);
		
		boolean hadArc = false;
		
		OrthArcEntry<N> tailEntry = entryA.getFirstOutLink();
		
		if (tailEntry.getHead().equals(entryB)) {
			entryA.setFirstOutLink(null);
			hadArc = true;
			
		} else {
			boolean found = false;
			OrthArcEntry<N> prevEntry;
			do {
				prevEntry = tailEntry;
				tailEntry = tailEntry.getTailLink();
				if (tailEntry.getHead().equals(entryB)) {
					found = true;
					hadArc = true;
					break;
				}
			} while (tailEntry != null);
			
			if (found) {
				prevEntry.setTailLink(tailEntry.getTailLink());
			}
		}
		

		OrthArcEntry<N> headEntry = entryB.getFirstInLink();
		
		if (headEntry.getTail().equals(entryA)) {
			entryB.setFirstInLink(null);
			hadArc = true;
			
		} else {
			boolean found = false;
			OrthArcEntry<N> prevEntry;
			do {
				prevEntry = headEntry;
				headEntry = headEntry.getHeadLink();
				if (headEntry.getTail().equals(entryA)) {
					found = true;
					hadArc = true;
					break;
				}
			} while (headEntry != null);
			
			if (found) {
				prevEntry.setHeadLink(headEntry.getHeadLink());
			}
		}
		
		return hadArc;	
	}
}


