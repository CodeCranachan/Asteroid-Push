package org.skullforge.asteroidpush.doodads;

/**
 * Interface for factories that build Doodads from Parts, Logic and Appearances.
 * 
 * @author Konfuzzyus
 * 
 */
public interface DoodadFactory {
   /**
    * Create a new Doodad from scratch.
    * 
    * @return a new Doodad.
    */
   Doodad createDoodad();
}
