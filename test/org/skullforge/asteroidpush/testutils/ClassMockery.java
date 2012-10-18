package org.skullforge.asteroidpush.testutils;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;

/**
 * This is an extension of the jmock Mockery that can also mock non-interface
 * classes. Use this instead of using the anonymous inner class definition that
 * needlessly clutters your test code.
 * 
 * @author Konfuzzyus
 * 
 */
public class ClassMockery extends Mockery {
   public ClassMockery() {
      setImposteriser(ClassImposteriser.INSTANCE);
   }
}
