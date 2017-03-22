package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Andrey on 01.01.2017.
 */
public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume("a man");
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new_uuid");
        System.out.println(r);
        //TODO : invoke r.toString via reflection
        Class c = r.getClass();
        Class[] paramTypes = new Class[]{String.class, int.class};
        Method method = c.getMethod("toString");
        System.out.println("r.toSring = " + method.invoke(r));
    }
}
