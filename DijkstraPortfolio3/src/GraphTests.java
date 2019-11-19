/***
 * Portfolie 3: Dijkstra's Algorithm
 * Udarbejdet af: Hassan Ahmad,
 * Maria Broe Nyberg Pedersen,
 * Mira-Louise Lyster Laustsen,
 * Morten Jensen,
 * Rebecca Veggerby og
 * Valbona Sinani
 * Kursusansvarlig: Line Reinhardt
 * Afleveringsdato: 21-11-19
 */

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.Map;

public class GraphTests
{
    public static void main(String[] args)
    {
        // Create graph
        GraphTests TestGraph = new GraphTests();

        Graph g = TestGraph.MakeSmallGraph();                                                                           //Vi opretter et objekt af typen Graph kaldet g
        Graph newGraph = TestGraph.MakeBigGraph();                                                                      //Vi opretter et objekt af typen Graph kaldet newGraph

        Vertex start = g.getvertex("A");                                                                             //Vi sætter startspositionen (start) til at være Vertex A i vores graf g
        Vertex end = g.getvertex("F");                                                                               //Vi sætter slutspositionen (end) til at være Vertex F i vores graf g

        Vertex newStart = newGraph.getvertex("10");                                                                  //Vi sætter startspositionen (newStart) til at være Vertex 10 i vores graf newGraph
        Vertex newEnd = newGraph.getvertex("6");                                                                     //Vi sætter slutposition (newEnd) til at være Vertex 6 i vores graf newGraph

        Pair<Integer, Map<Vertex, Vertex>> resultsForTime = g.ShortestTime(start, end);                                 //Vi laver et Pair for slutresultatet af grafen g.ShortestTime som indeholder start og end
        Pair<Integer, Map<Vertex, Vertex>> resultsForDist = g.ShortestDistance(start, end);                             //Vi laver et Pair for slutsresultatet af grafen g.ShortestDistance som indeholder start og end
        Pair<Integer, Map<Vertex, Vertex>> resultsForNewDist = newGraph.ShortestDistance(newStart, newEnd);             //Vi laver et Pair for slutsresultatet af grafen newGraph.ShortestDistance som indeholder newStart og newEnd

        Vertex currentDist = end;                                                                                       //Vi sætter Vertex currentDist til at være lig med end
        Vertex currentTime = end;                                                                                       //Vi sætter Vertex currentTime til at være lige med end
        Vertex newCurrentDist = newEnd;                                                                                 //Vi sætter Vertex newCurrentDist til at være lige med newEnd

        ArrayList<Vertex> PathDist = new ArrayList<>();                                                                 // Vi laver en ArrayList som kan indeholde Vertices.
        PathDist.add(end);                                                                                              // Vi tilføjer vores slutsposition til PathDist

        ArrayList<Vertex> PathTime = new ArrayList<>();                                                                 //Vi laver en ArrayList som kan indeholde Vertices.
        PathTime.add(end);                                                                                              //Vi tilføjer vores slutsposition til PathTime

        ArrayList<Vertex> newPathDist = new ArrayList<>();                                                              //Vi laver en ArrayList som kan indeholde Vertices.
        newPathDist.add(newEnd);                                                                                        //Vi tilføjer vores slutposition til newPathDist

        System.out.println("This is the shortest path for distance: ");                                                 //Vi printer ud

        while ((currentDist != start) && (resultsForDist.getValue().get(currentDist) != null))                          //Mens vores currentDist ikke er lig med start og resultatsværdien for den nuværende distance ikke er lig med null er skal...
        {
            currentDist = resultsForDist.getValue().get(currentDist);                                                   //currentDist bliver sat lig med resultatsværdien for den nuværende distance
            PathDist.add(0, currentDist);                                                                         //Vi tilføjer currentdist til index nul i PathDist. (da while loopen fortsætter til vi er nået end)
        }

        for (Vertex v : PathDist)                                                                                       //For hver v i PathDist gør...
        {
            System.out.print(v.Name);                                                                                   // ...Print vertex navnet (A, B, C...)

            if (v != end)                                                                                               // Hvis vertex ikke er nået end...
            {
                System.out.print("->");                                                                                 // ...Print en pil.
            }
        }

        System.out.println("");
        System.out.println("");
        System.out.println("This is the shortest path for time: ");                                                     //Se noter ovenfor
        while((currentTime != start) && (resultsForTime.getValue().get(currentTime) != null))
        {
            currentTime = resultsForTime.getValue().get(currentTime);
            PathTime.add(0, currentTime);
        }

        for (Vertex v : PathTime)
        {
            System.out.print(v.Name);
            if (v != end)
            {
                System.out.print("->");
            }
        }
        System.out.println();
        System.out.println();

        System.out.println("This is our new END! ");
        while ((newCurrentDist != newStart) && (resultsForNewDist.getValue().get(newCurrentDist) != null))
        {
            newCurrentDist = resultsForNewDist.getValue().get(newCurrentDist);
            newPathDist.add(0, newCurrentDist);
        }

        for (Vertex v : newPathDist)
        {
            System.out.print(v.Name);
            if (v != newEnd)
            {
                System.out.print("->");
            }
        }

    }


