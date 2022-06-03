package homework2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node <T>{

    T label;
    Map<T, Node> incomingEdges= new HashMap<>();
    Map<T, Node> outgoingEdges= new HashMap<>();
    List<T> parents= new ArrayList<>();
    List<T> children= new ArrayList<>();
    public Node(T label){
        this.label= label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;

        }
        Node<T> o1= (Node<T>)o;
        return (this.label.equals(o1.label));

    }

    @Override
    public int hashCode(){
        return this.label.hashCode();
    }

}

