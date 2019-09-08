package org.graph.pathfinder.concurrent;

import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import org.graph.pathfinder.Edge;
import org.graph.pathfinder.MutableGraph;
import org.graph.pathfinder.Vertex;
import org.graph.pathfinder.directed.SimpleDigraph;
import org.graph.pathfinder.exceptions.GraphException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Andrew.Arefyev@gmail.com
 * Date: 07.09.2019
 * Time: 23:07
 */
class ConcurrencyTest {

    @Test
    @DisplayName("simple concurrent add")
    void testAddEdge() throws InterruptedException {
        final int COUNT = 100;
        var latch = new CountDownLatch(1);
        var finishLatch = new CountDownLatch(COUNT);

        var graph = new ReadWriteSafeMutableGraph(new SimpleDigraph());
        //var graph = new SimpleBiDirectionalDigraph();
        var service = Executors.newFixedThreadPool(60);
        for (int i = 0; i < COUNT; i++) {
            service.submit(() -> {
                silentAwait(latch);
                graph.addEdge(new Edge(new Vertex(), new Vertex()));
                finishLatch.countDown();
            });
        }
        latch.countDown();
        finishLatch.await();
        assertEquals(COUNT, graph.getEdges().size());
    }

    @Test
    @DisplayName("check that length during getting edges would not change")
    void testRWSWrapGetEdgesFixed() {
        var startLatch = new CountDownLatch(1);
        var falseLatch = new CountDownLatch(1);
        var addInLatch = new CountDownLatch(1);

        var graph = new ReadWriteSafeMutableGraph(new MutableGraph() {

            @Override
            public void addEdge(final Edge edge) throws GraphException {
                falseLatch.countDown();
            }

            @Override
            public Set<Edge> getEdges() {
                startLatch.countDown();
                try {
                    Thread.sleep(100);
                    addInLatch.await();
                } catch (InterruptedException ignored) {}
                assertEquals(1, falseLatch.getCount(), "added an edge when get edges");
                return null;
            }
        });

        new Thread(() -> {
            silentAwait(startLatch);
            addInLatch.countDown();
            graph.addEdge(new Edge(new Vertex(), new Vertex()));
        }).start();
        graph.getEdges();

    }

    private static void silentAwait(final CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException ignored) {}
    }

}