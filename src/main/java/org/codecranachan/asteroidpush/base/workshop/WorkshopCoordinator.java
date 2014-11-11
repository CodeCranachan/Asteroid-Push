package org.codecranachan.asteroidpush.base.workshop;

import java.util.Vector;

import org.codecranachan.asteroidpush.base.workshop.assembly.Part;
import org.codecranachan.asteroidpush.base.workshop.tokenboard.Placement;
import org.codecranachan.asteroidpush.utils.OrthogonalCoordinate;

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

   public void createNewBlueprint() {
      selector.clearSelection();
      collection.addBlueprint(new Blueprint());
      manipulator.setBlueprint(collection.getActive());
   }

   public void pickSquare(OrthogonalCoordinate coordinate) {
      if (manipulator.getSelection() == null) {
         manipulator.pick(coordinate);
      } else {
         boolean partPlaced = manipulator.place(coordinate);
         if (partPlaced) {
            manipulator.setSelection(selector.getNewPart());
         }
      }
   }

   public void clearSquare(OrthogonalCoordinate coordinate) {
      if (manipulator.getSelection() == null) {
         manipulator.erase(coordinate);
      } else {
         clearSelection();
      }
   }

   public Blueprint getManipulatedBlueprint() {
      return manipulator.getBlueprint();
   }

   public ManipulatedArea getManipulatedArea() {
      return manipulator.getManipulatedArea();
   }

   public Vector<PartFactory> getAvailablePartFactories() {
      return selector.getPartFactories();
   }

   public void selectPart(int index) {
      selector.selectByIndex(index);
      PartFactory factory = selector.getSelected();
      if (factory == null) {
         manipulator.clearSelection();
      } else {
         manipulator.setSelection(factory.createPart());
      }
   }

   public void clearSelection() {
      manipulator.clearSelection();
      selector.clearSelection();
   }

   public Part getSelectedPart() {
      return manipulator.getSelection();
   }

   public PartFactory getSelectedPartFactory() {
      return selector.getSelected();
   }

   public void rotatePartRight() {
      manipulator.rotateSelectionRight();
   }

   public void rotatePartLeft() {
      manipulator.rotateSelectionLeft();
   }

   public Placement getPartSelectedPartPlacement() {
      return manipulator.getProspectivePlacement();
   }
}
