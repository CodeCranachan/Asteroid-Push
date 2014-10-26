package org.codecranachan.asteroidpush.content.parts;

import org.codecranachan.asteroidpush.base.simulation.Material;
import org.codecranachan.asteroidpush.base.simulation.Primitive;
import org.codecranachan.asteroidpush.base.workshop.PartFactory;
import org.codecranachan.asteroidpush.base.workshop.assembly.Part;
import org.codecranachan.asteroidpush.base.workshop.assembly.Socket;
import org.codecranachan.asteroidpush.base.workshop.tokenboard.Shape;
import org.codecranachan.asteroidpush.content.actors.CannonballFactory;
import org.codecranachan.asteroidpush.content.behaviors.ActorSpawnFactory;
import org.codecranachan.asteroidpush.content.behaviors.CollisionBehaviorFactory;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.codecranachan.asteroidpush.utils.Velocity;
import org.jbox2d.common.Vec2;

public class Cannon implements PartFactory {

   public String getName() {
      return "Cannon";
   }

   public Part createPart() {
      Part part = new Part(getPartShape());

      Socket socket = new Socket();
      socket.addLink(-1, 0);

      Primitive shape = new Primitive();
      shape.AddVertex(new Vec2(0.4f, -0.15f));
      shape.AddVertex(new Vec2(0.4f, 0.15f));
      shape.AddVertex(new Vec2(-0.5f, 0.25f));
      shape.AddVertex(new Vec2(-0.5f, -0.25f));
      CollisionBehaviorFactory collisionFactory = new CollisionBehaviorFactory(
            shape, Material.METAL, socket);
      part.AddBehaviorFactory(collisionFactory);

      Arrow spawnOrigin = new Arrow(new Vec2(0.75f, 0.0f), 0f, 1.0f);
      Velocity spawnVelocity = new Velocity(new Vec2(10f, 0.0f), 0f);
      ActorSpawnFactory spawnFactory = new ActorSpawnFactory(spawnOrigin,
            spawnVelocity, new CannonballFactory(), socket);
      part.AddBehaviorFactory(spawnFactory);

      return part;
   }

   private Shape getPartShape() {
      return new Shape("XX");
   }

}
