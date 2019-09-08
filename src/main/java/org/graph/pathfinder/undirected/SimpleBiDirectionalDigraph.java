package org.graph.pathfinder.undirected;

import java.util.HashSet;
import java.util.Set;
import org.graph.pathfinder.Edge;
import org.graph.pathfinder.MutableGraph;
import org.graph.pathfinder.directed.SimpleDigraph;
import org.graph.pathfinder.exceptions.GraphException;

/**
 * Is not thread-safe use {@link org.graph.pathfinder.concurrent.ReadWriteSafeMutableGraph} wrapper.
 * @author Andrew.Arefyev@gmail.com
 * Date: 04.09.2019
 *
 */
public class SimpleBiDirectionalDigraph extends SimpleDigraph implements MutableGraph {

    @Override
    public void addEdge(Edge edge) throws GraphException {
        super.addEdge(edge);
    }

    @Override
    public Set<Edge> getEdges() {
        var edges = new HashSet<Edge>();
        for (Edge edge : super.getEdges()) {
            edges.add(edge);
            edges.add(new Edge(edge.getDestination(), edge.getSource(), edge.getWeight()));
        }
        return edges;
    }
}
