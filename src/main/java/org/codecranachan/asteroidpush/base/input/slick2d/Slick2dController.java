package org.codecranachan.asteroidpush.base.input.slick2d;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Slick2dController {
   private Slick2dInputMapper bindings;
   private Map<String, TreeMap<Integer, Float>> streams;
   private int nextFrame;

   public Slick2dController(Slick2dInputMapper bindings) {
      this.bindings = bindings;
      this.streams = new HashMap<String, TreeMap<Integer, Float>>();
      this.nextFrame = 0;
   }

   public void setNextFrame(int frame) {
      this.nextFrame = frame;
   }

   public void inputKey(int key, float magnitude) {
      String command = bindings.mapKey(key);

      TreeMap<Integer, Float> stream;
      if (streams.containsKey(command)) {
         stream = streams.get(command);
      } else {
         stream = createStream();
         streams.put(command, stream);
      }
      stream.put(nextFrame, magnitude);
   }

   public float getControl(String command, int frame) {
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
}
