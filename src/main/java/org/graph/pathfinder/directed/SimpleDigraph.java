package org.graph.pathfinder.directed;

import java.util.HashSet;
import java.util.Set;
import org.graph.pathfinder.Edge;
import org.graph.pathfinder.MutableGraph;
import org.graph.pathfinder.exceptions.EdgeDuplicateException;
import org.graph.pathfinder.exceptions.GraphException;
import org.graph.pathfinder.exceptions.LoopEdgeException;

/**
 * Is not thread-safe use {@link org.graph.pathfinder.concurrent.ReadWriteSafeMutableGraph} wrapper.
 * 
 * @author Andrew.Arefyev@gmail.com
 * Date: 04.09.2019
 */
public class SimpleDigraph implements MutableGraph {
    private Set<Edge> edges = new HashSet<>();

    /**
     *
     * @param edge directed edge
     * @throws LoopEdgeException on a loop edged
     * @throws EdgeDuplicateException on a repeated edge
     */
    @Override
    public void addEdge(Edge edge) throws GraphException {
        if (edge.getSource() == edge.getDestination()) throw new LoopEdgeException();
        if (edges.contains(edge)) throw new EdgeDuplicateException();
        edges.add(edge);
    }

    @Override
    public Set<Edge> getEdges() {
        return Set.copyOf(edges);
    }
}
