package model;

import java.util.HashMap;
import java.util.Map;

public class Outlet {

    private int outletNo;

    //status of the outlet, is it preparing a beverage or not.
    private boolean isBeingUsed;

    private Map<String,Integer> ingredientQtyMap;


    public Outlet(int outletNo)
    {
        this.outletNo=outletNo;
        this.ingredientQtyMap=new HashMap<String, Integer>();
    }

    public void addIngredient(String ingredientName,int quantity)
    {
        ingredientQtyMap.put(ingredientName,quantity);
    }

    public void setIsBeingUsed(boolean isBeingUsed)
    {
        this.isBeingUsed=isBeingUsed;
    }

    public boolean isBeingUsed() {
        return isBeingUsed;
    }

    private void clearIngredientQtyMap()
    {
        ingredientQtyMap.clear();
    }

    //set is being used to false after serving a beverage
    public void prepareAndServeTheBeverage(String beverageToPrepare)
    {
        //System.out.println("Starting to prepare Beverage : "+beverageToPrepare+" outletNo. : "+outletNo);

        //To simulate the beverage preparation
        for(int i=0;i<1;i++)
        {
            System.out.println("("+beverageToPrepare+" in progress.. outletNo. : "+outletNo+")");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(beverageToPrepare+" is prepared outletNo. : "+outletNo);
        this.clearIngredientQtyMap();
        this.setIsBeingUsed(false);
    }



}
