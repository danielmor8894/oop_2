package homework2;


/**
 * A GCDFilter is an abstraction of a GCD Filter object, that extends Node,
 * and implements the Simulatable interface.
 * A typical GCDFilter consists of a set of properties:
 *  { label, incomingEdges, outgoingEdges, parents, children}.
 */

public class GCDFilter<T> extends Node<T> implements Simulatable<T> {

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
     incomingEdges != NULL & there are 2 incoming Edges named "a" & "b"
     outgoingEdges != NULL & there are 3 outgoing Edges named "a", "b" & "gcd"
     parents != NULL & parent.size==2
     children != NULL & children.size==3
     */

    public GCDFilter( T label){
        super(label);
    }


    /**
     * @requires none
     * @modifies this
     * @effects activate one round of gcd filter simulation.
     */
    @Override
    public void simulate(BipartiteGraph<T> graph) {
        checkRep();
        int a=0, b= 0, gcd= 0;
        IntPipe<T> aInputPipe= (IntPipe<T>) this.incomingEdges.get("a");
        IntPipe<T> bInputPipe= (IntPipe<T>) this.incomingEdges.get("b");
        IntPipe<T> gcdOutputPipe= ((IntPipe<T>) this.outgoingEdges.get("gcd"));
        IntPipe<T> aOutputPipe= ((IntPipe<T>) this.outgoingEdges.get("a"));
        IntPipe<T> bOutputPipe= ((IntPipe<T>) this.outgoingEdges.get("b"));

        if (aInputPipe.isOutputListEmpty() || (bInputPipe.isOutputListEmpty())){
            checkRep();
            return;
        }
        else{
            a= aInputPipe.getLastNumberInPipe();
            b= bInputPipe.getLastNumberInPipe();
        }
        //Euclid Algorithm
        if (b==0){
            ((IntPipe<T>) this.outgoingEdges.get("gcd")).injectInput(a);
        }
        else if(a<b){

            ((IntPipe<T>) this.outgoingEdges.get("a")).injectInput(b);
            ((IntPipe<T>) this.outgoingEdges.get("b")).injectInput(a);

        }
        else{
            ((IntPipe<T>) this.outgoingEdges.get("a")).injectInput(b);
            ((IntPipe<T>) this.outgoingEdges.get("b")).injectInput(a%b);

        }
        checkRep();

    }

    /**
     * @throws AssertionError if representation invariant is violated
     */

    private void checkRep(){
        assert (this.children.size()==3): "filter doesn't have 3 outgoing edges";
        assert (this.parents.size()==2): "filter doesn't have 2 incoming edges";
        assert (this.incomingEdges.containsKey("a")&& (this.incomingEdges.containsKey("b"))): "wrong edge labels";
        assert (this.outgoingEdges.containsKey("a") && this.outgoingEdges.containsKey("b")&&
                this.outgoingEdges.containsKey("gcd")): "wrong edge labels";

    }
}
