package org.skullforge.asteroidpush.designer;

import org.skullforge.asteroidpush.designer.grid.Placement;

public interface Module {
   String getName();

   Placement getPlacement();

   void setPlacement(Placement position);
}