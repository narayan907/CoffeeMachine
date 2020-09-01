package controller;

import service.CoffeeMachineService;

// This is the main controller class which will call methods of coffee machine service upon receiving requests.
public class CoffeeMachineController {

    private CoffeeMachineService coffeeMachineService;

    public CoffeeMachineController(CoffeeMachineService coffeeMachineService)
    {
        this.coffeeMachineService = coffeeMachineService;
    }

    // A request consists to two params beverageToPrepare and the outlet from which it is requested.
    public void prepareABeverage(String beverageToPrepare,int outletNo)
    {
        coffeeMachineService.prepareABeverage(beverageToPrepare,outletNo);
    }


    //Other features

    public void getListOfAllBeveragesAvailable()
    {
        coffeeMachineService.getAllBeverages();
    }

    public void getAllIngredientsAmount()
    {
        coffeeMachineService.getStatusOfAllIngredients();
    }

    public void getStatusOfAnOutlet(int outletNo)
    {
        coffeeMachineService.getOutletStatus(outletNo);
    }

    public void refillAnIngredient(String ingredientName,int quantity)
    {
        coffeeMachineService.refillAnIngredient(ingredientName,quantity);
    }
}
