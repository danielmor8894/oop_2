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
    public void blackBoxTest1(){
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph");

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


    }

    @Test
    public void blackBoxTest2(){
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create a graph
        driver.createGraph("graph");
        driver.addBlackNode("graph", "n1");  //valid
        driver.addWhiteNode("graph", "n2");  //valid
        driver.addWhiteNode("graph", "n3");  //valid
        driver.addBlackNode("graph", "n4");  //valid
        driver.addWhiteNode("graph", "a5");  //valid
        driver.addBlackNode("graph", "a6");  //valid
        driver.addBlackNode("graph", "a7");  //valid

        driver.addEdge("graph","n1", "n2", "edge1");  //valid
        driver.addEdge("graph","n100", "n2", "edge2");  //one of the verges doesn't exist
        driver.addEdge("graph","n1", "n3", "edge2");  //valid
        driver.addEdge("graph","a7", "n2", "edge3");  //valid

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


    @Test
    public void blackBoxTest3(){
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        driver.createGraph("graph");

        driver.addWhiteNode("graph", "a1");
        driver.addWhiteNode("graph", "a2");
        driver.addWhiteNode("graph", "a3");
        driver.addBlackNode("graph", "b1");
        driver.addBlackNode("graph", "b2");
        driver.addBlackNode("graph", "b3");

        driver.addEdge("graph","a1", "b2", "edge1");  //valid
        driver.addEdge("graph","a2", "b2", "edge1");  //invalid, label already exists in b2
        driver.addEdge("graph","a2", "b2", "edge2");  //valid
        driver.addEdge("graph","a2", "b2", "edge3");  //invalid, edge already exists
        driver.addEdge("graph","b1", "a2", "edge3");  //valid
        driver.addEdge("graph","b3", "a3", "edge4");  //valid
        driver.addEdge("graph","a1", "b3", "edge1");  //invalid, label already exists
        driver.addEdge("graph","a3", "b2", "edge2");  //invalid, label already exists

        assertEquals("wrong black nodes", "b1 b2 b3", driver.listBlackNodes("graph"));
        assertEquals("wrong white nodes", "a1 a2 a3", driver.listWhiteNodes("graph"));
        assertEquals("wrong parents", "a1 a2", driver.listParents ("graph", "b2"));
        assertEquals("wrong parents", "a1 a2", driver.listParents ("graph", "b2"));
        assertEquals("wrong parents", "", driver.listParents ("graph", null));
        assertEquals("wrong parents", "", driver.listParents ("graph", "a100"));
        assertEquals("wrong parents", "", driver.listParents ("graph", "a1"));
        assertEquals("wrong parents", "b3", driver.listParents ("graph", "a3"));
        assertEquals("wrong parents", "", driver.listParents ("graph", "b3"));
        assertEquals("wrong children", "b2", driver.listChildren ("graph", "a2"));



    }

    @Test
    public void blackBoxTest4(){
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        driver.createGraph("graph2");

        driver.addWhiteNode("graph2", "a1");
        driver.addWhiteNode("graph2", "a2");
        driver.addWhiteNode("graph2", "a3");
        driver.addBlackNode("graph2", "b1");
        driver.addBlackNode("graph2", "b2");
        driver.addBlackNode("graph2", "b3");

        driver.addEdge("graph2","a1", "b2", "edge1");  //valid
        driver.addEdge("graph2","a2", "b2", "edge1");  //invalid, label already exists in b2
        driver.addEdge("graph2","a2", "b2", "edge2");  //valid
        driver.addEdge("graph2","a2", "b2", "edge3");  //invalid, edge already exists
        driver.addEdge("graph2","b1", "a2", "edge3");  //valid
        driver.addEdge("graph2","b3", "a3", "edge4");  //valid
        driver.addEdge("graph2","a1", "b3", "edge1");  //invalid, label already exists
        driver.addEdge("graph2","a3", "b2", "edge2");  //invalid, label already exists

        assertEquals("wrong children", "a2", driver.listChildren ("graph2", "b1"));
        assertEquals("wrong child", "b2", driver.getChildByEdgeLabel ("graph2", "a2", "edge2"));
        assertEquals("wrong child", "", driver.getChildByEdgeLabel ("graph2", "a2", "edge100"));
        assertEquals("wrong child", "", driver.getChildByEdgeLabel ("graph2", null, "edge2"));
        assertEquals("wrong child", "a2", driver.getChildByEdgeLabel ("graph2", "b1", "edge3"));
        assertEquals("wrong child", "", driver.getChildByEdgeLabel ("graph2", "b1", "edge100"));
        assertEquals("wrong parent", "a2", driver.getParentByEdgeLabel ("graph2", "b2", "edge2"));
        assertEquals("wrong parent", "b1", driver.getParentByEdgeLabel ("graph2", "a2", "edge3"));
        assertEquals("wrong parent", "", driver.getParentByEdgeLabel ("graph2", "a2", "edge100"));
        assertEquals("wrong parent", "", driver.getParentByEdgeLabel ("graph2", "b2", null));
        assertEquals("wrong parent", "", driver.getParentByEdgeLabel ("graph2", "b2", "edge100"));

    }

    @Test
    public void blackBoxTest5(){
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        driver.createGraph("graph3");
        driver.addWhiteNode("graph3", "w1");
        driver.addWhiteNode("graph3", "w2");
        driver.addWhiteNode("graph3", "w3");
        driver.addBlackNode("graph3", "b1");
        driver.addBlackNode("graph3", "b2");
        driver.addBlackNode("graph3", "b3");
        driver.addEdge("graph3","b1", "w1", "edge1");  //valid
        driver.addEdge("graph3","b1", "w2", "edge2");  //valid
        driver.addEdge("graph3","b1", "w3", "edge3");  //valid
        driver.addEdge("graph3","b2", "w3", "edge4");  //valid
        driver.addEdge("graph3","b3", "w3", "edge4");  //invalid
        driver.addEdge("graph3","b3", "w3", "edge5");  //valid

        assertEquals("wrong black nodes", "b1 b2 b3", driver.listBlackNodes("graph3"));
        assertEquals("wrong white nodes", "w1 w2 w3", driver.listWhiteNodes("graph3"));
        assertEquals("wrong children", "w1 w2 w3", driver.listChildren ("graph3", "b1"));
        assertEquals("wrong parents", "b1 b2 b3", driver.listParents ("graph3", "w3"));
        assertEquals("wrong child", "b1", driver.getParentByEdgeLabel ("graph3", "w2", "edge2"));
        assertEquals("wrong parent", "w1", driver.getChildByEdgeLabel ("graph3", "b1", "edge1"));

        driver.removeNode("graph3", "b1");
        assertEquals("wrong black nodes", "b2 b3", driver.listBlackNodes("graph3"));
        assertEquals("wrong black nodes", "w1 w2 w3", driver.listWhiteNodes("graph3"));
        assertEquals("wrong parents", "b2 b3", driver.listParents ("graph3", "w3"));
        assertEquals("wrong parent", "b2", driver.getParentByEdgeLabel ("graph3", "w3", "edge4"));
        driver.removeEdge("graph3", "edge4", "b2", "w3");
        assertEquals("wrong children", "", driver.listChildren ("graph3", "b2"));
        assertEquals("wrong children", "b3", driver.listParents ("graph3", "w3"));
        assertEquals("wrong parent", "", driver.getParentByEdgeLabel ("graph3", "w3", "edge4"));

    }

    @Test
    public void blackBoxTest6(){
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        driver.createGraph("graph3");
        driver.addWhiteNode("graph3", "w1");
        driver.addWhiteNode("graph3", "w2");
        driver.addWhiteNode("graph3", "w3");
        driver.addBlackNode("graph3", "b1");
        driver.addBlackNode("graph3", "b2");
        driver.addBlackNode("graph3", "b3");
        driver.addEdge("graph3","w1", "b1", "edge1");  //valid
        driver.addEdge("graph3","b1", "w1", "edge10");  //valid
        driver.addEdge("graph3","b1", "w2", "edge2");  //valid
        driver.addEdge("graph3","b1", "w3", "edge3");  //valid
        driver.addEdge("graph3","b2", "w3", "edge4");  //valid
        driver.addEdge("graph3","b3", "w3", "edge4");  //invalid
        driver.addEdge("graph3","b3", "w3", "edge5");  //valid

        assertEquals("wrong black nodes", "b1 b2 b3", driver.listBlackNodes("graph3"));
        assertEquals("wrong black nodes", "w1 w2 w3", driver.listWhiteNodes("graph3"));
        assertEquals("wrong children", "w1 w2 w3", driver.listChildren ("graph3", "b1"));
        assertEquals("wrong parents", "b1 b2 b3", driver.listParents ("graph3", "w3"));
        assertEquals("wrong child", "b1", driver.getParentByEdgeLabel ("graph3", "w2", "edge2"));
        assertEquals("wrong parent", "w1", driver.getChildByEdgeLabel ("graph3", "b1", "edge10"));

        driver.removeNode("graph3", "w3");
        assertEquals("wrong black nodes", "b1 b2 b3", driver.listBlackNodes("graph3"));
        assertEquals("wrong black nodes", "w1 w2", driver.listWhiteNodes("graph3"));
        assertEquals("wrong children", "w1 w2", driver.listChildren ("graph3", "b1"));
        assertEquals("wrong parent", "w1", driver.getParentByEdgeLabel ("graph3", "b1", "edge1"));
        assertEquals("wrong children", "b1", driver.listChildren ("graph3", "w1"));
        assertEquals("wrong parents", "w1", driver.listParents ("graph3", "b1"));
        driver.removeEdge("graph3", "edge1", "w1", "b1");
        assertEquals("wrong children", "", driver.listChildren ("graph3", "w1"));
        assertEquals("wrong parents", "", driver.listParents ("graph3", "b1"));
        assertEquals("wrong parent", "", driver.getParentByEdgeLabel ("graph3", "b1", "edge1"));




    }


}