package org.skullforge.asteroidpush.designer;

import org.skullforge.asteroidpush.designer.catalogue.MetalSpikeFactory;
import org.skullforge.asteroidpush.designer.data.ModuleData;
import org.skullforge.asteroidpush.designer.grid.Facing;
import org.skullforge.asteroidpush.designer.grid.GridVector;
import org.skullforge.asteroidpush.designer.grid.Placement;
import org.skullforge.asteroidpush.designer.grid.Rotation;

public class BlueprintManipulator {
   Blueprint blueprint;
   Rotation rotation;
   ModuleData data;

   public BlueprintManipulator(Blueprint subject) {
      this.blueprint = subject;
      this.rotation = new Rotation(Facing.FORWARD);
      this.data = MetalSpikeFactory.createData();
   }

   public Rotation getRotation() {
      return this.rotation;
   }

   public ModuleData getSelection() {
      return this.data;
   }

   public void placeModule(GridVector coordinate) {
      Placement placement = new Placement(coordinate.getX(), coordinate.getY(), rotation.getFacing());
      if (blueprint.canAddModule(placement, data)) {
         blueprint.addModule(placement, data);
      }
   }
   
   public void removeModule(GridVector coordinate) {
      blueprint.removeModule(coordinate);
   }

   public void rotateSelectionLeft() {
      rotation.turnAnticlockwise();
   }

   public void rotateSelectionRight() {
      rotation.turnClockwise();
   }

   public void select(ModuleData data) {
      this.data = data;
   }
}
