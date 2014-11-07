package org.codecranachan.asteroidpush.base.simulation.jbox2d;

import org.codecranachan.asteroidpush.base.simulation.DynamicJoint;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.Joint;
import org.jbox2d.dynamics.joints.JointDef;

public class Box2dJoint implements DynamicJoint {
   Joint joint;
   World world;

   Box2dJoint(World world, JointDef jointDefinition) {
      assert world != null;
      assert jointDefinition != null;
      this.world = world;
      this.joint = world.createJoint(jointDefinition);
   }

   public void destroy() {
      world.destroyJoint(joint);
   }
}
