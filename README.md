# Dependency Injection

- In any object-oriented language, **classes and objects are the foundations** of any functionality you can think of.
- The **relationships** between these classes and objects, make it possible to **extend** and **reuse** some of these functionalities.
- The **way** that we choose to build these functionalities (or dependencies), determine how **decoupled** and **reusable** our code will be.

```java
public interface Food { }
```

```java
public class Pizza implements Food { }
```

```java
public class Burger implements Food { }
```

```java
public class Chef {

    private Food food;

    // Passing the dependency to the constructor
    // of the dependent object
    public Chef(Food food) {
        this.food = food;
    }

    public void prepareFood() {
        // do something with the food object
    }
}
```

```java
public class Main {
    public static void main(String[] args) {

        // Applying Dependency Injection
        Chef pizzaChef = new Chef(new Pizza());

        // Applying Dependency Injection
        Chef burgerChef = new Chef(new Burger());
    }
}
```

The Dependency was injected into the object instead of being created inside it.
- We **decoupled** the construction of `Chef` class from the construction of its **dependencies**.
- Now, it is **no longer** the **dependent** class's **responsibility** to figure out what is changing.

## Types of Dependency Injection

### Constructor Injection

- MOST RECOMMENDED One!
- Dependencies are provided through a class constructor.

### Setter Injection

The Dependency is injected via the setter method.

```java
public class ClassA {
    
    private ClassB classB;
    
    public CLass() {}
    
    public void setClassB(ClassB classB) {
        this.classB = classB;
    }
}
```

```java
public class Main {
    public static void main(String[] args) {
        ClassA classA = new ClassA();
        classA.setClassB(new ClassB());
    }
}
```

**Disadvantages**: Hides the dependencies
- By reading the constructor signature we **cannot identify** that there is a dependency **right away**, leading to **NullPointerException** at runtime.

### Field Injection

The Dependency is injected via the field.

```java
public class ClassA {
    
    public ClassB classB;
    
    public ClassA() {}
}
```

```java
public class Main {
    public static void main(String[] args) {
        ClassA classA = new ClassA();
        classA.classB = new ClassB();
    }
}
```

**Disadvantage**: Hides the dependency
- Adds complexity due to the mutation or reflection required.

## Inversion of Control

This is the **D** of SOLID Principles; Dependency Inversion Principle.

- It is the principle in software engineering which **transfers the control of objects** or portions of a program to a **framework** (or library).
- Enables a **framework to take control over the flow of a program** and make calls to our custom code.
- To achieve this, frameworks use **abstraction**, hence to add an extra behavior, we **extend the classes of the framework**.

<table>
<tr>
<th>With</th>
<th>Without</th>
</tr>
<tr>
<td>
<pre>
// Depends on the abstraction
// Loosely coupled code
public class Chef {
    private Food food;
    public Chef(Food food) {
        this.food = food;
    }
}
</pre>
</td>
<td>
<pre>
// Depends on the implementation
// Tightly coupled code
public class Chef {
    private Food food;
    public Chef() {
        food = new Burger();
    }
}
</pre>
</td>
</tr>
</table>

A class should concentrate on **fulfilling its responsibilities** 
and **not on creating objects** that are required to fulfil them.
Such objects should be provided by Dependency Injection.

## Advantages

- Reduces boilerplate and duplicate code, as the dependencies are provided by the injector.
- Follows **Open-Closed** principle.
- Program becomes easier to test as the dependencies can be isolated and mocked.
- Allows components to **communicate through contracts**.

## Before Applying DI

- Using Dependency Injection will most likely make the code worse.
- Should only be used whenever it is necessary.
- Consider if we can reduce or eliminate dependencies or if mocking will facilitate our testing.

#### Problem 1: Leaking Classes

Suppose `ClassB` is used only internally by `ClassA`.

RECOMMENDED Approach: Avoid Dependency Injection

```java
public class ClassA {
    
    public ClassA() {}
    
    public void doStuff() {
        // instantiate ClassB
        // do something with it
        // Clients don't care about it.
    }
    
    private static ClassB {
        // properties and methods here
    }
}
```

INVALID Approach: Applies Dependency Injection

```java
public class ClassA {
    
    private ClassB classB;
    
    public ClassA(ClassB classB) {
        this.classB = classB;
    }
    
    public void doStuff() {
        // do something with classB
    }
}
```

- Here, we have exposed the implementation detail of ClassA
- Now, `ClassB` has to exist in some other place as well.

This leads to an overall worse architecture with leaking concerns.

### Problem 2: Mocking

```java
public class ClassATest {
    
    @Test
    public void test() {
        ClassA classA = new ClassA(mock(new ClassB()));
        // test something
    }
}
```

If we mock `ClassB` as shown, our test will definitely pass. 
But, we will never be sure that the **actual production code** of `ClassA`
will work with the actual production code of `ClassB` as all we used was a `mock`.


