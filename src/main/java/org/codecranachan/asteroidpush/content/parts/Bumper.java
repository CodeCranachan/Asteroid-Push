package org.codecranachan.asteroidpush.content.parts;

import org.codecranachan.asteroidpush.base.simulation.Material;
import org.codecranachan.asteroidpush.base.simulation.Primitive;
import org.codecranachan.asteroidpush.base.simulation.PrismaticJointData;
import org.codecranachan.asteroidpush.base.workshop.PartFactory;
import org.codecranachan.asteroidpush.base.workshop.assembly.Part;
import org.codecranachan.asteroidpush.base.workshop.assembly.Socket;
import org.codecranachan.asteroidpush.base.workshop.tokenboard.Shape;
import org.codecranachan.asteroidpush.content.behaviors.CollisionBehaviorFactory;
import org.codecranachan.asteroidpush.content.behaviors.PrismaticConstraintBehaviorFactory;
import org.jbox2d.common.Vec2;

public class Bumper implements PartFactory {

   public String getName() {
      return "Bumper";
   }

   public Part createPart() {
      Part part = new Part(getPartShape());

      Socket baseSocket = new Socket();
      baseSocket.addLink(3, 0);
      baseSocket.addLink(2, 1);
      baseSocket.addLink(2, -1);
      Primitive baseShapeA = new Primitive();
      baseShapeA.AddVertex(new Vec2(1.5f, -0.5f));
      baseShapeA.AddVertex(new Vec2(0.5f, -0.5f));
      baseShapeA.AddVertex(new Vec2(1f, 0f));
      baseShapeA.AddVertex(new Vec2(1.5f, 0f));
      CollisionBehaviorFactory baseA = new CollisionBehaviorFactory(baseShapeA,
            Material.METAL, baseSocket);
      Primitive baseShapeB = new Primitive();
      baseShapeB.AddVertex(new Vec2(0.5f, 0.5f));
      baseShapeB.AddVertex(new Vec2(1f, 0f));
      baseShapeB.AddVertex(new Vec2(1.5f, 0f));
      baseShapeB.AddVertex(new Vec2(1.5f, 0.5f));
      CollisionBehaviorFactory baseB = new CollisionBehaviorFactory(baseShapeB,
            Material.METAL, baseSocket);

      Socket bumperSocket = new Socket();
      bumperSocket.addLink(-1, 0);
      Primitive bumperShape = new Primitive();
      bumperShape.AddVertex(new Vec2(0f, 0f));
      bumperShape.AddVertex(new Vec2(-0.5f, 0.5f));
      bumperShape.AddVertex(new Vec2(-0.5f, -0.5f));
      CollisionBehaviorFactory bumper = new CollisionBehaviorFactory(
            bumperShape, Material.METAL, bumperSocket);

      part.AddBehaviorFactory(baseA);
      part.AddBehaviorFactory(baseB);
      part.AddBehaviorFactory(bumper);

      PrismaticJointData prismaticData = new PrismaticJointData();
      prismaticData.setAnchorA(new Vec2(1.25f, 0f));
      prismaticData.setAnchorB(new Vec2(0f, 0f));
      prismaticData.setMaxLength(4f);
      prismaticData.setMinLength(0.25f);
      PrismaticConstraintBehaviorFactory prismatic = new PrismaticConstraintBehaviorFactory(
            baseSocket, bumperSocket, prismaticData);

      part.AddBehaviorFactory(prismatic);

      return part;
   }

   private Shape getPartShape() {
      return new Shape("XX");
   }
}
