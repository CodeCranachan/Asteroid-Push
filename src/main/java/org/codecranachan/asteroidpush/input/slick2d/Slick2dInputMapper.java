package org.codecranachan.asteroidpush.input.slick2d;

import java.util.HashMap;
import java.util.Map;

class ControllerMapping {
   private Map<Integer, String> buttonMapping;
   private Map<Integer, String> directionMapping;

   public static int RIGHT = 0;
   public static int UP = 1;
   public static int LEFT = 2;
   public static int DOWN = 3;

   public ControllerMapping() {
      buttonMapping = new HashMap<Integer, String>();
      directionMapping = new HashMap<Integer, String>();
   }

   public String mapButton(int button) {
      return Slick2dInputMapper.getBinding(button, buttonMapping);
   }

   public void bindButton(int button, String event) {
      buttonMapping.put(button, event);
   }

   public String mapDirection(int direction) {
      return Slick2dInputMapper.getBinding(direction, directionMapping);
   }

   public void bindDirection(int direction, String event) {
      directionMapping.put(direction, event);
   }
}

public class Slick2dInputMapper {
   private Map<Integer, String> keyMapping;
   private Map<Integer, String> mouseButtonMapping;
   private Map<Integer, String> mouseWheelMapping;
   private Map<Integer, ControllerMapping> controllerMapping;

   public static int MOUSE_WHEEL_UP = 0;
   public static int MOUSE_WHEEL_DOWN = 1;

   public static String NOT_BOUND = "";

   static public String getBinding(Integer key, Map<Integer, String> map) {
      // This function is used instead of Map.getOrDefault(), which is not
      // available in JAVA 1.7
      if (map.containsKey(key)) {
         return map.get(key);
      } else {
         return NOT_BOUND;
      }
   }

   public Slick2dInputMapper() {
      keyMapping = new HashMap<Integer, String>();
      mouseButtonMapping = new HashMap<Integer, String>();
      mouseWheelMapping = new HashMap<Integer, String>();
      controllerMapping = new HashMap<Integer, ControllerMapping>();
   }

   public String mapMouseButton(int button) {
      return getBinding(button, mouseButtonMapping);
   }

   public void bindMouseButton(int button, String event) {
      mouseButtonMapping.put(button, event);
   }

   public String mapMouseWheel(int direction) {
      return getBinding(direction, mouseWheelMapping);
   }

   public void bindMouseWheel(int direction, String event) {
      mouseWheelMapping.put(direction, event);
   }

   public String mapKey(int key) {
      return getBinding(key, keyMapping);
   }

   public void bindKey(int key, String event) {
      keyMapping.put(key, event);
   }

   public String mapControllerButton(int controller, int button) {
      if (controllerMapping.containsKey(controller)) {
         ControllerMapping mapping = controllerMapping.get(controller);
         return mapping.mapButton(button);
      } else {
         return NOT_BOUND;
      }
   }

   public void bindControllerButton(int controller, int button, String event) {
      ControllerMapping mapping;
      if (controllerMapping.containsKey(controller)) {
         mapping = controllerMapping.get(controller);
      } else {
         mapping = new ControllerMapping();
         controllerMapping.put(controller, mapping);
      }
      mapping.bindButton(button, event);
   }

   public String mapControllerDirection(int controller, int direction) {
      if (controllerMapping.containsKey(controller)) {
         ControllerMapping mapping = controllerMapping.get(controller);
         return mapping.mapDirection(direction);
      } else {
         return NOT_BOUND;
      }
   }

   public void bindControllerDirection(int controller,
                                       int direction,
                                       String event) {
      ControllerMapping mapping;
      if (controllerMapping.containsKey(controller)) {
         mapping = controllerMapping.get(controller);
      } else {
         mapping = new ControllerMapping();
         controllerMapping.put(controller, mapping);
      }
      mapping.bindDirection(direction, event);
   }
}
