package org.codecranachan.asteroidpush.workshop.parts;

import org.codecranachan.asteroidpush.simulation.Material;
import org.codecranachan.asteroidpush.simulation.Primitive;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.codecranachan.asteroidpush.workshop.assembly.Part;
import org.codecranachan.asteroidpush.workshop.assembly.Socket;
import org.codecranachan.asteroidpush.workshop.behaviors.CollisionBehaviorFactory;
import org.codecranachan.asteroidpush.workshop.tokenboard.Shape;
import org.jbox2d.common.Vec2;

public class Block implements PartFactory {

   public String getName() {
      return "Block";
   }

   public Part createPart() {
      Part part = new Part(getPartShape());

      Primitive shape = new Primitive();
      shape.AddSquare(new Arrow(new Vec2(0, 0), 0f, 0.5f));
      Socket socket = new Socket();
      socket.addLink(-1, 0);
      socket.addLink(0, -1);
      socket.addLink(1, 0);
      socket.addLink(0, 1);
      CollisionBehaviorFactory factory = new CollisionBehaviorFactory(shape,
            Material.METAL, socket);

      part.AddBehaviorFactory(factory);

      return part;
   }

   private Shape getPartShape() {
      return new Shape("X");
   }

}
