package homework2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class Simulator <T>  extends BipartiteGraph<T>{

    private int round = 0;

    public void simulate() {
        Iterator<Map.Entry<T, Node>> iter = this.blackNodes.entrySet().iterator();  //pipes are black nodes
        while (iter.hasNext()) {
            ((Simulatable<?>) iter.next().getValue()).simulate((BipartiteGraph)this);

        }
        Iterator<Map.Entry<T, Node>> iter2 = this.whiteNodes.entrySet().iterator(); //filters are white nodes
        while (iter2.hasNext()) {
            ((Simulatable<?>) iter2.next().getValue()).simulate((BipartiteGraph)this);

        }
        round++;

    }

    public void addPipe (T nodeLabel){
        if (nodeLabel==null){
            return;
        }
        IntPipe newPipe= new IntPipe(nodeLabel);
        if ((!this.blackNodes.containsKey(nodeLabel))&& (!this.whiteNodes.containsKey(nodeLabel))){
            this.blackNodes.put(nodeLabel, newPipe);
        }
    }


    public void addPlusFilter (T nodeLabel){
        if (nodeLabel==null){
            return;
        }
        PlusFilter newPlusFilter= new PlusFilter(nodeLabel);
        if ((!this.blackNodes.containsKey(nodeLabel))&& (!this.whiteNodes.containsKey(nodeLabel))){
            this.whiteNodes.put(nodeLabel, newPlusFilter);
        }
    }

    public void addGCDFilter (T nodeLabel){
        if (nodeLabel==null){
            return;
        }
        GCDFilter newGCDFilter= new GCDFilter(nodeLabel);
        if ((!this.blackNodes.containsKey(nodeLabel))&& (!this.whiteNodes.containsKey(nodeLabel))){
            this.whiteNodes.put(nodeLabel, newGCDFilter);
        }
    }

    public void injectPipeInput (T pipeName, int value){
        if( !this.blackNodes.containsKey(pipeName)){
            return;
        }
        else{
            IntPipe pipe= (IntPipe) this.blackNodes.get(pipeName);
            pipe.injectInput(value);
        }
    }

    public LinkedList<Integer> getContentOfPipe(T pipeName){
        if( !this.blackNodes.containsKey(pipeName)){
            return null;
        }
        else{
            IntPipe pipe= (IntPipe) this.blackNodes.get(pipeName);
            return pipe.getContent();
        }
    }

}
