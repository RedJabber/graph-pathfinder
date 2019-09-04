package org.graph.pathfinder;

import java.util.List;
import lombok.val;
import org.graph.pathfinder.directed.SimpleDigraph;
import org.graph.pathfinder.exceptions.EdgeDuplicateException;
import org.graph.pathfinder.exceptions.LoopEdgeException;
import org.graph.pathfinder.undirected.UndirectedGraph;
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
    static List<GraphWithPathfinder> graphItems() {
        return List.of(new UndirectedGraph(), new SimpleDigraph());
    }

    @ParameterizedTest
    @MethodSource("graphItems")
    @DisplayName("find the only way in connected graph")
    void testGetTheOnlyPath(GraphWithPathfinder graph) {
        val v0 = graph.addVertex();
        val v1 = graph.addVertex();
        val v2 = graph.addVertex();
        val v3 = graph.addVertex();
        val v4 = graph.addVertex();
        val v5 = graph.addVertex();
        val v6 = graph.addVertex();

        val edge0_1 = graph.addEdge(v0, v1);
        val edge1_2 = graph.addEdge(v1, v2);
        val edge1_3 = graph.addEdge(v1, v3);
        val edge2_4 = graph.addEdge(v2, v4);
        val edge4_5 = graph.addEdge(v4, v5);
        val edge3_6 = graph.addEdge(v3, v6);

        val theOnlyPath = graph.getPath(v0, v5);
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
    void testGetTheNoPath(GraphWithPathfinder graph) {
        val v0 = graph.addVertex();
        val v1 = graph.addVertex();
        val v2 = graph.addVertex();
        val v3 = graph.addVertex();
        val v4 = graph.addVertex();
        val v5 = graph.addVertex();
        val v6 = graph.addVertex();

        val edge0_2 = graph.addEdge(v0, v2);
        val edge2_4 = graph.addEdge(v2, v4);
        val edge4_6 = graph.addEdge(v4, v6);
        val edge1_3 = graph.addEdge(v1, v3);
        val edge3_5 = graph.addEdge(v3, v5);

        val noPath = graph.getPath(v0, v5);
        assertNotNull(noPath);
        assertTrue(noPath.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("graphItems")
    @DisplayName("Loop check")
    void testLoopCheck(GraphWithPathfinder graph) {
//        val graph = new UndirectedGraph();
        val v0 = graph.addVertex();

        assertThrows(LoopEdgeException.class, () -> graph.addEdge(v0, v0));

    }

    @ParameterizedTest
    @MethodSource("graphItems")
    @DisplayName("Multiple edge verification check")
    void testMultipleEdgeCheck(GraphWithPathfinder graph) {
        val v0 = graph.addVertex();
        val v1 = graph.addVertex();

        assertNotNull(graph.addEdge(v0, v1));
        assertThrows(EdgeDuplicateException.class, () -> graph.addEdge(v0, v0));
    }

}