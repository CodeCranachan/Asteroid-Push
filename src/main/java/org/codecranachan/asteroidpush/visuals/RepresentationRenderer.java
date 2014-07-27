package org.codecranachan.asteroidpush.visuals;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.codecranachan.asteroidpush.utils.Arrow;
import org.codecranachan.asteroidpush.utils.Trigonometry;
import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Vec2;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

public class RepresentationRenderer {
   private Arrow focus;
   private Rectangle frame;
   private List<Representation> reps;

   public RepresentationRenderer() {
      reps = new LinkedList<Representation>();
   }

   public void setRepresentations(List<Representation> representations) {
      reps.clear();
      reps.addAll(representations);
      RepresentationComparator comparator = new RepresentationComparator();
      Collections.sort(reps, comparator);
   }

   public void setFocus(Arrow focus) {
      this.focus = focus;
   }

   public void setFrame(Rectangle frame) {
      this.frame = frame;
   }

   public void render(Graphics g) {
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
      // First part: Transform to camera position
      // - translate the world so the focus is at the center
      // - rotate the world so the focus is pointing upwards
      Vector2f focusOffset = getFocusOffset();
      g.translate(focusOffset.getX(), focusOffset.getY());
      g.rotate(0, 0, getFocusRotation());

      // Second part: Map to screen coordinates
      // - map to screen coordinates and scale
      // - translate to center in view port
      float scale = getViewportScale();
      g.scale(scale, -scale);
      Vector2f viewOffset = getViewOffset();
      g.translate(viewOffset.getX(), viewOffset.getY());
   }

   private Vector2f getFocusOffset() {
      return new Vector2f(-focus.getTail().x, -focus.getTail().y);
   }

   private float getFocusRotation() {
      return Trigonometry.radToAngle(MathUtils.HALF_PI - focus.getAngle());
   }

   private Vector2f getViewOffset() {
      return new Vector2f(frame.getWidth() / 2.0f + frame.getX(),
            frame.getHeight() / 2.0f + frame.getY());
   }

   private float getViewportScale() {
      float frameSize = Math.min(frame.getWidth(), frame.getHeight());
      float scale = frameSize / focus.getMagnitude();
      return scale;
   }

   private Transform getViewportTransform() {
      Vector2f focusOffset = getFocusOffset();
      Transform transform = Transform.createTranslateTransform(focusOffset
            .getX(), focusOffset.getY());
      transform
            .concatenate(Transform.createRotateTransform(getFocusRotation()));

      float scale = getViewportScale();
      transform.concatenate(Transform.createScaleTransform(scale, -scale));
      Vector2f viewOffset = getViewOffset();
      transform.concatenate(Transform.createTranslateTransform(viewOffset
            .getX(), viewOffset.getY()));
      return transform;
   }

   private Transform getInverseViewportTransform() {
      // slick2d does not seem to have a getInverse() method on the Transform
      // class,
      // so we just engineer the inverse transform by doing the viewport
      // transform
      // backwards.
      Vector2f viewOffset = getViewOffset();
      Transform transform = Transform.createTranslateTransform(-viewOffset
            .getX(), -viewOffset.getY());
      float scale = getViewportScale();
      transform.concatenate(Transform.createScaleTransform(1 / scale, -1
            / scale));

      transform.concatenate(Transform
            .createRotateTransform(-getFocusRotation()));

      Vector2f focusOffset = getFocusOffset();
      transform.concatenate(Transform.createTranslateTransform(-focusOffset
            .getX(), -focusOffset.getY()));
      return transform;
   }
}

class RepresentationComparator implements Comparator<Representation> {

   public int compare(Representation first, Representation second) {
      return first.getPriority() - second.getPriority();
   }

}
