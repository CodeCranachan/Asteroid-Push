package org.codecranachan.asteroidpush.base.workshop.actor;

import java.util.Collection;

import org.codecranachan.asteroidpush.base.input.Controller;
import org.codecranachan.asteroidpush.base.simulation.RigidBody;
import org.codecranachan.asteroidpush.base.simulation.command.Command;
import org.codecranachan.asteroidpush.base.visuals.Representable;
import org.codecranachan.asteroidpush.base.visuals.Representation;

/**
 * In ModularActors, behaviors are the part of the logic that can be moved
 * between the RigidBodies of the actor.
 * 
 * A Behavior is attached to one or more RigidBodies, this association is
 * communicated to the Behavior by its environment by calling the update(),
 * onAttach() and onDetach() methods. In all these calls the Behavior is given a
 * Vector of Bodies, the order of this vector is preserved across calls, so the
 * Behavior can differentiate between - for example - the body "at the back" and
 * the body "in the front", if it so desires. They are associated with one or
 * more BodyVertices on the ModularActor's BodyGraph to track which RigidBodies
 * they are currently attached to.
 */
public interface Behavior extends Representable {
   /**
    * This is (must be) called every update tick of this behavior's parent
    * actor.
    * 
    * @param frame
    *           The current frame number of the simulation. You can use this to
    *           create timed effects.
    * 
    * @return An (unordered) collection of simulation commands, to be executed
    *         in the simulation. Must not be null.
    */
   public Collection<Command> update(int frame);

   /**
    * This is called whenever a behavior gets detached from a body for whatever
    * reason. You must ensure that the body could get safely destroyed once this
    * function exits e.g. anything that belongs to the behavior and that is
    * referencing the given bodies must be cleaned up.
    * 
    * @param body
    *           The body this behavior is being detached from.
    * @param index
    *           A behavior can be attached to multiple bodies, this parameter
    *           identifies which of the bodies is being detached.
    */
   public void onDetach(RigidBody body, int index);

   /**
    * This is called whenever a behavior gets attached to a body for whatever
    * reason. Note that this is also called when the behavior is attached to a
    * body for the first time.
    * 
    * @param bodies
    *           The body that this behavior is being attached to.
    * 
    * @param index
    *           A behavior can be attached to multiple bodies, this parameter
    *           identifies which of the bodies is being attached.
    */
   public void onAttach(RigidBody body, int index);

   /**
    * Returns the visual features of this behavior.
    */
   public Collection<Representation> getRepresentations();

   /**
    * Sets the controller(s) for this behavior. Since a behavior can be attached
    * to multiple bodies, the behavior gets one controller for each of them.
    * 
    * @param controller
    *           the controller that is being attached
    * @param index
    *           the body index the controller is being attached to
    */
   public void setController(Controller controller, int index);
}
