package service;
// this is the coffee machine service which has the logic to decide whether to prepare a beverage or not,
// initiate the process to prepare a beverage, refill ingredients and get other details and status.
import model.Beverage;
import model.IngredientContainer;
import model.Outlet;

import java.util.Map;

public class CoffeeMachineService {

    private Map<String, IngredientContainer> ingredientAndIngredientContainerMap;

    private Map<String,Beverage> beverageMap;

    private Outlet[] outlets;

    public CoffeeMachineService(Outlet[] outlets,Map<String,IngredientContainer> ingredientAndIngredientContainerMap, Map<String, Beverage> beverageMap)
    {

        this.outlets = outlets;
        this.ingredientAndIngredientContainerMap = ingredientAndIngredientContainerMap;
        this.beverageMap=beverageMap;
    }

    public void prepareABeverage(String nameOfBeverage,int outletNo)
    {
       // System.out.println("Request to prepare model.Beverage : "+nameOfBeverage+" outletNo. : "+outletNo);

        //To prepare a beverage we first check, whether is it possible to do so.
        // if yes, required ingredients are moved to outlet
        // a process is initiated to prepare the beverage in the respective outlet.
        // here we ensure that only one request at a time can read the ingredients.
       synchronized (ingredientAndIngredientContainerMap) {

            Map<String, Integer> ingredientRequiredQtyMap = beverageMap.get(nameOfBeverage).getIngredientRequiredQtyMap();

            if (checkPossibleToPrepareTheBeverage(nameOfBeverage, outletNo)) {
                for (Map.Entry<String, Integer> ingredientQtyEntry : ingredientRequiredQtyMap.entrySet()) {
                    String ingredientName = ingredientQtyEntry.getKey();
                    IngredientContainer ingredientContainer = ingredientAndIngredientContainerMap.get(ingredientName);
                    ingredientContainer.transferToOutlet(ingredientName,outletNo, ingredientQtyEntry.getValue());
                }
                outlets[outletNo].setIsBeingUsed(true);

                //A process is instantiated which will run on a thread and when finished will serve the beverage
                Process p = new Process(outlets[outletNo],nameOfBeverage);
                p.initiatePreparation();
            }
        }


    }

    // method to check ingredient required to prepare a beverage
    // here we also, check the status of the outlet request, if it is already processing a previous request, we ignore this request
    // if the outlet is not in use, we iterate through all the ingredient required and check it with the ingredientAndIngredientContainerMap,
    // which stores the actual quantity of each ingredient present in the machine.
    private boolean checkPossibleToPrepareTheBeverage(String nameOfBeverage,int outletNo)
    {
        if(outlets[outletNo].isBeingUsed())
        {
            System.out.println("OutletNo : "+outletNo+" is already in use");
            return false;
        }

        Map<String,Integer> ingredientRequiredQtyMap = beverageMap.get(nameOfBeverage).getIngredientRequiredQtyMap();

        for(Map.Entry<String,Integer> ingredientRequiredQtyEntry : ingredientRequiredQtyMap.entrySet())
        {

            String ingredientName = ingredientRequiredQtyEntry.getKey();
            IngredientContainer ingredientContainer = ingredientAndIngredientContainerMap.get(ingredientName);

            if(ingredientContainer==null || ingredientContainer.getQuantity()<ingredientRequiredQtyEntry.getValue())
            {
                if(ingredientContainer==null)
                {
                    System.out.println(nameOfBeverage+" cannot be prepared because "+ingredientName+" is not available");
                }
                else {
                    System.out.println(nameOfBeverage+" cannot be prepared because "+ingredientName+" is not sufficient");
                }
                return false;

            }
        }

        return true;

    }

    //other features


    //update the model.IngredientContainer storing this ingredient with this quantity.
    public void refillAnIngredient(String ingredientName,int quantity)
    {
        synchronized (ingredientAndIngredientContainerMap) {
            ingredientAndIngredientContainerMap.get(ingredientName).addQuantity(quantity);
        }
    }

    public void getOutletStatus(int outletNo)
    {
        System.out.println("Outlet Status");

        if(outlets[outletNo].isBeingUsed())
        {
            System.out.println("Outlet "+outletNo+" is preparing a beverage");
        }
        else
        {
            System.out.println("Outlet "+outletNo+" is available to use");
        }


    }

    public void getAllBeverages()
    {
        System.out.println("Beverages Available");
        for(Map.Entry<String,Beverage> beverageEntry: beverageMap.entrySet())
        {
            System.out.println(beverageEntry.getKey());
        }

    }

    public void getStatusOfAllIngredients()
    {
        System.out.println("Ingredients-Quantity Present");
        for(Map.Entry<String,IngredientContainer> ingredientContainerEntry: ingredientAndIngredientContainerMap.entrySet())
        {
            System.out.println("Ingredient : "+ingredientContainerEntry.getKey()+ " Quantity : "+ingredientContainerEntry.getValue().getQuantity());
        }

    }



}
