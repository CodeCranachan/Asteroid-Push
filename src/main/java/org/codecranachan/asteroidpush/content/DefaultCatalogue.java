package org.codecranachan.asteroidpush.content;

import java.util.Vector;

import org.codecranachan.asteroidpush.base.workshop.PartCatalogue;
import org.codecranachan.asteroidpush.base.workshop.PartFactory;
import org.codecranachan.asteroidpush.content.parts.Block;
import org.codecranachan.asteroidpush.content.parts.Spike;
import org.codecranachan.asteroidpush.content.parts.Thruster;
import org.codecranachan.asteroidpush.content.parts.Wedge;

public class DefaultCatalogue implements PartCatalogue {

   public Vector<PartFactory> getPartFactories() {
      Vector<PartFactory> factories = new Vector<PartFactory>();

      factories.add(new Block());
      factories.add(new Wedge());
      factories.add(new Spike());
      factories.add(new Thruster());

      return factories;
   }
}