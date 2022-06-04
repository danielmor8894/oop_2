package homework2;

import java.util.Iterator;
import java.util.Map;

public class PlusFilter<T> extends Node implements Simulatable {

    public PlusFilter(T label){
        super(label);
    }

    @Override
    public void simulate(BipartiteGraph graph) {
        checkRep();
        int acc= 0;
        Iterator<Map.Entry<String,Node>> iter= this.incomingEdges.entrySet().iterator(); //traverse the node's parents
        while( iter.hasNext()) {
            IntPipe input= (IntPipe) iter.next().getValue();
            if (!input.isOutputListEmpty()) {
                    acc += input.getLastNumberInPipe();
                }

        }
            if (!this.outgoingEdges.isEmpty()){  //put the result in the node's child
                Iterator<Map.Entry<String,Node>> iter2= this.outgoingEdges.entrySet().iterator();
                IntPipe output = (IntPipe) iter2.next().getValue();
                output.injectInput(acc);
            }
            checkRep();
        }


    private void checkRep(){
        assert (this.children.size()<=1 && this.outgoingEdges.size()<=1): "PlusFilter has more than one output pipe!";
    }

}
