package org.skullforge.asteroidpush.doodads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

import org.skullforge.asteroidpush.designer.data.ComponentData;
import org.skullforge.asteroidpush.designer.grid.GridVector;
import org.skullforge.asteroidpush.designer.grid.Placement;

public class GlueMap {
   public GlueMap() {
      xAxisGlue = new HashMap<GridVector, Glue>();
      yAxisGlue = new HashMap<GridVector, Glue>();
      gridGlue = new ArrayList<Glue>();
   }

   public void clear() {
      clearMap(xAxisGlue);
      clearMap(yAxisGlue);
      clearArray(gridGlue);
   }

   private void clearMap(HashMap<GridVector, Glue> map) {
      for (Glue glue : map.values()) {
         glue.clear();
      }
      map.clear();
   }

   private void clearArray(ArrayList<Glue> array) {
      for (Glue glue : array) {
         glue.clear();
      }
      array.clear();
   }

   public void putPart(Part part) {
      Glue gluedPart = new Glue(part);
      ComponentData component = part.getComponent();
      Placement placement = part.getPlacement();

      for (GridVector direction : component.getWeldDirections()) {
         GridVector rotatedWeld = direction.turn(placement.getRotation());
         if (rotatedWeld.getX() > 0) {
            putInXAxis(placement.getCoordinate(), gluedPart);
         }
         if (rotatedWeld.getX() < 0) {
            GridVector coord = placement.getCoordinate();
            GridVector back = new GridVector(-1, 0);
            putInXAxis(coord.add(back), gluedPart);
         }
         if (rotatedWeld.getY() > 0) {
            putInYAxis(placement.getCoordinate(), gluedPart);
         }
         if (rotatedWeld.getY() < 0) {
            GridVector coord = placement.getCoordinate();
            GridVector back = new GridVector(0, -1);
            putInYAxis(coord.add(back), gluedPart);
         }
      }

      gridGlue.add(gluedPart);
   }

   public Collection<Collection<Part>> getGlueGroups() {
      resetVisitedFlags();

      ArrayList<Collection<Part>> glueGroups = new ArrayList<Collection<Part>>();

      for (Glue glue : gridGlue) {
         LinkedList<Glue> openLinks = new LinkedList<Glue>();
         if (!glue.isVisited()) {
            openLinks.add(glue);
            LinkedList<Part> gluedParts = new LinkedList<Part>();
            walkLinks(openLinks, gluedParts);
            glueGroups.add(gluedParts);
         }
      }

      return glueGroups;
   }

   private void walkLinks(LinkedList<Glue> openLinks,
                          LinkedList<Part> gluedParts) {
      while (!openLinks.isEmpty()) {
         Glue current = openLinks.pop();
         if (!current.isVisited()) {
            current.setVisited();
            if (current.getPart() != null) {
               gluedParts.push(current.getPart());
            }
            for (Glue link : current.getLinks()) {
               if (!link.isVisited()) {
                  openLinks.push(link);
               }
            }
         }
      }
   }

   private void resetVisitedFlags() {
      for (Glue glue : xAxisGlue.values()) {
         glue.resetVisited();
      }
      for (Glue glue : yAxisGlue.values()) {
         glue.resetVisited();
      }
      for (Glue glue : gridGlue) {
         glue.resetVisited();
      }
   }

   private void putInXAxis(GridVector coordinate, Glue glue) {
      Glue other = getGlue(coordinate, xAxisGlue);
      Glue.link(glue, other);
   }

   private void putInYAxis(GridVector coordinate, Glue glue) {
      Glue other = getGlue(coordinate, yAxisGlue);
      Glue.link(glue, other);
   }

   private Glue getGlue(GridVector coordinate, HashMap<GridVector, Glue> map) {
      Glue glue = map.get(coordinate);
      if (glue == null) {
         glue = new Glue();
         map.put(coordinate, glue);
      }
      return glue;
   }

   HashMap<GridVector, Glue> xAxisGlue;
   HashMap<GridVector, Glue> yAxisGlue;
   ArrayList<Glue> gridGlue;
}
