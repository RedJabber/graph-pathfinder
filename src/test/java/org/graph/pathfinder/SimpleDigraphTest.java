package org.graph.pathfinder;

import lombok.NonNull;
import lombok.val;
import org.graph.pathfinder.directed.SimpleDigraph;
import org.graph.pathfinder.strategy.DijkstraPathFinderStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Andrew.Arefyev@gmail.com
 * Date: 04.09.2019
 * Time: 1:31
 */
class SimpleDigraphTest {

    @Test
    @DisplayName("find the best way in connected directed graph")
    void testGetTheBestPath() {
        val graph = new SimpleDigraph();
        val v0 = new Vertex();
        val v1 = new Vertex();
        val v2 = new Vertex();
        val v3 = new Vertex();
        val v4 = new Vertex();
        val v5 = new Vertex();
        val v6 = new Vertex();

        graph.addEdge(new Edge(v0, v1));
        graph.addEdge(new Edge(v0, v3, 4));

        graph.addEdge(new Edge(v1, v2));
        graph.addEdge(new Edge(v1, v3, 3));

        graph.addEdge(new Edge(v2, v3));
        graph.addEdge(new Edge(v2, v4, 3));

        graph.addEdge(new Edge(v3, v4));
        graph.addEdge(new Edge(v4, v5));
        graph.addEdge(new Edge(v3, v6));

        val theOnlyPath = DijkstraPathFinderStrategy.on(graph).getPath(v0, v5);
        assertNotNull(theOnlyPath);

        assertEquals(5, theOnlyPath.size());
        assertEdgeVectorEqual(v0, v1, theOnlyPath.get(0));
        assertEdgeVectorEqual(v1, v2, theOnlyPath.get(1));
        assertEdgeVectorEqual(v2, v3, theOnlyPath.get(2));
        assertEdgeVectorEqual(v3, v4, theOnlyPath.get(3));
        assertEdgeVectorEqual(v4, v5, theOnlyPath.get(4));
    }

    public static void assertEdgeVectorEqual(final @NonNull Vertex source, final @NonNull Vertex destination, Edge edge) {
        assertEquals(source, edge.getSource(), "Edge source mismatch");
        assertEquals(destination, edge.getDestination(), "Edge destination mismatch");
    }

    @Test
    @DisplayName("no path")
    void testGetWhenNoPath() {
        val graph = new SimpleDigraph();
        val v0 = new Vertex();
        val v2 = new Vertex();
        val v3 = new Vertex();
        val v4 = new Vertex();
        val v5 = new Vertex();

        graph.addEdge(new Edge(v0, v2, 1));
        graph.addEdge(new Edge(v2, v4));
        graph.addEdge(new Edge(v4, new Vertex()));
        graph.addEdge(new Edge(new Vertex(), v3));
        graph.addEdge(new Edge(v3, v5));

        val noPath = DijkstraPathFinderStrategy.on(graph).getPath(v0, v5);
        assertNotNull(noPath);
        assertTrue(noPath.isEmpty());

        val noPath1 = DijkstraPathFinderStrategy.on(graph).getPath(v4, v0);
        assertNotNull(noPath1);
        assertTrue(noPath1.isEmpty());

    }

}