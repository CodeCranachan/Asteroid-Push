package org.skullforge.asteroidpush.doodads.spaceship;

import java.util.ArrayList;
import java.util.Collection;


public class Glue {

   public static void link(Glue first, Glue second) {
      first.links.add(second);
      second.links.add(first);
   }

   private Part part;
   private boolean isVisited;
   private ArrayList<Glue> links;

   public Glue() {
      this.links = new ArrayList<Glue>();
      this.part = null;
      this.isVisited = false;
   }

   public Glue(Part part) {
      this.links = new ArrayList<Glue>();
      this.part = part;
   }

   public void clear() {
      links.clear();
      part = null;
   }

   public Collection<Glue> getLinks() {
      return links;
   }

   public Part getPart() {
      return part;
   }

   public boolean isVisited() {
      return isVisited;
   }

   public void resetVisited() {
      isVisited = false;
   }

   public void setVisited() {
      isVisited = true;
   }
}
