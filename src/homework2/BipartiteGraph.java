package homework2;
import java.util.*;



/**
 * A BipartiteGraph is an abstraction of a Bipartite Graph object. A typical Bipartite Graph consists of
 * a set of properties: {black Nodes, white Nodes, edges}.
 */


public class BipartiteGraph<T> {

    Map<T, Node<T>> blackNodes= new HashMap<T, Node<T>>();
    Map<T,Node<T>> whiteNodes= new HashMap<T,Node<T>>();
    Set <Edge<T>> edges= new HashSet<Edge<T>>();

    /**
     Abstraction Function:
     blackNodes - is a map that connects between node labels and the black nodes they refer to
     whiteNodes - is a map that connects between node labels and the white nodes they refer to
     edges- is a set of edges in the Bipartite Graph

     Representation Invariant:
     blackNodes != NULL
     whiteNodes != NULL
     edges != NULL
     */


    /**
     * @requires none
     * @modifies this
     * @effects adds a black node to this, identified by nodeLabel
     */

    public void addBlackNode (T nodeLabel){
        checkRep();
        if (nodeLabel==null){
            return;
        }
        Node<T> newBlackNode= new Node<>(nodeLabel);
        if ((!this.blackNodes.containsKey(nodeLabel))&& (!this.whiteNodes.containsKey(nodeLabel))){
            this.blackNodes.put(nodeLabel, newBlackNode);
        }
        checkRep();
    }


    /**
     * @requires none
     * @modifies this
     * @effects adds a white node to this, identified by nodeLabel
     */
    public void addWhiteNode (T nodeLabel){

        checkRep();
        if (nodeLabel==null){
            return;
        }
        Node<T> newWhiteNode= new Node<>(nodeLabel);
        if ((!this.whiteNodes.containsKey(nodeLabel))&& (!this.blackNodes.containsKey(nodeLabel))) {
            this.whiteNodes.put(nodeLabel, newWhiteNode);
        }
        checkRep();
    }

    /**
     * @requires none
     * @modifies this
     * @effects adds an edge to this, from parentVertex to childVertex,  identified by edgeLabel.
     * if parentVertex or childVertex aren't valid node labels in the graph, it does nothing.
     */
    public void addEdge (T parentVertex, T childVertex, T edgeLabel){

        checkRep();
        if (parentVertex==null || childVertex== null || edgeLabel==null){
            return;
        }
        else if (!isEdgeLegal(parentVertex,childVertex)){
            return;
        }

        Node<T> parent= new Node<>(parentVertex);
        Node<T> child= new Node<>(childVertex);
        Edge<T> newEdge= new Edge<>(edgeLabel, parent, child);
        if (this.edges.contains(newEdge)){
            return;
        }
        else if (this.blackNodes.containsKey(parentVertex)){                             //parent is black & child is white
            if (this.blackNodes.get(parentVertex).outgoingEdges.containsKey(edgeLabel)||  //make sure there is no edge with the same label
                    this.whiteNodes.get(childVertex).incomingEdges.containsKey(edgeLabel)){
                return;
            }
            Node<T> parent1= this.blackNodes.get(parentVertex);
            Node<T> child1= this.whiteNodes.get(childVertex);
            this.blackNodes.get(parentVertex).outgoingEdges.put(edgeLabel,child1 );
            this.blackNodes.get(parentVertex).children.add(childVertex);
            this.whiteNodes.get(childVertex).incomingEdges.put(edgeLabel, parent1);
            this.whiteNodes.get(childVertex).parents.add(parentVertex);
        }
        else {
            //parent is white & child is black
            if (this.whiteNodes.get(parentVertex).outgoingEdges.containsKey(edgeLabel)||  //make sure there is no edge with the same label
                    this.blackNodes.get(childVertex).incomingEdges.containsKey(edgeLabel)){
                return;
            }
            Node<T> parent2=  this.whiteNodes.get(parentVertex);
            Node<T> child2 = this.blackNodes.get(childVertex);
            this.whiteNodes.get(parentVertex).outgoingEdges.put(edgeLabel,child2);
            this.whiteNodes.get(parentVertex).children.add(childVertex);
            this.blackNodes.get(childVertex).incomingEdges.put(edgeLabel,parent2);
            this.blackNodes.get(childVertex).parents.add(parentVertex);
        }
        this.edges.add(newEdge);
        checkRep();

    }

    /**
     * @requires none
     * @modifies none
     * @effects returns true if there is a node in the Bipartite graph, iddentified  by 'label'.
     * else return false
     */

