package org.skullforge.asteroidpush.designer.data.joints;

import java.util.ArrayList;

import org.jbox2d.dynamics.joints.JointDef;
import org.skullforge.asteroidpush.designer.data.JointAnchorData;
import org.skullforge.asteroidpush.designer.data.JointData;
import org.skullforge.asteroidpush.entities.spaceship.Part;

public class BasicJointData implements JointData {
   private ArrayList<JointAnchorData> anchors;

   public BasicJointData() {
      this.anchors = new ArrayList<JointAnchorData>(2);
      this.anchors.add(null);
      this.anchors.add(null);
   }

   public void setFirst(JointAnchorData data) {
      anchors.set(0, data);
   }

   public void setSecond(JointAnchorData data) {
      anchors.set(1, data);
   }

   public JointAnchorData getFirst() {
      return anchors.get(0);
   }

   public JointAnchorData getSecond() {
      return anchors.get(1);
   }

   public JointDef generateJointDef(Part first, Part second, float moduleSize) {
      return null;
   }

}
