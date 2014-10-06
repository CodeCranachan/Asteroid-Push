package org.codecranachan.asteroidpush.simulation.modular;

import java.util.Collection;
import java.util.Vector;

import org.codecranachan.asteroidpush.simulation.RigidBody;
import org.codecranachan.asteroidpush.simulation.command.Command;

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
public interface Behavior {
   /**
    * This is (must be) called every update tick of this behavior's parent
    * actor.
    * 
    * @param frame
    *           The current frame number of the simulation. You can use this to
    *           create timed effects.
    * 
    * @param bodies
    *           A vector of bodies this behavior is currently attached to. The
    *           ordering of the vector is significant (if nothing changes, the
    *           first body will always stay the first body). The caller
    *           guarantees that there will be no null elements in this vector.
    *           The contents of this vector may change between calls to update.
    *           If it does, the onDetach and onAttach methods will be invoked
    *           previously.
    * 
    * @return An (unordered) collection of simulation commands, to be executed
    *         in the simulation. Must not be null.
    */
   public Collection<Command> update(Vector<RigidBody> bodies, int frame);

   /**
    * This is called whenever a behavior gets detached from a body for whatever
    * reason. You must ensure that the body could get safely destroyed once this
    * function exits e.g. anything that belongs to the behavior and that is
    * referencing the given bodies must be cleaned up.
    * 
    * @param frame
    *           The current frame number of the simulation. You can use this to
    *           create timed effects.
    * 
    * @param bodies
    *           A vector of bodies that this behavior is being detached from.
    *           The ordering of the vector is significant. A null element in
    *           this vector means that there has been no change to the
    *           attachment of that particular body.
    * 
    * @return An (unordered) collection of simulation commands, to be executed
    *         in the simulation. Must not be null.
    */
   public Collection<Command> onDetach(Vector<RigidBody> bodies, int frame);

   /**
    * This is called whenever a behavior gets attached to a body for whatever
    * reason. Note that this is also called when the behavior is attached to a
    * body for the first time.
    * 
    * @param frame
    *           The current frame number of the simulation. You can use this to
    *           create timed effects.
    * 
    * @param bodies
    *           A vector of bodies that this behavior is being attached to. The
    *           ordering of the vector is significant. A null element in this
    *           vector means that there has been no change to the attachment of
    *           that particular body.
    * 
    * @return An (unordered) collection of simulation commands, to be executed
    *         in the simulation. Must not be null.
    */
   public Collection<Command> onAttach(Vector<RigidBody> bodies, int frame);
}
