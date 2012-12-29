package org.skullforge.asteroidpush.designer.data.joints;

import org.skullforge.asteroidpush.designer.data.JointAnchorData;

public interface JointData {
   JointAnchorData getFirst();
   JointAnchorData getSecond();
   void setFirst(JointAnchorData data);
   void setSecond(JointAnchorData data);
}
