/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2010, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
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
package org.jboss.ejb3.proxy.spi.factory;

import java.lang.reflect.InvocationHandler;

/**
 * Defines the contract for a factory capable of creating 
 * implementation non-specific proxies.
 *
 * @author Jaikiran Pai
 * @author <a href="mailto:andrew.rubinger@jboss.org">ALR</a>
 * @version $Revision: $
 */
public interface ProxyFactory
{
   //-------------------------------------------------------------------------------------||
   // Contracts --------------------------------------------------------------------------||
   //-------------------------------------------------------------------------------------||
   
   /**
    * Creates a proxy which is castable to the specified type(s) passed;
    * the specified {@link InvocationHandler} will service requests
    * 
    * @param cl The classloader to use for creating the proxy
    * @param types Target types supported by the returned proxy.  In some implementations,
    *   this may require an array of interfaces; in others a concrete base class must be supplied.
    *   It is the responsibility of implementors to throw {@link IllegalArgumentException} on 
    *   unsupported input
    * @param invocationHandler Underlying handler used to service invocations upon the returned proxy; must
    * not be null else {@link IllegalArgumentException} will be raised
    * @throws IllegalArgumentException
    */
   Object createProxy(ClassLoader cl, Class<?>[] types, InvocationHandler invocationHandler) throws IllegalArgumentException;

   /**
    * Creates a proxy which is castable to the specified type(s) passed;
    * the specified {@link InvocationHandler} will service requests
    * <p>
    *  It's upto the implementation to use a classloader of their choice to create the proxy. If the calling
    *  code expects a specific classloader to be used for proxy creation, then use the other {@link #createProxy(ClassLoader, Class[], InvocationHandler)}
    *  method.
    * </p>
    * @param types Target types supported by the returned proxy.  In some implementations,
    *   this may require an array of interfaces; in others a concrete base class must be supplied.
    *   It is the responsibility of implementors to throw {@link IllegalArgumentException} on 
    *   unsupported input
    * @param invocationHandler Underlying handler used to service invocations upon the returned proxy; must
    * not be null else {@link IllegalArgumentException} will be raised
    * @throws IllegalArgumentException
    */
   Object createProxy(Class<?>[] types, InvocationHandler invocationHandler) throws IllegalArgumentException;

}
