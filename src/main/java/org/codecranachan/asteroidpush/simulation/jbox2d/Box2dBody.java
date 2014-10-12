package org.codecranachan.asteroidpush.simulation.jbox2d;

import java.util.HashMap;
import java.util.Map;

import org.codecranachan.asteroidpush.simulation.InteractionHandler;
import org.codecranachan.asteroidpush.simulation.Hull;
import org.codecranachan.asteroidpush.simulation.Material;
import org.codecranachan.asteroidpush.simulation.RigidBody;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.codecranachan.asteroidpush.utils.GeometryConverter;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.Fixture;

public class Box2dBody implements RigidBody {
   private World world;
   private Body body;
   private Map<Hull, Fixture> fixtureMap;

   public Box2dBody(World world, Arrow initial_placement) {
      assert (world != null);
      assert (initial_placement != null);
      this.world = world;
      this.fixtureMap = new HashMap<Hull, Fixture>();

      BodyDef def = getBasicBodyDef();
      def.position.set(initial_placement.getTail());
      def.angle = initial_placement.getAngle();

      body = this.world.createBody(def);
   }

   public Box2dBody(World world, BodyDef bodyDefinition) {
      assert world != null;
      assert bodyDefinition != null;
      this.world = world;
      this.fixtureMap = new HashMap<Hull, Fixture>();
      body = this.world.createBody(bodyDefinition);
   }

   public void destroy() {
      assert body != null;
      fixtureMap.clear();
      world.destroyBody(body);
      body = null;
   }

   public RigidBody shallowClone() {
      BodyDef def = getBasicBodyDef();
      def.position.set(body.getPosition());
      def.angle = body.getAngle();
      def.angularVelocity = body.getAngularVelocity();
      def.linearVelocity = body.getLinearVelocity();
      return new Box2dBody(world, def);
   }

   private BodyDef getBasicBodyDef() {
      BodyDef def = new BodyDef();

      def.type = BodyType.DYNAMIC;
      def.linearDamping = 0.05f;
      def.angularDamping = 0.25f;
      def.fixedRotation = false;

      return def;
   }

   public Arrow getPosition() {
      assert body != null;
      return new Arrow(body.getPosition(), body.getAngle());
   }

   public void addHull(Hull hull, InteractionHandler handler) {
      FixtureDef def = new FixtureDef();
      def.shape = ConvertToShape(hull);
      def.userData = handler;
      setMaterial(def, hull.getMaterial());

      Fixture fix = body.createFixture(def);
      fixtureMap.put(hull, fix);

      // TODO attach interaction handler somewhere
   }

   public void removeHull(Hull primitive) {
      assert (fixtureMap.containsKey(primitive));
      body.destroyFixture(fixtureMap.get(primitive));
      fixtureMap.remove(primitive);
   }

   public void applyForce(Arrow force) {
      body.applyForce(force.getDirection(), force.getTail());
   }

   public void applyTorque(float torque) {
      body.applyTorque(torque);
   }

   private Shape ConvertToShape(Hull hull) {
      Shape shape = GeometryConverter.convertToBox2dShape(hull.getShape(),
                                                          hull.getOffset());
      return shape;
   }

   private void setMaterial(FixtureDef def, Material material) {
      switch (material) {
      case METAL:
         def.density = 2500.09f;
         def.friction = 0.8f;
         def.restitution = 0.1f;
         break;
      case RUBBER:
         def.density = 2500.0f;
         def.friction = 0.9f;
         def.restitution = 0.9f;
         break;
      }
   }
}
