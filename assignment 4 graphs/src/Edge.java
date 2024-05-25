
import java.util.Objects;

public class Edge<V> {
    private V source;
    private V dest;
    private Double weight;

    public Edge(V source, V dest, Double weight) {
        this.source = source;
        this.dest = dest;
        this.weight = weight;
    }

    public V getSource() {
        return source;
    }

    public V getDest() {
        return dest;
    }

    public Double getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?> edge = (Edge<?>) o;
        return Objects.equals(source, edge.source) && Objects.equals(dest, edge.dest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, dest);
    }
}
