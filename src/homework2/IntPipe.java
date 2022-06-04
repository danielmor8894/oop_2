package homework2;

import java.util.*;

public class IntPipe<T> extends Node implements Simulatable {

    private LinkedList<Integer> inputPipe= new LinkedList<>();
    private LinkedList<Integer> outputPipe= new LinkedList<>();


    public IntPipe(T label){
        super(label);
    }

    @Override
    public void simulate(BipartiteGraph graph) {
        Iterator it = this.inputPipe.descendingIterator();
        while (it.hasNext()){
            this.outputPipe.addFirst((Integer) it.next());
        }
        this.inputPipe.clear();

    }

    public void injectInput(int value){
        this.inputPipe.addFirst(value);
    }

    public LinkedList<Integer> getContent (){
        LinkedList<Integer> toRet= new LinkedList<>(this.inputPipe);
        LinkedList<Integer> toAppend= new LinkedList<>(this.outputPipe);
        toRet.addAll(toAppend);
        return toRet;
    }
    public int getLastNumberInPipe (){
        int toRet= this.outputPipe.removeLast();
        return toRet;
    }

    public boolean isOutputListEmpty(){
        return this.outputPipe.isEmpty();
    }


}
