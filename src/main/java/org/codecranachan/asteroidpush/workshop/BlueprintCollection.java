package org.codecranachan.asteroidpush.workshop;

import java.util.LinkedList;
import java.util.List;

public class BlueprintCollection {
   private List<Blueprint> blueprints;
   private Blueprint active;

   public BlueprintCollection() {
      blueprints = new LinkedList<Blueprint>();
      active = null;
   }

   public void addBlueprint(Blueprint blueprint) {
      blueprints.add(blueprint);

      if (active == null) {
         active = blueprint;
      }
   }

   public void removeBlueprint(Blueprint blueprint) {
      if (active == blueprint) {
         if (blueprints.size() > 1) {
            selectNextBlueprint();
         } else {
            active = null;
         }
      }

      blueprints.remove(blueprint);
   }

   public Blueprint getActive() {
      return active;
   }

   public void selectBlueprint(int index) {
      if (blueprints.isEmpty()) {
         throw new IllegalArgumentException(
               "tried to select blueprints on empty list");
      }

      active = blueprints.get(index);
   }

   public void selectNextBlueprint() {
      if (blueprints.isEmpty()) {
         throw new IllegalArgumentException(
               "tried to select blueprints on empty list");
      }

      if (active == null) {
         selectBlueprint(0);
      } else {
         int index = blueprints.indexOf(active);
         selectBlueprint((index + 1) % blueprints.size());
      }
   }

   public void selectPreviousBlueprint() {
      if (blueprints.isEmpty()) {
         throw new IllegalArgumentException(
               "tried to select blueprints on empty list");
      }

      if (active == null) {
         active = blueprints.get(0);
      } else {
         int index = blueprints.indexOf(active);
         selectBlueprint((index + blueprints.size() - 1) % blueprints.size());
      }
   }
}
