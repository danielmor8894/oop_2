package homework2;

import java.util.*;

/**
 * This class implements a testing driver for BipartiteGraph. The driver
 * manages BipartiteGraphs whose nodes and edges are Strings.
 */
public class BipartiteGraphTestDriver {

    private Map<String, BipartiteGraph<String>> graphs;

    /**
     * @modifies this
     * @effects Constructs a new test driver.
     */
    public BipartiteGraphTestDriver () {
        this.graphs= new HashMap<>();

    }

    
    /**
     * @requires graphName != null
     * @modifies this
     * @effects Creates a new graph named graphName. The graph is initially
     * 			empty.
     */
    public void createGraph(String graphName) {
        BipartiteGraph<String> newGraph= new BipartiteGraph();
        this.graphs.put(graphName,newGraph);

    }

    
    /**
     * @requires createGraph(graphName)
     *           && nodeName != null
     *           && neither addBlackNode(graphName,nodeName) 
     *                  nor addWhiteNode(graphName,nodeName)
     *                      has already been called on this
     * @modifies graph named graphName
     * @effects Adds a black node represented by the String nodeName to the
     * 			graph named graphName.
     */
    public void addBlackNode(String graphName, String nodeName) {
        this.graphs.get(graphName).addBlackNode(nodeName);

    }

    
    /**
     * @requires createGraph(graphName)
     *           && nodeName != null
     *           && neither addBlackNode(graphName,nodeName) 
     *                  nor addWhiteNode(graphName,nodeName)
     *                      has already been called on this
     * @modifies graph named graphName
     * @effects Adds a white node represented by the String nodeName to the
     * 			graph named graphName.
     */
    public void addWhiteNode(String graphName, String nodeName) {
        this.graphs.get(graphName).addWhiteNode(nodeName);

    }

    
    /**
     * @requires createGraph(graphName)
     *           && ((addBlackNode(parentName) && addWhiteNode(childName))
     *              || (addWhiteNode(parentName) && addBlackNode(childName)))
     *           && edgeLabel != null
     *           && node named parentName has no other outgoing edge labeled
     * 				edgeLabel
     *           && node named childName has no other incoming edge labeled
     * 				edgeLabel
     * @modifies graph named graphName
     * @effects Adds an edge from the node parentName to the node childName
     * 			in the graph graphName. The new edge's label is the String
     * 			edgeLabel.
     */
    public void addEdge(String graphName,
    					String parentName, String childName, 
                        String edgeLabel) {
        this.graphs.get(graphName).addEdge(parentName, childName, edgeLabel);

    }

    
    /**
     * @requires createGraph(graphName)
     * @return a space-separated list of the names of all the black nodes
     * 		   in the graph graphName, in alphabetical order.
     */
    public String listBlackNodes(String graphName) {
        ArrayList<String> blackNodes= this.graphs.get(graphName).getListBlackNodes();
        Collections.sort(blackNodes, new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }
                });
        return String.join(" ", blackNodes);

    }

    
    /**
     * @requires createGraph(graphName)
     * @return a space-separated list of the names of all the white nodes
     * 		   in the graph graphName, in alphabetical order.
     */
    public String listWhiteNodes(String graphName) {
        ArrayList<String> whiteNodes= this.graphs.get(graphName).getListWhiteNodes();
        Collections.sort(whiteNodes, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        return String.join(" ", whiteNodes);

    }

    
    /**
     * @requires createGraph(graphName) && createNode(parentName)
     * @return a space-separated list of the names of the children of
     * 		   parentName in the graph graphName, in alphabetical order.
     */
    public String listChildren(String graphName, String parentName) {
        ArrayList<String> childrenNodes=  this.graphs.get(graphName).getListChildren(parentName);
        Collections.sort(childrenNodes, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        return String.join(" ", childrenNodes);

    }

    
    /**
     * @requires createGraph(graphName) && createNode(childName)
     * @return a space-separated list of the names of the parents of
     * 		   childName in the graph graphName, in alphabetical order.
     */
    public String listParents(String graphName, String childName) {
        ArrayList<String> parentsNodes= this.graphs.get(graphName).getListParents(childName);
        Collections.sort(parentsNodes, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        return String.join(" ", parentsNodes);

    }

    
    /**
     * @requires addEdge(graphName, parentName, str, edgeLabel) for some
     * 			 string str
     * @return the name of the child of parentName that is connected by the
     * 		   edge labeled edgeLabel, in the graph graphName.
     */
    public String getChildByEdgeLabel(String graphName, String parentName,
    								   String edgeLabel) {
        String toRet= this.graphs.get(graphName).getChildVertexByEdgeLabel(parentName, edgeLabel);
        if (toRet==null){
            return "";
        }
        else{
            return toRet;
        }

    }

    
    /**
     * @requires addEdge(graphName, str, childName, edgeLabel) for some
     * 			 string str
     * @return the name of the parent of childName that is connected by the 
     * 		   edge labeled edgeLabel, in the graph graphName.
     */
    public String getParentByEdgeLabel(String graphName, String childName,
    									String edgeLabel) {
        String toRet= this.graphs.get(graphName).getParentVertexByEdgeLabel(childName,edgeLabel);
        if (toRet==null){
            return "";
        }
        else{
            return toRet;
        }

    }


    /**
     * @requires createGraph(graphName)
     * @modifies this
     * @effects removes the node which is identified with 'label' from the BipartiteGraph graphName  .
     * does nothing if there is no such a node.
     */

    public void removeNode(String graphName, String  label){
        this.graphs.get(graphName).removeNode(label);
    }


    /**
     * @requires createGraph(graphName)
     * @modifies this
     * @effects removes the edge which is identified with 'label' and goes from 'source' to 'dest'
     * from .the BipartiteGraph graphName.
     * does nothing if there is no such an edge.
     */

    public void removeEdge(String graphName, String edgeLabel, String source, String dest){
        this.graphs.get(graphName).removeEdge(edgeLabel,source,dest);
    }
}
