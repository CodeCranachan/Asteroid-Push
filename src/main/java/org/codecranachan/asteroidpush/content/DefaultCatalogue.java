package org.codecranachan.asteroidpush.content;

import java.util.Vector;

import org.codecranachan.asteroidpush.base.workshop.PartCatalogue;
import org.codecranachan.asteroidpush.base.workshop.PartFactory;
import org.codecranachan.asteroidpush.content.parts.Block;
import org.codecranachan.asteroidpush.content.parts.Bumper;
import org.codecranachan.asteroidpush.content.parts.Cannon;
import org.codecranachan.asteroidpush.content.parts.LeftSlope;
import org.codecranachan.asteroidpush.content.parts.RightSlope;
import org.codecranachan.asteroidpush.content.parts.Spinner;
import org.codecranachan.asteroidpush.content.parts.Thruster;
import org.codecranachan.asteroidpush.content.parts.Wedge;

public class DefaultCatalogue implements PartCatalogue {

   public Vector<PartFactory> getPartFactories() {
      Vector<PartFactory> factories = new Vector<PartFactory>();

      factories.add(new Block());
      factories.add(new Wedge());
      factories.add(new LeftSlope());
      factories.add(new RightSlope());
      factories.add(new Thruster());
      factories.add(new Spinner());
      factories.add(new Cannon());
      factories.add(new Bumper());

      return factories;
   }
}
