package org.codecranachan.asteroidpush.base.workshop.tokenboard;

import java.util.Collection;

import org.codecranachan.asteroidpush.base.visuals.Representable;
import org.codecranachan.asteroidpush.base.visuals.Representation;

public interface Placeable extends Representable {
   public Shape getShape();
   public Collection<Representation> getRepresentations();
}
