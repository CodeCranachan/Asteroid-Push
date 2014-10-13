package org.codecranachan.asteroidpush.base.workshop;

import java.util.Collection;
import java.util.LinkedList;

import org.codecranachan.asteroidpush.base.workshop.tokenboard.Board;
import org.codecranachan.asteroidpush.base.workshop.tokenboard.Token;
import org.codecranachan.asteroidpush.utils.OrthogonalCoordinate;

public class Blueprint {
   private String name;
   private Board plan;

   public Blueprint() {
      name = new String();
      plan = new Board();
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Board getPlan() {
      return plan;
   }
   
   public boolean canPlace(Token token) {
      return getConflictingCoordinates(token).isEmpty();
   }

   public void place(Token token) {
      plan.place(token);
   }

   public Token pick(OrthogonalCoordinate location) {
      return plan.pick(location);
   }

   public Collection<OrthogonalCoordinate> getConflictingCoordinates(Token token) {
      Collection<OrthogonalCoordinate> conflicts = new LinkedList<OrthogonalCoordinate>();
      for (OrthogonalCoordinate coord : token.getOccupiedCoordinates()) {
         if (plan.inspect(coord) != null) {
            conflicts.add(coord);
         }
      }
      return conflicts;
   }
   
   public Collection<Token> getTokens() {
      return plan.getTokens();
   }

   public OrthogonalCoordinate getBottomLeftCorner() {
      return plan.getBottomLeftCorner();
   }

   public OrthogonalCoordinate getTopRightCorner() {
      return plan.getTopRightCorner();
   }
}
