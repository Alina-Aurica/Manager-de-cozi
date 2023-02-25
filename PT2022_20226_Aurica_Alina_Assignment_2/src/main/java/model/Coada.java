package model;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Coada implements Runnable, Comparable<Coada>{
    private ArrayBlockingQueue<Client> queue;
    private AtomicInteger waitingPeriod;

    public Coada(int q){
        queue = new ArrayBlockingQueue<Client>(q);
        waitingPeriod = new AtomicInteger(0);
    }

    public ArrayBlockingQueue<Client> getQueue() {
        return this.queue;
    }

    public void setQueue(ArrayBlockingQueue<Client> queue) {
        this.queue = queue;
    }

    public AtomicInteger getWaitingPeriod() {
        return this.waitingPeriod;
    }

    public void setWaitingPeriod(AtomicInteger waitingPeriod) {

        this.waitingPeriod = waitingPeriod;
    }

    @Override
    public String toString(){
        String result = "";
        for(Client c: this.queue){
            result = result + c.toString() + " ";
        }

        return result;
    }

    public synchronized void add(Client c){
        this.queue.add(c);
        this.waitingPeriod.addAndGet(c.getTService()); //cu ce ar trebui incrementat waitingPeriod????
        //System.out.println("wPeriod: " + this.waitingPeriod);
    }

    public /*synchronized*/ void remove(Client c){
        this.queue.remove(c);
    }

    public int getSize(){
        return this.queue.size();
    }

    public ArrayList<Client> getClientList() {
        ArrayList<Client> rezultat = new ArrayList<Client>();
        for(Client t : this.queue)
            rezultat.add(t);

        return rezultat;
    }

    @Override
    /*
    public void run() { //pare o abordare okay, cred, sper
        boolean coadaGoala = false;
        int size = queue.size();

        //while(coadaGoala == false)
        while(true)
        {
            //for(Client c: this.queue)
            if(this.queue.size() != 0)
            {
                if(this.queue.element().getTService() == 0)
                {
                    this.queue.remove();
                }
                else
                {
                    this.queue.element().setTService(this.queue.element().getTService()-1);
                    this.waitingPeriod.addAndGet(-1);
                }

                try {
                    Thread.sleep(1000);
                    //this.queue.element().setTService(this.queue.element().getTService()-1);
                    //this.waitingPeriod.addAndGet(-1);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(this.waitingPeriod.equals(0))
                {
                    coadaGoala = true;
                }

            }
        }
    }
     */

    public void run() {

        while (true) {
            if(!queue.isEmpty()) {
                Client c = queue.element();
                c.setTService(c.getTService()-1);

                if(c.getTService() == 0) {
                    queue.remove(c);
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //ceva
            }
            waitingPeriod.decrementAndGet();
            //this.waitingPeriod.addAndGet(-1);
        }
    }

    @Override
    public int compareTo(Coada c) {
        return this.waitingPeriod.get() - c.waitingPeriod.get();
    }
}
