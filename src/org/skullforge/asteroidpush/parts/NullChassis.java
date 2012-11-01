package org.skullforge.asteroidpush.parts;

import java.util.ArrayList;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.Joint;

/**
 * This is a Null-Object for the Chassis interface.
 * 
 * @author Konfuzzyus
 * 
 */

public class NullChassis implements Chassis {

   @Override
   public void spawn(World world) {
   }

   @Override
   public void despawn(World world) {
   }

   @Override
   public ArrayList<Body> getBodies() {
      return new ArrayList<Body>();
   }

   @Override
   public ArrayList<Joint> getJoints() {
      return new ArrayList<Joint>();
   }
}
