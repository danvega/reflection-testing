package com.abc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {

    private static Class employee;

    @BeforeAll
    public static void setup() {
        try {
            employee = Class.forName("com.abc.Employee");
        } catch (ClassNotFoundException e) {
            fail("com.abc.Employee class not found");
        }
    }

    @Test
    public void shouldHaveFieldFirstName() {
        try {
            Field firstName = employee.getDeclaredField("firstName");
            // should be a private field
            assertTrue(Modifier.isPrivate(firstName.getModifiers()),"firstName field should be private");
            // should be type String
            assertEquals("java.lang.String",firstName.getType().getName(),"firstName should be type String");
            // should have a getter
            assertTrue(hasGetter(firstName),"firstName should have a getter");
            // should have a setter
            assertTrue(hasSetter(firstName));
        } catch (NoSuchFieldException e) {
            fail("Employee class must contain field: firstName");
        }
    }

    @Test
    public void shouldHaveFieldLastName() {
        try {
            Field lastName = employee.getDeclaredField("lastName");
            // should be a private field
            assertTrue(Modifier.isPrivate(lastName.getModifiers()),"lastName field should be private");
            // should be type String
            assertEquals("java.lang.String",lastName.getType().getName(),"lastName should be type String");
            // should have a getter
            assertTrue(hasGetter(lastName),"lastName should have a getter");
            // should have a setter
            assertTrue(hasSetter(lastName));
        } catch (NoSuchFieldException e) {
            fail("Employee class must contain field: lastName");
        }
    }

    @Test
    public void constructorShouldSetFields() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        try {
            Constructor constructor = employee.getConstructor(String.class,String.class);
            Object dan = constructor.newInstance("Dan", "Vega");
            Method getFirstName = dan.getClass().getMethod("getFirstName");
            Method getLastName = dan.getClass().getMethod("getLastName");
            assertEquals(getFirstName.invoke(dan),"Dan","The constructor did not set the firstName");
            assertEquals(getLastName.invoke(dan),"Vega","The constructor did not set the lastName");
        } catch (NoSuchMethodException e) {
            fail("No such method: " + e.getMessage());
        }
    }

    @Test
    public void getFullNameShouldReturnFullName() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        try {
            Constructor constructor = employee.getConstructor(String.class, String.class);
            Object dan = constructor.newInstance("Dan", "Vega");
            Method getFullName = dan.getClass().getMethod("getFullName");
            assertEquals(getFullName.invoke(dan),"Dan Vega","Employee.getFullName() must return firstName + \" \" + lastName");
        } catch (NoSuchMethodException e) {
            fail("No such method: " + e.getMessage());
        }

    }

    private boolean hasGetter(Field field) {
        return hasGetterOrSetter(field,"get");
    }

    private boolean hasSetter(Field field) {
        return hasGetterOrSetter(field, "set");
    }

    private boolean hasGetterOrSetter(Field field, String prefix) {
        Optional<Method> getterOrSetter = Arrays.stream(employee.getMethods())
                .filter(f -> f.getName().equals(prefix + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1)))
                .findFirst();
        return getterOrSetter.isPresent();
    }

}
