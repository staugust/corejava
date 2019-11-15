package cn.augusto.annotations;

import javax.swing.*;
import java.awt.event.*;
import java.lang.reflect.*;
import java.lang.annotation.*;


public class ListenerDemo {

  public static void processAnnotations(Object obj){
    try{
      Class<?> clz = obj.getClass();
      for(Method m : clz.getDeclaredMethods()){
        ActionListenerFor a = m.getAnnotation(ActionListenerFor.class);
        if (a != null){
          Field f = clz.getDeclaredField(a.source());
          f.setAccessible(true);
          addListener(f.get(obj), obj, m);
        }
      }
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  public static void addListener(Object source, final Object obj, final Method m) throws ReflectiveOperationException {
    InvocationHandler handler = new InvocationHandler() {
      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return m.invoke(obj);
      }
    };
    Object listener = Proxy.newProxyInstance(null, new Class[]{java.awt.event.ActionListener.class}, handler);
    Method adder = source.getClass().getMethod("addActionListener", ActionListener.class);
    adder.invoke(source, listener);
  }

  public static void main(String[] argv){

  }
}
