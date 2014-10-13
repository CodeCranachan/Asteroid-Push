package org.codecranachan.asteroidpush.base.workshop.tokenboard;

import org.codecranachan.asteroidpush.utils.OrthogonalCoordinate;

public class Placement {
   private int orientation;
   private OrthogonalCoordinate pivotCoordinate;

   public Placement(int orientation, OrthogonalCoordinate pivotCoordinate) {
      this.orientation = orientation;
      this.pivotCoordinate = pivotCoordinate;
   }

   public Placement clone() {
      return new Placement(orientation, new OrthogonalCoordinate(
            pivotCoordinate));
   }

   public int getOrientation() {
      return orientation;
   }

   public OrthogonalCoordinate getPivotCoordinate() {
      return pivotCoordinate;
   }
   
   public void rotateClockwise() {
      orientation -= 1;
   }
   
   public void rotateAnticlockwise() {
      orientation += 1;
   }
}