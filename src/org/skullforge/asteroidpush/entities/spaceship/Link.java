package org.skullforge.asteroidpush.entities.spaceship;

import org.jbox2d.dynamics.joints.JointDef;
import org.skullforge.asteroidpush.designer.data.JointData;

public class Link {
   private JointData data;
   private Part first;
   private Part second;

   public Link(JointData data) {
      this.data = data;
      this.first = null;
      this.second = null;
   }

   public void match(Part part) {
      if (part.getComponent() == data.getFirst().component) {
         first = part;
      }
      if (part.getComponent() == data.getSecond().component) {
         second = part;
      }
   }

   public JointDef getJointDef(float standardModuleSize) {
      if (first == null || second == null) {
         return null;
      }

      if (first.getBody() == second.getBody()) {
         return null;
      }

      return data.generateJointDef(first, second, standardModuleSize);
   }
}
