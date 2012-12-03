package org.skullforge.asteroidpush;

import org.jbox2d.common.Vec2;
import org.skullforge.asteroidpush.designer.ShipDesign;
import org.skullforge.asteroidpush.designer.grid.Facing;
import org.skullforge.asteroidpush.designer.grid.Placement;
import org.skullforge.asteroidpush.designer.modules.MetalBlock;
import org.skullforge.asteroidpush.designer.modules.SteamThruster;
import org.skullforge.asteroidpush.doodads.Doodad;
import org.skullforge.asteroidpush.ui.PositionTracker;

public class Player implements PositionTracker {

   public Player() {
      this.controlDescription = new StringBuffer("No Control");
      this.currentShip = null;
      this.shipDesign = new ShipDesign();
      this.controller = new SignalController();
      shipDesign.addModule(new Placement(0, 0, Facing.FORWARD), new SteamThruster());
      shipDesign.addModule(new Placement(0, 1, Facing.FORWARD), new SteamThruster());
      shipDesign.addModule(new Placement(0, 2, Facing.FORWARD), new SteamThruster());
      shipDesign.addModule(new Placement(0, 3, Facing.FORWARD), new SteamThruster());
      shipDesign.addModule(new Placement(0, 4, Facing.FORWARD), new SteamThruster());
      shipDesign.addModule(new Placement(1, 0, Facing.FORWARD), new MetalBlock());
      shipDesign.addModule(new Placement(1, 1, Facing.FORWARD), new MetalBlock());
      shipDesign.addModule(new Placement(1, 2, Facing.FORWARD), new MetalBlock());
      shipDesign.addModule(new Placement(1, 3, Facing.FORWARD), new MetalBlock());
      shipDesign.addModule(new Placement(1, 4, Facing.FORWARD), new MetalBlock());
      shipDesign.addModule(new Placement(2, 1, Facing.FORWARD), new MetalBlock());
      shipDesign.addModule(new Placement(2, 2, Facing.FORWARD), new MetalBlock());
      shipDesign.addModule(new Placement(2, 3, Facing.FORWARD), new MetalBlock());
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

   public ShipDesign getShipDesign() {
      return shipDesign;
   }

   public void setShip(Doodad ship) {
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
   private ShipDesign shipDesign;
   private Doodad currentShip;
   private StringBuffer controlDescription;
}
