package org.codecranachan.asteroidpush.simulation.modular;

import java.util.List;

import org.codecranachan.asteroidpush.visuals.Representable;
import org.codecranachan.asteroidpush.visuals.Representation;
import org.codecranachan.asteroidpush.workshop.assembly.AssemblyVertex;

public interface BehaviorFactory extends Representable {
   public List<AssemblyVertex> getNodes();

   public Behavior createBehavior(List<BodyVertex> node);

   public Representation getRepresentation();
}
