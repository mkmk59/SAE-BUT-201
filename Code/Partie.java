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
        int nb_robot_Or_place = 1;
        while (nb_robot_Or_place <= nb_robot_Or) {
            Robot robot_Or = new Robot(nb_robot_Or_place, Robot.Specialite.Or,generateur.nextInt(20,30),
                                 generateur.nextInt(15,25));
            robot_Or.setCarte(this.carte);
            int abs = generateur.nextInt(0, 10);
            int ord = generateur.nextInt(0, 10);
            if (robot_Or.setParcelle(this.carte.getParcelle(abs, ord))){
                nb_robot_Or_place++;
                this.robots.add(robot_Or);
            }
        }

        // Insertions des robots extracteur de Nickel
        int nb_robot_Ni_place = 1;
        while (nb_robot_Ni_place <= nb_robot_Ni) {
            Robot robot_Ni = new Robot(nb_robot_Ni_place+nb_robot_Or, Robot.Specialite.Ni,generateur.nextInt(20,30),
                                        generateur.nextInt(15,25));
            robot_Ni.setCarte(this.carte);
            int abs = generateur.nextInt(0, 10);
            int ord = generateur.nextInt(0, 10);
            if (robot_Ni.setParcelle(this.carte.getParcelle(abs, ord))) {
                nb_robot_Ni_place++;
                this.robots.add(robot_Ni);
            }
        }

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

    public void afficherInformationsTour() {
        System.out.println("Tour : " + this.tour);
        // Information sur les mines
        for (Mine m : this.mines) {
            System.out.println(m.getNom()
                    + "  "
                    + m.getParcelle().getCoordonnees()[0] +":"+ m.getParcelle().getCoordonnees()[1]
                    + "  "
                    + m.getSpecialite().toString()
                    + "  "
                    + m.getQuantite_minerai_restant()
                    + "/"
                    + m.getQuantite_minerai_max()
            );
        }
        // Information sur les entrepôts
        for (Entrepot e : this.entrepots) {
            System.out.println(e.getNom()
                    + "  "
                    + e.getParcelle().getCoordonnees()[0] +":"+ e.getParcelle().getCoordonnees()[1]
                    + "  "
                    + e.getSpecialite().toString()
                    + "  "
                    + e.getQuantite_minerai_actuelle()
                    + "/"
                    + e.getCapacite_stockage()
            );
        }
        // Information sur les robots
        for (Robot r : this.robots) {
            System.out.println(r.getNom()
                    + "  "
                    + r.getParcelle().getCoordonnees()[0] +":"+ r.getParcelle().getCoordonnees()[1]
                    + "  "
                    + r.getSpecialite().toString()
                    + "  "
                    + r.getQuantite_minerai()
                    + "/"
                    + r.getCapacite_minerai_max()
                    +"  "
                    +r.getNombre_action()
                    +"/1"
            );
        }
        System.out.println();
    }

    public boolean passerAuTourSuivant()
    {
        int nb_actions_du_tour=0;
        for (Robot r: this.robots){
            nb_actions_du_tour = nb_actions_du_tour + r.getNombre_action();
        }
        if (nb_actions_du_tour==this.robots.size()){
            System.out.println("Fin du tour : Tous les robots ont utilisé leur action ! ");
            this.tour=this.tour+1;
            for (Robot r : this.robots){
                r.setNombre_action(0);
            }
            return true;
        }
        else if (nb_actions_du_tour==0){
            return false;
        }
        else if (0<nb_actions_du_tour && nb_actions_du_tour<this.robots.size()){
            System.out.println("Tous les robots n'ont pas effectué leur action ! ");
            return false;
        }
        return false;
    }

    public void jouerTour()
    {
        //Création d'une liste de tous les noms de robot possibles
        ArrayList<String> liste_nom_robots = new ArrayList<>();
        liste_nom_robots.add("R1");
        liste_nom_robots.add("R2");
        liste_nom_robots.add("R3");
        liste_nom_robots.add("R4");
        //Création d'une liste de toutes les actions réalisable par le robot
        ArrayList<String> liste_actions = new ArrayList<>();
        liste_actions.add("droite");
        liste_actions.add("gauche");
        liste_actions.add("haut");
        liste_actions.add("bas");
        liste_actions.add("recolter");
        liste_actions.add("deposer");
        liste_actions.add("passer");
        while (!this.passerAuTourSuivant()){
            // Choix du robot sur lequel avec lequel jouer
            Scanner clavier = new Scanner(System.in);
            System.out.println("Choix du robot : ");
            String entree_clavier = clavier.nextLine();
            entree_clavier = entree_clavier.toUpperCase();
            entree_clavier = entree_clavier.replaceAll("\\s", "");
            while (!liste_nom_robots.contains(entree_clavier)) {
                System.out.println("Erreur de saisie ! Entrez de nouveau le nom d'un robot qui n'a pas déjà joué : ");
                entree_clavier = clavier.nextLine();
                entree_clavier = entree_clavier.toUpperCase();
                entree_clavier = entree_clavier.replaceAll("\\s", "");
            }
            int index = 0;
            for (int i = 0; i < this.robots.size(); i++) {
                if (this.robots.get(i).getNom().equals(entree_clavier)) {
                    index=i;
                }
            }

            // Choix de l'action par le joueur
            clavier  = new Scanner(System.in);
            System.out.println("Choix de l'action : { droite | gauche | haut | bas | recolter | deposer | passer }  ");
            entree_clavier = clavier.nextLine();
            entree_clavier = entree_clavier.toLowerCase();
            entree_clavier = entree_clavier.replaceAll("\\s", "");
            while (!liste_actions.contains(entree_clavier)) {
                System.out.println("Erreur de saisie ! Choisissez l'action : { droite | gauche | haut | bas | recolter | deposer | passer }  ");
                entree_clavier = clavier.nextLine();
                entree_clavier = entree_clavier.toLowerCase();
                entree_clavier = entree_clavier.replaceAll("\\s", "");
            }
            switch (entree_clavier) {
                case "droite" -> {
                    if(this.robots.get(index).Aller_a_droite()) {
                        liste_nom_robots.remove(this.robots.get(index).getNom());
                        this.getCarte().afficherMap();
                        this.afficherInformationsTour();
                    }
                }
                case "gauche" -> {
                    if (this.robots.get(index).Aller_a_gauche()) {
                        liste_nom_robots.remove(this.robots.get(index).getNom());
                        this.getCarte().afficherMap();
                        this.afficherInformationsTour();
                    }
                }
                case "haut" -> {
                    if (this.robots.get(index).Aller_en_haut()) {
                        liste_nom_robots.remove(this.robots.get(index).getNom());
                        this.getCarte().afficherMap();
                        this.afficherInformationsTour();
                    }
                }
                case "bas" -> {
                    if (this.robots.get(index).Aller_en_bas()) {
                        liste_nom_robots.remove(this.robots.get(index).getNom());
                        this.getCarte().afficherMap();
                        this.afficherInformationsTour();
                    }
                }
                case "recolter" -> {
                    if (this.robots.get(index).collecter()) {
                        liste_nom_robots.remove(this.robots.get(index).getNom());
                        this.getCarte().afficherMap();
                        this.afficherInformationsTour();
                    }
                }
                case "deposer" -> {
                    if (this.robots.get(index).deposer()) {
                        liste_nom_robots.remove(this.robots.get(index).getNom());
                        this.getCarte().afficherMap();
                        this.afficherInformationsTour();
                    }
                }
                case "passer" -> {
                    this.robots.get(index).setNombre_action(1);
                    liste_nom_robots.remove(this.robots.get(index).getNom());
                    this.getCarte().afficherMap();
                    this.afficherInformationsTour();
                }
            }
        }
        this.getCarte().afficherMap();
        this.afficherInformationsTour();
    }

    public void jouerPartie(){
        System.out.println("***************************** Debut de la partie ***************************** ");
        ArrayList<Boolean> liste_estVide_mines= new ArrayList<>();
        for (Mine m : this.mines) {
            liste_estVide_mines.add(m.estVide());
        }
        ArrayList<Boolean> liste_estPlein_entrepots= new ArrayList<>();
        for (Entrepot e : this.entrepots) {
            liste_estPlein_entrepots.add(e.estPlein());
        }
        while (liste_estPlein_entrepots.contains(Boolean.FALSE) && liste_estVide_mines.contains(Boolean.FALSE)){
            this.jouerTour();
            //Vérifier l'état des mines à chaque tour
            for (Mine m : this.mines) {
                liste_estVide_mines.add(m.estVide());
            }
            // Vérification de l'état des entrepôts à chaque tour
            for (Entrepot e : this.entrepots) {
                liste_estPlein_entrepots.add(e.estPlein());
            }
        }
        System.out.println("***************************** FIN de la partie ***************************** ");
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

