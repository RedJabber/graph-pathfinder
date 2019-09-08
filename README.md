# graph-pathfinder
A simple graph lib. 
> "simple" is in math context - no loops, no multiple edges
## Graphs
* Vertex creation - `new Vertex()`
* Edge creation - `new Edge(...)`
* Graph makes a deal with edges only. 

## Path finding 
use any `org.graph.pathfinder.PathFinderStrategy`
```java
List<Edge> path = DijkstraPathFinderStrategy.on(graph).getPath(vertex0, vertexN)
```

## Multi-threading
use `org.graph.pathfinder.concurrent.ReadWriteSafeMutableGraph` 
 


