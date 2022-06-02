package homework2;
import java.util.*;

public class BipartiteGraph<T> {

    class Node<T> {
        T label;
        Map<T,Node> incomingEdges= new HashMap<>();
        Map<T,Node> outgoingEdges= new HashMap<>();
        List<T> parents= new ArrayList<>();
        List<T> children= new ArrayList<>();
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
    Map<T, Node> blackNodes= new HashMap();
    Map<T,Node> whiteNodes= new HashMap<>();
    Set <Edge> edges= new HashSet<>();


    public void addBlackNode (T nodeLabel){
        checkRep();
        if (nodeLabel==null){
            return;
        }
        Node newBlackNode= new Node(nodeLabel);
        if ((!this.blackNodes.containsKey(nodeLabel))&& (!this.whiteNodes.containsKey(nodeLabel))){
            this.blackNodes.put(nodeLabel, newBlackNode);
        }
        checkRep();
    }

    public void addWhiteNode (T nodeLabel){

        checkRep();
        if (nodeLabel==null){
            return;
        }
        Node newWhiteNode= new Node(nodeLabel);
        if ((!this.whiteNodes.containsKey(nodeLabel))&& (!this.blackNodes.containsKey(nodeLabel))) {
            this.whiteNodes.put(nodeLabel, newWhiteNode);
        }
        checkRep();
    }

    public void addEdge (T parentVertex, T childVertex, T edgeLabel){

        checkRep();
        if (parentVertex==null || childVertex== null || edgeLabel==null){
            return;
        }
        else if (!isEdgeLegal(parentVertex,childVertex)){
            return;
        }
        Node parent= new Node(parentVertex);
        Node child= new Node(childVertex);
        Edge newEdge= new Edge(edgeLabel, parent, child);
        if (this.edges.contains(newEdge)){
            return;
        }
        else if (this.blackNodes.containsKey(parentVertex)){                             //parent is black & child is white
            if (this.blackNodes.get(parentVertex).outgoingEdges.containsKey(edgeLabel)||  //make sure there is no edge with the same label
                    this.whiteNodes.get(childVertex).incomingEdges.containsKey(edgeLabel)){
                return;
            }
            this.blackNodes.get(parentVertex).outgoingEdges.put(edgeLabel,child );
            this.blackNodes.get(parentVertex).children.add(childVertex);
            this.whiteNodes.get(childVertex).incomingEdges.put(edgeLabel, parent);
            this.whiteNodes.get(childVertex).parents.add(parentVertex);
        }
        else {
            //parent is white & child is black
            if (this.whiteNodes.get(parentVertex).outgoingEdges.containsKey(edgeLabel)||  //make sure there is no edge with the same label
                    this.blackNodes.get(childVertex).incomingEdges.containsKey(edgeLabel)){
                return;
            }
            this.whiteNodes.get(parentVertex).outgoingEdges.put(edgeLabel,child);
            this.whiteNodes.get(parentVertex).children.add(childVertex);
            this.blackNodes.get(childVertex).incomingEdges.put(edgeLabel,parent);
            this.blackNodes.get(childVertex).parents.add(parentVertex);
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

    public ArrayList<T> getListBlackNodes(){
        ArrayList<T> toRet= new ArrayList<>();
        Iterator<Map.Entry<T,Node>> iter= this.blackNodes.entrySet().iterator();
        while (iter.hasNext()){
             toRet.add(iter.next().getKey());
        }
        return toRet;

    }
    public ArrayList<T> getListWhiteNodes(){
        ArrayList<T> toRet= new ArrayList<>();
        Iterator<Map.Entry<T,Node>> iter= this.whiteNodes.entrySet().iterator();
        while (iter.hasNext()){
            toRet.add(iter.next().getKey());
        }
        return toRet;
    }
    public ArrayList<T> getListChildren(T parentVertex){

        if (parentVertex==null ||(!this.checkIfNodeExists(parentVertex))){   //node doesn't exist
            return new ArrayList<>() ;
        }
        else if(this.whiteNodes.containsKey(parentVertex)){   //node is white
            return new ArrayList<>(this.whiteNodes.get(parentVertex).children);

        }
        else {     //node is black
            return new ArrayList<>(this.blackNodes.get(parentVertex).children);

        }
    }

    public ArrayList<T> getListParents (T childVertex){
        if (childVertex==null || (!this.checkIfNodeExists(childVertex))){   //node doesn't exist
            return new ArrayList<>() ;
        }
        else if(this.whiteNodes.containsKey(childVertex)){   //node is white
            return new ArrayList<>(this.whiteNodes.get(childVertex).parents);
        }
        else {     //node is black
            return new ArrayList<>(this.blackNodes.get(childVertex).parents);
        }
    }

    public T getChildVertexByEdgeLabel(T parentLabel, T edgeLabel){
        if (parentLabel==null || edgeLabel== null || (!this.checkIfNodeExists(parentLabel))){   //parent node doesn't exist
            return null;
        }
        else if (this.whiteNodes.containsKey(parentLabel))  {   //parent node is white
            if (!this.whiteNodes.get(parentLabel).outgoingEdges.containsKey(edgeLabel)){
                return null;
            }
            else{
                Node<T> e1= (Node<T>) this.whiteNodes.get(parentLabel).outgoingEdges.get(edgeLabel);
                return e1.label;
            }
        }
        else {   //parent node is black
            if (!this.blackNodes.get(parentLabel).outgoingEdges.containsKey(edgeLabel)){
                return null;
            }
            else{
                Node<T> e1= (Node<T>) this.blackNodes.get(parentLabel).outgoingEdges.get(edgeLabel);
                return e1.label;
            }

        }
    }

    public T getParentVertexByEdgeLabel(T childLabel, T edgeLabel){
        if (childLabel==null || edgeLabel== null || (!this.checkIfNodeExists(childLabel))){   //parent node doesn't exist
            return null;
        }
        else if (this.whiteNodes.containsKey(childLabel))  {   //parent node is white
            if (!this.whiteNodes.get(childLabel).incomingEdges.containsKey(edgeLabel)){
                return null;
            }
            else{
                Node<T> e1= (Node<T>) this.whiteNodes.get(childLabel).incomingEdges.get(edgeLabel);
                return e1.label;
            }
        }
        else {   //parent node is black
            if (!this.blackNodes.get(childLabel).incomingEdges.containsKey(edgeLabel)){
                return null;
            }
            else{
                Node<T> e1= (Node<T>) this.blackNodes.get(childLabel).incomingEdges.get(edgeLabel);
                return e1.label;
            }

        }
    }

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
