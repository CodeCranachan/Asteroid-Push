package org.codecranachan.asteroidpush.workshop.assembly;


public class DynamicConnector {
   private AssemblyVertex nodeA;
   private AssemblyVertex nodeB;
   private ConstraintFactory constraintFactory;

   public DynamicConnector(AssemblyVertex nodeA, AssemblyVertex nodeB,
         ConstraintFactory constraintFactory) {
      this.nodeA = nodeA;
      this.nodeB = nodeB;
      this.constraintFactory = constraintFactory;
   }

   public AssemblyVertex getNodeA() {
      return nodeA;
   }

   public AssemblyVertex getNodeB() {
      return nodeB;
   }

   public ConstraintFactory getConstraintFactory() {
      return constraintFactory;
   }
}