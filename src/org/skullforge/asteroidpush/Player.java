package org.skullforge.asteroidpush;

import org.jbox2d.common.Vec2;
import org.skullforge.asteroidpush.designer.Blueprint;
import org.skullforge.asteroidpush.designer.catalogue.MetalBlockFactory;
import org.skullforge.asteroidpush.designer.catalogue.SteamThrusterFactory;
import org.skullforge.asteroidpush.designer.data.ModuleData;
import org.skullforge.asteroidpush.designer.grid.Facing;
import org.skullforge.asteroidpush.designer.grid.Placement;
import org.skullforge.asteroidpush.entities.Entity;
import org.skullforge.asteroidpush.ui.PositionTracker;

public class Player implements PositionTracker {

   public Player() {
      this.controlDescription = new StringBuffer("No Control");
      this.currentShip = null;
      this.shipDesign = new Blueprint();
      this.controller = new SignalController();

      ModuleData thruster = SteamThrusterFactory.createData();
      ModuleData block = MetalBlockFactory.createData();

      shipDesign.addModule(new Placement(0, 0, Facing.FORWARD), thruster);
      shipDesign.addModule(new Placement(0, 2, Facing.FORWARD), thruster);
      shipDesign.addModule(new Placement(0, 4, Facing.FORWARD), thruster);
      shipDesign.addModule(new Placement(1, 0, Facing.FORWARD), block);
      shipDesign.addModule(new Placement(1, 1, Facing.FORWARD), block);
      shipDesign.addModule(new Placement(1, 2, Facing.FORWARD), block);
      shipDesign.addModule(new Placement(1, 3, Facing.FORWARD), block);
      shipDesign.addModule(new Placement(1, 4, Facing.FORWARD), block);
      shipDesign.addModule(new Placement(2, 1, Facing.FORWARD), block);
      shipDesign.addModule(new Placement(2, 2, Facing.FORWARD), block);
      shipDesign.addModule(new Placement(2, 3, Facing.FORWARD), block);
   }

   @Override
   public Vec2 getCenter() {
      if (currentShip == null) {
         return new Vec2(0.0f, 0.0f);
      } else {
         return currentShip.getCenterOfInterest();
      }
   }

   @Override
   public float getRadius() {
      if (currentShip == null) {
         return 75.0f;
      } else {
         return currentShip.getRadiusOfInterest();
      }
   }

   public Blueprint getShipDesign() {
      return shipDesign;
   }

   public void setShip(Entity ship) {
      currentShip = ship;
   }

   public String getName() {
      return "LocalPlayer";
   }

   public StringBuffer getControls() {
      return controlDescription;
   }

   public SignalController getController() {
      return controller;
   }

   public void handleKeyUp(int key) {
      controller.keyUp(key);
      controlDescription.delete(0, controlDescription.length());
      controlDescription.append(controller.getControllerDescription());
   }

   public void handleKeyDown(int key) {
      controller.keyDown(key);
      controlDescription.delete(0, controlDescription.length());
      controlDescription.append(controller.getControllerDescription());
   }

   private SignalController controller;
   private Blueprint shipDesign;
   private Entity currentShip;
   private StringBuffer controlDescription;
}
