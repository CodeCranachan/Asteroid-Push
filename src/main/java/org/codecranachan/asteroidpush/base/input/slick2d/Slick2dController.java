package org.codecranachan.asteroidpush.base.input.slick2d;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.codecranachan.asteroidpush.base.input.ControlItem;
import org.codecranachan.asteroidpush.base.input.Controller;

public class Slick2dController implements Controller {
   private Slick2dInputMapper bindings;
   private Map<ControlItem, TreeMap<Integer, Float>> streams;
   private int nextFrame;

   public Slick2dController(Slick2dInputMapper bindings) {
      this.bindings = bindings;
      this.streams = new HashMap<ControlItem, TreeMap<Integer, Float>>();
      this.nextFrame = 0;
   }

   public void setNextFrame(int frame) {
      this.nextFrame = frame;
   }

   /**
    * Update the state for a key driven command stream.
    * 
    * @param key
    *           integer code for the key that changed state
    * @param magnitude
    *           the new floating point value for the key
    * @return true if the key is actually bound to a command, false if it is not
    *         bound to a command
    */
   public boolean inputKey(int key, float magnitude) {
      ControlItem command = bindings.mapKey(key);
      if (command == ControlItem.NOT_BOUND) {
         return false;
      }

      TreeMap<Integer, Float> stream;
      if (streams.containsKey(command)) {
         stream = streams.get(command);
      } else {
         stream = createStream();
         streams.put(command, stream);
      }
      stream.put(nextFrame, magnitude);
      return true;
   }

   public float getControl(ControlItem command, int frame) {
      if (streams.containsKey(command)) {
         TreeMap<Integer, Float> stream = streams.get(command);
         return stream.floorEntry(frame).getValue();
      }
      return 0.0f;
   }

   private TreeMap<Integer, Float> createStream() {
      TreeMap<Integer, Float> stream;
      stream = new TreeMap<Integer, Float>();
      stream.put(0, 0.0f);
      return stream;
   }

   public void clearStreams() {
      streams.clear();
   }
}
