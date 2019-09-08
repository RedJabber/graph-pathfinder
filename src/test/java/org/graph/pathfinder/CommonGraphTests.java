package org.graph.pathfinder;

import java.util.List;
import lombok.val;
import org.graph.pathfinder.directed.SimpleDigraph;
import org.graph.pathfinder.exceptions.LoopEdgeException;
import org.graph.pathfinder.strategy.DijkstraPathFinderStrategy;
import org.graph.pathfinder.undirected.SimpleBiDirectionalDigraph;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Andrew.Arefyev@gmail.com
 * Date: 04.09.2019
 * Time: 1:31
 */
class CommonGraphTests {

    @TestFactory
    static List<MutableGraph> graphItems() {
        return List.of(new SimpleBiDirectionalDigraph(), new SimpleDigraph());
    }

    @ParameterizedTest
    @MethodSource("graphItems")
    @DisplayName("find the only way in connected graph")
    void testGetTheOnlyPath(MutableGraph graph) {
        val v0 = new Vertex();
        val v1 = new Vertex();
        val v2 = new Vertex();
        val v3 = new Vertex();
        val v4 = new Vertex();
        val v5 = new Vertex();
        val v6 = new Vertex();

        val edge0_1 = new Edge(v0, v1);
        graph.addEdge(edge0_1);
        val edge1_2 = new Edge(v1, v2);
        graph.addEdge(edge1_2);
        graph.addEdge(new Edge(v1, v3));
        val edge2_4 = new Edge(v2, v4);
        graph.addEdge(edge2_4);
        val edge4_5 = new Edge(v4, v5);
        graph.addEdge(edge4_5);
        graph.addEdge(new Edge(v3, v6));

        val theOnlyPath = DijkstraPathFinderStrategy.on(graph).getPath(v0, v5);
        assertNotNull(theOnlyPath);

        assertEquals(4, theOnlyPath.size());
        assertEquals(edge0_1, theOnlyPath.get(0));
        assertEquals(edge1_2, theOnlyPath.get(1));
        assertEquals(edge2_4, theOnlyPath.get(2));
        assertEquals(edge4_5, theOnlyPath.get(3));
    }

    @ParameterizedTest
    @MethodSource("graphItems")
    @DisplayName("find the only way in connected undirected graph")
    void testGetTheNoPath(MutableGraph graph) {
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

    @ParameterizedTest
    @MethodSource("graphItems")
    @DisplayName("Loop check")
    void testLoopCheck(MutableGraph graph) {
        val v0 = new Vertex();
        assertThrows(LoopEdgeException.class, () -> graph.addEdge(new Edge(v0, v0)));

    }

    @ParameterizedTest
    @MethodSource("graphItems")
    @DisplayName("Multiple edge verification check")
    void testMultipleEdgeCheck(MutableGraph graph) {
        val v0 = new Vertex();
        val v1 = new Vertex();
        graph.addEdge(new Edge(v0, v1));

        assertNotNull(graph.getEdges());
        assertFalse(graph.getEdges().isEmpty());
        assertThrows(LoopEdgeException.class, () -> graph.addEdge(new Edge(v0, v0)));
    }

}