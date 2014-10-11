package org.codecranachan.asteroidpush;

public class foggy {
   // vertex 
   //  - reference(s) to plugs (behavior + slot index)
   
   // graph
   //  - collection of all vertices
   

   // subgraphs are used to map vertices and bodies
   
   // subgraph of graph
   //  - reference to a body
   
   
   // how do we traverse the behaviors?
   // for each behavior 
   //  - How do we figure out which vertices point to the behavior?
   
   // for each vertex <==
   //  - create a collection of body-vector to behavior, then for each on that
   //  -> map can be saved and updated when subgraphs change
   
   
   // how do we build the graph?
   // how do we manipulate the graph?
   // how do we detect subgraph change?
   //  - observer pattern?
   //  - how do we split bodies?
   //  - how do we join bodies?
   //    - interface on body? join() and split()?
   //      -> makes no sense
   //    - just add / delete in callbacks?
   //  
   
   
}
