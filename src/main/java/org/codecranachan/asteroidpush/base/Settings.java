package org.codecranachan.asteroidpush.base;

import org.codecranachan.asteroidpush.base.input.ControlItem;
import org.codecranachan.asteroidpush.base.input.slick2d.Slick2dInputMapper;
import org.codecranachan.asteroidpush.base.workshop.BlueprintCollection;
import org.newdawn.slick.Input;

public class Settings {
   private BlueprintCollection blueprints;

   public Settings() {
      blueprints = new BlueprintCollection();
   }

   public BlueprintCollection getBlueprints() {
      return blueprints;
   }

   public Slick2dInputMapper getDefaultBindings() {
      Slick2dInputMapper bindings = new Slick2dInputMapper();

      bindings.bindKey(Input.KEY_W, ControlItem.FORWARD_THRUST);
      bindings.bindKey(Input.KEY_A, ControlItem.ROTATE_COUNTERCLOCKWISE);
      bindings.bindKey(Input.KEY_S, ControlItem.ROTATE_CLOCKWISE);
      bindings.bindKey(Input.KEY_D, ControlItem.BACKWARD_THRUST);
      bindings.bindKey(Input.KEY_Q, ControlItem.LEFT_THRUST);
      bindings.bindKey(Input.KEY_E, ControlItem.RIGHT_THRUST);
      bindings.bindKey(Input.KEY_SPACE, ControlItem.FIRE_PRIMARY);
      
      return bindings;
   }

}
