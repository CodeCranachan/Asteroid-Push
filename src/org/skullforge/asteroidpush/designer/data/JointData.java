package org.skullforge.asteroidpush.designer.data;

import org.jbox2d.dynamics.joints.JointDef;
import org.skullforge.asteroidpush.entities.spaceship.Part;

public interface JointData {
   JointAnchorData getFirst();
   JointAnchorData getSecond();
   void setFirst(JointAnchorData data);
   void setSecond(JointAnchorData data);
   
   JointDef generateJointDef(Part first, Part second, float moduleSize);
}
