package homework2;

public class GCDFilter extends Node implements Simulatable {

    public GCDFilter( String label){
        super(label);
    }

    @Override
    public void simulate(BipartiteGraph graph) {
        checkRep();
        int a=0, b= 0, gcd= 0;
        IntPipe aInputPipe= (IntPipe) this.incomingEdges.get("a");
        IntPipe bInputPipe= (IntPipe) this.incomingEdges.get("b");
        IntPipe gcdOutputPipe= ((IntPipe) this.outgoingEdges.get("gcd"));
        IntPipe aOutputPipe= ((IntPipe) this.outgoingEdges.get("a"));
        IntPipe bOutputPipe= ((IntPipe) this.outgoingEdges.get("b"));

        if (aInputPipe.isOutputListEmpty() || (bInputPipe.isOutputListEmpty())){
                return;
            }
        else{
                a= aInputPipe.getLastNumberInPipe();
                b= bInputPipe.getLastNumberInPipe();
            }
        //Euclid Algorithm
        if (b==0){
            ((IntPipe) this.outgoingEdges.get("gcd")).injectInput(a);
        }
        else if(a<b){
            ((IntPipe) this.outgoingEdges.get("a")).injectInput(b);
            ((IntPipe) this.outgoingEdges.get("b")).injectInput(a);

        }
        else{
            ((IntPipe) this.outgoingEdges.get("a")).injectInput(b);
            ((IntPipe) this.outgoingEdges.get("b")).injectInput(a%b);

        }

    }

    private void checkRep(){
        assert (this.children.size()==3): "filter doesn't have 3 outgoing edges";
        assert (this.parents.size()==2): "filter doesn't have 2 incoming edges";
        assert (this.incomingEdges.containsKey("a")&& (this.incomingEdges.containsKey("b"))): "wrong edge labels";
        assert (this.outgoingEdges.containsKey("a") && this.outgoingEdges.containsKey("b")&&
                this.outgoingEdges.containsKey("gcd")): "wrong edge labels";

    }
}
