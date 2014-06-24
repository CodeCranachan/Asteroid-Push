package org.codecranachan.asteroidpush.workshop.spaceship;

import java.util.List;
import java.util.Set;

import org.codecranachan.asteroidpush.entities.Entity;
import org.codecranachan.asteroidpush.entities.EntityFactory;
import org.codecranachan.asteroidpush.workshop.OrthogonalCoordinate;
import org.codecranachan.asteroidpush.workshop.assembly.Part;
import org.codecranachan.asteroidpush.workshop.tokenboard.Token;
import org.codecranachan.asteroidpush.workshop.tokenboard.Board;
import org.codecranachan.asteroidpush.workshop.tokenboard.Placement;
import org.jbox2d.common.Vec2;
import org.jgrapht.alg.ConnectivityInspector;

public class SpaceshipFactory implements EntityFactory {
   private Board<Part> blueprint;

   public SpaceshipFactory(Board<Part> blueprint) {
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
      for (Token<Part> token : blueprint.getTokens()) {
         Part part = token.getData();
         Placement placement = token.getPlacement();
         for (Hardpoint hardpoint : part.getHardpoints()) {
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