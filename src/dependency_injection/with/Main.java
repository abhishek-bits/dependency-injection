package dependency_injection.with;

import dependency_injection.Burger;
import dependency_injection.Pizza;

public class Main {
    public static void main(String[] args) {

        // Applying Dependency Injection
        Chef pizzaChef = new Chef(new Pizza());

        // Applying Dependency Injection
        Chef burgerChef = new Chef(new Burger());
    }
}
