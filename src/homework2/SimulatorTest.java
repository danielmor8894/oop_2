package homework2;

import static org.junit.Assert.*;
import org.junit.Test;


public class SimulatorTest {
    @Test
    public void blackBoxPlusFilter1(){
        SimulatorTestDriver driver= new SimulatorTestDriver();
        driver.createSimulator("simulator1");
        driver.addPipe("simulator1", "input1");
        driver.addPipe("simulator1", "input2");
        driver.addPipe("simulator1", "input3");
        driver.addPipe("simulator1", "output");
        driver.addPlusFilter("simulator1", "plusFilter1");
        driver.addEdge("simulator1", "input1","plusFilter1", "a1" );
        driver.addEdge("simulator1", "input2","plusFilter1", "a2" );
        driver.addEdge("simulator1", "input3","plusFilter1", "a3" );
        driver.addEdge("simulator1", "plusFilter1","output", "a4" );
        driver.injectInput("simulator1", "input1", 2);
        driver.injectInput("simulator1", "input1", 3);
        driver.injectInput("simulator1", "input1", 12);
        driver.injectInput("simulator1", "input1", 8);
        driver.injectInput("simulator1", "input2", 9);
        driver.injectInput("simulator1", "input2", 4);

        assertEquals("wrong input list", "8 12 3 2",
                    driver.listContents("simulator1","input1" ));
        assertEquals("wrong input list", "4 9",
                driver.listContents("simulator1","input2" ));
        assertEquals("wrong input list", "",
                driver.listContents("simulator1","input3" ));
        assertEquals("wrong output list", "",
                driver.listContents("simulator1","output" ));

        driver.simulate("simulator1");
        assertEquals("wrong input list", "8 12 3",
                driver.listContents("simulator1","input1" ));
        assertEquals("wrong input list", "4",
                driver.listContents("simulator1","input2" ));
        assertEquals("wrong input list", "",
                driver.listContents("simulator1","input3" ));
        assertEquals("wrong output list", "11",
                driver.listContents("simulator1","output" ));

        driver.simulate("simulator1");
        assertEquals("wrong input list", "8 12",
                driver.listContents("simulator1","input1" ));
        assertEquals("wrong input list", "",
                driver.listContents("simulator1","input2" ));
        assertEquals("wrong input list", "",
                driver.listContents("simulator1","input3" ));
        assertEquals("wrong output list", "7 11",
                driver.listContents("simulator1","output" ));

        driver.simulate("simulator1");
        assertEquals("wrong input list", "8",
                driver.listContents("simulator1","input1" ));
        assertEquals("wrong input list", "",
                driver.listContents("simulator1","input2" ));
        assertEquals("wrong input list", "",
                driver.listContents("simulator1","input3" ));
        assertEquals("wrong output list", "12 7 11",
                driver.listContents("simulator1","output" ));

        driver.simulate("simulator1");
        assertEquals("wrong input list", "",
                driver.listContents("simulator1","input1" ));
        assertEquals("wrong input list", "",
                driver.listContents("simulator1","input2" ));
        assertEquals("wrong input list", "",
                driver.listContents("simulator1","input3" ));
        assertEquals("wrong output list", "8 12 7 11",
                driver.listContents("simulator1","output" ));


    }

    @Test
    public void blackBoxGCDFilter1(){
        SimulatorTestDriver driver= new SimulatorTestDriver();
        driver.createSimulator("simulator2");
        driver.addPipe("simulator2", "APipe");
        driver.addPipe("simulator2", "BPipe");
        driver.addPipe("simulator2", "gcdPipe");
        driver.addGCDFilter("simulator2", "gcdFilter");
        driver.injectInput("simulator2", "APipe", 9);
        driver.injectInput("simulator2", "BPipe", 12);
        driver.addEdge("simulator2", "APipe", "gcdFilter", "a");
        driver.addEdge("simulator2", "BPipe", "gcdFilter", "b");
        driver.addEdge("simulator2", "gcdFilter", "APipe", "a");
        driver.addEdge("simulator2", "gcdFilter", "BPipe", "b");
        driver.addEdge("simulator2", "gcdFilter", "gcdPipe", "gcd");


        assertEquals("wrong input list", "9",
                driver.listContents("simulator2","APipe" ));
        assertEquals("wrong input list", "12",
                driver.listContents("simulator2","BPipe" ));
        assertEquals("wrong output list", "",
                driver.listContents("simulator2","gcdPipe" ));

        driver.simulate("simulator2");
        assertEquals("wrong input list", "12",
                driver.listContents("simulator2","APipe" ));
        assertEquals("wrong input list", "9",
                driver.listContents("simulator2","BPipe" ));
        assertEquals("wrong output list", "",
                driver.listContents("simulator2","gcdPipe" ));

        driver.simulate("simulator2");
        assertEquals("wrong input list", "9",
                driver.listContents("simulator2","APipe" ));
        assertEquals("wrong input list", "3",
                driver.listContents("simulator2","BPipe" ));
        assertEquals("wrong output list", "",
                driver.listContents("simulator2","gcdPipe" ));


        driver.simulate("simulator2");
        assertEquals("wrong input list", "3",
                driver.listContents("simulator2","APipe" ));
        assertEquals("wrong input list", "0",
                driver.listContents("simulator2","BPipe" ));
        assertEquals("wrong output list", "",
                driver.listContents("simulator2","gcdPipe" ));

        driver.simulate("simulator2");
        assertEquals("wrong input list", "",
                driver.listContents("simulator2","APipe" ));
        assertEquals("wrong input list", "",
                driver.listContents("simulator2","BPipe" ));
        assertEquals("wrong output list", "3",
                driver.listContents("simulator2","gcdPipe" ));

    }

