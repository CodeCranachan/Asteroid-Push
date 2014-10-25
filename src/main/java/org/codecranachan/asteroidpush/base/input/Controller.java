package org.codecranachan.asteroidpush.base.input;

public interface Controller {
   void setNextFrame(int frame);

   float getControl(ControlItem command, int frame);
}
