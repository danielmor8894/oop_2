package homework2;
import java.util.*;

public class BipartiteGraph<T> {

    class Node<T> {
        T label;
        Map<T,Node> incomingEdges= new HashMap<>();
        Map<T,Node> outgoingEdges= new HashMap<>();
        public Node(T label){
            this.label= label;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || o.getClass() != this.getClass()) {
                return false;

            }
            Node<T> o1= (Node<T>)o;
            return (this.label.equals(o1.label));

        }

        @Override
        public int hashCode(){
            return this.label.hashCode();
        }

    }
    class Edge<T>{
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
    private String name;
    Map<T, Node> blackNodes= new HashMap();
    Map<T,Node> whiteNodes= new HashMap<>();
    Set <Edge> edges= new HashSet<>();

    public BipartiteGraph( String name){
        this.name= name;
    }

    public void addBlackNode (T nodeLabel){
        checkRep();
        Node newBlackNode= new Node(nodeLabel);
        this.blackNodes.put(nodeLabel, newBlackNode);
        checkRep();
    }

    public void addWhiteNode (T nodeLabel){

        checkRep();
        Node newWhiteNode= new Node(nodeLabel);
        this.blackNodes.put(nodeLabel, newWhiteNode);
        checkRep();
    }

    public void addEdge (T parentVertex, T childVertex, T edgeLabel){

        checkRep();
        if (!isEdgeLegal(parentVertex,childVertex)){
            return;
        }
        Node parent= new Node(parentVertex);
        Node child= new Node(childVertex);
        Edge newEdge= new Edge(edgeLabel, parent, child);
        if (this.edges.contains(newEdge)){
            return;
        }
        else if (this.blackNodes.containsKey(parentVertex)){                             //parent is black & child is white
            this.blackNodes.get(parentVertex).outgoingEdges.put(edgeLabel,child );
            this.whiteNodes.get(childVertex).incomingEdges.put(edgeLabel, parent);
        }
        else {                                                                      //parent is white & child is black
            this.whiteNodes.get(parentVertex).outgoingEdges.put(edgeLabel,child);
            this.blackNodes.get(childVertex).incomingEdges.put(edgeLabel,parent);
        }
        this.edges.add(newEdge);
        checkRep();

    }

    private Boolean checkIfNodeExists(T label){
         return (this.blackNodes.containsKey(label)|| this.whiteNodes.containsKey(label));
    }

    private Boolean isEdgeLegal (T source, T dest){
        if ((!checkIfNodeExists(source))|| (!checkIfNodeExists(dest))){
            return false;
        }
        else if (this.whiteNodes.containsKey(source)&& this.whiteNodes.containsKey(dest)){
            return false;
        }
        else if (this.blackNodes.containsKey(source)&& this.blackNodes.containsKey(dest)){
            return false;
        }
        else{
            return true;
        }
    }

    public ArrayList<T> getListBlackNodes(){}
    public ArrayList<T> getListWhiteNodes(){}
    public ArrayList<T> getListChildren(T parentVertex){}
    public ArrayList<T> getListParents (T childVertex){}
    public T getChildVertexByEdgeLabel(T parentLabel, T edgeLabel){}
    public T getParentVertexByEdgeLabel(T childLabel, T edgeLabel){}

    private void checkRep(){
        Iterator<Edge> iter= this.edges.iterator();
        while (iter.hasNext()){
            Edge e= iter.next();
            assert (!((this.blackNodes.containsKey(e.source.label))&&(this.blackNodes.containsKey(e.dest.label)))):
                    "both nodes are black";
            assert (!((this.whiteNodes.containsKey(e.source.label))&&(this.whiteNodes.containsKey(e.dest.label)))):
                    "both nodes are white";
        }
    }

}
