package org.codecranachan.asteroidpush.workshop.parts;

import org.codecranachan.asteroidpush.workshop.assembly.Part;

public interface PartFactory {
   public String getName();

   public Part createPart();
}
