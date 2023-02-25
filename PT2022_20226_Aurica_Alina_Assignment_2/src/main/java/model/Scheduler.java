package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Scheduler {
    private ArrayList<Coada> cozi;
    private int maxNoQueue;
    private int maxClientsPerCoada;

    public Scheduler(int maxNoQueue, int maxClientsPerCoada)
    {
        this.cozi = new ArrayList<Coada>();
        for( int i = 0; i < maxNoQueue; i++) {
            Coada queue = new Coada(maxClientsPerCoada);
            this.cozi.add(queue);
            Thread t = new Thread (queue);
            t.start();
        }
    }

    public ArrayList<Coada> getCozi() {
        return this.cozi;
    }

    public void setCozi(ArrayList<Coada> cozi) {
        this.cozi = cozi;
    }

    public int getMaxNoQueue() {
        return this.maxNoQueue;
    }

    public void setMaxNoQueue(int maxNoQueue) {
        this.maxNoQueue = maxNoQueue;
    }

    public int getMaxClientsPerCoada() {
        return this.maxClientsPerCoada;
    }

    public void setMaxClientsPerCoada(int maxClientsPerCoada) {
        this.maxClientsPerCoada = maxClientsPerCoada;
    }

    public void timeStrategy(Client client)
    {
        /*
        for(Coada c1: this.cozi)
        {
            for(Coada c2: this.cozi)
            {
                int i1 = c1.getWaitingPeriod().get();
                int i2 = c2.getWaitingPeriod().get();
                if(i1 > i2 && !c1.equals(c2) && c1.getInversat() == false)
                {
                    Coada c = new Coada(maxClientsPerCoada);
                    c.setQueue(c1.getQueue());
                    c1.setQueue(c2.getQueue());
                    c2.setQueue(c.getQueue());
                }
                c1.setInversat(true);
            }
        }
         */
        Collections.sort(this.cozi);
        //System.out.println(this.cozi.get(0).getWaitingPeriod());
        this.cozi.get(0).add(client);

    }

    public String toString(){
        String result = "";
        for(int i = 0; i < this.cozi.size(); i++)
            result += "coada "+ i + ": " + this.cozi.get(i).toString() + "\n";

        return result;
    }


}
