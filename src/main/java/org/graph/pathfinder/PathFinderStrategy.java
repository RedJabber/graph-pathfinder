package org.graph.pathfinder;

import java.util.List;

/**
 *
 * @author Andrew.Arefyev@gmail.com
 * Date: 06.09.2019
 *
 */
public interface PathFinderStrategy {

    List<Edge> getPath(Vertex source, Vertex destination);
}
