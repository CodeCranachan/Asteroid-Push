package org.codecranachan.asteroidpush.base;

import org.codecranachan.asteroidpush.base.simulation.Material;
import org.jbox2d.common.MathUtils;

public class Balancing {
   /**
    * Size factor for space ships, this corresponds to the grid size used when
    * constructing the ModularActor from a Blueprint.
    */
   static final float SHIP_SIZE_FACTOR = 0.5f;

   /**
    * Default gravity used by the game, in Newtons.
    */
   static final float DEFAULT_GRAVITY = 10.0f;

   /**
    * Used to balance the forces exerted by force feeders. A force feeder is
    * considered able to lift a block if its exerted force causes an linear
    * acceleration of at least this magnitude. The constant is in m/s^2.
    */
   static final float LIFT_ACCELERATION = 1.0f;

   /**
    * Used to balance the torque exerted by torque feeders. A torque feeder is
    * considered able to spin a block if its exerted torque causes an angular
    * acceleration of at least this magnitude. The constant is in rad/s^2.
    */
   static final float SPIN_ACCELERATION = MathUtils.TWOPI * 2.0f;

   public static float getRequiredForceToLiftBlock(Material material) {
      float block_mass = getBlockArea() * material.density;
      float gravitational_force = DEFAULT_GRAVITY * block_mass;
      float required_accelerating_force = LIFT_ACCELERATION * block_mass;
      return gravitational_force + required_accelerating_force;
   }

   public static float getRequiredTorqueToSpinBlock(Material material) {
      float block_inertia = getBlockInertia() * material.density * getBlockArea();
      return block_inertia * SPIN_ACCELERATION;
   }

   public static float getShipSizeFactor() {
      return SHIP_SIZE_FACTOR;
   }

   public static float getDefaultGravity() {
      return DEFAULT_GRAVITY;
   }

   public static float getBlockArea() {
      return SHIP_SIZE_FACTOR * SHIP_SIZE_FACTOR;
   }

   public static float getBlockInertia() {
      float a = SHIP_SIZE_FACTOR;
      return (a * a + a * a) / 12f;
   }
}
