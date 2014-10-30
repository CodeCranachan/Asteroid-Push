package org.codecranachan.asteroidpush.base.simulation.jbox2d;

import java.util.HashMap;
import java.util.Map;

import org.codecranachan.asteroidpush.base.simulation.Hull;
import org.codecranachan.asteroidpush.base.simulation.InteractionHandler;
import org.codecranachan.asteroidpush.base.simulation.Material;
import org.codecranachan.asteroidpush.base.simulation.RigidBody;
import org.codecranachan.asteroidpush.utils.Angle;
import org.codecranachan.asteroidpush.utils.Circle;
import org.codecranachan.asteroidpush.utils.Force;
import org.codecranachan.asteroidpush.utils.GeometryConverter;
import org.codecranachan.asteroidpush.utils.NewtonianState;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
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

   public NewtonianState getState() {
      assert body != null;
      NewtonianState state = new NewtonianState();
      state.setState(body.getPosition(), Angle.fromRad(body.getAngle()));
      state.setVelocity(body.getLinearVelocity(),
                        Angle.fromRad(body.getAngularVelocity()));
      return state;
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

   public void applyForce(Force force) {
      Force transformed = transformToWorld(force);
      body.applyForce(transformed.getForce(), transformed.getOffset());
   }

   public Force transformToWorld(Force force) {
      Vec2 point = body.getWorldPoint(force.getOffset());
      Vec2 vector = body.getWorldVector(force.getForce());
      return new Force(point, vector);
   }

   public Circle getEnclosingCircle() {
      // TODO: calculate correct radius of circle
      return new Circle(body.getWorldCenter(), 10f);
   }

   public NewtonianState transformToWorld(NewtonianState state) {
      NewtonianState transformed = new NewtonianState();
      transformed.setState(body.getWorldPoint(state.getPosition()), Angle
            .fromRad(body.getAngle()).add(state.getRotation()));
      Vec2 linVel = body.getWorldVector(state.getLinearVelocity())
            .add(body.getLinearVelocity());
      Angle angVel = Angle.fromRad(body.getAngularVelocity())
            .add(state.getAngularVelocity());
      transformed.setVelocity(linVel, angVel);
      return transformed;
   }
}
