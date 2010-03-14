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
package org.jboss.ejb3.proxy.reflect.test.unit;

import java.lang.reflect.InvocationHandler;

import junit.framework.Assert;

import org.jboss.ejb3.proxy.reflect.ReflectProxyFactory;
import org.jboss.ejb3.proxy.reflect.test.DelegatingInvocationHandler;
import org.jboss.ejb3.proxy.reflect.test.Echo;
import org.jboss.ejb3.proxy.reflect.test.Greeter;
import org.jboss.ejb3.proxy.reflect.test.SimpleClass;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@link ReflectProxyFactory}
 *
 * @author Jaikiran Pai
 * @version $Revision: $
 */
public class ReflectProxyFactoryTestCase
{

   private ReflectProxyFactory reflectProxyFactory;

   @Before
   public void beforeTest()
   {
      this.reflectProxyFactory = new ReflectProxyFactory();
   }

   /**
    * Tests that the proxy creation through {@link ReflectProxyFactory} works as expected
    * @throws Exception
    */
   @Test
   public void testProxyCreation() throws Exception
   {
      SimpleClass instance = new SimpleClass();
      InvocationHandler invocationHandler = new DelegatingInvocationHandler<SimpleClass>(instance);
      Object obj = this.reflectProxyFactory.createProxy(new Class<?>[]{Echo.class}, invocationHandler);
      
      Assert.assertNotNull("Proxy factory created a null proxy", obj);
      Assert.assertTrue("Proxy factory created a proxy which is *not* of type " + Echo.class, (obj instanceof Echo));
      
      Echo proxy = (Echo) obj;
      String msg = "Hello";
      String result = proxy.echo(msg);
      Assert.assertEquals("Unexpected return value from proxy invocation", msg, result);
   }
   
   /**
    * Test that proxy creation through {@link ReflectProxyFactory} for multiple class types
    * does not lead to any exception
    * 
    * @throws Exception
    */
   @Test
   public void testProxyCreationForMultipleTypes() throws Exception
   {
      SimpleClass instance = new SimpleClass();
      InvocationHandler invocationHandler = new DelegatingInvocationHandler<SimpleClass>(instance);
      Object obj = this.reflectProxyFactory.createProxy(new Class<?>[]{Echo.class, Greeter.class}, invocationHandler);
      
      Assert.assertNotNull("Proxy factory created a null proxy", obj);
      Assert.assertTrue("Proxy factory created a proxy which is *not* of type " + Echo.class, (obj instanceof Echo));
      Assert.assertTrue("Proxy factory created a proxy which is *not* of type " + Greeter.class, (obj instanceof Greeter));
      
      Echo proxy = (Echo) obj;
      String msg = "Hello";
      String result = proxy.echo(msg);
      Assert.assertEquals("Unexpected return value from proxy invocation", msg, result);
      
      Greeter greeter = (Greeter) obj;
      String user = "me";
      String greeting = greeter.greet(user);
      String expected = "Hello " + user;
      Assert.assertEquals("Unexpected return value from proxy invocation", expected, greeting);
   }
}
