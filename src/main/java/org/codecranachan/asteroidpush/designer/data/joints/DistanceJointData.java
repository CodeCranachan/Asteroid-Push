//    Asteroid Push - A game featuring selfmade spaceships and pompous physics
//    Copyright (C) 2013  Christian Meyer, Silvan Wegmann
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.

package org.codecranachan.asteroidpush.designer.data.joints;

import org.codecranachan.asteroidpush.entities.spaceship.Part;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.common.Rot;
import org.jbox2d.dynamics.joints.DistanceJointDef;
import org.jbox2d.dynamics.joints.JointDef;

public class DistanceJointData extends BasicJointData {

   private float frequency;
   private float dampingRatio;
   private float length;
   private boolean collideConnected;
   
   public DistanceJointData() {
      this.frequency = 5.0f;
      this.dampingRatio = 0.1f;
      this.length = 1.0f;
      this.collideConnected = true;
   }

   public void setFrequency(float frequency) {
      this.frequency = frequency;
   }

   public void setDampingRatio(float dampingRatio) {
      this.dampingRatio = dampingRatio;
   }

   public void setLength(float length) {
      this.length = length;
   }

   public void setCollideConnected(boolean collideConnected) {
      this.collideConnected = collideConnected;
   }

   @Override
   public JointDef generateJointDef(Part first, Part second, float moduleSize) {

      DistanceJointDef def = new DistanceJointDef();
      def.bodyA = first.getBody();
      def.bodyB = second.getBody();

      Transform firstTransform = first.getPlacement().getTransform();
      Vec2 firstAnchor = new Vec2();
      Rot.mulTrans(firstTransform.q, getFirst().anchor, firstAnchor);
      firstAnchor.addLocal(firstTransform.p);
      firstAnchor.mulLocal(moduleSize);
      def.localAnchorA.set(firstAnchor);

      Transform secondTransform = second.getPlacement().getTransform();
      Vec2 secondAnchor = new Vec2();
      Rot.mulTrans(secondTransform.q, getSecond().anchor, secondAnchor);
      secondAnchor.addLocal(secondTransform.p);
      secondAnchor.mulLocal(moduleSize);
      def.localAnchorB.set(secondAnchor);

      def.collideConnected = this.collideConnected;
      def.frequencyHz = this.frequency;
      def.dampingRatio = this.dampingRatio;
      def.length = this.length * moduleSize;

      return def;
   }
}
