package org.codecranachan.asteroidpush.base.ui.simulation;

import java.util.Collection;

import org.codecranachan.asteroidpush.base.ui.widget.Widget;
import org.codecranachan.asteroidpush.base.visuals.Representation;

public interface Viewport extends Widget {
   void setRepresentations(Collection<Representation> representations);
}
