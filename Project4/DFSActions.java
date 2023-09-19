/*
 * Vincent Testagrossa
 * Project 4: Graph Dependencies
 * 09JUL2022
 * 
 * Requirements: None.
 * 
 * Public Methods:
 *  cycleDetected(): gereric interface method for when a cycle is detected.
 *  processVertex(): gereric interface method for processing a vertex.
 *  descend(): gereric interface method to descend down a DFS.
 *  ascend(): gereric interface method to ascend back up in a DFS.
 * 
 * Defines the generic interface for each type of list to be created by a depth-first-search.
 */
package Project4;


public interface DFSActions<T> {
    public void cycleDetected();
    public void processVertex(T data);
    public void descend(T data);
    public void ascend(T data);
    
}
