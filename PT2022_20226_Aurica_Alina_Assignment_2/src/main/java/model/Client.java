package model;

public class Client implements Comparable<Client>{
    private int id;
    private int tArrive;
    private int tService;

    public Client(int id, int tArrive, int tService){
        this.id = id;
        this.tArrive = tArrive;
        this.tService = tService;
    }

    public int getId() {
        return id;
    }

    public int getTArrive() {
        return tArrive;
    }

    public synchronized int getTService() {
        return tService;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTArrive(int tArrive) {
        this.tArrive = tArrive;
    }

    public synchronized void setTService(int tService) {
        this.tService = tService;
    }

    @Override
    public String toString(){

        return "( " + this.id + ", " + this.tArrive + ", " + this.tService + " )";
    }

    public int compareTo(Client c){

        return this.tArrive - c.tArrive;
    }
}
