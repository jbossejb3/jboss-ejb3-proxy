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
package org.jboss.ejb3.proxy.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.jboss.ejb3.proxy.spi.factory.ProxyFactory;

/**
 * Implementation of {@link ProxyFactory} which creates {@link java.lang.reflect.Proxy}
 * based proxies
 *
 * @author Jaikiran Pai
 * @version $Revision: $
 */
public class ReflectProxyFactory implements ProxyFactory
{

   /**
    * Creates a {@link Proxy}
    * <p>
    *   This method uses the classloader of <code>types</code>[0] {@link Class} for creating the proxy 
    * </p>
    * @see org.jboss.ejb3.proxy.spi.factory.ProxyFactory#createProxy(java.lang.Class<?>[], java.lang.reflect.InvocationHandler)
    */
   public Object createProxy(Class<?>[] types, InvocationHandler invocationHandler) throws IllegalArgumentException
   {
      ClassLoader cl = types[0].getClassLoader();
      return this.createProxy(cl, types, invocationHandler);
   }

   /**
    * @see org.jboss.ejb3.proxy.spi.factory.ProxyFactory#createProxy(java.lang.ClassLoader, java.lang.Class<?>[], java.lang.reflect.InvocationHandler)
    */
   public Object createProxy(ClassLoader cl, Class<?>[] types, InvocationHandler invocationHandler)
         throws IllegalArgumentException
   {
      return Proxy.newProxyInstance(cl, types, invocationHandler);
   }

}
