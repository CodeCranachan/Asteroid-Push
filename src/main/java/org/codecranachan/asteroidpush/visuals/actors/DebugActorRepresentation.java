package org.codecranachan.asteroidpush.visuals.actors;

import org.codecranachan.asteroidpush.simulation.Actor;
import org.codecranachan.asteroidpush.visuals.Representation;

public class DebugActorRepresentation implements Representation {
   public Actor actor;
   
   public DebugActorRepresentation(Actor actor) {
      this.actor = actor;
   }
}
