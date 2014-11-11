package org.codecranachan.asteroidpush.content.parts;

import org.codecranachan.asteroidpush.base.simulation.DistanceJointData;
import org.codecranachan.asteroidpush.base.simulation.Material;
import org.codecranachan.asteroidpush.base.simulation.Primitive;
import org.codecranachan.asteroidpush.base.simulation.PrismaticJointData;
import org.codecranachan.asteroidpush.base.workshop.PartFactory;
import org.codecranachan.asteroidpush.base.workshop.assembly.Component;
import org.codecranachan.asteroidpush.base.workshop.assembly.Part;
import org.codecranachan.asteroidpush.base.workshop.assembly.Socket;
import org.codecranachan.asteroidpush.base.workshop.tokenboard.Shape;
import org.codecranachan.asteroidpush.content.behaviors.CollisionBehaviorFactory;
import org.codecranachan.asteroidpush.content.behaviors.DistanceConstraintBehaviorFactory;
import org.codecranachan.asteroidpush.content.behaviors.PrismaticConstraintBehaviorFactory;
import org.jbox2d.common.Vec2;

public class Bumper implements PartFactory {

   public String getName() {
      return "Bumper";
   }

   public Part createPart() {
      Component base = createBaseComponent();
      base.add(createBaseCollision(), 0);

      Component bumper = createBumperComponent();
      bumper.add(createBumperCollision(), 0);

      PrismaticConstraintBehaviorFactory prismatic = createPrismaticJoint();
      DistanceConstraintBehaviorFactory distance = createDistanceJoint();
      base.add(prismatic, 0);
      base.add(distance, 0);
      bumper.add(prismatic, 1);
      bumper.add(distance, 1);

      Part part = new Part(getPartShape());
      part.addComponent(base);
      part.addComponent(bumper);
      return part;
   }

   private DistanceConstraintBehaviorFactory createDistanceJoint() {
      DistanceJointData distanceData = new DistanceJointData();
      distanceData.setAnchorA(new Vec2(0f, 0f));
      distanceData.setAnchorB(new Vec2(3f, 0f));
      distanceData.setDampingRatio(1.0f);
      distanceData.setFrequency(12.5f);
      distanceData.setLength(3.0f);
      DistanceConstraintBehaviorFactory distance = new DistanceConstraintBehaviorFactory(
            distanceData);
      return distance;
   }

   private PrismaticConstraintBehaviorFactory createPrismaticJoint() {
      PrismaticJointData prismaticData = new PrismaticJointData();
      prismaticData.setAnchorA(new Vec2(0f, 0f));
      prismaticData.setAnchorB(new Vec2(3f, 0f));
      prismaticData.setMaxLength(6.0f);
      prismaticData.setMinLength(0.5f);
      PrismaticConstraintBehaviorFactory prismatic = new PrismaticConstraintBehaviorFactory(
            prismaticData);
      return prismatic;
   }

   private CollisionBehaviorFactory createBumperCollision() {
      Primitive bumperShape = new Primitive();
      bumperShape.AddVertex(new Vec2(2.5f, -0.5f));
      bumperShape.AddVertex(new Vec2(3.5f, -0.5f));
      bumperShape.AddVertex(new Vec2(3.5f, 0.5f));
      bumperShape.AddVertex(new Vec2(2.5f, 0.5f));
      CollisionBehaviorFactory bumperCol = new CollisionBehaviorFactory(
            bumperShape, Material.METAL);
      return bumperCol;
   }

   private CollisionBehaviorFactory createBaseCollision() {
      Primitive baseShape = new Primitive();
      baseShape.AddVertex(new Vec2(-0.5f, -0.5f));
      baseShape.AddVertex(new Vec2(0.5f, -0.5f));
      baseShape.AddVertex(new Vec2(0.5f, 0.5f));
      baseShape.AddVertex(new Vec2(-0.5f, 0.5f));
      CollisionBehaviorFactory base = new CollisionBehaviorFactory(baseShape,
            Material.METAL);
      return base;
   }

   private Component createBaseComponent() {
      Socket baseSocket = new Socket();
      baseSocket.addLink(-1, 0);
      baseSocket.addLink(0, 1);
      baseSocket.addLink(0, -1);
      Component base = new Component(baseSocket);
      return base;
   }

   private Component createBumperComponent() {
      Component bumper;
      Socket bumperSocket = new Socket();
      bumperSocket.addLink(7, 0);
      bumperSocket.addLink(6, 1);
      bumperSocket.addLink(6, -1);
      bumper = new Component(bumperSocket);
      return bumper;
   }

   private Shape getPartShape() {
      return new Shape("XXXX");
   }
}
