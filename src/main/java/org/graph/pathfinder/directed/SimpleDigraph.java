package org.graph.pathfinder.directed;

import java.util.List;
import lombok.NonNull;
import org.graph.pathfinder.Edge;
import org.graph.pathfinder.GraphWithPathfinder;
import org.graph.pathfinder.Vertex;
import org.graph.pathfinder.exceptions.GraphException;

/**
 * @author Andrew.Arefyev@gmail.com
 * Date: 04.09.2019
 * Time: 3:03
 */
public class SimpleDigraph implements GraphWithPathfinder {
    @Override
    public @NonNull Vertex addVertex() {
        return null;
    }

    /**
     *
     * @param source source point
     * @param destination destination point
     * @return
     */
    @Override
    public @NonNull Edge addEdge(final Vertex source, final Vertex destination) throws GraphException {
        return null;
    }

    @Override
    public @NonNull List<Edge> getPath(final Vertex source, final Vertex destination) {
        return null;
    }
}
