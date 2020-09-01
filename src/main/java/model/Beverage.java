package model;

import java.util.Map;

public class Beverage
{

    private String beverageName;

    //to store the ingredient and their respective quantity required to prepare this beverage.
    private  Map<String,Integer> ingredientRequiredQtyMap;

    public Beverage(String beverageName, Map<String, Integer> ingredientRequiredQtyMap) {
        this.beverageName = beverageName;
        this.ingredientRequiredQtyMap = ingredientRequiredQtyMap;
    }

    public String getBeverageName() {
        return beverageName;
    }

    public Map<String, Integer> getIngredientRequiredQtyMap() {
        return ingredientRequiredQtyMap;
    }

}
