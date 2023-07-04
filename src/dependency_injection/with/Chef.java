package dependency_injection.with;

import dependency_injection.Food;

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
