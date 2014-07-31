package org.codecranachan.asteroidpush.workshop.parts;

import org.codecranachan.asteroidpush.workshop.assembly.Part;
import org.codecranachan.asteroidpush.workshop.tokenboard.Token;

public interface TokenFactory {
   public String getName();

   public Token<Part> createToken();
}
