import java.util.*;
import javafx.util.Pair;

public class Graph
{
    private ArrayList<Vertex> Vertices = new ArrayList<>();

    public Vertex addvertex(String id)
    {
        Vertex newvertex = new Vertex(id);
        Vertices.add(newvertex);
        return newvertex;
    }


    public Vertex getvertex(String s)
    {
        for(Vertex v : Vertices )
        {
            if (v.Name==s)
            {
                return v;
            }
        }
        return null;
    }

    public void newEdgeForSmallGraph(Vertex from, Vertex to, int dist, int time)                                        //Laver en metode, definere hvordan newEdge skal se ud for SmallGraph.
    {
        Edge newEdge = new Edge(from, to);                                                                              // Her oprettes et objekt, der hedder newEdge med parametrene "from" & "to".
        newEdge.distance = dist;                                                                                        // newEdge.distance bliver defineret til at være lig dist.
        newEdge.time = time;                                                                                            // newEdge.time bliver defineret til at være lig time.
    }

    public void newEdgeForBigGraph(Vertex from, Vertex to, int dist)                                                    //Laver en metode, definere hvordan newEdge skal se ud for BigGraph.
    {
        Edge newEdge=new Edge(from,to);                                                                                 // Her oprettes et objekt, der hedder newEdge med parametrene "from" & "to".
        newEdge.distance = dist;                                                                                        // newEdge.distance bliver defineret til at være lig dist.
    }

                                                                                                                        //Dijkstra for ShortestDistance starter
    public Pair<Integer, Map<Vertex,Vertex> > ShortestDistance(Vertex start, Vertex end)                                // Vi laver en Pair, hvor key er Integer og value er et map med værdierne Vertex og Vertex, kaldet ShortestDistance med parametrene "start" og "end" af typen Vertex.
    {
        Map<Vertex,Vertex> PredecessorMapForDist = new HashMap<>();                                                     // Vi laver en PredecessorMapForDist af typen HashMap der tager to Vertices
        Map<Vertex,Integer> DistanceMap = new HashMap<>();                                                              // Vi laver en DistanceMap af typen HashMap der tager en Vertex og en Integer
        Map<Vertex, Integer> TMap = new HashMap<>();                                                                    // Vi laver en TMap af typen HashMap der tager en Vertex og en Integer.
                                                                                                                        // DistanceMap behandler edges mens TMap behandler vertices. TMap er derudover en kopi af DistanceMap
        // initialize arrays

        for(Vertex v: Vertices)                                                                                         // For each løkke. For hver Vertex v kører vi igennem Vertices
        {
            DistanceMap.put(v,1000);                                                                                    //For hver Vertex v, sætter vi 1000 = simulation of infinity
            TMap.put(v,1000);                                                                                           //For hver Vertex v, sætter vi 1000
            PredecessorMapForDist.put(v, null);                                                                         //Anden parameter for PredecessorMapForDist sætter vi til null, grundet vi ikke har været nogen steder endnu i vores graf
        }

        DistanceMap.put(start, 0);                                                                                      //Edges     - De skal opdateres sammen hver gang
        TMap.put(start, 0);                                                                                             //Vertices  - De skal opdateres sammen hver gang

        for (int i = 0; i < Vertices.size() ; i++)                                                                      // Så længe i er mindre en Vertices.size, gør i større med en
        {
            Vertex current = getMinimum(TMap);                                                                              // Vi laver en Vertex der hedder current som vi sætter lig med vores funktion getMin som bliver brugt af TMap


            for (int j = 0; j < current.getOutEdges().size(); j++)                                                      // Så længe at j er mindre end vores nuværende OutEdge størrelse, gør j større med en
            {
                if (DistanceMap.get(current) + current.getOutEdges().get(j).distance <                                  // hvis vores current vertex + den current outEdge (distance) er mindre end vores næste vertex værdi gør...
                        DistanceMap.get(current.getOutEdges().get(j).getTovertex()))
                {
                    DistanceMap.put(current.getOutEdges().get(j).getTovertex(), DistanceMap.get(current) +              //... sæt vores næste vertex til at være lig med current vertex + current OutEdge i DistanceMap
                            current.getOutEdges().get(j).distance);

                    TMap.put(current.getOutEdges().get(j).getTovertex(), DistanceMap.get(current) +                     //... sæt vores næste vertex til at være lig med current vertex + current OutEdge i TMap
                            current.getOutEdges().get(j).distance);

                    PredecessorMapForDist.put(current.getOutEdges().get(j).getTovertex(),current);                      //... opdater vores current til at være lig med vores "tidligere" næste vertex værdi
                }
            }
            TMap.remove(current);                                                                                       //current fjernes fra TMap
        }
        return (new Pair<Integer,Map<Vertex,Vertex>> (DistanceMap.get(end), PredecessorMapForDist));                    // Vores Pair med key DistanceMap.get(end) og value PredecessorMapForDist bliver retuneret her
    }                                                                                                                   // Slut på ShortestDistance


