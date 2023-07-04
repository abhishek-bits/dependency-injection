package dependency_injection.without_b;

import dependency_injection.Burger;
import dependency_injection.Food;

public class BurgerChef {

    private Food burger;

    public BurgerChef() {
        burger = new Burger();
    }
}
