package org.graph.pathfinder;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * @author Andrew.Arefyev@gmail.com
 * Date: 04.09.2019
 */
public class Edge implements Comparable<Edge> {
    @Getter
    private long weight = 1;

    @Getter
    private final Vertex source;

    @Getter
    private final Vertex destination;

    public Edge(Vertex source, Vertex destination) {
        this.source = source;
        this.destination = destination;
    }

    public Edge(@NotNull Vertex source, @NotNull Vertex destination, long weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return Objects.equal(source, edge.source) &&
                       Objects.equal(destination, edge.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(source, destination);
    }

    @Override
    public int compareTo(@NotNull Edge o) {
        return ComparisonChain.start()
                       .compare(source, o.source)
                       .compare(destination, o.destination)
                       .result();
    }
}
