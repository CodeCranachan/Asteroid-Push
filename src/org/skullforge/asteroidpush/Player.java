package org.skullforge.asteroidpush;

import org.jbox2d.common.Vec2;
import org.skullforge.asteroidpush.designer.ControlModule;
import org.skullforge.asteroidpush.designer.GridCoordinate;
import org.skullforge.asteroidpush.designer.ShipDesign;
import org.skullforge.asteroidpush.doodads.Doodad;
import org.skullforge.asteroidpush.ui.PositionTracker;

public class Player implements PositionTracker {

   public Player() {
      this.currentShip = null;
      this.shipDesign = new ShipDesign();
      shipDesign.addModule(new GridCoordinate(2, 2), new ControlModule());
      shipDesign.addModule(new GridCoordinate(1, 2), new ControlModule());
      shipDesign.addModule(new GridCoordinate(3, 2), new ControlModule());
      shipDesign.addModule(new GridCoordinate(2, 1), new ControlModule());
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

   private ShipDesign shipDesign;
   private Doodad currentShip;
}
