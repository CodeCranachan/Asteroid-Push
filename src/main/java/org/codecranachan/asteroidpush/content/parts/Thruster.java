package org.codecranachan.asteroidpush.content.parts;

import org.codecranachan.asteroidpush.base.Balancing;
import org.codecranachan.asteroidpush.base.simulation.Material;
import org.codecranachan.asteroidpush.base.simulation.Primitive;
import org.codecranachan.asteroidpush.base.workshop.PartFactory;
import org.codecranachan.asteroidpush.base.workshop.assembly.Part;
import org.codecranachan.asteroidpush.base.workshop.assembly.Socket;
import org.codecranachan.asteroidpush.base.workshop.tokenboard.Shape;
import org.codecranachan.asteroidpush.content.behaviors.CollisionBehaviorFactory;
import org.codecranachan.asteroidpush.content.behaviors.ForceFeederFactory;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.jbox2d.common.Vec2;

public class Thruster implements PartFactory {

   public String getName() {
      return "Thruster";
   }

   public Part createPart() {
      Part part = new Part(getPartShape());

      Socket socket = new Socket();
      socket.addLink(1, 0);

      Primitive shape = new Primitive();
      shape.AddVertex(new Vec2(0.5f, -0.5f));
      shape.AddVertex(new Vec2(0.5f, 0.5f));
      shape.AddVertex(new Vec2(0.0f, 0.25f));
      shape.AddVertex(new Vec2(0.0f, -0.25f));
      CollisionBehaviorFactory collisionFactory = new CollisionBehaviorFactory(
            shape, Material.METAL, socket);

      float magnitude = Balancing.getRequiredForceToLiftBlock(Material.METAL) * 6f;
      Arrow force = new Arrow(new Vec2(0, 0), 0, magnitude);
      ForceFeederFactory feederFactory = new ForceFeederFactory(force, socket);

      part.AddBehaviorFactory(collisionFactory);
      part.AddBehaviorFactory(feederFactory);

      return part;
   }

   private Shape getPartShape() {
      return new Shape("X");
   }

}
