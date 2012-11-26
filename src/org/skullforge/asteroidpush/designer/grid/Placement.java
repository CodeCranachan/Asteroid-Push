package org.skullforge.asteroidpush.designer.grid;

public class Placement {
   public Placement() {
      coordinate = new Coordinate();
      rotation = new Rotation();
   }
   
   public Placement(int x, int y, Facing facing) {
      this.coordinate = new Coordinate(x, y);
      this.rotation = new Rotation(facing);
   }
   
   public void set(Placement placement) {
      this.coordinate.set(placement.getCoordinate());
      this.rotation = placement.rotation;
   }
   
   public Coordinate getCoordinate() {
      return coordinate;
   }

   public void setCoordinate(Coordinate coordinate) {
      this.coordinate.set(coordinate);
   }

   public Rotation getRotation() {
      return rotation;
   }

   public void setRotation(Rotation rotation) {
      this.rotation = rotation;
   }

   private Coordinate coordinate;
   private Rotation rotation;
}
