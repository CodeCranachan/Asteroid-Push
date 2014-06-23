package org.codecranachan.asteroidpush.workshop.spaceship;



public interface Softlink {
   public Hardpoint getFirstPoint();
   public Hardpoint getSecondPoint();

   public void OnCreation(Body firstBody, Body secondBody);
   public void OnDestruction();
}