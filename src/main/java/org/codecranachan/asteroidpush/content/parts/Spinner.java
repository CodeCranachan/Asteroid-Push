package org.codecranachan.asteroidpush.content.parts;

import org.codecranachan.asteroidpush.base.Balancing;
import org.codecranachan.asteroidpush.base.simulation.Material;
import org.codecranachan.asteroidpush.base.simulation.Primitive;
import org.codecranachan.asteroidpush.base.workshop.PartFactory;
import org.codecranachan.asteroidpush.base.workshop.assembly.Component;
import org.codecranachan.asteroidpush.base.workshop.assembly.Part;
import org.codecranachan.asteroidpush.base.workshop.assembly.Socket;
import org.codecranachan.asteroidpush.base.workshop.tokenboard.Shape;
import org.codecranachan.asteroidpush.content.behaviors.CollisionBehaviorFactory;
import org.codecranachan.asteroidpush.content.behaviors.TorqueFeederFactory;
import org.jbox2d.common.Vec2;

public class Spinner implements PartFactory {

   public String getName() {
      return "Spinner";
   }

   public Part createPart() {
      Part part = new Part(getPartShape());
      part.addComponent(createMainComponent());
      return part;
   }

   private Component createMainComponent() {
      Socket socket = new Socket();
      socket.addLink(1, 0);
      socket.addLink(-1, 0);
      socket.addLink(0, 1);
      socket.addLink(0, -1);
      Component main = new Component(socket);

      Primitive shape = new Primitive();
      shape.AddVertex(new Vec2(0.5f, -0.5f));
      shape.AddVertex(new Vec2(0.5f, 0.5f));
      shape.AddVertex(new Vec2(-0.5f, 0.5f));
      shape.AddVertex(new Vec2(-0.5f, -0.5f));
      CollisionBehaviorFactory collisionFactory = new CollisionBehaviorFactory(
            shape, Material.METAL);

      float magnitude = Balancing.getRequiredTorqueToSpinBlock(Material.METAL) * 10f;
      TorqueFeederFactory feederFactory = new TorqueFeederFactory(magnitude,
            socket);
      main.add(collisionFactory, 0);
      main.add(feederFactory, 0);
      return main;
   }

   private Shape getPartShape() {
      return new Shape("X");
   }

}
