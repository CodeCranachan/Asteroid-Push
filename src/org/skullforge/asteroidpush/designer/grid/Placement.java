package org.skullforge.asteroidpush.designer.grid;

import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;

public class Placement {
   public Placement() {
      coordinate = new GridVector();
      rotation = new Rotation();
   }
   
   public Placement(int x, int y, Facing facing) {
      this.coordinate = new GridVector(x, y);
      this.rotation = new Rotation(facing);
   }
   
   public void set(Placement placement) {
      this.coordinate.set(placement.getCoordinate());
      this.rotation = placement.rotation;
   }
   
   public GridVector getCoordinate() {
      return coordinate;
   }

   public void setCoordinate(GridVector coordinate) {
      this.coordinate.set(coordinate);
   }

   public Rotation getRotation() {
      return rotation;
   }

   public void setRotation(Rotation rotation) {
      this.rotation = new Rotation(rotation);
   }
   
   public Transform getTransform() {
      Transform transform = new Transform();
      transform.set(new Vec2(coordinate.getX(), coordinate.getY()), rotation.getRadians());
      return transform;
   }

   private GridVector coordinate;
   private Rotation rotation;
}
