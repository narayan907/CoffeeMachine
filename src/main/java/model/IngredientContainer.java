package model;

public class IngredientContainer {

    //Each Ingredient Container is Connected with all the outlets present
    private Outlet outlet[];

    //quantity of ingredient present.
    private int quantity;

    public IngredientContainer(Outlet[] outlet, int quantity) {
        this.outlet = outlet;
        this.quantity=quantity;
    }


    public void addQuantity(int quantity)
    {
        this.quantity+=quantity;
    }

    public void transferToOutlet(String ingredientName,int outletNo,int quantity)
    {
        outlet[outletNo].addIngredient(ingredientName,quantity);
        this.quantity-=quantity;
    }

    public int getQuantity() {
        return quantity;
    }

}
