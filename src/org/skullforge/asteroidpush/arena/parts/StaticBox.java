package org.skullforge.asteroidpush.arena.parts;

import java.util.ArrayList;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;

public class StaticBox implements Part {

   /**
    * Creates a static box with the given dimensions. The box is hollow and
    * defined by two half diagonals, each pointing to any corner of one of the
    * limiting rectangles that make up the box.
    * 
    * @param halfDiagonalA
    *           the half diagonal of the first rectangle.
    * @param halfDiagonalB
    *           the half diagonal of the second rectangle.
    */
   public StaticBox(Vec2 halfDiagonalA, Vec2 halfDiagonalB) {
      body = null;
   }

   @Override
   public void spawn(World world) {
      body = world.createBody(getBodyDef());
   }

   private BodyDef getBodyDef() {
      return new BodyDef();
   }

   @Override
   public void despawn(World world) {
      world.destroyBody(body);
      body = null;
   }

   @Override
   public ArrayList<Body> getBodies() {
      ArrayList<Body> bodies = new ArrayList<Body>();
      if (body != null) {
         bodies.add(body);
      }
      return bodies;
   }

   private Body body;
}
