package com.example.sae_robots_mineur;

public class Parcelle {
    /* Attribut */
    private int ligne, colonne;
    private boolean presence_lieu;
    private boolean presence_robot;
    private Lieu lieu;
    private Robot robot;
    private Map map;

    private ParcelleGUI parcelleGUI;

    /* Constructor */
    public Parcelle(int x, int y) {
        this.ligne=x;
        this.colonne=y;
        this.presence_lieu = false;
        this.presence_robot = false;
        this.map = null;
        this.lieu = new Terrain_vide(this);
        this.robot = null;
        this.parcelleGUI=new ParcelleGUI(this);
    }

    public Parcelle(int x, int y,ParcelleGUI parcelleGUI) {
        this.ligne=x;
        this.colonne=y;
        this.presence_lieu = false;
        this.presence_robot = false;
        this.map = null;
        this.lieu = new Terrain_vide(this);
        this.robot = null;
        this.parcelleGUI=parcelleGUI;
    }

    public Parcelle(Parcelle p) {
        this.ligne =p.ligne;
        this.colonne = p.colonne;
        this.lieu = p.lieu;
        this.robot = p.robot;
        this.map = p.map;
        this.presence_lieu = p.presence_lieu;
        this.presence_robot = p.presence_robot;
        this.parcelleGUI=p.parcelleGUI;
    }

    /* Méthodes */
    public int[] getCoordonnees() {
        int[] pos=new int[] {this.ligne,this.colonne};
        return pos;
    }

    public boolean isPresence_lieu() {
        return this.presence_lieu;
    }

    public boolean isPresence_robot() {
        return this.presence_robot;
    }

    public void setPresence_lieu(boolean presence_lieu) {
        this.presence_lieu = presence_lieu;
    }

    public void setPresence_robot(boolean presence_robot) {
        this.presence_robot = presence_robot;
    }

    public Lieu getLieu() {
        return this.lieu;
    }

    public Robot getRobot() {
        return this.robot;
    }

    public Map getMap() {
        return this.map;
    }

    public void setAbcisse(int a) {
        this.ligne = a;
    }

    public void setColonne(int o){
        this.colonne =o;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public void setMap(Map map) {this.map = map;}

    public void libererParcelleRobot(){
        this.robot=null;
        this.setPresence_robot(false);
        this.parcelleGUI.setRobotGUI(null);
    }

    public String toString(){
        return "Coordonnée : ("+this.getCoordonnees()[0]+":"+this.getCoordonnees()[1]+")"
                +"\n | Robot : "+this.getRobot().getNom();
    }

    public void setParcelleGUI(ParcelleGUI parcelleGUI) {
        this.parcelleGUI = parcelleGUI;
    }

    public ParcelleGUI getParcelleGUI() {
        return this.parcelleGUI;
    }
}