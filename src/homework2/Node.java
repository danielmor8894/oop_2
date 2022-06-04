package homework2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A Node is an abstraction of a node  object. A typical node consists of
 * a set of properties: { label, incomingEdges, outgoingEdges, parents, children}.
 */

public class Node <T>{

    T label;
    Map<T, Node> incomingEdges= new HashMap<>();
    Map<T, Node> outgoingEdges= new HashMap<>();
    List<T> parents= new ArrayList<>();
    List<T> children= new ArrayList<>();

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
     outgoingEdges != NULL
     parents != NULL
     children != NULL
     */


    /**
     * @requires none
     * @modifies none
     * @effects initializes this with a given label
     */
    public Node(T label){
        this.label= label;
        checkRep();
    }

    /**
     * @requires none
     * @modifies none
     * @effects returns true if  object is a node that holds the same label 'this' has,
     * otherwise returns false.
     */
    @Override
    public boolean equals(Object o) {
        checkRep();
        if (this == o) {
            checkRep();
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            checkRep();
            return false;

        }
        Node<T> o1= (Node<T>)o;
        checkRep();
        return (this.label.equals(o1.label));

    }

    /**
     * @requires none
     * @modifies none
     * @effects returns the hashcode of 'this'.
     */
    @Override
    public int hashCode(){
        checkRep();
        return this.label.hashCode();
    }

    /**
     * @throws AssertionError if representation invariant is violated
     */

    private void checkRep(){
        assert (this.label!=null && this.incomingEdges!=null &&
                this.outgoingEdges!=null&& this.children!=null && this.parents!=null):
                "wrong initialization of Node";
    }

}

