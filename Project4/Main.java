/*
 * Vincent Testagrossa
 * Project 4: Graph Dependencies
 * 09JUL2022
 * 
 * Requirements: DirectedGraph.
 * 
 * Public Methods:
 * buildGraph(): takes a filename string and a graph object, and builds the graph from the provided filename, or throws an IOException
 * if the file isn't found.
 */
package Project4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

public class Main {
    public static void main(String[] args){
        //Starts the user in the Project4 directory with the supplied test_input.text selected
        JFileChooser selectedFile = new JFileChooser("Project4\\test_input.txt");
        selectedFile.showOpenDialog(null);
        //the filename
        String filename = selectedFile.getSelectedFile().toString();


        //The directed graph that will store all the vertices and edges
        DirectedGraph<String> graph = new DirectedGraph<String>();
        try{
            //if the file doesn't exist buildGraph will throw an IOException.
            buildGraph(filename, graph);
        }
        catch (IOException ex){
            System.out.println(ex.getMessage() + "\nThe program will now exit.");
            System.exit(0);
        }
        graph.depthFirstSearch();
        System.out.println(graph.paren.toString());
        System.out.println(graph.hierarch.toString());
        graph.displayUnreachable();
        System.exit(0); //terminates the program, since it's complete.
    }

    public static void buildGraph(String filename, DirectedGraph<String> graph) throws IOException{
        /*
         * Inputs: String filename, DirectedGraph<String> graph.
         * 
         * Takes a Digraph object and creates the vertices and edges from a file.
         */
        String source, destination;
        ArrayList<String[]> lnSpltFile = new ArrayList<String[]>();
        try{
            //bufferedreader is used to read line by line to add to the arraylist of string arrays.
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null){
                //process each line with the split method so an array of vertices is added to lnSplitFile
                lnSpltFile.add(line.split(" "));
            }
            reader.close();
        }
        catch(IOException ex){
            throw new IOException(ex.getMessage());
        }
        /*
         * Performs a foreach loop through the linesplit file ArrayList. Every line has been tokenized and the first
         * index of each line will be added as the source vertex for the remainder of the vertices on that line.
         */
        for (String[] line : lnSpltFile){
            for (int i = 1; i < line.length; i++){
                source = line[0].strip();
                destination = line[i].strip();
                graph.addEdge(source, destination);
            }
        }
    }
}
