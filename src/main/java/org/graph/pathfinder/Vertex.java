package org.graph.pathfinder;

import lombok.Getter;

/**
 * @author Andrew.Arefyev@gmail.com
 * Date: 04.09.2019
 * Time: 0:26
 */
@Getter
public class Vertex {
    private final long id;

    public Vertex(final long id) {
        this.id = id;
    }

}
