package org.codecranachan.asteroidpush.simulation.modular;

public interface BehaviorFactory {
   public Behavior createBehavior(BodyVertex node);
}
