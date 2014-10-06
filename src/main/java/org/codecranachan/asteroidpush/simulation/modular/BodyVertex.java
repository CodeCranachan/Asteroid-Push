package org.codecranachan.asteroidpush.simulation.modular;

import java.util.Collection;
import java.util.LinkedList;

public class BodyVertex {
   private Collection<Plug> plugs;

   public BodyVertex() {
      this.plugs = new LinkedList<Plug>();
   }

   public Collection<Plug> getPlugs() {
      return plugs;
   }

   public void addPlug(Plug plug) {
      plugs.add(plug);
   }
}
