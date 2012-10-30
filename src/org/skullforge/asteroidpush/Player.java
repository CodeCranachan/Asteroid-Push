package org.skullforge.asteroidpush;

import org.jbox2d.common.Vec2;
import org.skullforge.asteroidpush.designer.ControlModule;
import org.skullforge.asteroidpush.designer.GridCoordinate;
import org.skullforge.asteroidpush.designer.ShipDesign;
import org.skullforge.asteroidpush.doodads.Doodad;
import org.skullforge.asteroidpush.ui.PositionTracker;

public class Player implements PositionTracker {

   public Player() {
      this.controlDescription = new StringBuffer("No Control");
      this.currentShip = null;
      this.shipDesign = new ShipDesign();
      this.controller = new SignalController();
      shipDesign.addModule(new GridCoordinate(0, 1), new ControlModule());
      shipDesign.addModule(new GridCoordinate(0, 3), new ControlModule());
      shipDesign.addModule(new GridCoordinate(1, 1), new ControlModule());
      shipDesign.addModule(new GridCoordinate(1, 2), new ControlModule());
      shipDesign.addModule(new GridCoordinate(1, 3), new ControlModule());
      shipDesign.addModule(new GridCoordinate(2, 2), new ControlModule());
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
