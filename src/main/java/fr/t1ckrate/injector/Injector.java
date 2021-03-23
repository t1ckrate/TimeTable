package fr.t1ckrate.injector;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.FieldInfo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Injector {

    public void defaultInject() {
        Map<Class<?>, Object> classes = new HashMap<>();
        classes.put(Injector.class, this);

        new ClassGraph().enableAllInfo().scan().getClassesWithAnnotation(ToInject.class.getName()).loadClasses().forEach(aClass -> {
            try {
                Object o = aClass.getDeclaredConstructor().newInstance();
                classes.put(aClass, o);
                System.out.println("Registered " + aClass.getName() + "(" + o + ")");
            } catch (NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        new ClassGraph().enableAllInfo().scan().getClassesWithFieldAnnotation(Inject.class.getName()).forEach(classInfo -> {
            System.out.println("Injecting in " + classInfo.getName());
            for (FieldInfo fieldInfo : classInfo.getDeclaredFieldInfo()) {
                if (fieldInfo.hasAnnotation(Inject.class.getName())) {
                    Field field = fieldInfo.loadClassAndGetField();
                    field.setAccessible(true);

                    if (classes.containsKey(field.getType())) {
                        try {
                            field.set(null, field.getType().cast(classes.get(field.getType())));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    public void injectAtRuntime(Object o) {
        new ClassGraph().enableAllInfo().scan().getClassesWithFieldAnnotation(Inject.class.getName()).forEach(classInfo -> {
            System.out.println("Injecting in " + classInfo.getName());
            for (FieldInfo fieldInfo : classInfo.getDeclaredFieldInfo()) {
                if (fieldInfo.hasAnnotation(Inject.class.getName())) {
                    Field field = fieldInfo.loadClassAndGetField();
                    field.setAccessible(true);

                    if (field.getType().isAssignableFrom(o.getClass())) {
                        try {
                            field.set(null, field.getType().cast(o));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

}
