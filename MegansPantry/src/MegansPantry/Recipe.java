package MegansPantry;

import java.util.ArrayList;

public class Recipe {

    private String name;
    private String url;
    private ArrayList<Ingredient> ingredientList;

    public Recipe(String name, String url) {
        this.name = name;
        this.url = url;
    }

}
