package org.codecranachan.asteroidpush.simulation.jbox2d;

import java.util.HashMap;
import java.util.Map;

import org.codecranachan.asteroidpush.simulation.InteractionHandler;
import org.codecranachan.asteroidpush.simulation.Hull;
import org.codecranachan.asteroidpush.simulation.Material;
import org.codecranachan.asteroidpush.simulation.Primitive;
import org.codecranachan.asteroidpush.simulation.RigidBody;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
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

      BodyDef def = new BodyDef();
      def.position.set(initial_placement.getTail());
      def.angle = initial_placement.getAngle();

      def.type = BodyType.DYNAMIC;
      def.linearDamping = 0.05f;
      def.angularDamping = 0.25f;
      def.fixedRotation = false;

      body = this.world.createBody(def);
   }

   public void addHull(Hull primitive, InteractionHandler handler) {
      FixtureDef def = new FixtureDef();
      def.shape = ConvertToShape(primitive);
      def.userData = handler;
      setMaterial(def, primitive.getMaterial());

      Fixture fix = body.createFixture(def);
      fixtureMap.put(primitive, fix);
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
      Primitive genericShape = hull.getShape();
      int verticeCount = genericShape.getVertices().size();
      Vec2 vertices[] = new Vec2[verticeCount];
      genericShape.getVertices().copyInto(vertices);
      PolygonShape jbox2dShape = new PolygonShape();
      jbox2dShape.set(vertices, verticeCount);
      return jbox2dShape;
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
