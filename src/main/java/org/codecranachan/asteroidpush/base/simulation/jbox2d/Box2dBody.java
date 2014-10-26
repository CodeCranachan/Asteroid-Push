package org.codecranachan.asteroidpush.base.simulation.jbox2d;

import java.util.HashMap;
import java.util.Map;

import org.codecranachan.asteroidpush.base.simulation.Hull;
import org.codecranachan.asteroidpush.base.simulation.InteractionHandler;
import org.codecranachan.asteroidpush.base.simulation.Material;
import org.codecranachan.asteroidpush.base.simulation.RigidBody;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.codecranachan.asteroidpush.utils.GeometryConverter;
import org.codecranachan.asteroidpush.utils.Velocity;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.Fixture;

public class Box2dBody implements RigidBody {
   private World world;
   private Body body;
   private Map<Hull, Fixture> fixtureMap;

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
      BodyDef def = new BodyDef();
      def.active = body.isActive();
      def.angularDamping = body.getAngularDamping();
      def.linearDamping = body.getLinearDamping();
      def.fixedRotation = body.isFixedRotation();
      def.position.set(body.getPosition());
      def.angle = body.getAngle();
      def.angularVelocity = body.getAngularVelocity();
      def.linearVelocity = body.getLinearVelocity();
      return new Box2dBody(world, def);
   }

   public Arrow getPosition() {
      assert body != null;
      return new Arrow(body.getPosition(), body.getAngle());
   }
   
   public Velocity getVelocity() {
      assert body != null;
      return new Velocity(body.getLinearVelocity(), body.getAngularVelocity());
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
      def.density = material.density;
      def.friction = material.friction;
      def.restitution = material.restitution;
   }

}
