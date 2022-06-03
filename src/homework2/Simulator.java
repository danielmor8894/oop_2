package homework2;

import java.util.Iterator;
import java.util.Map;

public class Simulator <T>  extends BipartiteGraph<T>{

    private int round = 0;

    public void simulate() {
        Iterator<Map.Entry<T, Node>> iter = this.blackNodes.entrySet().iterator();  //pipes are black nodes
        while (iter.hasNext()) {
            ((Simulatable<?>) iter.next().getValue()).simulate((BipartiteGraph)this);

        }
        Iterator<Map.Entry<T, Node>> iter2 = this.whiteNodes.entrySet().iterator(); //filters are white nodes
        while (iter.hasNext()) {
            ((Simulatable<?>) iter.next().getValue()).simulate((BipartiteGraph)this);

        }
        round++;

    }

}
