package org.codecranachan.asteroidpush.base.input.slick2d;

import java.util.HashMap;
import java.util.Map;

import org.codecranachan.asteroidpush.base.input.ControlItem;

class ControllerMapping {
   private Map<Integer, ControlItem> buttonMapping;
   private Map<Integer, ControlItem> directionMapping;

   public static int RIGHT = 0;
   public static int UP = 1;
   public static int LEFT = 2;
   public static int DOWN = 3;

   public ControllerMapping() {
      buttonMapping = new HashMap<Integer, ControlItem>();
      directionMapping = new HashMap<Integer, ControlItem>();
   }

   public ControlItem mapButton(int button) {
      return Slick2dInputMapper.getBinding(button, buttonMapping);
   }

   public void bindButton(int button, ControlItem event) {
      buttonMapping.put(button, event);
   }

   public ControlItem mapDirection(int direction) {
      return Slick2dInputMapper.getBinding(direction, directionMapping);
   }

   public void bindDirection(int direction, ControlItem event) {
      directionMapping.put(direction, event);
   }
}

public class Slick2dInputMapper {
   private Map<Integer, ControlItem> keyMapping;
   private Map<Integer, ControlItem> mouseButtonMapping;
   private Map<Integer, ControlItem> mouseWheelMapping;
   private Map<Integer, ControllerMapping> controllerMapping;

   public static int MOUSE_WHEEL_UP = 0;
   public static int MOUSE_WHEEL_DOWN = 1;

   static public ControlItem getBinding(Integer key,
                                        Map<Integer, ControlItem> map) {
      // This function is used instead of Map.getOrDefault(), which is not
      // available in JAVA 1.7
      if (map.containsKey(key)) {
         return map.get(key);
      } else {
         return ControlItem.NOT_BOUND;
      }
   }

   public Slick2dInputMapper() {
      keyMapping = new HashMap<Integer, ControlItem>();
      mouseButtonMapping = new HashMap<Integer, ControlItem>();
      mouseWheelMapping = new HashMap<Integer, ControlItem>();
      controllerMapping = new HashMap<Integer, ControllerMapping>();
   }

   public ControlItem mapMouseButton(int button) {
      return getBinding(button, mouseButtonMapping);
   }

   public void bindMouseButton(int button, ControlItem event) {
      mouseButtonMapping.put(button, event);
   }

   public ControlItem mapMouseWheel(int direction) {
      return getBinding(direction, mouseWheelMapping);
   }

   public void bindMouseWheel(int direction, ControlItem event) {
      mouseWheelMapping.put(direction, event);
   }

   public ControlItem mapKey(int key) {
      return getBinding(key, keyMapping);
   }

   public void bindKey(int key, ControlItem event) {
      keyMapping.put(key, event);
   }

   public ControlItem mapControllerButton(int controller, int button) {
      if (controllerMapping.containsKey(controller)) {
         ControllerMapping mapping = controllerMapping.get(controller);
         return mapping.mapButton(button);
      } else {
         return ControlItem.NOT_BOUND;
      }
   }

   public void bindControllerButton(int controller,
                                    int button,
                                    ControlItem event) {
      ControllerMapping mapping;
      if (controllerMapping.containsKey(controller)) {
         mapping = controllerMapping.get(controller);
      } else {
         mapping = new ControllerMapping();
         controllerMapping.put(controller, mapping);
      }
      mapping.bindButton(button, event);
   }

   public ControlItem mapControllerDirection(int controller, int direction) {
      if (controllerMapping.containsKey(controller)) {
         ControllerMapping mapping = controllerMapping.get(controller);
         return mapping.mapDirection(direction);
      } else {
         return ControlItem.NOT_BOUND;
      }
   }

   public void bindControllerDirection(int controller,
                                       int direction,
                                       ControlItem event) {
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
