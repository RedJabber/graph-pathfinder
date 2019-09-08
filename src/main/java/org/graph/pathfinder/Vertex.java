package org.graph.pathfinder;

import java.util.concurrent.atomic.AtomicLong;
import org.jetbrains.annotations.NotNull;

/**
 * @author Andrew.Arefyev@gmail.com
 * Date: 04.09.2019
 */
public class Vertex implements Comparable<Vertex>/*, Serializable*/ {

    private final long id = idGenerator.getAndIncrement();

    private static final AtomicLong idGenerator = new AtomicLong(0);

    @Override
    public int compareTo(@NotNull Vertex o) {
        return Long.compare(id, o.id);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return id == vertex.id;
    }

    @Override
    public int hashCode() {
        return (int) id;
    }
}
