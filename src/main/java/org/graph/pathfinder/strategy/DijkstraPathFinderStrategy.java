package org.graph.pathfinder.strategy;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.val;
import org.graph.pathfinder.Edge;
import org.graph.pathfinder.Graph;
import org.graph.pathfinder.PathFinderStrategy;
import org.graph.pathfinder.Vertex;

/**
 * can be moved to external module.
 * @author Andrew.Arefyev@gmail.com
 * Date: 06.09.2019
 */
public class DijkstraPathFinderStrategy implements PathFinderStrategy {
    private Map<Vertex, Map<Vertex, Long>> adjacency = new HashMap<>();

    private DijkstraPathFinderStrategy(Graph graph) {
        graph.getEdges().forEach(edge -> {
            adjacency.computeIfAbsent(edge.getSource(), k -> new HashMap<>());
            adjacency.get(edge.getSource()).put(edge.getDestination(), edge.getWeight());
        });
    }

    public static PathFinderStrategy on(Graph graph) {
        return new DijkstraPathFinderStrategy(graph);
    }

    @Override
    public List<Edge> getPath(final Vertex source, final Vertex destination) {
        if (source == null) return List.of();
        if (destination == null) return List.of();
        if (source.equals(destination)) return List.of();
        if (!adjacency.containsKey(source)) return List.of();

        //to, from
        Map<Vertex, Vertex> P = calculatePathChains(source);

        return buildPath(source, destination, P);
    }

    private Map<Vertex, Vertex> calculatePathChains(final Vertex source) {
        //vertices with distances
        val D = new HashMap<Vertex, Long>();
        //to, from
        val P = new HashMap<Vertex, Vertex>();
        adjacency.forEach((src, destinations) -> {
            D.put(src, null);
            destinations.keySet().forEach(v -> D.put(v, null));
        });

        D.remove(source);
        var current = new Object() {
            long curPointDist = 0L;
            Vertex vertex = source;
        };
        while (!D.isEmpty()) {
            var adjMap = adjacency.getOrDefault(current.vertex, Map.of());
            D.forEach((destVertex, currentDistance) -> {
                var delta = adjMap.get(destVertex);
                if (delta == null) return;
                if (currentDistance == null || currentDistance > (current.curPointDist + delta)) {
                    D.replace(destVertex, current.curPointDist + delta);
                    //to, from
                    P.put(destVertex, current.vertex);
                }
            });

            current.vertex = findNearestVertex(D);
            if (current.vertex == null) break;
            current.curPointDist = D.get(current.vertex);
            D.remove(current.vertex);
        }
        return P;
    }

    private List<Edge> buildPath(final Vertex source, final Vertex destination, final Map<Vertex, Vertex> P) {
        //P must contain a path to destination if the one was reached.
        if (!P.containsKey(destination)) return List.of();

        var result = new ArrayList<Edge>();
        var currentVertex = destination;
        while (currentVertex != source) {
            var nextVertex = P.get(currentVertex);
            result.add(new Edge(nextVertex, currentVertex, adjacency.get(nextVertex).get(currentVertex)));
            currentVertex = nextVertex;
        }

        return Lists.reverse(result);
    }

    private Vertex findNearestVertex(Map<Vertex, Long> d) {
        var min = new Object() {
            Long weight = null;
            Vertex candidate = null;
        };
        d.forEach((vertex, weight) -> {
            if (weight == null) return;
            if (min.weight == null || weight < min.weight) {
                min.weight = weight;
                min.candidate = vertex;
            }
        });
        return min.candidate;
    }
}
