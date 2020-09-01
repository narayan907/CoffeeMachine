import controller.CoffeeMachineController;
import model.Beverage;
import model.IngredientContainer;
import model.Outlet;
import org.testng.annotations.Test;
import service.CoffeeMachineService;

import java.util.HashMap;
import java.util.Map;

public class JavaTestngTestCases {

    @Test
    public static void prepareHotTeaAndHotCoffee()
    {
        CoffeeMachineController coffeeMachineController = initialiseMachineWithGivenInput();
        coffeeMachineController.prepareABeverage("hot_tea",1);
        coffeeMachineController.prepareABeverage("hot_coffee",2);

        wait(3000);

        coffeeMachineController.prepareABeverage("green_tea",0);
        coffeeMachineController.prepareABeverage("black_tea" ,0);

        System.out.println();
        System.out.println();


    }

    @Test
    public static void prepareHotCoffeeAndBlackTea()
    {
        CoffeeMachineController coffeeMachineController = initialiseMachineWithGivenInput();
        coffeeMachineController.prepareABeverage("hot_coffee",1);
        coffeeMachineController.prepareABeverage("black_tea",2);

        wait(3000);

        coffeeMachineController.prepareABeverage("green_tea",0);
        coffeeMachineController.prepareABeverage("hot_tea" ,0);

        System.out.println();
        System.out.println();

    }

    @Test
    public static void prepareHotTeaAndBlackTea()
    {
        CoffeeMachineController coffeeMachineController = initialiseMachineWithGivenInput();
        coffeeMachineController.prepareABeverage("hot_tea",1);
        coffeeMachineController.prepareABeverage("black_tea",2);

        wait(3000);

        coffeeMachineController.prepareABeverage("green_tea",0);
        coffeeMachineController.prepareABeverage("hot_coffee" ,0);

        System.out.println();
        System.out.println();

    }

    @Test
    public static void simulateMoreThanOneRequestToAnOutlet()
    {
        CoffeeMachineController coffeeMachineController = initialiseMachineWithGivenInput();
        coffeeMachineController.prepareABeverage("hot_tea",1);
        coffeeMachineController.prepareABeverage("black_tea",1);

        wait(3000);

        System.out.println();
        System.out.println();
    }



    public static CoffeeMachineController initialiseMachineWithGivenInput()
    {
        int N=3;
        Outlet[] outlets = new Outlet[N];
        for(int i=0;i<outlets.length;i++)
        {
            outlets[i] = new Outlet(i);
        }

        Map<String, IngredientContainer> ingredientAndIngredientContainerMap = new HashMap<String,IngredientContainer>();

        String hotWater="hot_water";
        ingredientAndIngredientContainerMap.put(hotWater,new IngredientContainer(outlets,500));
        String hotMilk="hot_milk";
        ingredientAndIngredientContainerMap.put(hotMilk,new IngredientContainer(outlets,500));
        String gingerSyrup="ginger_syrup";
        ingredientAndIngredientContainerMap.put(gingerSyrup,new IngredientContainer(outlets,100));
        String sugarSyrup="sugar_syrup";
        ingredientAndIngredientContainerMap.put(sugarSyrup,new IngredientContainer(outlets,100));
        String teaLeavesSyrup="tea_leaves_syrup";
        ingredientAndIngredientContainerMap.put(teaLeavesSyrup,new IngredientContainer(outlets,100));



        Map<String, Beverage> beverageMap = new HashMap<String, Beverage>();

        String beverageName1 = "hot_tea";
        Map<String,Integer> ingredientRequiredQtyMap1 = new HashMap<String,Integer>();
        ingredientRequiredQtyMap1.put(hotWater,200);
        ingredientRequiredQtyMap1.put(hotMilk,100);
        ingredientRequiredQtyMap1.put(gingerSyrup,10);
        ingredientRequiredQtyMap1.put(sugarSyrup,10);
        ingredientRequiredQtyMap1.put(teaLeavesSyrup,30);
        beverageMap.put(beverageName1,new Beverage(beverageName1,ingredientRequiredQtyMap1));

        String beverageName2 = "hot_coffee";
        Map<String,Integer> ingredientRequiredQtyMap2 = new HashMap<String,Integer>();
        ingredientRequiredQtyMap2.put(hotWater,100);
        ingredientRequiredQtyMap2.put(gingerSyrup,30);
        ingredientRequiredQtyMap2.put(hotMilk,400);
        ingredientRequiredQtyMap2.put(sugarSyrup,50);
        ingredientRequiredQtyMap2.put(teaLeavesSyrup,30);
        beverageMap.put(beverageName2,new Beverage(beverageName2,ingredientRequiredQtyMap2));

        String beverageName3 = "black_tea";
        Map<String,Integer> ingredientRequiredQtyMap3 = new HashMap<String,Integer>();
        ingredientRequiredQtyMap3.put(hotWater,300);
        ingredientRequiredQtyMap3.put(gingerSyrup,30);
        ingredientRequiredQtyMap3.put(sugarSyrup,50);
        ingredientRequiredQtyMap3.put(teaLeavesSyrup,30);
        beverageMap.put(beverageName3,new Beverage(beverageName3,ingredientRequiredQtyMap3));

        String beverageName4 = "green_tea";
        Map<String,Integer> ingredientRequiredQtyMap4 = new HashMap<String,Integer>();
        ingredientRequiredQtyMap4.put(hotWater,100);
        ingredientRequiredQtyMap4.put(gingerSyrup,30);
        ingredientRequiredQtyMap4.put(sugarSyrup,50);
        ingredientRequiredQtyMap4.put("green_mixture",30);
        beverageMap.put(beverageName4,new Beverage(beverageName4,ingredientRequiredQtyMap4));

        CoffeeMachineService coffeeMachineService = new CoffeeMachineService(outlets,ingredientAndIngredientContainerMap,beverageMap);
        return new CoffeeMachineController(coffeeMachineService);
    }

    public static void wait(int milliseconds)
    {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
