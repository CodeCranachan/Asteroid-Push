package org.skullforge.asteroidpush.doodads;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.skullforge.asteroidpush.Player;
import org.skullforge.asteroidpush.doodads.appearances.Appearance;
import org.skullforge.asteroidpush.doodads.appearances.NullAppearance;
import org.skullforge.asteroidpush.doodads.assemblies.Assembly;
import org.skullforge.asteroidpush.doodads.assemblies.NullChassis;
import org.skullforge.asteroidpush.doodads.logic.Logic;
import org.skullforge.asteroidpush.doodads.logic.NullLogic;
import org.skullforge.asteroidpush.ui.Renderer;

public class Doodad {
   public Doodad() {
      this.assembly = new NullChassis();
      this.appearance = new NullAppearance();
      this.logic = new NullLogic();
      this.spawned = false;
      this.owner = null;
   }

   public void setAssembly(Assembly assembly) {
      this.assembly = assembly;
   }

   public void setLogic(Logic logic) {
      this.logic = logic;
   }

   public void setAppearance(Appearance appearance) {
      this.appearance = appearance;
   }

   public void spawn(World world) {
      assembly.spawn(world);
      spawned = true;
   }

   public void despawn(World world) {
      assembly.despawn(world);
      spawned = false;
   }

   public void render(Renderer renderer) {
      renderer.draw(appearance);
   }

   public void update(int frameNumber) {
      if (owner == null) {
         logic.update(frameNumber, null);
      } else {
         logic.update(frameNumber, owner.getController());
      }
   }

   public Vec2 getCenterOfInterest() {
      Vec2 position = new Vec2(0.0f, 0.0f);
      float numberOfBodies = 0.0f;
      for (Body body : assembly.getBodies()) {
         position.addLocal(body.getWorldCenter());
         ++numberOfBodies;
      }
      return position.mul(1.0f / numberOfBodies);
   }

   public float getRadiusOfInterest() {
      return 35.0f;
   }

   public boolean isSpawned() {
      return this.spawned;
   }

   public void setOwner(Player owner) {
      this.owner = owner;
   }

   private Assembly assembly;
   private Logic logic;
   private Appearance appearance;
   private boolean spawned;
   private Player owner;
}
