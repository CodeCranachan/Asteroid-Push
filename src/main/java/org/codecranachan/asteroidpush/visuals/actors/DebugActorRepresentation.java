package org.codecranachan.asteroidpush.visuals.actors;

import org.codecranachan.asteroidpush.simulation.Actor;
import org.codecranachan.asteroidpush.visuals.Representation;
import org.newdawn.slick.Graphics;

public class DebugActorRepresentation implements Representation {
   public Actor actor;
   
   public DebugActorRepresentation(Actor actor) {
      this.actor = actor;
   }

   public void render(Graphics g) {
      // TODO Auto-generated method stub
      
   }

   public int getPriority() {
      // TODO Auto-generated method stub
      return 0;
   }
}