    private Boolean checkIfNodeExists(T label){
         return (this.blackNodes.containsKey(label)|| this.whiteNodes.containsKey(label));
    }

    /**
     * @requires none
     * @modifies none
     * @effects returns true if the edge from source to dest ig a valid edge between existent nodes
     * from different groups
     */

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

    /**
     * @requires none
     * @modifies none
     * @effects returns a list of all the black nodes in the Bipartite graph
     */

    public ArrayList<T> getListBlackNodes(){
        checkRep();
        ArrayList<T> toRet= new ArrayList<>();
        Iterator<Map.Entry<T,Node<T>>> iter= this.blackNodes.entrySet().iterator();
        while (iter.hasNext()){
             toRet.add(iter.next().getKey());
        }
        checkRep();
        return toRet;

    }

    /**
     * @requires none
     * @modifies none
     * @effects returns a list of all the white nodes in the Bipartite graph
     */
    public ArrayList<T> getListWhiteNodes(){
        checkRep();
        ArrayList<T> toRet= new ArrayList<>();
        Iterator<Map.Entry<T,Node<T>>> iter= this.whiteNodes.entrySet().iterator();
        while (iter.hasNext()){
            toRet.add(iter.next().getKey());
        }
        checkRep();
        return toRet;
    }

    /**
     * @requires none
     * @modifies none
     * @effects returns a list of all the nodes in the bipartite graph that have
     * an incoming edge originated from parentVertex
     * if parentVertex==nul returns an empty list
     */
    public ArrayList<T> getListChildren(T parentVertex){
        checkRep();
        if (parentVertex==null ||(!this.checkIfNodeExists(parentVertex))){   //node doesn't exist
            checkRep();
            return new ArrayList<>() ;
        }
        else if(this.whiteNodes.containsKey(parentVertex)){   //node is white
            checkRep();
            return new ArrayList<>(this.whiteNodes.get(parentVertex).children);

        }
        else {     //node is black
            checkRep();
            return new ArrayList<>(this.blackNodes.get(parentVertex).children);

        }
    }

    /**
     * @requires none
     * @modifies none
     * @effects returns a list of all the nodes in the bipartite graph that have
     * an outgoing edge  that goes to  childVertex.
     * if childVertex==nul returns an empty list
     */
    public ArrayList<T> getListParents (T childVertex){
        checkRep();
        if (childVertex==null || (!this.checkIfNodeExists(childVertex))){   //node doesn't exist
            checkRep();
            return new ArrayList<>() ;
        }
        else if(this.whiteNodes.containsKey(childVertex)){   //node is white
            checkRep();
            return new ArrayList<>(this.whiteNodes.get(childVertex).parents);
        }
        else {     //node is black
            checkRep();
            return new ArrayList<>(this.blackNodes.get(childVertex).parents);
        }
    }

    /**
     * @requires none
     * @modifies none
     * @effects returns a node's label that has an incoming edge identified with edgeLabel,
     * and originated from the node which is identified with parentLabel.
     * returns null if there isn't such a node
     */

    public T getChildVertexByEdgeLabel(T parentLabel, T edgeLabel){
        checkRep();
        if (parentLabel==null || edgeLabel== null || (!this.checkIfNodeExists(parentLabel))){   //parent node doesn't exist
            checkRep();
            return null;
        }
        else if (this.whiteNodes.containsKey(parentLabel))  {   //parent node is white
            checkRep();
            if (!this.whiteNodes.get(parentLabel).outgoingEdges.containsKey(edgeLabel)){
                return null;
            }
            else{
                Node<T> e1= (Node<T>) this.whiteNodes.get(parentLabel).outgoingEdges.get(edgeLabel);
                checkRep();
                return e1.label;
            }
        }
        else {   //parent node is black
            if (!this.blackNodes.get(parentLabel).outgoingEdges.containsKey(edgeLabel)){
                checkRep();
                return null;
            }
            else{
                Node<T> e1= (Node<T>) this.blackNodes.get(parentLabel).outgoingEdges.get(edgeLabel);
                checkRep();
                return e1.label;
            }

        }
    }

    /**
     * @requires none
     * @modifies none
     * @effects returns a node's label that has an outgoing edge identified with edgeLabel,
     * and goes to the node which is identified with childLabel.
     * returns null if there isn't such a node
     */

