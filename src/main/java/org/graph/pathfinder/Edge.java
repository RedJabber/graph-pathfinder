package org.graph.pathfinder;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Andrew.Arefyev@gmail.com
 * Date: 04.09.2019
 * Time: 0:19
 */
@Getter
public class Edge {
    @Setter
    private long weight = 1;
    private final Vertex source;
    private final Vertex destination;

    public Edge(Vertex source, Vertex destination) {
        //TODO assert source != destination;
        this.source = source;
        this.destination = destination;
    }


}
