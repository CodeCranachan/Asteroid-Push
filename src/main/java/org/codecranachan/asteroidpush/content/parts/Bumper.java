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
      base.add(createBaseCollisionA(), 0);
      base.add(createBaseCollisionB(), 0);

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
      distanceData.setAnchorA(new Vec2(1f, 0f));
      distanceData.setAnchorB(new Vec2(0f, 0f));
      distanceData.setDampingRatio(1.0f);
      distanceData.setFrequency(2.0f);
      distanceData.setLength(0.8f);
      DistanceConstraintBehaviorFactory distance = new DistanceConstraintBehaviorFactory(
            distanceData);
      return distance;
   }

   private PrismaticConstraintBehaviorFactory createPrismaticJoint() {
      PrismaticJointData prismaticData = new PrismaticJointData();
      prismaticData.setAnchorA(new Vec2(1.25f, 0f));
      prismaticData.setAnchorB(new Vec2(0f, 0f));
      prismaticData.setMaxLength(4f);
      prismaticData.setMinLength(0.25f);
      PrismaticConstraintBehaviorFactory prismatic = new PrismaticConstraintBehaviorFactory(
            prismaticData);
      return prismatic;
   }

   private CollisionBehaviorFactory createBumperCollision() {
      Primitive bumperShape = new Primitive();
      bumperShape.AddVertex(new Vec2(0f, 0f));
      bumperShape.AddVertex(new Vec2(-0.5f, 0.5f));
      bumperShape.AddVertex(new Vec2(-0.5f, -0.5f));
      CollisionBehaviorFactory bumperCol = new CollisionBehaviorFactory(
            bumperShape, Material.METAL);
      return bumperCol;
   }

   private CollisionBehaviorFactory createBaseCollisionB() {
      Primitive baseShapeB = new Primitive();
      baseShapeB.AddVertex(new Vec2(0.5f, 0.5f));
      baseShapeB.AddVertex(new Vec2(1f, 0f));
      baseShapeB.AddVertex(new Vec2(1.5f, 0f));
      baseShapeB.AddVertex(new Vec2(1.5f, 0.5f));
      CollisionBehaviorFactory baseB = new CollisionBehaviorFactory(baseShapeB,
            Material.METAL);
      return baseB;
   }

   private CollisionBehaviorFactory createBaseCollisionA() {
      Primitive baseShapeA = new Primitive();
      baseShapeA.AddVertex(new Vec2(1.5f, -0.5f));
      baseShapeA.AddVertex(new Vec2(0.5f, -0.5f));
      baseShapeA.AddVertex(new Vec2(1f, 0f));
      baseShapeA.AddVertex(new Vec2(1.5f, 0f));
      CollisionBehaviorFactory baseA = new CollisionBehaviorFactory(baseShapeA,
            Material.METAL);
      return baseA;
   }

   private Component createBaseComponent() {
      Socket baseSocket = new Socket();
      baseSocket.addLink(3, 0);
      baseSocket.addLink(2, 1);
      baseSocket.addLink(2, -1);
      Component base = new Component(baseSocket);
      return base;
   }

   private Component createBumperComponent() {
      Component bumper;
      Socket bumperSocket = new Socket();
      bumperSocket.addLink(-1, 0);
      bumper = new Component(bumperSocket);
      return bumper;
   }

   private Shape getPartShape() {
      return new Shape("XX");
   }
}
