package org.codecranachan.asteroidpush.simulation.modular;

import java.util.AbstractList;

import org.codecranachan.asteroidpush.visuals.Representable;
import org.codecranachan.asteroidpush.visuals.Representation;
import org.codecranachan.asteroidpush.workshop.assembly.AssemblyVertex;

public interface BehaviorFactory extends Representable {
   public AbstractList<AssemblyVertex> getNodes();

   public Behavior createBehavior(AbstractList<BodyVertex> node);

   public Representation getRepresentation();
}
