package org.skullforge.asteroidpush.designer.data.joints;

import java.util.ArrayList;

import org.skullforge.asteroidpush.designer.data.JointAnchorData;

public class BasicJointData implements JointData {
   private ArrayList<JointAnchorData> anchors;

   public BasicJointData() {
      this.anchors = new ArrayList<JointAnchorData>(2);
      this.anchors.add(null);
      this.anchors.add(null);
   }

   @Override
   public void setFirst(JointAnchorData data) {
      anchors.set(0, data);
   }

   @Override
   public void setSecond(JointAnchorData data) {
      anchors.set(1, data);
   }

   @Override
   public JointAnchorData getFirst() {
      return anchors.get(0);
   }

   @Override
   public JointAnchorData getSecond() {
      return anchors.get(1);
   }

}
