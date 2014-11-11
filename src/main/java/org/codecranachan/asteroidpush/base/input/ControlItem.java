package org.codecranachan.asteroidpush.base.input;

public enum ControlItem {
   NOT_BOUND(""),
   FORWARD_THRUST("forward"),
   BACKWARD_THRUST("backward"),
   LEFT_THRUST("left"),
   RIGHT_THRUST("right"),
   ROTATE_CLOCKWISE("clockwise"),
   ROTATE_COUNTERCLOCKWISE("counterclockwise"),
   FIRE_PRIMARY("fire");
   
   private final String name;
   
   private ControlItem(String name) {
      this.name = name;
   }
   
   public String getCommandName() {
      return name;
   }
}
