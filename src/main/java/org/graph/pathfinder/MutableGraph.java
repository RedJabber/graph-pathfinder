package org.graph.pathfinder;

import org.graph.pathfinder.exceptions.GraphException;

/**
 * @author Andrew.Arefyev@gmail.com
 * Date: 04.09.2019
 * Time: 1:23
 */
public interface MutableGraph extends Graph {

    void addEdge(Edge edge) throws GraphException;

}
