package org.codecranachan.asteroidpush.workshop;

import java.util.HashSet;
import java.util.Set;

public class TokenPlacement<TokenData> {
   private Token<TokenData> token;
   private int orientation;
   private OrthogonalCoordinate pivotCoordinate;
   private Set<OrthogonalCoordinate> occupiedCoordinates; 

   public TokenPlacement(int orientation, OrthogonalCoordinate pivotCoordinate,
         Token<TokenData> token) {
      this.orientation = orientation;
      this.pivotCoordinate = pivotCoordinate;
      this.token = token;
      
      Set<OrthogonalCoordinate> coordinates = token.getShape().getOccupiedCoordinates();
      coordinates = rotateShape(coordinates, orientation);
      coordinates = translateShape(coordinates, pivotCoordinate);
      occupiedCoordinates = coordinates;
   }

   public int getOrientation() {
      return orientation;
   }

   public OrthogonalCoordinate getPivotCoordinate() {
      return pivotCoordinate;
   }

   public Token<TokenData> getToken() {
      return token;
   }
   
   public Set<OrthogonalCoordinate> getOccupiedCoordinates() {
      return occupiedCoordinates;
   }
   
   private Set<OrthogonalCoordinate> translateShape(Set<OrthogonalCoordinate> shape,
                                                    OrthogonalCoordinate offset) {
      Set<OrthogonalCoordinate> cooked = new HashSet<OrthogonalCoordinate>();
      for (OrthogonalCoordinate original : shape) {
         OrthogonalCoordinate translated = new OrthogonalCoordinate(original);
         translated.move(offset.getX(), offset.getY());
         cooked.add(translated);
      }
      return cooked;
   }
   
   private Set<OrthogonalCoordinate> rotateShape(Set<OrthogonalCoordinate> shape,
                                                 int quarterTurns) {
      Set<OrthogonalCoordinate> cooked = new HashSet<OrthogonalCoordinate>();
      for (OrthogonalCoordinate original : shape) {
         OrthogonalCoordinate rotated = new OrthogonalCoordinate(original);
         rotated.turn(quarterTurns);
         cooked.add(rotated);
      }
      return cooked;
   }
}