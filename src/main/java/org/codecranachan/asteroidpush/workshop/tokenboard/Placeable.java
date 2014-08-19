package org.codecranachan.asteroidpush.workshop.tokenboard;

import java.util.Collection;

import org.codecranachan.asteroidpush.visuals.Representable;
import org.codecranachan.asteroidpush.visuals.Representation;

public interface Placeable extends Representable {
   public Shape getShape();
   public Collection<Representation> getRepresentations();
}
