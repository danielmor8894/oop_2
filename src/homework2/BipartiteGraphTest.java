package homework2;

import static org.junit.Assert.*;
import org.junit.Test;


/**
 * BipartiteGraphTest contains JUnit block-box unit tests for BipartiteGraph.
 */
public class BipartiteGraphTest {

    @Test
    public void testExample() {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph1");

        //add a pair of nodes
        driver.addBlackNode("graph1", "n1");
        driver.addWhiteNode("graph1", "n2");

        //add an edge
        driver.addEdge("graph1", "n1", "n2", "edge");

        //check neighbors
        assertEquals("wrong black nodes", "n1", driver.listBlackNodes("graph1"));
        assertEquals("wrong white nodes", "n2", driver.listWhiteNodes("graph1"));
        assertEquals("wrong children", "n2", driver.listChildren ("graph1", "n1"));
        assertEquals("wrong children", "", driver.listChildren ("graph1", "n2"));
        assertEquals("wrong parents", "", driver.listParents ("graph1", "n1"));
        assertEquals("wrong parents", "n1", driver.listParents ("graph1", "n2"));
    }

    @Test
    public void blackBoxTest(){
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph");

        //add a pair of nodes
        driver.addBlackNode("graph", "n1");  //valid
        driver.addWhiteNode("graph", "n2");  //valid
        driver.addBlackNode("graph", "n1");  //invalid insertion
        driver.addWhiteNode("graph", "n2");  //invalid insertion
        driver.addBlackNode("graph", "n2");  //invalid insertion
        driver.addWhiteNode("graph", "n1");  //invalid insertion
        driver.addWhiteNode("graph", null);
        driver.addBlackNode("graph", null);
        driver.addWhiteNode("graph", "n3");  //valid
        driver.addBlackNode("graph", "n4");  //valid
        driver.addWhiteNode("graph", "a5");  //valid
        driver.addBlackNode("graph", "a6");  //valid
        driver.addBlackNode("graph", "a7");  //valid


        driver.addEdge("graph","n1", "n4", "edge1"); //invalid edge, both verges are black
        driver.addEdge("graph","n2", "n2", "edge1"); //invalid edge
        driver.addEdge("graph","n2", "n3", "edge1"); //invalid edge, both verges are black
        driver.addEdge("graph","n1", "n2", "edge1");  //valid
        driver.addEdge("graph","n1", "n2", "edge2");  //edge already exists between 2 nodes
        driver.addEdge("graph","n1", "n3", "edge1");  //an edge with the same label connected to n1
        driver.addEdge("graph","n4", "n2", "edge1");  //an edge with the same label connected to n2
        driver.addEdge("graph","n100", "n2", "edge2");  //one of the verges doesn't exist
        driver.addEdge("graph","n1", "n3", "edge2");  //valid
        driver.addEdge("graph","a7", "n2", "edge3");  //valid


        assertEquals("wrong black nodes", "a6 a7 n1 n4", driver.listBlackNodes("graph"));
        assertEquals("wrong white nodes", "a5 n2 n3", driver.listWhiteNodes("graph"));
        assertEquals("wrong children", "n2 n3", driver.listChildren ("graph", "n1"));
        assertEquals("wrong children", "", driver.listChildren ("graph", "n2"));
        assertEquals("wrong children", "", driver.listChildren ("graph", "n3"));
        assertEquals("wrong children", "", driver.listChildren ("graph", null));
        assertEquals("wrong children", "", driver.listChildren ("graph", "n105"));
        assertEquals("wrong children", "", driver.listChildren ("graph", "a5"));
        assertEquals("wrong parents", "", driver.listParents ("graph", "n1"));
        assertEquals("wrong parents", "a7 n1", driver.listParents ("graph", "n2"));
        assertEquals("wrong parents", "n1", driver.listParents ("graph", "n3"));
        assertEquals("wrong child", "n2", driver.getChildByEdgeLabel ("graph", "n1", "edge1"));
        assertEquals("wrong parent", "n1", driver.getParentByEdgeLabel ("graph", "n3", "edge2"));
        assertEquals("wrong child", "", driver.getParentByEdgeLabel ("graph", "n30", "edge2"));
        assertEquals("wrong child", "", driver.getParentByEdgeLabel ("graph", "n3", "edge20"));
        assertEquals("wrong child", "", driver.getParentByEdgeLabel ("graph", "n3", null));
        assertEquals("wrong child", "", driver.getParentByEdgeLabel ("graph", null, "edge1"));

    }



    //  TODO: Add black-box tests


}