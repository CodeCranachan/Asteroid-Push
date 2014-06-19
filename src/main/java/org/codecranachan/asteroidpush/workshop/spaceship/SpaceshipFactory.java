package org.codecranachan.asteroidpush.workshop.spaceship;

import java.util.List;
import java.util.Set;

import org.codecranachan.asteroidpush.entities.Entity;
import org.codecranachan.asteroidpush.entities.EntityFactory;
import org.codecranachan.asteroidpush.workshop.OrthogonalCoordinate;
import org.codecranachan.asteroidpush.workshop.Part;
import org.codecranachan.asteroidpush.workshop.TokenBoard;
import org.codecranachan.asteroidpush.workshop.TokenPlacement;
import org.jbox2d.common.Vec2;
import org.jgrapht.alg.ConnectivityInspector;

public class SpaceshipFactory implements EntityFactory {
   private TokenBoard<Part> blueprint;

   public SpaceshipFactory(TokenBoard<Part> blueprint) {
      assert (blueprint != null);
      this.blueprint = blueprint;
   }

   public Entity createEntity(Vec2 location) {
      SpaceshipGraph skeleton = assembleSkeleton();
      ConnectivityInspector<Hardpoint, Hardlink> inspector = new ConnectivityInspector<Hardpoint, Hardlink>(
            skeleton);
      // Find independent components and create bodies for them
      List<Set<Hardpoint>> subskeleton = inspector.connectedSets();
      // Iterate through all hardpoints in a body and call onCreation
      // Iterate through all soft links and call onCreation
      // Assemble entity and return

      // TODO Auto-generated method stub
      return null;
   }

   private SpaceshipGraph assembleSkeleton() {
      SpaceshipGraph skeleton = new SpaceshipGraph();
      for (TokenPlacement<Part> placement : blueprint.getPlacements()) {
         Part part = placement.getToken().getData();
         for (Hardpoint hardpoint : part.hardpoints) {
            for (OrthogonalCoordinate relLink : hardpoint.getHardLinks()) {
               // transform relative link coordinate to absolute link coordinate
               // using token placement
               OrthogonalCoordinate absLink = new OrthogonalCoordinate(relLink);
               // rotate around coordinate 1/1 (central hardpoint of pivot
               // token) and then translate it according to the token placement
               absLink.move(-1, -1);
               absLink.turn(placement.getOrientation());
               absLink.move(1 + placement.getPivotCoordinate().getX(),
                            1 + placement.getPivotCoordinate().getY());
               skeleton.attachHardpoint(hardpoint, absLink);
            }
         }
      }
      return skeleton;
   }
}