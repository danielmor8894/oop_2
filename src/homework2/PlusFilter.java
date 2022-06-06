package homework2;

import java.util.Iterator;
import java.util.Map;

/**
 * A PlusFilter is an abstraction of a Plus Filter object, that extends Node,
 * and implements the Simulatable interface.
 * A typical GCDFilter consists of a set of properties:
 *  { label, incomingEdges, outgoingEdges, parents, children}.
 */

public class PlusFilter<T> extends Node<T> implements Simulatable<T> {

    /**
     Abstraction Function:
     label - is an identifier of generic type, that is unique per node
     incomingEdges - is a map that connects between an edge label and the destination node that
     the edge goes to.
     outgoingEdges- is a map that connects between an edge label and the source node that
     the edge comes from.
     parents- the parent nodes of this
     children- the children nodes of this

     Representation Invariant:
     label != NULL
     incomingEdges != NULL
     outgoingEdges != NULL  & there is 1 outgoing Edge at most
     parents != NULL
     children != NULL & children.size<=1
     */

    public PlusFilter(T label){

        super(label);
    }

    /**
     * @requires none
     * @modifies this
     * @effects activate one round of plus filter simulation.
     */
    @Override
    public void simulate(BipartiteGraph<T> graph) {
        checkRep();
        int acc= 0;
        Iterator<Map.Entry<T,Node<T>>> iter= this.incomingEdges.entrySet().iterator(); //traverse the node's parents
        while( iter.hasNext()) {
            IntPipe<T> input= (IntPipe<T>) iter.next().getValue();
            if (!input.isOutputListEmpty()) {
                    acc += input.getLastNumberInPipe();
                }

        }
            if (!this.outgoingEdges.isEmpty()){  //put the result in the node's child
                Iterator<Map.Entry<T,Node<T>>> iter2= this.outgoingEdges.entrySet().iterator();
                IntPipe<T> output = (IntPipe<T>) iter2.next().getValue();
                output.injectInput(acc);
            }
            checkRep();
        }


    /**
     * @throws AssertionError if representation invariant is violated
     */

    private void checkRep(){
        assert (this.children.size()<=1 && this.outgoingEdges.size()<=1): "PlusFilter has more than one output pipe!";
    }

}
