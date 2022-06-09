package com.example.java301fx;

import javafx.scene.control.Alert;

import java.security.AllPermission;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Partie {
    private int tour;
    private Map carte;
    private ArrayList<Robot> robots;
    private ArrayList<Mine> mines;
    private ArrayList<Entrepot> entrepots;

    public Partie() {
        this.tour = 0;
        this.carte = new Map();
        this.robots=new ArrayList<>();
        this.mines=new ArrayList<>();
        this.entrepots=new ArrayList<>();
    }

    public void initialiserPartie(int nb_robot_Or,int nb_robot_Ni,int nb_Mines_Or, int nb_Mines_Ni) {
        // Instanciation d'un générateur aléatoire
        Random generateur = new Random();

        // Insertion des mines d'or
        int nb_mine_Or_placee = 0;
        while (nb_mine_Or_placee < nb_Mines_Or) {
            Mine mine_Or = new Mine(nb_mine_Or_placee+1, Robot.Specialite.Or, generateur.nextInt(50, 101));
            int abs = generateur.nextInt(0, 10);
            int ord = generateur.nextInt(0, 10);
            if (mine_Or.setParcelle(this.carte.getParcelle(abs, ord))) {
                nb_mine_Or_placee++;
                this.mines.add(mine_Or);
            }
        }

        // Insertion des mines de Ni
        int nb_mine_Ni_placee = 0;
        while (nb_mine_Ni_placee < nb_Mines_Ni) {
            Mine mine_Ni = new Mine(nb_mine_Ni_placee+nb_Mines_Or+1, Robot.Specialite.Ni, generateur.nextInt(50, 101));
            int abs = generateur.nextInt(0, 10);
            int ord = generateur.nextInt(0, 10);
            if (mine_Ni.setParcelle(this.carte.getParcelle(abs, ord))) {
                nb_mine_Ni_placee++;
                this.mines.add(mine_Ni);
            }
        }

        // Insertion des Entrepots d'or
        while (this.entrepots.size()<1) {
            Entrepot entrepot_Or = new Entrepot(1, Robot.Specialite.Or);
            int capacite_totale_mine_Or = 0;
            for (int c = 0; c < nb_mine_Or_placee; c++) {
                capacite_totale_mine_Or = capacite_totale_mine_Or + this.mines.get(c).getQuantite_minerai_max();
            }
            entrepot_Or.setCapacite_stockage(capacite_totale_mine_Or);
            int abs = generateur.nextInt(0, 10);
            int ord = generateur.nextInt(0, 10);
            if (entrepot_Or.setParcelle(this.carte.getParcelle(abs, ord))) {
                this.entrepots.add(entrepot_Or);
            }
        }

        // Insertion des Entrepots de Nickel
        while (this.entrepots.size()<2){
            Entrepot entrepot_Ni = new Entrepot(2, Robot.Specialite.Ni);
            int capacite_totale_mine_Ni = 0;
            for (int c=nb_mine_Or_placee;c<nb_mine_Or_placee+nb_mine_Ni_placee;c++){
                capacite_totale_mine_Ni = capacite_totale_mine_Ni + this.mines.get(c).getQuantite_minerai_max();
            }
            entrepot_Ni.setCapacite_stockage(capacite_totale_mine_Ni);
            int abs = generateur.nextInt(0, 10);
            int ord = generateur.nextInt(0, 10);
            if (entrepot_Ni.setParcelle(this.carte.getParcelle(abs, ord))) {
                this.entrepots.add(entrepot_Ni);
            }
        }

        // Insertions des robots extracteur d'Or
        ArrayList<int[]> liste_coordonnees_robot = new ArrayList<>();

        int nb_robot_Or_place = 1;
        while (nb_robot_Or_place <= nb_robot_Or) {
            int capacite_minerai_max = generateur.nextInt(20,30);
            int capacite_extraction = generateur.nextInt(15,25);
            Robot robot_Or = new Robot(nb_robot_Or_place, Robot.Specialite.Or,capacite_minerai_max,
                    capacite_extraction);
            robot_Or.setCarte(this.carte);
            int abs = generateur.nextInt(0, 10);
            int ord = generateur.nextInt(0, 10);
            int[] tab_coor = new int[] {abs,ord};
            while (liste_coordonnees_robot.contains(tab_coor)){
                abs = generateur.nextInt(0, 10);
                ord = generateur.nextInt(0, 10);
                tab_coor = new int[] {abs,ord};
            }
            liste_coordonnees_robot.add(tab_coor);
            robot_Or.setParcelle(this.carte.getParcelle(abs, ord));
            nb_robot_Or_place++;
            this.robots.add(robot_Or);
        }

        System.out.println(this.robots.size());

        // Insertions des robots extracteur de Nickel
        int nb_robot_Ni_place = 1;
        while (nb_robot_Ni_place <= nb_robot_Ni) {
            Robot robot_Ni = new Robot(nb_robot_Ni_place+nb_robot_Or, Robot.Specialite.Ni,generateur.nextInt(20,30),
                    generateur.nextInt(15,25));
            robot_Ni.setCarte(this.carte);
            int abs = generateur.nextInt(0, 10);
            int ord = generateur.nextInt(0, 10);
            int[] tab_coor = new int[] {abs,ord};
            while (liste_coordonnees_robot.contains(tab_coor)){
                abs = generateur.nextInt(0, 10);
                ord = generateur.nextInt(0, 10);
                tab_coor = new int[] {abs,ord};
            }
            liste_coordonnees_robot.add(tab_coor);
            robot_Ni.setParcelle(this.carte.getParcelle(abs, ord));
            nb_robot_Ni_place++;
            this.robots.add(robot_Ni);
        }

        System.out.println(this.robots.size());

        // Insertion des plans d'eau
        int nb_plan_d_eau = generateur.nextInt(2, 11);
        int nb_plan_deau_insere = 1;
        while (nb_plan_deau_insere <= nb_plan_d_eau) {
            Plan_d_eau plan_d_eau = new Plan_d_eau();
            int abs = generateur.nextInt(0, 10);
            int ord = generateur.nextInt(0, 10);
            if(plan_d_eau.setParcelle(this.carte.getParcelle(abs, ord))) {
                nb_plan_deau_insere++;
            }
        }
    }

    public boolean passerAuTourSuivant()
    {
        int nb_actions_du_tour=0;
        if (this.robots.size()>0) {
            for (Robot r : this.robots) {
                nb_actions_du_tour = nb_actions_du_tour + r.getNombre_action();
            }
            if (nb_actions_du_tour == this.robots.size()) {
                this.tour = this.tour + 1;
                for (Robot r : this.robots) {
                    r.setNombre_action(0);
                }
                return true;
            } else if (nb_actions_du_tour == 0) {
                return false;
            } else if (0 < nb_actions_du_tour && nb_actions_du_tour < this.robots.size()) {
                return false;
            }
            return false;
        }
        else{
            Alert partie_non_initialisee = new Alert(Alert.AlertType.WARNING);
            partie_non_initialisee.setHeaderText("Partie Non initialisée.");
            partie_non_initialisee.setTitle("Partie non initialisée");
            partie_non_initialisee.setContentText("Choisissez les paramètres de jeu avant de lancer la partie (Bouton paramètres).");
            return false;
        }
    }

    public boolean fin_de_partie(){
        ArrayList<Boolean> liste_estVide_mines= new ArrayList<>();
        for (Mine m : this.mines) {
            liste_estVide_mines.add(m.estVide());
        }
        ArrayList<Boolean> liste_estPlein_entrepots= new ArrayList<>();
        for (Entrepot e : this.entrepots) {
            liste_estPlein_entrepots.add(e.estPlein());
        }
        if (!liste_estPlein_entrepots.contains(false) && !liste_estVide_mines.contains(false)){
            return true;
        }
        else{
            return false;
        }
    }

    public int getTour() {
        return this.tour;
    }

    public Map getCarte() {
        return this.carte;
    }

    public ArrayList<Robot> getRobots() {
        return this.robots;
    }

    public ArrayList<Mine> getMines() {
        return this.mines;
    }

    public ArrayList<Entrepot> getEntrepots() {
        return this.entrepots;
    }
}
