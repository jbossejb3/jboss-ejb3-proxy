/*
* JBoss, Home of Professional Open Source
* Copyright 2005, JBoss Inc., and individual contributors as indicated
* by the @authors tag. See the copyright.txt in the distribution for a
* full listing of individual contributors.
*
* This is free software; you can redistribute it and/or modify it
* under the terms of the GNU Lesser General Public License as
* published by the Free Software Foundation; either version 2.1 of
* the License, or (at your option) any later version.
*
* This software is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
* Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public
* License along with this software; if not, write to the Free
* Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
* 02110-1301 USA, or see the FSF site: http://www.fsf.org.
*/
package org.jboss.ejb3.proxy.javassist.test.unit;

import java.lang.reflect.InvocationHandler;

import junit.framework.Assert;

import org.jboss.ejb3.proxy.javassist.JavassistProxyFactory;
import org.jboss.ejb3.proxy.javassist.test.DelegatingInvocationHandler;
import org.jboss.ejb3.proxy.javassist.test.Dummy;
import org.jboss.ejb3.proxy.javassist.test.SimpleClass;
import org.junit.Before;
import org.junit.Test;

/**
 * Test {@link JavassistProxyFactory}
 *
 * @author Jaikiran Pai
 * @version $Revision: $
 */
public class JavassistProxyFactoryTestCase
{

   private JavassistProxyFactory javassistProxyFactory;

   @Before
   public void beforeTest()
   {
      this.javassistProxyFactory = new JavassistProxyFactory();
   }

   /**
    * Tests that the proxy creation through {@link JavassistProxyFactory} works as expected
    * @throws Exception
    */
   @Test
   public void testProxyCreation() throws Exception
   {
      SimpleClass instance = new SimpleClass();
      InvocationHandler invocationHandler = new DelegatingInvocationHandler<SimpleClass>(instance);
      Object obj = this.javassistProxyFactory.createProxy(new Class<?>[]
      {SimpleClass.class}, invocationHandler);

      Assert.assertNotNull("Proxy factory created a null proxy", obj);
      Assert.assertTrue("Proxy factory created a proxy which is *not* of type " + SimpleClass.class,
            (obj instanceof SimpleClass));

      SimpleClass proxy = (SimpleClass) obj;
      String msg = "Hello";
      String result = proxy.echo(msg);
      Assert.assertEquals("Unexpected return value from proxy invocation", msg, result);
   }

   /**
    * Test that proxy creation through {@link JavassistProxyFactory} leads to {@link IllegalArgumentException}
    * if the number of {@link Class} types passed, is more than one
    * @throws Exception
    */
   @Test
   public void testProxyCreationForMoreThanOneType() throws Exception
   {
      SimpleClass instance = new SimpleClass();
      InvocationHandler invocationHandler = new DelegatingInvocationHandler<SimpleClass>(instance);
      try
      {
         Object obj = this.javassistProxyFactory.createProxy(new Class<?>[]
         {SimpleClass.class, Dummy.class}, invocationHandler);
         Assert.fail("Javassist proxy factory unexpectedly created a proxy for more than one type");
      }
      catch (IllegalArgumentException iae)
      {
         //expected
      }

   }
}
