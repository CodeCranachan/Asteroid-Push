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
      return buttonMapping.getOrDefault(button, Slick2dInputMapper.NOT_BOUND);
   }

   public void bindButton(int button, String event) {
      buttonMapping.put(button, event);
   }

   public String mapDirection(int direction) {
      return directionMapping.getOrDefault(direction,
                                           Slick2dInputMapper.NOT_BOUND);
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

   public Slick2dInputMapper() {
      keyMapping = new HashMap<Integer, String>();
      mouseButtonMapping = new HashMap<Integer, String>();
      mouseWheelMapping = new HashMap<Integer, String>();
      controllerMapping = new HashMap<Integer, ControllerMapping>();
   }

   public String mapMouseButton(int button) {
      return mouseButtonMapping.getOrDefault(button, NOT_BOUND);
   }

   public void bindMouseButton(int button, String event) {
      mouseButtonMapping.put(button, event);
   }

   public String mapMouseWheel(int direction) {
      return mouseWheelMapping.getOrDefault(direction, NOT_BOUND);
   }

   public void bindMouseWheel(int direction, String event) {
      mouseWheelMapping.put(direction, event);
   }

   public String mapKey(int key) {
      return keyMapping.getOrDefault(key, NOT_BOUND);
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

   public void bindControllerDirection(int controller, int direction, String event) {
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
