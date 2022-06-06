package homework2;

import java.util.*;

/**
 * An IntPipe is an abstraction of a int pipe object, that extends Node,
 * and implements the Simulatable interface.
 * A typical IntPipe consists of a set of properties:
 *  { label, incomingEdges, outgoingEdges, parents, children,inputPipe, outputPipe  }.
 */

public class IntPipe<T> extends Node<T> implements Simulatable<T> {

    private LinkedList<Integer> inputPipe= new LinkedList<>();
    private LinkedList<Integer> outputPipe= new LinkedList<>();

    /**
     Abstraction Function:
     label - is an identifier of generic type, that is unique per node
     incomingEdges - is a map that connects between an edge label and the destination node that
     the edge goes to.
     outgoingEdges- is a map that connects between an edge label and the source node that
     the edge comes from.
     parents- the parent nodes of this
     children- the children nodes of this
     inputPipe- the input pipe of the whole pipe
     outputPipe- the output pipe of the whole pipe

     Representation Invariant:
     label != NULL
     incomingEdges != NULL
     outgoingEdges != NULL  & there is 1 outgoing Edge at most
     parents != NULL
     children != NULL
     inputPipe != NULL
     outputPipe != NULL

     */


    public IntPipe(T label){

        super(label);
    }

    /**
     * @requires none
     * @modifies this
     * @effects activate one round of int pipe simulation.
     */
    @Override
    public void simulate(BipartiteGraph<T> graph) {
        checkRep();
        Iterator it = this.inputPipe.descendingIterator();
        while (it.hasNext()){
            this.outputPipe.addFirst((Integer) it.next());
        }
        this.inputPipe.clear();
        checkRep();

    }

    /**
     * @requires a value as integer
     * @modifies this
     * @effects inserts value into the input pipe.
     */

    public void injectInput(int value){
        checkRep();
        this.inputPipe.addFirst(value);
    }

    /**
     * @requires none
     * @modifies none
     * @effects returns the integers that have been inserted into the pipe,
     * and haven't been pushed out yet.
     */
    public LinkedList<Integer> getContent (){
        checkRep();
        LinkedList<Integer> toRet= new LinkedList<>(this.inputPipe);
        LinkedList<Integer> toAppend= new LinkedList<>(this.outputPipe);
        toRet.addAll(toAppend);
        checkRep();
        return toRet;
    }

    /**
     * @requires none
     * @modifies this
     * @effects returns the first integer that has been inserted into the pipe,
     *  and removes it from the pipe.
     */
    public int getLastNumberInPipe (){
        checkRep();
        return this.outputPipe.removeLast();

    }

    /**
     * @requires none
     * @modifies none
     * @effects returns an integers list of the integers that have been inserted into the pipe,
     * and haven't been pushed out.
     */
    public boolean isOutputListEmpty(){
        return this.outputPipe.isEmpty();
    }

    private void checkRep(){
        assert (this.inputPipe!= null && this.outputPipe!= null): "invalid initialization";

    }


}
