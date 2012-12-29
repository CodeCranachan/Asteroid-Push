package org.skullforge.asteroidpush.entities.spaceship;

import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.joints.DistanceJointDef;
import org.jbox2d.dynamics.joints.JointDef;
import org.skullforge.asteroidpush.designer.data.joints.JointData;

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

      DistanceJointDef def = new DistanceJointDef();
      def.bodyA = first.getBody();
      def.bodyB = second.getBody();

      Transform firstTransform = first.getPlacement().getTransform();
      Vec2 firstAnchor = firstTransform.R.mul(data.getFirst().anchor);
      firstAnchor.addLocal(firstTransform.position);
      firstAnchor.mulLocal(standardModuleSize);
      def.localAnchorA.set(firstAnchor);

      Transform secondTransform = second.getPlacement().getTransform();
      Vec2 secondAnchor = secondTransform.R.mul(data.getSecond().anchor);
      secondAnchor.addLocal(secondTransform.position);
      secondAnchor.mulLocal(standardModuleSize);
      def.localAnchorB.set(secondAnchor);

      def.collideConnected = true;
      def.frequencyHz = 5.0f;
      def.dampingRatio = 0.2f;
      def.length = 0.9f * standardModuleSize;

      return def;
   }
}
