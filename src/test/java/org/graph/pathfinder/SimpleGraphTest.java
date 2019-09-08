package org.graph.pathfinder;

import lombok.val;
import org.graph.pathfinder.strategy.DijkstraPathFinderStrategy;
import org.graph.pathfinder.undirected.SimpleBiDirectionalDigraph;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.graph.pathfinder.SimpleDigraphTest.assertEdgeVectorEqual;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Andrew.Arefyev@gmail.com
 * Date: 04.09.2019
 * Time: 1:31
 */
class SimpleGraphTest {

    @Test
    @DisplayName("find the only way in connected undirected graph")
    void testGetTheOnlyPath() {
        MutableGraph graph = new SimpleBiDirectionalDigraph();
        val v0 = new Vertex();
        val v1 = new Vertex();
        val v2 = new Vertex();
        val v3 = new Vertex();
        val v4 = new Vertex();
        val v5 = new Vertex();
        val v6 = new Vertex();

        graph.addEdge(new Edge(v1, v0));
        graph.addEdge(new Edge(v1, v2));
        graph.addEdge(new Edge(v1, v3));
        graph.addEdge(new Edge(v4, v2));
        graph.addEdge(new Edge(v4, v5));
        graph.addEdge(new Edge(v6, v3));

        val theOnlyPath = DijkstraPathFinderStrategy.on(graph).getPath(v0, v5);
        assertNotNull(theOnlyPath);

        assertEquals(4, theOnlyPath.size());
        assertEdgeVectorEqual(v0,v1, theOnlyPath.get(0));
        assertEdgeVectorEqual(v1,v2, theOnlyPath.get(1));
        assertEdgeVectorEqual(v2,v4, theOnlyPath.get(2));
        assertEdgeVectorEqual(v4,v5, theOnlyPath.get(3));
    }

    @Test
    @DisplayName("shell not pass")
    void testGetNoPath() {
        val graph = new SimpleBiDirectionalDigraph();
        val v0 = new Vertex();
        val v1 = new Vertex();
        val v2 = new Vertex();
        val v3 = new Vertex();
        val v4 = new Vertex();
        val v5 = new Vertex();
        val v6 = new Vertex();

        graph.addEdge(new Edge(v0, v2));
        graph.addEdge(new Edge(v2, v4));
        graph.addEdge(new Edge(v4, v6));
        graph.addEdge(new Edge(v1, v3));
        graph.addEdge(new Edge(v3, v5));

        val noPath = DijkstraPathFinderStrategy.on(graph).getPath(v0, v5);
        assertNotNull(noPath);
        assertTrue(noPath.isEmpty());
    }

}