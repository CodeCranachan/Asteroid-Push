package org.codecranachan.asteroidpush.input;

public interface InputMapper {
   public void RegisterListener(EventListener listener);

   public void RemoveListener(EventListener listener);
}
