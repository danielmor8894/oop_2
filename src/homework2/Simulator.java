package homework2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * A Simulator is an abstraction of a Simulator object, that extends Bipartite graph,
 * and activates a simulation.
 * A typical Simulator consists of a set of properties:
 * {black Nodes, white Nodes, edges, round}.
 */

public class Simulator <T>  extends BipartiteGraph<T>{

    private int round = 0;
    /**
     Abstraction Function:
     blackNodes - is a map that connects between node labels and the black nodes they refer to
     whiteNodes - is a map that connects between node labels and the white nodes they refer to
     edges- is a set of edges in the Bipartite Graph
     round- number of the current simulation

     Representation Invariant:
     blackNodes != NULL
     whiteNodes != NULL
     edges != NULL
     round>=0
     */

    /**
     * @requires none
     * @modifies this
     * @effects activate one round of simulation, first starts with  the pipes simulation
     * and then moves to the filter simulation.
     */

    public void simulate() {
        Iterator<Map.Entry<T, Node>> iter = this.blackNodes.entrySet().iterator();  //pipes are black nodes
        while (iter.hasNext()) {
            ((Simulatable<?>) iter.next().getValue()).simulate((BipartiteGraph)this);

        }
        Iterator<Map.Entry<T, Node>> iter2 = this.whiteNodes.entrySet().iterator(); //filters are white nodes
        while (iter2.hasNext()) {
            ((Simulatable<?>) iter2.next().getValue()).simulate((BipartiteGraph)this);

        }
        round++;

    }

    /**
     * @requires none
     * @modifies this
     * @effects add a new pipe as a black node to simulator.
     * if nodeLabel s null, it does nothing.
     */

    public void addPipe (T nodeLabel){
        if (nodeLabel==null){
            return;
        }
        IntPipe newPipe= new IntPipe(nodeLabel);
        if ((!this.blackNodes.containsKey(nodeLabel))&& (!this.whiteNodes.containsKey(nodeLabel))){
            this.blackNodes.put(nodeLabel, newPipe);
        }
    }

    /**
     * @requires none
     * @modifies this
     * @effects add a new plus Filter as a white node to simulator.
     * if nodeLabel s null, it does nothing.
     */

    public void addPlusFilter (T nodeLabel){
        if (nodeLabel==null){
            return;
        }
        PlusFilter newPlusFilter= new PlusFilter(nodeLabel);
        if ((!this.blackNodes.containsKey(nodeLabel))&& (!this.whiteNodes.containsKey(nodeLabel))){
            this.whiteNodes.put(nodeLabel, newPlusFilter);
        }
    }

    /**
     * @requires none
     * @modifies this
     * @effects add a new gcd filter as a white node to simulator.
     * if nodeLabel s null, it does nothing.
     */

    public void addGCDFilter (T nodeLabel){
        if (nodeLabel==null){
            return;
        }
        GCDFilter newGCDFilter= new GCDFilter(nodeLabel);
        if ((!this.blackNodes.containsKey(nodeLabel))&& (!this.whiteNodes.containsKey(nodeLabel))){
            this.whiteNodes.put(nodeLabel, newGCDFilter);
        }
    }

    /**
     * @requires none
     * @modifies this
     * @effects adds a new value to the pipe which is identified with 'pipeName'.
     * if the pipe doesn't exist, it does nothing.
     */

    public void injectPipeInput (T pipeName, int value){
        if( !this.blackNodes.containsKey(pipeName)){
            return;
        }
        else{
            IntPipe pipe= (IntPipe) this.blackNodes.get(pipeName);
            pipe.injectInput(value);
        }
    }

    /**
     * @requires none
     * @modifies none
     * @effects returns a list of integers that has been inserted to the pipe,
     * and hasn't been pushed out yet, where the newest items are located in the beginning of the list,
     * and the oldest are in the end of it.
     *
     */

    public LinkedList<Integer> getContentOfPipe(T pipeName){
        if( !this.blackNodes.containsKey(pipeName)){
            return null;
        }
        else{
            IntPipe pipe= (IntPipe) this.blackNodes.get(pipeName);
            return pipe.getContent();
        }
    }

}
