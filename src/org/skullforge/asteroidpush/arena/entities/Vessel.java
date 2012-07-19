package org.skullforge.asteroidpush.arena.entities;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.skullforge.asteroidpush.arena.Entity;
import org.skullforge.asteroidpush.arena.Viewport;

public class Vessel implements Entity {

   public Vessel(Image image) throws SlickException {
      vesselImage = image;
      body = null;
   }

   @Override
   public void render(Viewport view) {
      if (body != null) {
         view.showImage(vesselImage,
                        body.getPosition(),
                        body.getAngle(),
                        size);
      }
   }

   @Override
   public void update(int delta) {
      // Do nothing
   }

   @Override
   public void spawn(World world, Vec2 position) {
      body = world.createBody(GetBodyDef(position));
      body.createFixture(GetFixureDef());
   }

   private BodyDef GetBodyDef(Vec2 position) {
      BodyDef def = new BodyDef();
      def.type = BodyType.DYNAMIC;
      def.position.set(position);
      return def;
   }

   private FixtureDef GetFixureDef() {
      PolygonShape shape = new PolygonShape();
      shape.setAsBox(size.x/2.0f, size.y/2.0f);
      FixtureDef def = new FixtureDef();
      def.density = 1000.0f;
      def.shape = shape;
      def.friction = 0.3f;
      return def;
   }

   final Vec2 size = new Vec2(4.0f, 4.0f);
   private Image vesselImage;
   private Body body;
}
