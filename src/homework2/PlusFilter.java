package homework2;

import java.util.Iterator;
import java.util.Map;

public class PlusFilter extends Node implements Simulatable {
    public PlusFilter(String label){
        super(label);
    }

    @Override
    public void simulate(BipartiteGraph graph) {
        int acc= 0;
        Iterator<Map.Entry<String,Node>> iter= this.incomingEdges.entrySet().iterator(); //traverse the node's parents
        while( iter.hasNext()) {
            if (iter.next().getValue() instanceof IntPipe) {
                if (!((IntPipe) iter.next().getValue()).isOutputListEmpty()) {
                    acc += ((IntPipe) iter.next().getValue()).getLastNumberInPipe();
                }
            }
        }
            if (!this.outgoingEdges.isEmpty()){  //put the result in the node's child
                Iterator<Map.Entry<String,Node>> iter2= this.outgoingEdges.entrySet().iterator();
                if (iter2.next().getValue() instanceof IntPipe){
                    ((IntPipe) iter2.next().getValue()).injectInput(acc);
                }
            }

        }


    private void checkRep(){
        assert (this.children.size()<=1): "PlusFilter has more than one output pipe!";
    }

}
