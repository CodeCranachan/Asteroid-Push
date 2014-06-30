package org.codecranachan.asteroidpush.simulation;

import org.codecranachan.asteroidpush.workshop.spaceship.BodyGraph;
import org.codecranachan.asteroidpush.workshop.spaceship.BodyVertex;

public interface RigidBody {
   public void addBehavior(Behavior behavior, BodyVertex node);
   public void setGraph(BodyGraph graph);
}
