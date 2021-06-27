package javacore.chapter08.runtimeAnnotations;

import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ActionListenerInstaller {
    public static void processAnnotations(Object obj){
        Class<?> cl = obj.getClass();
        for (Method method : cl.getDeclaredMethods()) {
            ActionListenerFor actionListenerFor = method.getAnnotation(ActionListenerFor.class);
            if (actionListenerFor!=null) {
                try {
                    //��ȡsource�ֶ�ָ���feld����,��Button����
                    String source = actionListenerFor.source();
                    System.out.println("source :"+source);
                    Field field = cl.getDeclaredField(source);
                    field.setAccessible(true);
                    addListener(field.get(obj),obj,method);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private static void addListener(Object addTarget, Object param, Method listenerMethod) throws Exception {

        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("��̬�����౻���� "+listenerMethod.toString());
                return listenerMethod.invoke(param);
            }
        };
        ActionListener listener = (ActionListener)Proxy.newProxyInstance(null, new Class[]{ActionListener.class}, invocationHandler);
        //��ȡ�����addActionListener����
        Method addActionListener = addTarget.getClass().getMethod("addActionListener", ActionListener.class);
        addActionListener.invoke(addTarget,listener);
    }
}