    public T getParentVertexByEdgeLabel(T childLabel, T edgeLabel){
        if (childLabel==null || edgeLabel== null || (!this.checkIfNodeExists(childLabel))){   //parent node doesn't exist
            checkRep();
            return null;
        }
        else if (this.whiteNodes.containsKey(childLabel))  {   //child node is white
            if (!this.whiteNodes.get(childLabel).incomingEdges.containsKey(edgeLabel)){
                checkRep();
                return null;
            }
            else{
                Node<T> e1= (Node<T>) this.whiteNodes.get(childLabel).incomingEdges.get(edgeLabel);
                checkRep();
                return e1.label;
            }
        }
        else {   //child node is black
            if (!this.blackNodes.get(childLabel).incomingEdges.containsKey(edgeLabel)){
                checkRep();
                return null;
            }
            else{
                Node<T> e1= (Node<T>) this.blackNodes.get(childLabel).incomingEdges.get(edgeLabel);
                checkRep();
                return e1.label;
            }

        }
    }

    /**
     * @requires none
     * @modifies this
     * @effects removes the node which is identified with 'label' from the Bipartite Graph.
     * does nothing if there is no such a node.
     */

    public void removeNode(T label){
        checkRep();
        if (!this.blackNodes.containsKey(label) && !this.whiteNodes.containsKey(label)){
            return;
        }
        else if (this.blackNodes.containsKey(label)){
            this.eraseFromList(label, this.blackNodes);

            }
        else {
            this.eraseFromList(label, this.whiteNodes);

        }
        checkRep();
    }

    /**
     * @requires none
     * @modifies this
     * @effects removes the node which is identified with 'label' from 'nodesList'.
     * does nothing if there is no such a node.
     */


    private void eraseFromList(T label, Map<T, Node<T>> nodesList) {
        Node<T> toRemove= nodesList.get(label);
        Iterator<Map.Entry<T,Node<T>>> iter= toRemove.outgoingEdges.entrySet().iterator();
        while (iter.hasNext()){  //removes all outgoing edges
            Map.Entry<T, Node<T>> it= iter.next();
            T edgeLabel= it.getKey();
            it.getValue().incomingEdges.remove(edgeLabel);
            it.getValue().parents.remove(toRemove.label);
            this.edges.remove(new Edge<>(edgeLabel,toRemove, it.getValue() ));
        }

        Iterator<Map.Entry<T,Node<T>>> iter2= toRemove.incomingEdges.entrySet().iterator();
        while (iter2.hasNext()){  //removes all incoming edges
            Map.Entry<T, Node<T>> it= iter2.next();
            T edgeLabel= it.getKey();
            it.getValue().outgoingEdges.remove(edgeLabel);
            it.getValue().children.remove(toRemove.label);
            this.edges.remove(new Edge<>(edgeLabel, it.getValue(), toRemove));
        }
        nodesList.remove(label);

    }

    /**
     * @requires none
     * @modifies this
     * @effects removes the edge which is identified with 'label' and goes from 'source' to 'dest'.
     * does nothing if there is no such an edge.
     */

    public void removeEdge(T label, T source, T dest){
        checkRep();
        Node <T> sourceNode= new Node<>(source);
        Node <T> destNode= new Node<>(dest);
        Edge<T> toRemove= new Edge<>(label,sourceNode,destNode);
        if (!this.edges.contains(toRemove)){
            return;
        }
        else{
            if (this.blackNodes.containsKey(source)){  //source is black & dest is white
                this.blackNodes.get(source).outgoingEdges.remove(label);
                this.blackNodes.get(source).children.remove(dest);
                this.whiteNodes.get(dest).incomingEdges.remove(label);
                this.whiteNodes.get(dest).parents.remove(source);
            }
            else{  //source is white & dest is black
                this.whiteNodes.get(source).outgoingEdges.remove(label);
                this.whiteNodes.get(source).children.remove(dest);
                this.blackNodes.get(dest).incomingEdges.remove(label);
                this.blackNodes.get(dest).parents.remove(source);
            }
            this.edges.remove(toRemove);
        }
        checkRep();
    }

    /**
     * @throws AssertionError if representation invariant is violated
     */

    private void checkRep(){
        Iterator<Edge<T>> iter= this.edges.iterator();
        while (iter.hasNext()){
            Edge e= iter.next();
            assert (!((this.blackNodes.containsKey(e.source.label))&&(this.blackNodes.containsKey(e.dest.label)))):
                    "both nodes are black";
            assert (!((this.whiteNodes.containsKey(e.source.label))&&(this.whiteNodes.containsKey(e.dest.label)))):
                    "both nodes are white";
        }

    }

}
