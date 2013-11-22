package org.skullforge.asteroidpush.designer.catalogue;

import org.jbox2d.common.Vec2;
import org.skullforge.asteroidpush.designer.data.ComponentData;
import org.skullforge.asteroidpush.designer.data.JointAnchorData;
import org.skullforge.asteroidpush.designer.data.Material;
import org.skullforge.asteroidpush.designer.data.ModuleData;
import org.skullforge.asteroidpush.designer.data.PrimitiveData;
import org.skullforge.asteroidpush.designer.data.joints.DistanceJointData;
import org.skullforge.asteroidpush.designer.data.joints.PrismaticJointData;
import org.skullforge.asteroidpush.designer.grid.GridVector;

public class DamperFactory {

   static public ModuleData createData() {
      ModuleData data = new ModuleData("Damper");

      ComponentData front = new ComponentData();
      data.addComponent(front);

      PrimitiveData frontBlock = new PrimitiveData();
      frontBlock.setMaterial(Material.METAL);
      Vec2 frontShape[] = {
            new Vec2(0.5f, 0.5f),
            new Vec2(0.4f, 0.4f),
            new Vec2(0.4f, -0.4f),
            new Vec2(0.5f, -0.5f)
      };
      frontBlock.setVertices(frontShape);
      front.addPrimitive(frontBlock);
      front.addWeldDirection(GridVector.FORWARD);

      ComponentData back = new ComponentData();
      data.addComponent(back);

      PrimitiveData backBlock = new PrimitiveData();
      backBlock.setMaterial(Material.METAL);
      Vec2 backShape[] = {
            new Vec2(-0.5f, -0.5f),
            new Vec2(-0.4f, -0.4f),
            new Vec2(-0.4f, 0.4f),
            new Vec2(-0.5f, 0.5f)
      };
      backBlock.setVertices(backShape);
      back.addPrimitive(backBlock);
      back.addWeldDirection(GridVector.BACK);

      JointAnchorData firstAnchor = new JointAnchorData();
      firstAnchor.component = back;
      firstAnchor.anchor = new Vec2(-0.45f, 0.0f);
      JointAnchorData secondAnchor = new JointAnchorData();
      secondAnchor.component = front;
      secondAnchor.anchor = new Vec2(0.45f, 0.0f);

      PrismaticJointData rotationConstraint = new PrismaticJointData();
      rotationConstraint.setFirst(firstAnchor);
      rotationConstraint.setSecond(secondAnchor);
      rotationConstraint.setCollideConnected(true);
      data.addJoint(rotationConstraint);
      
      DistanceJointData distanceConstraint = new DistanceJointData();
      distanceConstraint.setFirst(firstAnchor);
      distanceConstraint.setSecond(secondAnchor);
      distanceConstraint.setCollideConnected(true);
      distanceConstraint.setDampingRatio(0.2f);
      distanceConstraint.setFrequency(5.0f);
      distanceConstraint.setLength(0.9f);
      data.addJoint(distanceConstraint);
      
      return data;
   }
}
