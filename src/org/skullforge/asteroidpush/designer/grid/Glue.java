package org.skullforge.asteroidpush.designer.grid;

import java.util.ArrayList;
import java.util.Collection;

import org.skullforge.asteroidpush.designer.SubModule;

public class Glue {

   public Glue() {
      this.links = new ArrayList<Glue>();
      this.subModule = null;
      this.isVisited = false;
   }

   public Glue(SubModule subModule) {
      this.links = new ArrayList<Glue>();
      this.subModule = subModule;
   }

   public void clear() {
      links.clear();
      subModule = null;
   }

   public static void link(Glue first, Glue second) {
      first.links.add(second);
      second.links.add(first);
   }

   public void setVisited() {
      isVisited = true;
   }

   public void resetVisited() {
      isVisited = false;
   }

   public boolean isVisited() {
      return isVisited;
   }

   public Collection<Glue> getLinks() {
      return links;
   }

   public SubModule getSubModule() {
      return subModule;
   }

   private boolean isVisited;
   private ArrayList<Glue> links;
   private SubModule subModule;
}
