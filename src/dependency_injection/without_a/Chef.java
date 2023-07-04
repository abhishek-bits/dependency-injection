package dependency_injection.without_a;


import dependency_injection.Burger;
import dependency_injection.Food;
import dependency_injection.Pizza;

public class Chef {

    private Food burger;
    private Food pizza;

    // This approach cannot be used and
    // tested independently of this class.
    // This is a HARD Dependency hence should be avoided.
    public Chef() {
        burger = new Burger();
        pizza = new Pizza();
    }

    public void prepareFood() {
        // do something with the burger object
    }

    public void preparePizza() {
        // do something with the pizza object
    }
}
