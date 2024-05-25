

import java.util.*;

public class DijkstraSearch<V> extends Search<V> {
    private final Set<V> unsettledNodes;
    private final Map<V, Double> distances;
    private final WeightedGraph<V> graph;

    public DijkstraSearch(WeightedGraph<V> graph, V source) {
        super(source);
        unsettledNodes = new HashSet<>();
        distances = new HashMap<>();
        this.graph = graph;
        dijkstra();
    }

    private void dijkstra() {
        distances.put(source, 0D);
        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            V currentNode = getVertexWithMinimumWeight(unsettledNodes);
            marked.add(currentNode);
            unsettledNodes.remove(currentNode);

            for (V neighbor : graph.adjacencyList(currentNode)) {
                double newDistance = getShortestDistance(currentNode) + getDistance(currentNode, neighbor);
                if (getShortestDistance(neighbor) > newDistance) {
                    distances.put(neighbor, newDistance);
                    edgeTo.put(neighbor, currentNode);
                    unsettledNodes.add(neighbor);
                }
            }
        }
    }

    private double getDistance(V node, V target) {
        for (Edge<V> edge : graph.getEdges(node)) {
            if (edge.getDest().equals(target)) return edge.getWeight();
        }
        throw new RuntimeException("Not found!");
    }

    private V getVertexWithMinimumWeight(Set<V> vertices) {
        V minimum = null;
        for (V vertex : vertices) {
            if (minimum == null || getShortestDistance(vertex) < getShortestDistance(minimum)) {
                minimum = vertex;
            }
        }
        return minimum;
    }

    private double getShortestDistance(V destination) {
        Double d = distances.get(destination);
        return (d == null ? Double.MAX_VALUE : d);
    }
}
