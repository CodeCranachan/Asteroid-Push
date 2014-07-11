package org.codecranachan.asteroidpush.visuals;

import java.util.HashMap;
import java.util.Map;

public class RepresentationLoader {
   private Map<Class<?>, RepresentationFactory> factoryMap;

   public RepresentationLoader() {
      factoryMap = new HashMap<Class<?>, RepresentationFactory>();
   }

   public Representation getRepresentation(Object object) {
      RepresentationFactory factory;
      Class<?> type = object.getClass();
      if (factoryMap.containsKey(type)) {
         factory = factoryMap.get(type);
      } else {
         factory = loadFactory(type);
         factoryMap.put(type, factory);
      }

      if (factory == null) {
         // TODO Replace with null representation
         return null;
      } else {
         return factory.createRepresentation(object);
      }
   }

   private RepresentationFactory loadFactory(Class<?> type) {
      RepresentationFactory myFactory = null;
      String factoryPackage = "org.codecranachan.asteroidpush.visuals.factories";
      String factoryName = factoryPackage + "." + type.getName()
            + RepresentationFactory.class.getName();

      ClassLoader classLoader = this.getClass().getClassLoader();
      Class<?> loadedClass = null;
      try {
         loadedClass = classLoader.loadClass(factoryName);
         Object object = loadedClass.newInstance();
         myFactory = (RepresentationFactory) object;
      } catch (ClassNotFoundException e) {
         System.out
               .format("Unable to find RepresentationFactory %s for %s: %s",
                       factoryName,
                       type.getName(),
                       e.getMessage());
      } catch (IllegalAccessException e) {
         System.out
               .format("Unable to access constructor of RepresentationFactory %s for %s: %s",
                       factoryName,
                       type.getName(),
                       e.getMessage());
      } catch (InstantiationException e) {
         System.out
               .format("Unable to construct RepresentationFactory %s for %s: %s",
                       factoryName,
                       type.getName(),
                       e.getMessage());
      } catch (ClassCastException e) {
         System.out
               .format("Class %s loaded as RepresentationFactory for %s does not implement the required interface: %s",
                       factoryName,
                       type.getName(),
                       e.getMessage());
      }

      if (myFactory == null) {
         // TODO use fallback representations if no adequate class was found
      }
      return myFactory;
   }
}