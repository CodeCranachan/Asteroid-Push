package org.codecranachan.asteroidpush.state.ui;

import org.codecranachan.asteroidpush.workshop.Blueprint;
import org.codecranachan.asteroidpush.workshop.BlueprintCollection;
import org.codecranachan.asteroidpush.workshop.ManipulatedArea;
import org.codecranachan.asteroidpush.workshop.Manipulator;
import org.codecranachan.asteroidpush.workshop.OrthogonalCoordinate;
import org.codecranachan.asteroidpush.workshop.PartSelector;
import org.codecranachan.asteroidpush.workshop.parts.TokenFactory;

public class WorkshopCoordinator {
   private Manipulator manipulator;
   private PartSelector selector;
   private BlueprintCollection collection;

   public WorkshopCoordinator(Manipulator manipulator, PartSelector selector,
         BlueprintCollection collection) {
      this.manipulator = manipulator;
      this.selector = selector;
      this.collection = collection;
      
      manipulator.setBlueprint(collection.getActive());
      selector.clearSelection();
   }

   public void selectPart(int index) {
      selector.selectByIndex(index);
      TokenFactory factory = selector.getSelected();
      manipulator.setSelection(factory.createToken());
   }

   public void createNewBlueprint() {
      selector.clearSelection();
      collection.addBlueprint(new Blueprint());
      manipulator.setBlueprint(collection.getActive());
   }

   public void pickSquare(OrthogonalCoordinate coordinate) {
      if (manipulator.getSelection() == null) {
         manipulator.pick(coordinate);
      } else {
         manipulator.place(coordinate);
      }
   }
   
   public void clearSquare(OrthogonalCoordinate coordinate) {
      if (manipulator.getSelection() == null) {
         manipulator.erase(coordinate);
      } else {
         manipulator.clearSelection();
      }
   }

   public Blueprint getManipulatedBlueprint() {
      return manipulator.getBlueprint();
   }
   
   public ManipulatedArea getManipulatedArea() {
      return manipulator.getManipulatedArea();
   }
}
