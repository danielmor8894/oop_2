package homework2;

public class Edge<T> {
    T label;
    Node source;
    Node dest;
    public Edge(T label, Node source, Node dest){
        this.label= label;
        this.source= source;
        this.dest= dest;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Edge<T> e1 = (Edge<T>) o;
        return (this.source.equals(e1.source) && (this.dest.equals(e1.dest)));

    }

    @Override
    public int hashCode(){
        return this.source.hashCode()+ this.dest.hashCode();

    }
}
