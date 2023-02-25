package controller;

import model.Client;
import model.Coada;
import model.Scheduler;
import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Manager implements Runnable {
    private Scheduler scheduler;
    private View view;
    private ArrayList<Client> generateClient = new ArrayList<Client>();

    public int timeLimit; //= 20;// = Integer.parseInt(view.getTSimulationField()); //constructor
    public int maxProcessingTime; //= 4;// = Integer.parseInt(view.getTServiceMaxField());
    public int minProcessingTime; // = 2;// = Integer.parseInt(view.getTServiceMinField());
    public int maxArrivalTime; // = 15;// = Integer.parseInt(view.getTArrivalMaxField());
    public int minArrivalTime; // = 2;// = Integer.parseInt(view.getTArrivalMinField());
    public int nrClienti; // = 4;// = Integer.parseInt(view.getNrQueueField());
    public int nrCozi; // = 2;// = Integer.parseInt(view.getNrPersonField());
    public String result = "";
    public double medServiceTime = 0.0;
    public int pickHour = 0;
    //public AddListener listener;

    public Manager(int nrClienti, int nrCozi, int timeLimit, int maxProcessingTime, int minProcessingTime, int maxArrivalTime, int minArrivalTime, View view){
        this.nrClienti = nrClienti;
        System.out.println(nrClienti);
        this.nrCozi = nrCozi;
        System.out.println(nrCozi);

        System.out.println("Inainte de scheduler");

        this.scheduler = new Scheduler(nrCozi, nrClienti);
        this.view = view;

        //listener = new AddListener();
        //this.view.addListener(listener);

        this.timeLimit = timeLimit; //constructor
        System.out.println(timeLimit);
        this.maxProcessingTime = maxProcessingTime;
        System.out.println(maxProcessingTime);
        this.minProcessingTime = minProcessingTime;
        System.out.println(minProcessingTime);
        this.maxArrivalTime = maxArrivalTime;
        System.out.println(maxArrivalTime);
        this.minArrivalTime = minArrivalTime;
        System.out.println(minArrivalTime);


        generateNRandomTasks();
        for(Client c: generateClient)
        {
            System.out.println(c.toString());
        }

        System.out.println("ies din constructor");
    }


    private void generateNRandomTasks(){
        //generateClient = new ArrayList<Client>();
        Random rand = new Random();
        for(int i = 1; i <=  nrClienti; i++)
        {
            //System.out.println(nrClienti);
            int arrivalT = rand.nextInt(maxArrivalTime - minArrivalTime) + minArrivalTime;
            int processingT = rand.nextInt(maxProcessingTime - minProcessingTime) + minProcessingTime;

            Client c = new Client(i, arrivalT, processingT);
            //System.out.println(c.toString());
            generateClient.add(c);
            medServiceTime += c.getTService();
        }

        medServiceTime = medServiceTime / nrClienti;
        Collections.sort(generateClient);
    }

    public void scriereFisier(String result)
    {
        try {
            FileWriter myFile = new FileWriter("D:\\Facultate\\ANUL 2\\Anul 2 - Semestrul 2\\TP\\PT2022_20226_Aurica_Alina_Assignment_2\\myFile3.txt");
            myFile.write(result);
            myFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int coziP()
    {
        int coziPornite = 0;
        for(Coada c : this.scheduler.getCozi())
        {
            if(c.getSize() != 0)
                coziPornite++;
        }

        return coziPornite;
    }

    @Override
    public void run() {
        int currentTime = 0;
        int maxClienti = 0; double sumWaitingTime = 0.0;

        while(currentTime < timeLimit) {
            int sumClienti = 0; double maxWaitingTime = 0.0;
            if(generateClient.size() != 0) {
                Client c = generateClient.get(0);
                while (currentTime == c.getTArrive() && generateClient.size() != 0) {
                    scheduler.timeStrategy(c); generateClient.remove(0);
                    if(generateClient.size() != 0)
                        c = generateClient.get(0);}}
            for(int i = 0; i < nrCozi; i++) {
                sumClienti += scheduler.getCozi().get(i).getSize();
                for(Client c: scheduler.getCozi().get(i).getClientList())
                    maxWaitingTime += c.getTService();
                if(scheduler.getCozi().get(i).getSize() != 0) {
                    maxWaitingTime = maxWaitingTime / scheduler.getCozi().get(i).getSize();
                    sumWaitingTime += maxWaitingTime;}}
            if(sumClienti > maxClienti) { maxClienti = sumClienti; pickHour = currentTime; }

            result = result + currentTime + "\n" + generateClient + "\n" + scheduler.toString() + "\n";
            view.setResultArea(result); scriereFisier(result);
            currentTime++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();}
            if(coziP() == 0 && generateClient.size() == 0)
                break;
        }
        sumWaitingTime = sumWaitingTime / (currentTime - 1);
        result += "pickHour: " + pickHour + "\n" + "average service time: " + medServiceTime + "\n" + "average waiting time: " + sumWaitingTime;
        view.setResultArea(result); scriereFisier(result);
    }
}
