package org.skullforge.asteroidpush.designer.data;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.FixtureDef;
import org.junit.Test;

public class PrimitiveDataTest {

   @Test
   public void testFixtureCreation() {
      PrimitiveData testPrimitive = new PrimitiveData();
      testPrimitive.setMaterial(Material.METAL);
      Vec2 testShape[] = {
            new Vec2(-0.5f, 0.0f), new Vec2(0.5f, 0.0f), new Vec2(0.0f, 0.5f)
      };
      testPrimitive.setShape(testShape);

      Transform testTransform = new Transform();
      testTransform.set(new Vec2(), 0.0f);
      FixtureDef def = testPrimitive.getFixtureDef(testTransform, 1.0f);
      assertThat(def.density, is(Material.METAL.density));
      assertThat(def.restitution, is(Material.METAL.restitution));
      assertThat(def.friction, is(Material.METAL.friction));

      for (Vec2 point : testShape) {
         assertThat(def.shape.testPoint(testTransform, point), is(true));
      }
   }
}
