package homework2;

import static org.junit.Assert.*;
import org.junit.Test;


public class SimulatorTest {
    @Test
    public void blackBoxPlusFilter(){
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
    public void blackBoxGCDFilter(){
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

}
