package org.graph.pathfinder.undirected;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import org.graph.pathfinder.Edge;
import org.graph.pathfinder.GraphWithPathfinder;
import org.graph.pathfinder.Vertex;
import org.graph.pathfinder.exceptions.GraphException;
import org.graph.pathfinder.exceptions.LoopEdgeException;

/**
 * @author Andrew.Arefyev@gmail.com
 * Date: 04.09.2019
 * Time: 0:38
 *
 * TODO make thread-safe
 */
public class UndirectedGraph implements GraphWithPathfinder {

    private final AtomicLong idSequencer = new AtomicLong(0);
    private final ConcurrentMap<Long, Vertex> vertices = new ConcurrentHashMap<>();

    @Override
    public Vertex addVertex() {
        long id = idSequencer.incrementAndGet();
        var vertex = new Vertex(id);
        vertices.put(id, vertex);
        return vertex;
    }


    /**
     * Vertices order doesn't matter.
     * @param source first point
     * @param destination second point
     * @return registered edge
     */
    @Override
    public Edge addEdge(Vertex source, Vertex destination) throws GraphException {
        if (source == destination) throw new LoopEdgeException();

        return new Edge(source, destination);
    }

    @Override
    public List<Edge> getPath(Vertex source, Vertex destination) {
        return new ArrayList<>();
    }
}
