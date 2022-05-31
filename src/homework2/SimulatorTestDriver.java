package homework2;

import java.util.*;

/**
 * This class implements a testing driver for Simulator. The driver
 * manages Simulators for integer arithmetic.
 */
public class SimulatorTestDriver {

    private Map<String, Simulator<String>> simulators;

    /**
     * @modifies this
     * @effects Constructs a new test driver.
     */
    public SimulatorTestDriver () {
    	// TODO: Implement this constructor

       
    }


    /**
     * @requires simName != null
     * @modifies this
     * @effects Creates a new simulator named simName. The simulator's
     * 			graph is initially empty.
     */
    public void createSimulator(String simName) {
        // TODO: Implement this method

    	
    }


    /**
     * @requires createSimulator(simName)
     *           && pipeName != null
     *  		 && pipeName has not been used in a previous addPipe(),
     * 				addPlusFilter(), or addGCDFilter() call on this object
     * @modifies simulator named simName
     * @effects Creates a new IntPipe named by the String pipeName and add it
     * 			to the simulator named simName.
     */
    public void addPipe(String simName, String pipeName) {
    	// TODO: Implement this method

    	
    }


    /**
     * @requires createSimulator(simName)
     *           && filterName != null
     *  		 && filterName has not been used in a previous addPipe(),
     * 				addPlusFilter(), or addGCDFilter() call on this object
     * @modifies simulator named simName
     * @effects Creates a new PlusFilter named by the String filterName and
     * 			add it to the simulator named simName.
     */
    public void addPlusFilter(String simName, String filterName) {
    	// TODO: Implement this method

    	
    }


    /**
     * @requires createSimulator(simName)
     *           && filterName != null
     *  		 && filterName has not been used in a previous addPipe(),
     * 				addPlusFilter(), or addGCDFilter() call on this object
     * @modifies simulator named simName
     * @effects Creates a new GCDFilter named by the string filterName and
     * 			add it to the simulator named simName.
     */
    public void addGCDFilter(String simName, String filterName) {
    	// TODO: Implement this method

    	
    }


    /**
     * @requires createSimulator(simName)
     *           && ((addPipe(parentName) && addFilter(childName))
     *              || (addFilter(parentName) && addPipe(childName)))
     *           && edgeLabel != null
     *           && node named parentName has no other outgoing edge labeled
     * 				edgeLabel
     *           && node named childName has no other incoming edge labeled
     * 				edgeLabel
     * @modifies simulator named simName
     * @effects Adds an edge from the node named parentName to the node named
     * 			childName in the simulator named simName. The new edge's
     * 			label is the String	edgeLabel.
     */
    public void addEdge(String simName,
    					String parentName, String childName,
                        String edgeLabel) {
    	//TODO: Implement this method

    	
    }


    /**
     * @requires createSimulator(simName)
     * 			 && addPipe(pipeName)
     * @modifies pipe named pipeName
     * @effects pushes the integer value into the pipe named pipeName in the
     * 			simulator named simName.
     */
    public void injectInput(String simName, String pipeName, int value) {
    	//TODO: Implement this method

    	
    }


    /**
     * @requires addPipe(pipeName)
     * @return a space-separated list of the integer values currently in the
     * 		   pipe named pipeName in the simulator named simName.
     */
    public String listContents(String simName, String pipeName) {
    	//TODO: Implement this method

    	
    }

    /**
     * @requires createSimulator(simName)
     * @modifies simulator named simName
     * @effects runs simulator named simName for a single time slice.
     */
    public void simulate(String simName) {
    	//TODO: Implement this method
    	
    	
    }
}
