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
package org.jboss.ejb3.proxy.javassist.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * DelegatingInvocationHandler
 *
 * @author Jaikiran Pai
 * @version $Revision: $
 */
public class DelegatingInvocationHandler <T> implements InvocationHandler
{

   private T delegateInstance;
   
   public DelegatingInvocationHandler(T delegate)
   {
      this.delegateInstance = delegate;
   }
   
   /** (non-Javadoc)
    * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
    */
   public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
   {
      return method.invoke(this.delegateInstance, args);
   }

}
