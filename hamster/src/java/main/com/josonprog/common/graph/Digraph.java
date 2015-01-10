/**
 * 
 */
package com.josonprog.common.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Directed-graph with incidence matrix.
 * 
 * @author Joson
 *
 */
public class Digraph<N extends Node> 
		implements Graph<N> {
	
	private OrthogonalList<N> orthList = new OrthogonalList<N>();
	
	
	
	/* (non-Javadoc)
	 * @see com.josonprog.common.graph.Graph#adjacent(com.josonprog.common.graph.Node, com.josonprog.common.graph.Node)
	 */
	@Override
	public boolean adjacent(N nodeA, N nodeB) {
		return this.orthList.hasArc(nodeA, nodeB);
	}

	/* (non-Javadoc)
	 * @see com.josonprog.common.graph.Graph#neighbors(com.josonprog.common.graph.Node)
	 */
	@Override
	public List<N> neighbors(N nodeA) {
		List<N> neighbors = new ArrayList<N>();

		OrthNodeEntry<N> entry = this.orthList.getEntry(nodeA);
		
		OrthArcEntry<N> outArcEntry = entry.getFirstOutLink();
		while (outArcEntry != null) {
			neighbors.add(outArcEntry.getHead().getNode());
			
			outArcEntry = outArcEntry.getTailLink();
		}
		
		return neighbors;
	}

	/* (non-Javadoc)
	 * @see com.josonprog.common.graph.Graph#add(com.josonprog.common.graph.Node, com.josonprog.common.graph.Node)
	 */
	@Override
	public boolean add(N nodeA, N nodeB) {
		return this.add(nodeA, nodeB);
	}

	/* (non-Javadoc)
	 * @see com.josonprog.common.graph.Graph#delete(com.josonprog.common.graph.Node, com.josonprog.common.graph.Node)
	 */
	@Override
	public int delete(N nodeA, N nodeB) {
		return this.orthList.removeArc(nodeA, nodeB) ? 1 : 0;
	}


}
