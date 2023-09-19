/*
 * Vincent Testagrossa
 * Project 4: Graph Dependencies
 * 09JUL2022
 * 
 * Requirements: DFSActions.
 * 
 * Public Methods:
 *  cycleDetected(): gereric interface method for when a cycle is detected.
 *  processVertex(): gereric interface method for processing a vertex.
 *  descend(): gereric interface method to descend down a DFS.
 *  ascend(): gereric interface method to ascend back up in a DFS.
 *  toString(): produces a string in parenthesized list format.
 * 
 * Uses a Queue<String> to build a parenthesizedList based on the actions of a depth-first search.
 */
package Project4;

import java.util.LinkedList;
import java.util.Queue;

public class ParenthesizedList<T> implements DFSActions<DirectedGraph<T>.Vertex> {
    private Queue<String> parenQueue = new LinkedList<String>();
    @Override
    public void cycleDetected() {
        //Adds an asterisk when a cycle is detected.
        parenQueue.add("*");
        
    }

    @Override
    public void processVertex(DirectedGraph<T>.Vertex v) {
        //Adds the vertex label to the queue
        parenQueue.add(v.label.toString());
    }

    @Override
    public void descend(DirectedGraph<T>.Vertex v) {
        //Adds an open paren to signify the DFS has descended.
        parenQueue.add("(");
        
    }

    @Override
    public void ascend(DirectedGraph<T>.Vertex v) {
        //Adds a close paren to signify the ascend action has been taken.
        parenQueue.add(")");
    }


    @Override
    public String toString(){
        /*
         * Processes the queue into a rtn String. Starts with an open paren since the first object in the directed graph is accessed via the head of the 
         * vertices LL. Adds all the appropriate spacing and then replaces empty descent and ascent actions with "", and cycle detected descents with "*".
         */
        String rtn = "( ";
        for (String s : parenQueue){
            rtn += s + " ";
        }
        rtn = rtn.replace("( * )", "*");
        rtn = rtn.replace("( )", "");
        return rtn + ")";
    }
}
