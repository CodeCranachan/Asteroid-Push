package org.codecranachan.asteroidpush.workshop.parts;

import org.codecranachan.asteroidpush.simulation.Material;
import org.codecranachan.asteroidpush.simulation.Primitive;
import org.codecranachan.asteroidpush.workshop.assembly.Part;
import org.codecranachan.asteroidpush.workshop.assembly.Socket;
import org.codecranachan.asteroidpush.workshop.behaviors.CollisionBehaviorFactory;
import org.codecranachan.asteroidpush.workshop.tokenboard.Shape;
import org.jbox2d.common.Vec2;

public class Spike implements PartFactory {

   public String getName() {
      return "Spike";
   }

   public Part createPart() {
      Part part = new Part(getPartShape());

      Primitive shape = new Primitive();
      shape.AddVertex(new Vec2(-0.5f, 0.5f));
      shape.AddVertex(new Vec2(-0.5f, -0.5f));
      shape.AddVertex(new Vec2(1.5f, 0.0f));
      Socket socket = new Socket();
      socket.addLink(-1, 0);
      CollisionBehaviorFactory factory = new CollisionBehaviorFactory(shape,
            Material.METAL, socket);

      part.AddBehaviorFactory(factory);

      return part;
   }

   private Shape getPartShape() {
      return new Shape("XX");
   }

}
