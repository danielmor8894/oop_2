package homework2;

import java.util.*;

public class IntPipe extends Node<String> implements Simulatable {

    private List<Integer> inputPipe= new ArrayList<Integer>();
    private List<Integer> outputPipe= new ArrayList<Integer>();


    public IntPipe(String label){
        super(label);
    }

    @Override
    public void simulate(BipartiteGraph graph) {
        Iterator it = this.inputPipe.iterator();
        while (it.hasNext()){
            this.outputPipe.add((Integer) it.next());
        }
        this.inputPipe.clear();

    }

    public void injectInput(int value){
        this.inputPipe.add(value);
    }
}
