package org.codecranachan.asteroidpush.workshop;

import java.util.Collection;
import java.util.LinkedList;

import org.codecranachan.asteroidpush.workshop.assembly.Part;
import org.codecranachan.asteroidpush.workshop.tokenboard.Board;
import org.codecranachan.asteroidpush.workshop.tokenboard.Token;

public class Blueprint {
   private String name;
   private Board<Part> plan;

   public Blueprint() {
      name = new String();
      plan = new Board<Part>();
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public boolean canPlace(Token<Part> token) {
      return getConflictingCoordinates(token).isEmpty();
   }

   public void place(Token<Part> token) {
      plan.place(token.clone());
   }

   public Token<Part> pick(OrthogonalCoordinate location) {
      return plan.pick(location);
   }

   public Collection<OrthogonalCoordinate> getConflictingCoordinates(Token<Part> token) {
      Collection<OrthogonalCoordinate> conflicts = new LinkedList<OrthogonalCoordinate>();
      for (OrthogonalCoordinate coord : token.getOccupiedCoordinates()) {
         if (plan.inspect(coord) != null) {
            conflicts.add(coord);
         }
      }
      return conflicts;
   }
   
   public Collection<Token<Part>> getTokens() {
      return plan.getTokens();
   }

   public OrthogonalCoordinate getBottomLeftCorner() {
      return plan.getBottomLeftCorner();
   }

   public OrthogonalCoordinate getTopRightCorner() {
      return plan.getTopRightCorner();
   }
}
