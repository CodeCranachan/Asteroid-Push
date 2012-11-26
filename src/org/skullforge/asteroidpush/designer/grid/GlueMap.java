package org.skullforge.asteroidpush.designer.grid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

import org.skullforge.asteroidpush.designer.SubModule;
import org.skullforge.asteroidpush.designer.modules.data.GlueData;
import org.skullforge.asteroidpush.designer.modules.data.PartData;

public class GlueMap {
   public GlueMap() {
      xAxisGlue = new HashMap<Coordinate, Glue>();
      yAxisGlue = new HashMap<Coordinate, Glue>();
      gridGlue = new ArrayList<Glue>();
   }

   public void clear() {
      clearMap(xAxisGlue);
      clearMap(yAxisGlue);
      clearArray(gridGlue);
   }

   private void clearMap(HashMap<Coordinate, Glue> map) {
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

   public void putPart(Placement placement, SubModule sub) {
      PartData data = sub.getData();
      GlueData rotatedGlue = data.getGlue().getRotated(placement.getRotation());

      Glue gluedPart = new Glue(sub);

      if (rotatedGlue.doStickToFront()) {
         putInXAxis(placement.getCoordinate(), gluedPart);
      }

      if (rotatedGlue.doStickToBack()) {
         Coordinate backCoordinate = new Coordinate(
               placement.getCoordinate().getX() - 1, placement.getCoordinate().getY());
         putInXAxis(backCoordinate, gluedPart);
      }

      if (rotatedGlue.doStickToLeft()) {
         putInYAxis(placement.getCoordinate(), gluedPart);
      }

      if (rotatedGlue.doStickToRight()) {
         Coordinate backCoordinate = new Coordinate(
               placement.getCoordinate().getX(), placement.getCoordinate().getY() - 1);
         putInYAxis(backCoordinate, gluedPart);
      }

      gridGlue.add(gluedPart);
   }

   public Collection<Collection<SubModule>> getGlueGroups() {
      resetVisitedFlags();

      ArrayList<Collection<SubModule>> glueGroups = new ArrayList<Collection<SubModule>>();

      for (Glue glue : gridGlue) {
         LinkedList<Glue> openLinks = new LinkedList<Glue>();
         if (!glue.isVisited()) {
            openLinks.add(glue);
            LinkedList<SubModule> gluedSubModules = new LinkedList<SubModule>();
            walkLinks(openLinks, gluedSubModules);
            glueGroups.add(gluedSubModules);
         }
      }

      return glueGroups;
   }

   private void walkLinks(LinkedList<Glue> openLinks,
                          LinkedList<SubModule> gluedSubModules) {
      while (!openLinks.isEmpty()) {
         Glue current = openLinks.pop();
         if (!current.isVisited()) {
            current.setVisited();
            if (current.getSubModule() != null) {
               gluedSubModules.push(current.getSubModule());
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

   private void putInXAxis(Coordinate coordinate, Glue glue) {
      Glue other = getGlue(coordinate, xAxisGlue);
      Glue.link(glue, other);
   }

   private void putInYAxis(Coordinate coordinate, Glue glue) {
      Glue other = getGlue(coordinate, yAxisGlue);
      Glue.link(glue, other);
   }

   private Glue getGlue(Coordinate coordinate, HashMap<Coordinate, Glue> map) {
      Glue glue = map.get(coordinate);
      if (glue == null) {
         glue = new Glue();
         map.put(coordinate, glue);
      }
      return glue;
   }

   HashMap<Coordinate, Glue> xAxisGlue;
   HashMap<Coordinate, Glue> yAxisGlue;
   ArrayList<Glue> gridGlue;
}
