
import java.util.*;

public class WeightedGraph<V> {
    private final boolean undirected;
    private final Map<V, List<Edge<V>>> map = new HashMap<>();

    public WeightedGraph() {
        this(true);
    }

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public void addVertex(V v) {
        if (hasVertex(v)) return;
        map.put(v, new LinkedList<>());
    }

    public void addEdge(V source, V dest, double weight) {
        if (!hasVertex(source)) addVertex(source);
        if (!hasVertex(dest)) addVertex(dest);
        if (hasEdge(source, dest) || source.equals(dest)) return; // reject parallels & self-loops

        map.get(source).add(new Edge<>(source, dest, weight));
        if (undirected) map.get(dest).add(new Edge<>(dest, source, weight));
    }

    public void addEdge(V source, V dest) {
        addEdge(source, dest, 1.0); // default weight for unweighted edges
    }

    public boolean hasVertex(V v) {
        return map.containsKey(v);
    }

    public boolean hasEdge(V source, V dest) {
        if (!hasVertex(source)) return false;
        return map.get(source).contains(new Edge<>(source, dest, 1.0)); // default weight for comparison
    }

    public List<V> adjacencyList(V v) {
        if (!hasVertex(v)) return null;
        List<V> vertices = new LinkedList<>();
        for (Edge<V> e : map.get(v)) {
            vertices.add(e.getDest());
        }
        return vertices;
    }

    public Iterable<Edge<V>> getEdges(V v) {
        if (!hasVertex(v)) return null;
        return map.get(v);
    }
}
