package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame{
    private Label nrPersonLabel;
    private TextField nrPersonField;
    private Label nrQueueLabel;
    private TextField nrQueueField;
    private Label tSimulationLabel;
    private TextField tSimulationField;
    private Label tArrivalMinLabel;
    private TextField tArrivalMinField;
    private Label tArrivalMaxLabel;
    private TextField tArrivalMaxField;
    private Label tServiceMinLabel;
    private TextField tServiceMinField;
    private Label tServiceMaxLabel;
    private TextField tServiceMaxField;
    private TextArea resultField;
    private JButton button;

    public View(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1500, 200);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();

        nrPersonLabel = new Label("Numar de clienti");
        nrPersonField = new TextField("4", 6);
        panel1.add(nrPersonLabel);
        panel1.add(nrPersonField);

        nrQueueLabel = new Label("Numar de cozi");
        nrQueueField = new TextField("2", 6);
        panel1.add(nrQueueLabel);
        panel1.add(nrQueueField);

        tSimulationLabel = new Label("Simulation time");
        tSimulationField = new TextField("60",6);
        panel1.add(tSimulationLabel);
        panel1.add(tSimulationField);

        tArrivalMinLabel = new Label("Arrival time min");
        tArrivalMinField = new TextField("2",6);
        panel1.add(tArrivalMinLabel);
        panel1.add(tArrivalMinField);

        tArrivalMaxLabel = new Label("Arrival time max");
        tArrivalMaxField = new TextField("10",6);
        panel1.add(tArrivalMaxLabel);
        panel1.add(tArrivalMaxField);

        tServiceMinLabel = new Label("Service time min");
        tServiceMinField = new TextField("2", 6);
        panel1.add(tServiceMinLabel);
        panel1.add(tServiceMinField);

        tServiceMaxLabel = new Label("Service time max");
        tServiceMaxField = new TextField("4",6);
        panel1.add(tServiceMaxLabel);
        panel1.add(tServiceMaxField);

        resultField = new TextArea(40, 60);
        panel2.add(resultField);

        button = new JButton("SEARCH");
        panel2.add(button);

        JPanel panel = new JPanel();
        panel.add(panel1);
        panel.add(panel2);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        this.setContentPane(panel);
    }

    public String getNrPersonField() {
        return nrPersonField.getText();
    }

    public void setNrPersonField(String string) {
        nrPersonField.setText(string);
    }

    public String getNrQueueField() {
        return nrQueueField.getText();
    }

    public void setNrQueueField(String string) {
        nrQueueField.setText(string);
    }

    public String getTSimulationField() {
        return tSimulationField.getText();
    }

    public void setTSimulationField(String string) {
        tSimulationField.setText(string);
    }

    public String getTArrivalMinField() {
        return tArrivalMinField.getText();
    }

    public void setTArrivalMinField(String string) {
        tArrivalMinField.setText(string);
    }

    public String getTArrivalMaxField() {
        return tArrivalMaxField.getText();
    }

    public void setTArrivalMaxField(String string) {
        tArrivalMaxField.setText(string);
    }

    public String getTServiceMinField() {
        return tServiceMinField.getText();
    }

    public void setTServiceMinField(String string) {
        tServiceMinField.setText(string);
    }

    public String getTServiceMaxField() {
        return tServiceMaxField.getText();
    }

    public void setTServiceMaxField(String string) {
        tServiceMaxField.setText(string);
    }

    public String getResultArea() {
        return resultField.getText();
    }

    public void setResultArea(String string) {
        resultField.setText(string);
    }

    public void addListener(ActionListener e) {
        button.addActionListener(e);
    }
}
