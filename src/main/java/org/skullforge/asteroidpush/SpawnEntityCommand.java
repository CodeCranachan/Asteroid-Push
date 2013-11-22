package org.skullforge.asteroidpush;

import org.jbox2d.common.Vec2;
import org.skullforge.asteroidpush.entities.Entity;
import org.skullforge.asteroidpush.entities.EntityFactory;

public class SpawnEntityCommand implements SimulatorCommand {

   private EntityFactory factory;
   private Vec2 location;
   private Player owner;

   public SpawnEntityCommand(EntityFactory factory, Vec2 location, Player owner) {
      this.factory = factory;
      this.location = location;
      this.owner = owner;
   }

   public void execute(Simulator simulator) {
      Entity entity = factory.createEntity(location);
      entity.setOwner(owner);
      if (owner != null) {
         owner.setShip(entity);
      }
      simulator.addEntity(entity);
   }

}