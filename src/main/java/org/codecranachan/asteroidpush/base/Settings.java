package org.codecranachan.asteroidpush.base;

import org.codecranachan.asteroidpush.base.workshop.BlueprintCollection;

public class Settings {
   private BlueprintCollection blueprints;

   public Settings() {
      blueprints = new BlueprintCollection();
   }

   public BlueprintCollection getBlueprints() {
      return blueprints;
   }

}
