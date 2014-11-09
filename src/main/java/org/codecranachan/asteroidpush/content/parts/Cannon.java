package org.codecranachan.asteroidpush.content.parts;

import org.codecranachan.asteroidpush.base.simulation.Material;
import org.codecranachan.asteroidpush.base.simulation.Primitive;
import org.codecranachan.asteroidpush.base.workshop.PartFactory;
import org.codecranachan.asteroidpush.base.workshop.assembly.Component;
import org.codecranachan.asteroidpush.base.workshop.assembly.Part;
import org.codecranachan.asteroidpush.base.workshop.assembly.Socket;
import org.codecranachan.asteroidpush.base.workshop.tokenboard.Shape;
import org.codecranachan.asteroidpush.content.actors.CannonballFactory;
import org.codecranachan.asteroidpush.content.behaviors.ActorSpawnFactory;
import org.codecranachan.asteroidpush.content.behaviors.CollisionBehaviorFactory;
import org.codecranachan.asteroidpush.utils.Angle;
import org.codecranachan.asteroidpush.utils.NewtonianState;
import org.jbox2d.common.Vec2;

public class Cannon implements PartFactory {

   public String getName() {
      return "Cannon";
   }

   public Part createPart() {
      Part part = new Part(getPartShape());
      part.addComponent(createMainComponent());
      return part;
   }

   private Component createMainComponent() {
      Socket socket = new Socket();
      socket.addLink(-1, 0);
      Component main = new Component(socket);

      Primitive shape = new Primitive();
      shape.AddVertex(new Vec2(0.4f, -0.15f));
      shape.AddVertex(new Vec2(0.4f, 0.15f));
      shape.AddVertex(new Vec2(-0.5f, 0.25f));
      shape.AddVertex(new Vec2(-0.5f, -0.25f));
      CollisionBehaviorFactory collisionFactory = new CollisionBehaviorFactory(
            shape, Material.METAL);
      main.add(collisionFactory, 0);

      NewtonianState initial_state = new NewtonianState();
      initial_state.setState(new Vec2(0.75f, 0f), new Angle());
      initial_state.setVelocity(new Vec2(25f, 0f), new Angle());
      ActorSpawnFactory spawnFactory = new ActorSpawnFactory(initial_state,
            new CannonballFactory(), socket);
      main.add(spawnFactory, 0);
      return main;
   }

   private Shape getPartShape() {
      return new Shape("XX");
   }

}
