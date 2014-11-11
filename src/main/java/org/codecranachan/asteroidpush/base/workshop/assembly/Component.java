package org.codecranachan.asteroidpush.base.workshop.assembly;

import java.util.Collection;
import java.util.LinkedList;

import org.codecranachan.asteroidpush.base.visuals.Representable;
import org.codecranachan.asteroidpush.base.visuals.Representation;

public class Component implements Representable {
   Socket socket;
   Collection<Plug> plugs;

   public Component(Socket socket) {
      this.socket = socket;
      this.plugs = new LinkedList<Plug>();
   }

   public void add(BehaviorFactory factory, int index) {
      plugs.add(new Plug(factory, index));
   }

   public Collection<Plug> getPlugs() {
      return plugs;
   }

   public Socket getSocket() {
      return socket;
   }

   public Collection<Representation> getRepresentations() {
      Collection<Representation> representations = new LinkedList<Representation>();
      for (Plug plug : plugs) {
         BehaviorFactory factory = plug.getFactory();
         assert factory != null;
         if (factory.getRepresentations() != null) {
            representations.addAll(factory.getRepresentations());
         }
      }
      return representations;
   }

}
