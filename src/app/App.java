package app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import gui.Gui;
import json_import.EdgeImport;
import json_import.Result;
import json_import.VertexImport;
import json_import_avs.MeshNode;
import json_import_avs.UnityMapStructure;


/**
 * @author Philipp Schmeier
 */
public class App {

    public static void start() {
        System.out.println("Start - Graphprogramm");

        // init Graph
        Graph graph = new Graph();

        // load data from JSON-File
        Gson gson = new Gson();

		readAlgoFile(graph, gson);

        // init GUI
        Gui gui = new Gui(graph);
        gui.setVisible(true);
        gui.revalidate();


        System.out.println("End - Graphprogramm");
    }


    private static void readAlgoFile(Graph graph, Gson gson) {
        BufferedReader br = null;
        try {
            // graph11 und 12 sind zu groß und können nicht mehr geladen werden
            // graph4_16 graph7_128 graph10_1024
            br = new BufferedReader(new FileReader("graph4_16.json"));

            Result res = gson.fromJson(br, Result.class);


            if (res != null) {
                for (VertexImport v : res.getVertices()) {
                    graph.addVertex(new Vertex(v.getId(), new Position(v.getPosition().getX(), v.getPosition().getY())));
                }

                for (EdgeImport e : res.getEdges()) {
                    Vertex from = graph.getVertices().get(e.getStart());
                    Vertex to = graph.getVertices().get(e.getEnd());
                    int a = Math.abs(from.getPosition().x - to.getPosition().x);
                    int b = Math.abs(from.getPosition().y - to.getPosition().y);
                    int weight = (int) Math.sqrt((a * a) + (b * b));

                    Edge edge = new Edge(e.getId(), from, to, weight);

                    if (!graph.edgeExists(edge))
                        graph.addEdge(edge);
                }

                // print A* schätzwerte
//				int targetID = 4;
//				Vertex target = graph.getVertex(targetID);
//				int x = target.getPosition().x;
//				int y = target.getPosition().y;
//
//				for (Vertex v : graph.getVertices()) {
//					if( v.getId() == targetID ) continue;
//
//					int a = Math.abs(x - v.getPosition().x);
//					int b = Math.abs(y - v.getPosition().y);
//					int length = (int) Math.sqrt((a*a) + (b*b));
//
//					System.out.println("ID: " + v.getId() + " : " + length);
//				}

                graph.printAdjacentList();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