    @Test
    public void blackBoxPlusFilter2(){
        SimulatorTestDriver driver= new SimulatorTestDriver();
        driver.createSimulator("simulator1");
        driver.addPipe("simulator1", "input1");
        driver.addPipe("simulator1", "input2");
        driver.addPipe("simulator1", "input3");
        driver.addPipe("simulator1", "output");
        driver.addPlusFilter("simulator1", "plusFilter1");
        driver.addEdge("simulator1", "input1","plusFilter1", "a1" );
        driver.addEdge("simulator1", "input2","plusFilter1", "a2" );
        driver.addEdge("simulator1", "input3","plusFilter1", "a3" );
        driver.addEdge("simulator1", "plusFilter1","output", "a4" );
        driver.injectInput("simulator1", "input1", 1);
        driver.injectInput("simulator1", "input1", 3);
        driver.injectInput("simulator1", "input1", 5);
        driver.injectInput("simulator1", "input2", 2);
        driver.injectInput("simulator1", "input3", 2);
        driver.injectInput("simulator1", "input3", 4);
        driver.injectInput("simulator1", "input3", 6);

        assertEquals("wrong input list", "5 3 1",
                driver.listContents("simulator1","input1" ));
        assertEquals("wrong input list", "2",
                driver.listContents("simulator1","input2" ));
        assertEquals("wrong input list", "6 4 2",
                driver.listContents("simulator1","input3" ));
        assertEquals("wrong output list", "",
                driver.listContents("simulator1","output" ));

        driver.simulate("simulator1");
        assertEquals("wrong input list", "5 3",
                driver.listContents("simulator1","input1" ));
        assertEquals("wrong input list", "",
                driver.listContents("simulator1","input2" ));
        assertEquals("wrong input list", "6 4",
                driver.listContents("simulator1","input3" ));
        assertEquals("wrong output list", "5",
                driver.listContents("simulator1","output" ));

        driver.simulate("simulator1");
        assertEquals("wrong input list", "5",
                driver.listContents("simulator1","input1" ));
        assertEquals("wrong input list", "",
                driver.listContents("simulator1","input2" ));
        assertEquals("wrong input list", "6",
                driver.listContents("simulator1","input3" ));
        assertEquals("wrong output list", "7 5",
                driver.listContents("simulator1","output" ));

        driver.simulate("simulator1");
        assertEquals("wrong input list", "",
                driver.listContents("simulator1","input1" ));
        assertEquals("wrong input list", "",
                driver.listContents("simulator1","input2" ));
        assertEquals("wrong input list", "",
                driver.listContents("simulator1","input3" ));
        assertEquals("wrong output list", "11 7 5",
                driver.listContents("simulator1","output" ));

    }

    @Test
    public void blackBoxPlusFilter3(){
        SimulatorTestDriver driver= new SimulatorTestDriver();
        driver.createSimulator("simulator1");
        driver.addPipe("simulator1", "input1");
        driver.addPipe("simulator1", "output");
        driver.addPlusFilter("simulator1", "plusFilter1");
        driver.addEdge("simulator1", "input1","plusFilter1", "a1" );
        driver.addEdge("simulator1", "plusFilter1","output", "a4" );
        assertEquals("wrong input list", "",
                driver.listContents("simulator1","input1" ));
        assertEquals("wrong output list", "",
                driver.listContents("simulator1","output" ));

        driver.simulate("simulator1");
        assertEquals("wrong input list", "",
                driver.listContents("simulator1","input1" ));
        assertEquals("wrong output list", "0",
                driver.listContents("simulator1","output" ));

        driver.simulate("simulator1");
        assertEquals("wrong input list", "",
                driver.listContents("simulator1","input1" ));
        assertEquals("wrong output list", "0 0",
                driver.listContents("simulator1","output" ));


    }

