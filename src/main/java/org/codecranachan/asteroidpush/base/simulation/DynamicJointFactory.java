package org.codecranachan.asteroidpush.base.simulation;


public interface DynamicJointFactory {
   DynamicJoint createPrismaticJoint(RigidBody bodyA, RigidBody bodyB, PrismaticJointData data);
}
