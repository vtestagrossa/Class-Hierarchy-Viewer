/*
 * Vincent Testagrossa
 * Project 4: Graph Dependencies
 * 09JUL2022
 * 
 * Requirements: DFSActions, Hierarchy, ParenthesizedList.
 * 
 * Public Methods:
 *  addVertex(): Adds a vertex to the graph (vertices LinkedList)
 *  addEdge(): Adds the vertices to the graph if they don't exist, then adds the references to the adjacency lists.
 *  depthFirstSearch(): Performs a DFS on the graph and calls the proper actions for hierarch and paren.
 *  displayUnreachable(): Displays any unreachable vertices from a DFS.
 *  toString(): Converts the graph back to a string like the one provided in the input.
 * 
 * Builds a Linked List of special Vertex objects. Each Vertex contains an adjacencyList of Vertices that share a type with
 * the overall DirectedGraph object, a label, and marks for visited and discovered. The DirectedGraph itself has a hierarchy,
 * a ParenthesizedList, and a boolean value to detect whether or not a cycle is detected during a DFS. Adding a vertex is normally
 * done by adding edges, which will create the adjacencyList in addition to adding the vertex to the main graph. 
 */
package Project4;

import java.util.LinkedList;

public class DirectedGraph<T> {



    //List that holds the vertices
    LinkedList<Vertex> vertices = new LinkedList<Vertex>();
    Hierarchy<T> hierarch = new Hierarchy<T>();
    ParenthesizedList<T> paren = new ParenthesizedList<T>();
    boolean cycle;

    class Vertex{
        /*
         * A custom inner class to hold the data for each vertex
         * plus an adjacency list, and boolean values for whether
         * they're marked or discovered.
         */
        public T label; //Data for the vertex
        LinkedList<Vertex> adjacencyList = new LinkedList<Vertex>(); //Directional adjacency list
        Boolean visited = false;
        boolean discovered = false;
        Vertex(T label){
            this.label = label;
        }
    }

    private Vertex findVertex(T label){
        //Used to get  a reference to the location of a vertex in the vertices list.
        for (Vertex v : vertices){
            if (v.label.equals(label)){
                return v;
            }
        }
        return null;
    }
    private boolean containsVertex(T index){
        //helper method to check the vertices list (the graph) before adding another vertex.
        if (vertices.isEmpty()){
            return false;
        }
        for (Vertex v : vertices){
            if (v.label.equals(index)){
                return true;
            }
        }
        return false;
    }

    private void dfs(Vertex s){
        /*
         * Recursive method for a depth-first search. Checks if the vertex has already been discovered and, if true, marks a cycle as detected, then
         * calls the appropriate methods for the heirarchy and parenthesizedList classes, then returns. Then, each DFSActions class processes the vertex,
         * descends, and the vertex is marked as discovered and visited. Finally, the adjacencyList of the vertex is tested and if it's not empty, each
         * vertex in the list is processed by calling dfs(theVertex) before the ascend actions are called and the vertex is again marked as undiscovered.
         */
        if (s.discovered){
            cycle = true;
            hierarch.cycleDetected();
            paren.cycleDetected();
            return;
        }
        hierarch.processVertex(s);
        paren.processVertex(s);

        hierarch.descend(s);
        paren.descend(s);

        s.discovered = true;
        s.visited = true;

        if (!s.adjacencyList.isEmpty()){
            for (Vertex v : s.adjacencyList){
                dfs(v);
            }
        }

        hierarch.ascend(s);
        paren.ascend(s);

        s.discovered = false;
    }

    //Initialization logic for dfs.
    private void dfsInitialize(){
        for (Vertex v : vertices){
            v.discovered = false;
            v.visited = false;
        }
        cycle = false;
    }

    public void addVertex(T s){
        //Adds a vertex to the vertices LL.
        vertices.add(new Vertex(s));
    }

    public void addEdge(T source, T destination){
        //only add a new vertex for source if it's not already
        //in the LinkedList
        if (!containsVertex(source)){
            addVertex(source);
        }
        //Only add a new vertex for destination if it's not 
        //already in the LinkedList
        if (!containsVertex(destination)){
            addVertex(destination);
        }
        //search for the source in the vertices list,
        //then when found add the destination to the adjacency list.
        for (Vertex v : vertices){
            if (v.label.equals(source)){
                v.adjacencyList.add(findVertex(destination));
                break;
            }
        }
    }

    public void depthFirstSearch(){
        //initializes the DFS, and then call dfs on the first vertex in the vertices list.
        dfsInitialize();
        dfs(vertices.getFirst());
    }

    public void displayUnreachable(){
        //Performs a DFS to ensure the graph is properly initialized, then prints all the vertices that were not visited.
        String message = "";
        depthFirstSearch();
        for (Vertex v : vertices){
            if (!v.visited){
                message = v.label.toString() + " is unreachable.";
                System.out.println(message);
            }
        }
    }

    @Override
    public String toString(){
        //converts the Graph to a string for testing. Only prints vertices with adjacency
        //lists greater than 0.
        String rtn = "";
        for (Vertex v : vertices){
            if (v.adjacencyList.size() > 0){
                rtn += v.label + " ";
                for (Vertex element : v.adjacencyList){
                    rtn += element.label.toString() + " ";
                }
                rtn += "\n";

            }
        }
        return rtn;
    }

    
}
