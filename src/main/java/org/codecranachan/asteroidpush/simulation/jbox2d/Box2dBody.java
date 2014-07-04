package org.codecranachan.asteroidpush.simulation.jbox2d;

import java.util.HashMap;
import java.util.Map;

import org.codecranachan.asteroidpush.simulation.InteractionHandler;
import org.codecranachan.asteroidpush.simulation.Primitive;
import org.codecranachan.asteroidpush.simulation.RigidBody;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.Fixture;

public class Box2dBody implements RigidBody {
   private World world;
   private Body body;
   private Map<Primitive, Fixture> fixtureMap;

   public Box2dBody(World world, Arrow initial_placement) {
      assert (world != null);
      assert (initial_placement != null);
      this.world = world;
      this.fixtureMap = new HashMap<Primitive, Fixture>();

      BodyDef def = new BodyDef();
      def.position.set(initial_placement.getTail());
      def.angle = initial_placement.getAngle();

      body = this.world.createBody(def);
   }

   public void AddPrimitive(Primitive primitive, InteractionHandler handler) {
      FixtureDef def = new FixtureDef();
      def.shape = ConvertToShape(primitive);
      def.userData = handler;
      // TODO Implement material

      Fixture fix = body.createFixture(def);
      fixtureMap.put(primitive, fix);
   }

   public void RemovePrimitive(Primitive primitive) {
      body.destroyFixture(fixtureMap.get(primitive));
      fixtureMap.remove(primitive);
   }

   public void ApplyForce(Arrow force) {
      body.applyForce(force.getDirection(), force.getTail());
   }

   public void ApplyTorque(float torque) {
      body.applyTorque(torque);
   }

   private Shape ConvertToShape(Primitive primitive) {
      // TODO Implement conversion
      return null;
   }
}
