package org.codecranachan.asteroidpush.state.ui;

import org.codecranachan.asteroidpush.ResourceLoader;
import org.codecranachan.asteroidpush.visuals.widget.BasicWidget;
import org.codecranachan.asteroidpush.visuals.widget.Widget;
import org.codecranachan.asteroidpush.workshop.BlueprintCollection;
import org.codecranachan.asteroidpush.workshop.Manipulator;
import org.codecranachan.asteroidpush.workshop.PartSelector;
import org.codecranachan.asteroidpush.workshop.parts.DefaultCatalogue;
import org.codecranachan.asteroidpush.workshop.parts.PartCatalogue;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class WorkshopUi extends BasicWidget {
   private WorkshopUiLayout layout;
   private WorkshopCoordinator coordinator;
   private CreateBlueprintButton createBlueprintButton;
   private Widget manipulatorWidget;

   static String defaultFontName = "resources/Alfphabet-IV.ttf";

   public WorkshopUi(BlueprintCollection collection, ResourceLoader loader) {
      layout = new WorkshopUiLayout();
      coordinator = createCoordinator(collection);

      manipulatorWidget = new ManipulatorWidget(coordinator, loader);
      createBlueprintButton = new CreateBlueprintButton(coordinator,
            loader.loadFont(defaultFontName, 15));
      updateBlueprintWidget();

      layout.setCatalogueWidget(new SelectorWidget(coordinator, loader
            .loadFont(defaultFontName, 15)));
      layout.setSelectionWidget(new StartTestRunButton(coordinator, loader
            .loadFont(defaultFontName, 15)));
   }

   private WorkshopCoordinator createCoordinator(BlueprintCollection collection) {
      Manipulator manipulator = new Manipulator();
      PartSelector selector = new PartSelector();

      PartCatalogue catalogue = new DefaultCatalogue();
      selector.addPartFactories(catalogue.getPartFactories());

      return new WorkshopCoordinator(manipulator, selector, collection);
   }

   public void resize(Rectangle frame) {
      updateBlueprintWidget();
      layout.resize(frame);
   }

   public void render(Graphics g) {
      layout.render(g);
   }

   public void update(GameContainer container, StateBasedGame game, int delta) {
      layout.update(container, game, delta);
   }

   public void setHover(float x, float y) {
      layout.setHover(x, y);
   }

   public void resetHover() {
      layout.resetHover();
   }

   public void mousePressed(int button, int x, int y) {
      layout.mousePressed(button, x, y);
   }

   public void keyPressed(int key) {
      switch (key) {
      case Input.KEY_LEFT:
      case Input.KEY_SPACE:
         coordinator.rotatePartLeft();
         break;
      case Input.KEY_RIGHT:
         coordinator.rotatePartRight();
         break;
      case Input.KEY_F1:
      case Input.KEY_F2:
      case Input.KEY_F3:
      case Input.KEY_F4:
      case Input.KEY_F5:
      case Input.KEY_F6:
      case Input.KEY_F7:
      case Input.KEY_F8:
      case Input.KEY_F9:
      case Input.KEY_F10:
      case Input.KEY_F11:
      case Input.KEY_F12:
         break;
      }
   }

   private void updateBlueprintWidget() {
      if (coordinator.getManipulatedBlueprint() == null) {
         layout.setBlueprintWidget(createBlueprintButton);
      } else {
         layout.setBlueprintWidget(manipulatorWidget);
      }
   }
}
