package org.skullforge.asteroidpush.designer.catalogue;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Collection;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.junit.Before;
import org.junit.Test;
import org.skullforge.asteroidpush.designer.data.ComponentData;
import org.skullforge.asteroidpush.designer.data.ModuleData;
import org.skullforge.asteroidpush.designer.data.PrimitiveData;
import org.skullforge.asteroidpush.utils.GeometryVerifier;

public class ModuleCatalogueTest {

   ModuleCatalogue testCatalogue;

   @Before
   public void setUp() throws Exception {
      testCatalogue = new ModuleCatalogue();
   }

   @Test
   public void testPrimitives() {
      Transform testTransform = new Transform();
      testTransform.set(new Vec2(), 0.0f);
      float testSize = 5.0f;

      for (ModuleData module : testCatalogue.getModuleData()) {
         Collection<ComponentData> components = module.getComponents();
         for (ComponentData component : components) {
            for (PrimitiveData primitive : component.getPrimitives()) {
               PolygonShape poly = primitive.getShape(testTransform, testSize);
               assertThat(module.getName(), GeometryVerifier.IsWoundCorrectly(poly), is(true));
            }
         }
      }
   }

}
