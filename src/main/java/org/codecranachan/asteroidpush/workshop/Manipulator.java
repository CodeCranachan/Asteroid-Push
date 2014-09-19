package org.codecranachan.asteroidpush.workshop;

import org.codecranachan.asteroidpush.workshop.assembly.Part;
import org.codecranachan.asteroidpush.workshop.tokenboard.Placement;
import org.codecranachan.asteroidpush.workshop.tokenboard.Token;

public class Manipulator {
   private Blueprint blueprint;
   private Part selection;
   private Placement prospectivePlacement;

   public Manipulator() {
      this.blueprint = null;
      this.selection = null;
      this.prospectivePlacement = new Placement(0, new OrthogonalCoordinate());
   }

   public Part getSelection() {
      return selection;
   }

   public Placement getProspectivePlacement() {
      return prospectivePlacement;
   }

   public void setSelection(Part part) {
      this.selection = part;
   }

   public void clearSelection() {
      selection = null;
   }

   public Blueprint getBlueprint() {
      return blueprint;
   }

   public void setBlueprint(Blueprint blueprint) {
      this.blueprint = blueprint;
   }

   public void rotateSelectionLeft() {
      prospectivePlacement.rotateAnticlockwise();
   }

   public void rotateSelectionRight() {
      prospectivePlacement.rotateClockwise();
   }

   public boolean place(OrthogonalCoordinate pivot) {
      Token token = new Token(selection);
      token.setPlacement(new Placement(prospectivePlacement.getOrientation(),
            pivot));
      if (blueprint.canPlace(token)) {
         blueprint.place(token);
         return true;
      } else {
         return false;
      }
   }
   
   

   public void erase(OrthogonalCoordinate pivot) {
      // ignore the return value since we are not keeping the
      // token that was picked up
      blueprint.pick(pivot);
   }

   public void pick(OrthogonalCoordinate pivot) {
      Token token = blueprint.pick(pivot);
      if (token != null) {
         selection = (Part) token.getData();
         prospectivePlacement = token.getPlacement();
      } else {
         selection = null;
      }
   }

   public ManipulatedArea getManipulatedArea() {
      return new ManipulatedArea(blueprint);
   }

}
