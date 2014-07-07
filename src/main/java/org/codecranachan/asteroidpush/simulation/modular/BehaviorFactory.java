package org.codecranachan.asteroidpush.simulation.modular;

import java.util.AbstractList;

import org.codecranachan.asteroidpush.workshop.assembly.AssemblyVertex;

public interface BehaviorFactory {
   public AbstractList<AssemblyVertex> getNodes();
   public Behavior createBehavior(AbstractList<BodyVertex> node);
}