    public Pair<Integer, Map<Vertex,Vertex> > ShortestTime(Vertex start, Vertex end)                                    //Dijkstra for ShortestTime - se noter ovenfor
    {
        Map<Vertex,Vertex> PredecessorMapForTime = new HashMap<>();
        Map<Vertex,Integer> TimeMap = new HashMap<>();
        Map<Vertex, Integer> TMap = new HashMap<>();

        for(Vertex v: Vertices)
        {
            TimeMap.put(v,1000);                                                                                        //1000 = simulation of infinity
            TMap.put(v,1000);
            PredecessorMapForTime.put(v, null);
        }

        TimeMap.put(start, 0);                                                                                          //Edges     - De skal opdateres sammen hver gang
        TMap.put(start, 0);                                                                                             //Vertices  - De skal opdateres sammen hver gang

        for (int i = 0; i < Vertices.size() ; i++)
        {
            Vertex current = getMinimum(TMap);

            for (int j = 0; j < current.getOutEdges().size(); j++)
            {
               if (TimeMap.get(current) + current.getOutEdges().get(j).time <
                       TimeMap.get(current.getOutEdges().get(j).getTovertex()))

                {
                    TimeMap.put(current.getOutEdges().get(j).getTovertex(), TimeMap.get(current) +
                            current.getOutEdges().get(j).time);

                    TMap.put(current.getOutEdges().get(j).getTovertex(), TimeMap.get(current) +
                            current.getOutEdges().get(j).time);

                    PredecessorMapForTime.put(current.getOutEdges().get(j).getTovertex(),current);
                }
            }
            TMap.remove(current);
        }
        return (new Pair<Integer,Map<Vertex,Vertex>> (TimeMap.get(end), PredecessorMapForTime));
    }


    public Vertex getMinimum(Map<Vertex,Integer> minimumTMap)                                                           // Vi laver en metode der kan retunere en vertex og kan bruge en map med Vertex og Integer.
    {
        Map.Entry<Vertex, Integer> minimumVertex = null;                                                                // Vi sætter vores minimumVertex til at være lig med null da den skal være null så der ikke bliver tilføjet noget til de værdier den skal tjekke
        for (Map.Entry<Vertex,Integer> entry : minimumTMap.entrySet())                                                  // For hver entry der er i vores entrySet, kører vi vores if statement
        {
            if (minimumVertex == null || minimumVertex.getValue() > entry.getValue())                                   // Hvis vores entry er større end den næste positions værdi...
            {
                minimumVertex = entry;                                                                                  // ...sæt den opdaterede minimumVertex lig med entry.
            }
        }

        return minimumVertex.getKey();                                                                                  //Returnere den Vertex, for hvilken værdi der er mindst
    }
}



//Denne klasse må IKKE ændres!
class Vertex
{
    String Name;
    ArrayList<Edge> OutEdges = new ArrayList<>();

    public  Vertex(String id)
    {
        Name=id;
    }

    public void addOutEdge(Edge outedge)
    {
        OutEdges.add(outedge);

    }

    public ArrayList<Edge> getOutEdges()
    {
        return OutEdges;
    }
}

//Denne klasse på IKKE ændres!
class Edge{
    private Vertex fromvertex;
    private Vertex tovertex;
    public int distance=0;
    public int time=0;

    public Vertex getTovertex() {
        return tovertex;
    }

    public Edge(Vertex from, Vertex to){
        fromvertex=from;
        tovertex=to;
        fromvertex.addOutEdge(this);
        //If not directional
        tovertex.addOutEdge(this);
    }
}

