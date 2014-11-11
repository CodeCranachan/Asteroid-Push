package org.codecranachan.asteroidpush.base.workshop;

import org.codecranachan.asteroidpush.base.workshop.assembly.Part;

public interface PartFactory {
   public String getName();

   public Part createPart();
}
