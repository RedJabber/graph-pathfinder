package org.graph.pathfinder;

import java.util.List;
import lombok.NonNull;
import org.graph.pathfinder.exceptions.GraphException;

/**
 * @author Andrew.Arefyev@gmail.com
 * Date: 04.09.2019
 * Time: 1:23
 */
public interface GraphWithPathfinder {
    @NonNull
    Vertex addVertex();

    /**
     *
     * @param source first point
     * @param destination second point
     * @return registered edge
     */
    @NonNull
    Edge addEdge(Vertex source, Vertex destination) throws GraphException;

//    @NonNull
//    Edge addWeightedEdge(Vertex source, Vertex destination, long weight);

    @NonNull
    List<Edge> getPath(Vertex source, Vertex destination);
}
