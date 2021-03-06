package org.codecranachan.asteroidpush.base.visuals;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.codecranachan.asteroidpush.utils.FieldOfView;
import org.codecranachan.asteroidpush.utils.Trigonometry;
import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Vec2;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

public class RepresentationRenderer {
   private FieldOfView fov;
   private Rectangle frame;
   private List<Representation> reps;

   public RepresentationRenderer() {
      reps = new LinkedList<Representation>();
   }

   public void setRepresentations(Collection<Representation> representations) {
      reps.clear();
      reps.addAll(representations);
      RepresentationComparator comparator = new RepresentationComparator();
      Collections.sort(reps, comparator);
   }

   public void setFieldOfView(FieldOfView fov) {
      this.fov = fov;
   }

   public void setFrame(Rectangle frame) {
      this.frame = frame;
   }

   public void render(Graphics g) {
      assert frame != null;
      assert fov != null;
      g.pushTransform();
      try {
         applyViewportTransform(g);
         for (Representation r : reps) {
            r.render(g);
         }
      } finally {
         g.popTransform();
      }
   }

   public Vec2 mapToWorldCoordinates(Vector2f screenCoordinate) {
      Transform transform = getInverseViewportTransform();
      Vector2f worldCoordinate = transform.transform(screenCoordinate);
      return new Vec2(worldCoordinate.getX(), worldCoordinate.getY());
   }

   public Vector2f mapToScreenCoordinates(Vec2 worldCoordinate) {
      Transform transform = getViewportTransform();
      return transform.transform(new Vector2f(worldCoordinate.x,
            worldCoordinate.y));
   }

   private void applyViewportTransform(Graphics g) {
      // First part: Map to screen coordinates
      // - translate to center in view port
      // - map to screen coordinates and scale
      Vector2f viewOffset = getViewOffset();
      g.translate(viewOffset.getX(), viewOffset.getY());
      float scale = getViewportScale();
      g.scale(scale, -scale);

      // Second part: Transform to camera position
      // - rotate the world so the focus is pointing upwards
      // - translate the world so the focus is at the center
      g.rotate(0, 0, getFocusRotationAsAngle());
      Vector2f focusOffset = getFocusOffset();
      g.translate(-focusOffset.getX(), -focusOffset.getY());
   }

   private Vector2f getFocusOffset() {
      return new Vector2f(fov.getCenter().x, fov.getCenter().y);
   }

   private float getFocusRotationAsAngle() {
      return Trigonometry.radToDeg(getFocusRotationAsRadian());
   }

   private float getFocusRotationAsRadian() {
      return MathUtils.HALF_PI - fov.getUp().rad();
   }

   private Vector2f getViewOffset() {
      return new Vector2f(frame.getWidth() / 2.0f + frame.getX(),
            frame.getMaxY() - frame.getHeight() / 2.0f);
   }

   private float getViewportScale() {
      float frameSize = Math.min(frame.getWidth(), frame.getHeight());
      float scale = frameSize / (fov.getRadius() * 2.0f);
      return scale;
   }

   private Transform getViewportTransform() {
      Vector2f viewOffset = getViewOffset();
      Transform viewOffsetTransform = Transform
            .createTranslateTransform(viewOffset.getX(), viewOffset.getY());
      float scale = getViewportScale();
      Transform viewScaleTransform = Transform.createScaleTransform(scale,
                                                                    -scale);

      Transform focusRotateTransform = Transform
            .createRotateTransform(getFocusRotationAsRadian());
      Vector2f focusOffset = getFocusOffset();
      Transform focusOffsetTransform = Transform
            .createTranslateTransform(-focusOffset.getX(), -focusOffset.getY());

      Transform transform = new Transform();
      transform.concatenate(viewOffsetTransform);
      transform.concatenate(viewScaleTransform);
      transform.concatenate(focusRotateTransform);
      transform.concatenate(focusOffsetTransform);
      return transform;
   }

   private Transform getInverseViewportTransform() {
      // slick2d does not seem to have a getInverse() method on the Transform
      // class,
      // so we just engineer the inverse transform by doing the viewport
      // transform
      // backwards.
      Vector2f viewOffset = getViewOffset();
      Transform viewOffsetTransform = Transform
            .createTranslateTransform(-viewOffset.getX(), -viewOffset.getY());
      float scale = getViewportScale();
      Transform viewScaleTransform = Transform.createScaleTransform(1 / scale,
                                                                    -1 / scale);
      Transform focusRotateTransform = Transform
            .createRotateTransform(-getFocusRotationAsRadian());

      Vector2f focusOffset = getFocusOffset();
      Transform focusOffsetTransform = Transform
            .createTranslateTransform(focusOffset.getX(), focusOffset.getY());

      Transform transform = new Transform();
      transform.concatenate(focusOffsetTransform);
      transform.concatenate(focusRotateTransform);
      transform.concatenate(viewScaleTransform);
      transform.concatenate(viewOffsetTransform);
      return transform;
   }
}
