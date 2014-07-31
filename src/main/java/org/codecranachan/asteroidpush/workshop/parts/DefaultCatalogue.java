package org.codecranachan.asteroidpush.workshop.parts;

import java.util.Vector;

public class DefaultCatalogue implements PartCatalogue {

   public Vector<TokenFactory> getPartFactories() {
      Vector<TokenFactory> factories = new Vector<TokenFactory>();

      factories.add(new Block());
      factories.add(new Wedge());

      return factories;
   }
}