    @Test
    public void blackBoxGCDFilter2(){

        SimulatorTestDriver driver= new SimulatorTestDriver();
        driver.createSimulator("simulator2");
        driver.addPipe("simulator2", "APipe");
        driver.addPipe("simulator2", "BPipe");
        driver.addPipe("simulator2", "gcdPipe");
        driver.addGCDFilter("simulator2", "gcdFilter");
        driver.injectInput("simulator2", "APipe", 17);
        driver.injectInput("simulator2", "BPipe", 15);
        driver.addEdge("simulator2", "APipe", "gcdFilter", "a");
        driver.addEdge("simulator2", "BPipe", "gcdFilter", "b");
        driver.addEdge("simulator2", "gcdFilter", "APipe", "a");
        driver.addEdge("simulator2", "gcdFilter", "BPipe", "b");
        driver.addEdge("simulator2", "gcdFilter", "gcdPipe", "gcd");

        assertEquals("wrong input list", "17",
                driver.listContents("simulator2","APipe" ));
        assertEquals("wrong input list", "15",
                driver.listContents("simulator2","BPipe" ));
        assertEquals("wrong output list", "",
                driver.listContents("simulator2","gcdPipe" ));

        driver.simulate("simulator2");
        assertEquals("wrong input list", "15",
                driver.listContents("simulator2","APipe" ));
        assertEquals("wrong input list", "2",
                driver.listContents("simulator2","BPipe" ));
        assertEquals("wrong output list", "",
                driver.listContents("simulator2","gcdPipe" ));

        driver.simulate("simulator2");
        assertEquals("wrong input list", "2",
                driver.listContents("simulator2","APipe" ));
        assertEquals("wrong input list", "1",
                driver.listContents("simulator2","BPipe" ));
        assertEquals("wrong output list", "",
                driver.listContents("simulator2","gcdPipe" ));

        driver.simulate("simulator2");
        assertEquals("wrong input list", "1",
                driver.listContents("simulator2","APipe" ));
        assertEquals("wrong input list", "0",
                driver.listContents("simulator2","BPipe" ));
        assertEquals("wrong output list", "",
                driver.listContents("simulator2","gcdPipe" ));

        driver.simulate("simulator2");
        assertEquals("wrong input list", "",
                driver.listContents("simulator2","APipe" ));
        assertEquals("wrong input list", "",
                driver.listContents("simulator2","BPipe" ));
        assertEquals("wrong output list", "1",
                driver.listContents("simulator2","gcdPipe" ));


    }

    @Test
    public void blackBoxGCDfilter3(){
        SimulatorTestDriver driver= new SimulatorTestDriver();
        driver.createSimulator("simulator2");
        driver.addPipe("simulator2", "APipe");
        driver.addPipe("simulator2", "BPipe");
        driver.addPipe("simulator2", "gcdPipe");
        driver.addGCDFilter("simulator2", "gcdFilter");
        driver.injectInput("simulator2", "APipe", 100);
        driver.injectInput("simulator2", "BPipe", 100);
        driver.addEdge("simulator2", "APipe", "gcdFilter", "a");
        driver.addEdge("simulator2", "BPipe", "gcdFilter", "b");
        driver.addEdge("simulator2", "gcdFilter", "APipe", "a");
        driver.addEdge("simulator2", "gcdFilter", "BPipe", "b");
        driver.addEdge("simulator2", "gcdFilter", "gcdPipe", "gcd");

        assertEquals("wrong input list", "100",
                driver.listContents("simulator2","APipe" ));
        assertEquals("wrong input list", "100",
                driver.listContents("simulator2","BPipe" ));
        assertEquals("wrong output list", "",
                driver.listContents("simulator2","gcdPipe" ));

        driver.simulate("simulator2");
        assertEquals("wrong input list", "100",
                driver.listContents("simulator2","APipe" ));
        assertEquals("wrong input list", "0",
                driver.listContents("simulator2","BPipe" ));
        assertEquals("wrong output list", "",
                driver.listContents("simulator2","gcdPipe" ));

        driver.simulate("simulator2");
        assertEquals("wrong input list", "",
                driver.listContents("simulator2","APipe" ));
        assertEquals("wrong input list", "",
                driver.listContents("simulator2","BPipe" ));
        assertEquals("wrong output list", "100",
                driver.listContents("simulator2","gcdPipe" ));

    }

    @Test (expected = AssertionError.class)
    public void blackBoxGCDFilter4(){

        SimulatorTestDriver driver= new SimulatorTestDriver();
        driver.createSimulator("simulator2");
        driver.addPipe("simulator2", "APipe");
        driver.addPipe("simulator2", "gcdPipe");
        driver.addGCDFilter("simulator2", "gcdFilter");
        driver.addEdge("simulator2", "APipe", "gcdFilter", "a");
        driver.addEdge("simulator2", "gcdFilter", "APipe", "a");
        driver.addEdge("simulator2", "gcdFilter", "gcdPipe", "gcd");
        driver.simulate("simulator2");

    }

}
