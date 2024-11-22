package com.topjava.webapp;

import com.topjava.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Resume resume = new Resume();
        Field field = resume.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(resume));
        field.set(resume, "new_uuid");
        System.out.println(resume);

        invokeToStringMethod(resume);
    }

    public static void invokeToStringMethod(Resume resume) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method toString = resume.getClass().getDeclaredMethod("toString");
        System.out.println("Invoke toString: " + toString.invoke(resume));
    }
}
