package service;

import model.Outlet;

// This Thread ensures that two beverages can be prepared in parallel.
public class Process implements Runnable {
    Thread t;
    Outlet outlet;
    String beverageToPrepare;

    public Process(Outlet outlet, String beverageToPrepare)
    {
        t = new Thread(this);
        this.outlet=outlet;
        this.beverageToPrepare = beverageToPrepare;
    }

    @Override
    public void run() {

        outlet.prepareAndServeTheBeverage(beverageToPrepare);

    }

    public void initiatePreparation()
    {
        t.start();
    }
}
