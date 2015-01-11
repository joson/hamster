/**
 * 
 */
package com.josonprog.common.petrinet;

import java.util.HashSet;
import java.util.Set;

import com.josonprog.common.graph.DefaultDigraph;
import com.josonprog.common.graph.OrthNodeEntry;

/**
 * @author Joson
 *
 */
public class DefaultPetriNet extends DefaultDigraph<PetriNode> 
		implements PetriNet {
	
	/**
	 * 
	 */
	private Set<OrthNodeEntry<Place>> placeEntrySet = new HashSet<OrthNodeEntry<Place>>();
	
	/**
	 * 
	 */
	private Set<OrthNodeEntry<Transition>> transitionEntrySet = new HashSet<OrthNodeEntry<Transition>>();

	/**
	 * 
	 * @param place
	 * @return
	 */
	protected OrthNodeEntry<Place> createPlaceEntry(Place place) {
		return new OrthNodeEntry<Place>(place);
	}

	/**
	 * 
	 * @param transition
	 * @return
	 */
	protected OrthNodeEntry<Transition> createTransitionEntry(Transition transition) {
		return new OrthNodeEntry<Transition>(transition);
	}
	
	@Override
	public boolean addNode(PetriNode node) {
		if (node instanceof Place) {
			return this.placeEntrySet.add(this.createPlaceEntry((Place) node));
		} else if (node instanceof Transition) {
			return this.transitionEntrySet.add(this.createTransitionEntry((Transition) node));
		} else {
			return false;
		}
	}
	
	@Override
	public boolean containsNode(PetriNode node) {
		if (node instanceof Place) {
			return this.placeEntrySet.contains(node);
		} else if (node instanceof Transition) {
			return this.transitionEntrySet.contains(node);
		} else {
			return false;
		}
	}
	
	
}
