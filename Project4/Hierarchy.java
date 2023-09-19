/*
 * Vincent Testagrossa
 * Project 4: Graph Dependencies
 * 09JUL2022
 * 
 * Requirements: DFSActions.
 * 
 * Public Methods:
 *  cycleDetected(): adds an asterisk to the queue.
 *  processVertex(): adds the label to the queue with the appropriate spacing.
 *  descend(): increases the tab count.
 *  ascend(): decreases the tab count.
 *  toString(): produces the Hierarchy string for a DirectedGraph object.
 * 
 * Uses a Queue<String> to create a hierarchy string for a DirectedGraph object. Each action builds the queue and tabulation
 * is done with tabCount, which will indent, or unindent each label based on where the graph is in the depth-first search.
 */
package Project4;

import java.util.LinkedList;
import java.util.Queue;

public class Hierarchy<T> implements DFSActions<DirectedGraph<T>.Vertex> {

    private Queue<String> hierQueue = new LinkedList<String>();
    private int tabCount = 0;
    @Override
    public void cycleDetected() {
        //Adds an asterisk to the queue if a cycle is detected.
        hierQueue.add("*");
        
    }

    @Override
    public void processVertex(DirectedGraph<T>.Vertex v) {
        //Inserts a label into the queue with the appropriate indentation level.
        String tabs = "";
        for (int i = 0; i < tabCount; i++){
            tabs += "    ";
        }
        hierQueue.add(tabs + v.label.toString());
        
    }

    @Override
    public void descend(DirectedGraph<T>.Vertex v) {
        //Increases the tab count because the DFS has descended.
        tabCount++;
        
    }

    @Override
    public void ascend(DirectedGraph<T>.Vertex v) {
        //Decreases the tab count because the DFS has ascended
        tabCount--;
        
    }

    @Override
    public String toString(){
        /*
         * Builds a rtn String by looping through the heirQueue. Uses nln to keep track of the newline character (skips the first iteration).
         * Checks the strings to see if they are asterisks and puts a space in between them and the previous queued item. If the string isn't
         * an asterisk, then appends a newline character and the properly tabbed string containing the vertex label.
         */
        String rtn = "", nln = "";
        for (String s : hierQueue){
            if (s.equals("*")){
                rtn += " " + s;
            }
            else{
                rtn += nln + s;
            }
            nln = "\n";
        }
        return rtn;
    }
    
}
