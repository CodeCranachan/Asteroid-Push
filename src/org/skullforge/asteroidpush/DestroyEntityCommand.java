package org.skullforge.asteroidpush;

import org.skullforge.asteroidpush.entities.Entity;

public class DestroyEntityCommand implements SimulatorCommand {

   private Entity entity;

   public DestroyEntityCommand(Entity entity) {
      this.entity = entity;
   }

   @Override
   public void execute(Simulator simulator) {
      entity.destroy();
      simulator.removeEntity(entity);
   }

}