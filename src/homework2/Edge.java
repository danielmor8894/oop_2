package homework2;

/**
 * An Edge is an abstraction of an edge  object. A typical edge consists of
 * a set of properties: { label, source, destination}.
 */

public class Edge<T> {
    T label;
    Node source;
    Node dest;
    public Edge(T label, Node source, Node dest){
        this.label= label;
        this.source= source;
        this.dest= dest;
        checkRep();
    }

    /**
     Abstraction Function:
     label - is an identifier of generic type, that is unique per edge
     source - the source node of the edge.
     dest- the destination node of the edge.

     Representation Invariant:
     label != NULL
     source != NULL
     dest != NULL
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

        Edge<T> e1 = (Edge<T>) o;
        checkRep();
        return (this.source.equals(e1.source) && (this.dest.equals(e1.dest)));

    }
    /**
     * @requires none
     * @modifies none
     * @effects returns the hashcode of 'this'.
     */
    @Override
    public int hashCode(){
        checkRep();
        return this.source.hashCode()+ this.dest.hashCode();

    }

    /**
     * @throws AssertionError if representation invariant is violated
     */

    private void  checkRep(){
        assert (this.dest!=null&& this.source!=null && this.label!= null):
                "illegal edge initialization ";
    }
}
