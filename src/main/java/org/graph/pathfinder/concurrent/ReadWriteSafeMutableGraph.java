package org.graph.pathfinder.concurrent;

import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.graph.pathfinder.Edge;
import org.graph.pathfinder.MutableGraph;
import org.graph.pathfinder.exceptions.GraphException;

/**
 * @author Andrew.Arefyev@gmail.com
 * Date: 07.09.2019
 */
public class ReadWriteSafeMutableGraph implements MutableGraph {

    private final MutableGraph target;

    private volatile ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public ReadWriteSafeMutableGraph(MutableGraph target) {
        this.target = target;
    }

    @Override
    public void addEdge(Edge edge) throws GraphException {
        try {
            readWriteLock.writeLock().lock();
            target.addEdge(edge);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public Set<Edge> getEdges() {
        try {
            readWriteLock.readLock().lock();
            return target.getEdges();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
