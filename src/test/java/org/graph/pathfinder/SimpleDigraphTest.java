package org.graph.pathfinder;

import lombok.val;
import org.graph.pathfinder.directed.SimpleDigraph;
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
    @DisplayName("find the only way in connected directed graph")
    static void testGetTheOnlyPath() {
        val graph = new SimpleDigraph();
        val v0 = graph.addVertex();
        val v1 = graph.addVertex();
        val v2 = graph.addVertex();
        val v3 = graph.addVertex();
        val v4 = graph.addVertex();
        val v5 = graph.addVertex();
        val v6 = graph.addVertex();

        val edge0_1 = graph.addEdge(v0, v1);
        val edge2_4 = graph.addEdge(v2, v4);
        val edge1_2 = graph.addEdge(v1, v2);
        val edge4_5 = graph.addEdge(v4, v5);
        graph.addEdge(v1, v3);
        graph.addEdge(v3, v6);

        val theOnlyPath = graph.getPath(v0, v5);
        assertNotNull(theOnlyPath);

        assertEquals(4, theOnlyPath.size());
        assertEquals(edge0_1, theOnlyPath.get(0));
        assertEquals(edge1_2, theOnlyPath.get(1));
        assertEquals(edge2_4, theOnlyPath.get(2));
        assertEquals(edge4_5, theOnlyPath.get(3));
    }

    @Test
    @DisplayName("find the only way in connected undirected graph")
    void testGetWhenNoPath() {
        val graph = new SimpleDigraph();
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




}