    public Graph MakeSmallGraph()                                                                                       //Graf givet af Line
    {
        Graph myGraph = new Graph();

        final Vertex A = myGraph.addvertex("A");
        final Vertex B = myGraph.addvertex("B");
        final Vertex C = myGraph.addvertex("C");
        final Vertex D = myGraph.addvertex("D");
        final Vertex E = myGraph.addvertex("E");
        final Vertex F = myGraph.addvertex("F");

        myGraph.newEdgeForSmallGraph(A, B, 1, 2);
        myGraph.newEdgeForSmallGraph(A, C, 5, 1);
        myGraph.newEdgeForSmallGraph(A, D, 4, 6);
        myGraph.newEdgeForSmallGraph(B, C, 3, 2);
        myGraph.newEdgeForSmallGraph(B, D, 2, 3);
        myGraph.newEdgeForSmallGraph(B, E, 2, 4);
        myGraph.newEdgeForSmallGraph(C, F, 1, 8);
        myGraph.newEdgeForSmallGraph(C, E, 2, 2);
        myGraph.newEdgeForSmallGraph(D, F, 2, 7);
        myGraph.newEdgeForSmallGraph(E, F, 3, 6);

        return myGraph;
    }

    public Graph MakeBigGraph()                                                                                         // Graf givet af portfolieopgaven
    {
        Graph bigGraph = new Graph();

        final Vertex v1 =   bigGraph.addvertex("1");
        final Vertex v2 =   bigGraph.addvertex("2");
        final Vertex v3 =   bigGraph.addvertex("3");
        final Vertex v4 =   bigGraph.addvertex("4");
        final Vertex v5 =   bigGraph.addvertex("5");
        final Vertex v6 =   bigGraph.addvertex("6");
        final Vertex v7 =   bigGraph.addvertex("7");
        final Vertex v8 =   bigGraph.addvertex("8");
        final Vertex v9 =   bigGraph.addvertex("9");
        final Vertex v10 =  bigGraph.addvertex("10");

        bigGraph.newEdgeForBigGraph(v1, v2, 10);
        bigGraph.newEdgeForBigGraph(v1, v4, 20);
        bigGraph.newEdgeForBigGraph(v1, v5, 20);
        bigGraph.newEdgeForBigGraph(v1, v6, 5);
        bigGraph.newEdgeForBigGraph(v1, v7, 15);

        bigGraph.newEdgeForBigGraph(v2, v3, 5);
        bigGraph.newEdgeForBigGraph(v2, v4, 10);

        bigGraph.newEdgeForBigGraph(v3, v2, 15);
        bigGraph.newEdgeForBigGraph(v3, v4, 5);

        bigGraph.newEdgeForBigGraph(v4, v5, 10);

        bigGraph.newEdgeForBigGraph(v5, v6, 5);

        bigGraph.newEdgeForBigGraph(v7, v6, 10);

        bigGraph.newEdgeForBigGraph(v8, v1, 5);
        bigGraph.newEdgeForBigGraph(v8, v2, 20);
        bigGraph.newEdgeForBigGraph(v8, v7, 5);

        bigGraph.newEdgeForBigGraph(v9, v2, 15);
        bigGraph.newEdgeForBigGraph(v9, v8, 20);
        bigGraph.newEdgeForBigGraph(v9, v10, 10);

        bigGraph.newEdgeForBigGraph(v10, v2, 5);
        bigGraph.newEdgeForBigGraph(v10, v3, 15);


     return bigGraph;
    }

}


