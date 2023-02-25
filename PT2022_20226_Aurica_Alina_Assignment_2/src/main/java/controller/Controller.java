package controller;

import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private View view;
    private Manager gen;

    public Controller(View view){
        this.view = view;

        this.view.addListener(new AddListener());
    }

    class AddListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            int nrClienti = Integer.parseInt(view.getNrPersonField());
            System.out.println(nrClienti);
            int nrCozi = Integer.parseInt(view.getNrQueueField());
            System.out.println(nrCozi);

            int timeLimit = Integer.parseInt(view.getTSimulationField());
            System.out.println(timeLimit);

            int maxProcessingTime = Integer.parseInt(view.getTServiceMaxField());
            System.out.println(maxProcessingTime);
            int minProcessingTime = Integer.parseInt(view.getTServiceMinField());
            System.out.println(minProcessingTime);

            int maxArrivalTime = Integer.parseInt(view.getTArrivalMaxField());
            System.out.println(maxArrivalTime);
            int minArrivalTime = Integer.parseInt(view.getTArrivalMinField());
            System.out.println(minArrivalTime);

            gen = new Manager(nrClienti, nrCozi, timeLimit, maxProcessingTime, minProcessingTime, maxArrivalTime, minArrivalTime, view);
            Thread t = new Thread(gen);
            t.start();

            //view.setResultField(gen.result);
        }
    }

}
