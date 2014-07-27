package org.codecranachan.asteroidpush.workshop;

import java.util.Collection;

import org.codecranachan.asteroidpush.workshop.assembly.Part;
import org.codecranachan.asteroidpush.workshop.tokenboard.Placement;
import org.codecranachan.asteroidpush.workshop.tokenboard.Token;

public class Manipulator {
   private Blueprint blueprint;
   private Token<Part> selection;

   public Manipulator() {
      this.blueprint = null;
      this.selection = null;
   }

   public Token<Part> getSelection() {
      return selection;
   }

   public void setSelection(Token<Part> token) {
      Placement place;
      if (selection == null)
         place = new Placement(0, new OrthogonalCoordinate());
      else
         place = selection.getPlacement();
      token.setPlacement(place);
      this.selection = token;
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
      if (selection != null)
         selection.getPlacement().rotateClockwise();
   }

   public void rotateSelectionRight() {
      if (selection != null)
         selection.getPlacement().rotateClockwise();
   }

   public void place(OrthogonalCoordinate pivot) {
      if (blueprint.canPlace(selection)) {
         blueprint.place(selection);
      }
   }

   public void erase(OrthogonalCoordinate pivot) {
      // ignore the return value since we are not keeping the
      // token that was picked up
      blueprint.pick(pivot);
   }

   public void pick(OrthogonalCoordinate pivot) {
      selection = blueprint.pick(pivot);
   }

   public ManipulatedArea getManipulatedArea() {
      return new ManipulatedArea(blueprint);
   }
   
   public Collection<Token<Part>> getTokens() {
      return blueprint.getTokens();
   }
}
