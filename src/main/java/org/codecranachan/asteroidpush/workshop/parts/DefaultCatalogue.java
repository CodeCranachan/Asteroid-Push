package org.codecranachan.asteroidpush.workshop.parts;

import java.util.Vector;

public class DefaultCatalogue implements PartCatalogue {

   public Vector<PartFactory> getPartFactories() {
      Vector<PartFactory> factories = new Vector<PartFactory>();

      factories.add(new Block());
      factories.add(new Wedge());
      factories.add(new Spike());

      return factories;
   }
}
