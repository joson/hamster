/**
 * 
 */
package com.josonprog.common.graph;

import java.util.List;

/**
 * @author Joson
 *
 */
public interface Graph<N extends Node> {

	/**
	 * tests whether there is an arc from node A to node B.
	 * 
	 * @param nodeA
	 * @param nodeB
	 * @return
	 */
	public boolean adjacent(N nodeA, N nodeB);
	
	/**
	 * lists all nodes nodeB such that there is an arc from nodeA to nodeB.
	 * 
	 * @param node
	 * @return
	 */
	public List<N> neighbors(N nodeA);
	
	/**
	 * adds the arc from nodeA to nodeB, if it is not there.
	 * 
	 * @param nodeA
	 * @param nodeB
	 * @return
	 */
	public boolean add(N nodeA, N nodeB);
	
	
	/**
	 * removes the arc from nodeA to nodeB, if it is there.
	 * 
	 * @param nodeA
	 * @param nodeB
	 * @return
	 */
	public int delete(N nodeA, N nodeB);
	
}
