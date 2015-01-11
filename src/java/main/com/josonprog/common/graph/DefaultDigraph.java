/**
 * 
 */
package com.josonprog.common.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Directed-graph with orthogonal list.
 * 
 * @author Joson
 *
 */
public class DefaultDigraph<N extends Node> 
		implements Digraph<N> {
	private Set<OrthNodeEntry<N>> nodeEntrySet = new HashSet<OrthNodeEntry<N>>();
	
	/**
	 * 
	 * 
	 * @param node
	 * @return
	 */
	protected OrthNodeEntry<N> createEntry(N node) {
		return new OrthNodeEntry<N>(node);
	}
	
	/**
	 * Get the entry which wraps the specified node from this graph.
	 * 
	 * @param node
	 * @return OrthNodeEntry<N> if the specified node in this graph, otherwise null returned.
	 */
	public OrthNodeEntry<N> getEntry(N node) {
		if (this.nodeEntrySet.contains(node)) {
			return this.createEntry(node);
		} else {
			return null;
		}
	}
	
	/**
	 * Append a node if it's not in this graph.
	 * 
	 * @param node
	 * @return true if the graph did not already contain the specified node.
	 */
	@Override
	public boolean addNode(N node) {
		return this.nodeEntrySet.add(this.createEntry(node));
	}
	
	@Override
	public boolean containsNode(N node) {
		return this.nodeEntrySet.contains(node);
	}
	
	
	/* (non-Javadoc)
	 * @see com.josonprog.common.graph.Graph#adjacent(com.josonprog.common.graph.Node, com.josonprog.common.graph.Node)
	 */
	@Override
	public boolean hasArc(N nodeA, N nodeB) {
		if (!this.containsNode(nodeA) || !this.containsNode(nodeB))
			return false;

		boolean res = false;
		
		OrthNodeEntry<N> fromEntry = this.getEntry(nodeA);
		
		OrthArcEntry<N> arcEntry = fromEntry.getFirstOutLink();
		while (arcEntry != null) {
			if (arcEntry.getHead().isNode(nodeB)) {
				res = true;
				break;
			}
			
			arcEntry = arcEntry.getTailLink();
		}
		
		return res;
	}

	/* (non-Javadoc)
	 * @see com.josonprog.common.graph.Graph#neighbors(com.josonprog.common.graph.Node)
	 */
	@Override
	public List<N> neighbors(N nodeA) {
		List<N> neighbors = new ArrayList<N>();
		
		if (!this.containsNode(nodeA)) return neighbors;
		
		OrthNodeEntry<N> entryA = this.getEntry(nodeA);
		
		OrthArcEntry<N> arcEntry = entryA.getFirstOutLink();
		while (arcEntry != null) {
			neighbors.add(arcEntry.getTail().getNode());
			arcEntry = arcEntry.getTailLink();
		}
		
		return neighbors;
	}

	/**
	 * Add an arc from nodeA to nodeB if there's not already had such an arc.
	 * If nodeA or nodeB hasn't been in list, it'll be appended first.
	 * 
	 * @param nodeA
	 * @param nodeB
	 * @return true if there's not already had such an arc and then a new arc added, otherwise false returned.
	 */
	@Override
	public boolean addArc(N nodeA, N nodeB) {
		if (this.hasArc(nodeA, nodeB)) return false;
		

		if (!this.containsNode(nodeA)) {
			this.addNode(nodeA);
		}

		if (!this.containsNode(nodeB)) {
			this.addNode(nodeB);
		}

		OrthNodeEntry<N> entryA = this.getEntry(nodeA),
						entryB = this.getEntry(nodeB);

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

	/* (non-Javadoc)
	 * @see com.josonprog.common.graph.Graph#delete(com.josonprog.common.graph.Node, com.josonprog.common.graph.Node)
	 */
	@Override
	public int removeArc(N nodeA, N nodeB) {
		if (!this.containsNode(nodeA) || !this.containsNode(nodeB)) 
			return 0;

		OrthNodeEntry<N> entryA = this.getEntry(nodeA);

		OrthNodeEntry<N> entryB = this.getEntry(nodeB);
		
		boolean hadArc = false;
		
		OrthArcEntry<N> tailEntry = entryA.getFirstOutLink();
		
		if (tailEntry.getHead().isNode(nodeB)) {
			entryA.setFirstOutLink(null);
			hadArc = true;
			
		} else {
			boolean found = false;
			OrthArcEntry<N> prevEntry;
			do {
				prevEntry = tailEntry;
				tailEntry = tailEntry.getTailLink();
				if (tailEntry.getHead().isNode(nodeB)) {
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
		
		if (headEntry.getTail().isNode(nodeA)) {
			entryB.setFirstInLink(null);
			hadArc = true;
			
		} else {
			boolean found = false;
			OrthArcEntry<N> prevEntry;
			do {
				prevEntry = headEntry;
				headEntry = headEntry.getHeadLink();
				if (headEntry.getTail().isNode(nodeA)) {
					found = true;
					hadArc = true;
					break;
				}
			} while (headEntry != null);
			
			if (found) {
				prevEntry.setHeadLink(headEntry.getHeadLink());
			}
		}
		
		return hadArc ? 1 : 0;	
	}
}


