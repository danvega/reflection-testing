# Creating Classes

In this exercise you will learn how to write tests for a class that doesn't yet exist. 
This scenario comes up when you want to give someone an exercise that involves
writing classes, fields, methods, etc...

## Employee Class

### Step One: Create a First Name field

- firstName
    - access modifier: private
    - type: String
    - getter: getFirstName()
    - setter: setFirstName(String firstName)

### Step Two: Create a Last Name field
    
- lastName
    - access modifier: private
    - type: String
    - getter: getLastName()
    - setter: getLastName(String lastName)

### Step Three: Create a constructor

The constructor should take in firstName and lastName as arguments and set each field.

### Step Four: Create a new method

- getFullName()
    - returns a String
    - should return firstName + " " + lastName
    
