package org.codecranachan.asteroidpush.simulation.actors.modular;

import java.util.Collection;
import java.util.LinkedList;

import org.codecranachan.asteroidpush.simulation.RigidBody;

public class BodyVertex {
   private Collection<Plug> plugs;
   private RigidBody body;

   public BodyVertex() {
      this.plugs = new LinkedList<Plug>();
      body = null;
   }

   public RigidBody getBody() {
      return body;
   }

   public void setBody(RigidBody body) {
      this.body = body;
   }

   public Collection<Plug> getPlugs() {
      return plugs;
   }

   public void addPlug(Plug plug) {
      plugs.add(plug);
   }
}
