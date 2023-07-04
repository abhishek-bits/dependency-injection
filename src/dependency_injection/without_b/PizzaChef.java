package dependency_injection.without_b;

import dependency_injection.Food;
import dependency_injection.Pizza;

public class PizzaChef {

    private Food pizza;

    public PizzaChef() {
        pizza = new Pizza();
    }
}
