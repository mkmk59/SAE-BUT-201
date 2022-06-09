package com.example.java301fx;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.Random;

public class Robot {
    /* Attribut */
    private String nom;
    private Specialite specialite;
    public enum Specialite {Or,Ni}
    private int numero;
    private int quantite_minerai, capacite_minerai_max,capacite_extraction;
    private Parcelle parcelle;
    private Map carte;
    private int nombre_action;
    private RobotGUI robotGUI;

    public static <T extends Enum<?>> T randomEnum(Class<T> classe){
        Random generateur = new Random();
        int x = generateur.nextInt(classe.getEnumConstants().length);
        return classe.getEnumConstants()[x];
    }

    /* Constructor */
    public Robot(int numero, Specialite specialite, int capacite_minerai_max, int capacite_extraction) {
        this.numero = numero;
        this.nom ="R"+this.numero;
        this.specialite = specialite;
        this.quantite_minerai = 0;
        this.capacite_minerai_max = capacite_minerai_max;
        this.capacite_extraction = capacite_extraction;
        this.carte=null;
        this.parcelle=null;
        this.nombre_action=0;
        this.robotGUI=new RobotGUI(this);
    }

    public Robot(int numero) {
        this.numero = numero;
        Random generateur = new Random();
        this.nom = "R"+this.numero;
        this.specialite = randomEnum(Specialite.class);
        this.quantite_minerai = 0;
        this.capacite_minerai_max = generateur.nextInt(5,10);
        this.capacite_extraction = generateur.nextInt(1,4);
        this.carte=null;
        this.parcelle=null;
        this.nombre_action=0;
        this.robotGUI=new RobotGUI(this);
    }

    public Robot(Robot robot) {
        this.numero = robot.numero;
        this.nom = robot.nom;
        this.specialite = robot.specialite;
        this.quantite_minerai = robot.quantite_minerai;
        this.capacite_minerai_max = robot.capacite_minerai_max;
        this.capacite_extraction = robot.capacite_extraction;
        this.carte=robot.carte;
        this.parcelle=robot.parcelle;
        this.nombre_action=robot.nombre_action;
        this.robotGUI=robot.robotGUI;
    }

    /* Méthodes */
    public String getNom() {
        return this.nom;
    }

    public Specialite getSpecialite() {
        return this.specialite;
    }

    public RobotGUI getRobotGUI() {
        return this.robotGUI;
    }

    public int getNumero() {
        return this.numero;
    }

    public int getQuantite_minerai() {
        return this.quantite_minerai;
    }

    public int getCapacite_minerai_max() {
        return this.capacite_minerai_max;
    }

    public int getCapacite_extraction() {
        return this.capacite_extraction;
    }

    public int getNombre_action(){
        return this.nombre_action;
    }

    public Parcelle getParcelle() {
        return this.parcelle;
    }

    public void setQuantite_minerai(int quantite_minerai) {
        this.quantite_minerai = quantite_minerai;
    }

    public boolean setParcelle(Parcelle parcelle)
    {
        if (parcelle.getRobot()==this){
            return false;
        }
        else {
            if (!parcelle.isPresence_robot() && parcelle.getLieu().getType_Lieu() != Lieu.Type_Lieu.PLAN_D_EAU) {
                if (this.parcelle!=null) {
                    this.parcelle.libererParcelleRobot();
                }
                this.parcelle=parcelle;
                // Indiquer la présence du robot dans la nouvelle parcelle
                this.parcelle.setRobot(this);
                this.parcelle.setPresence_robot(true);
                //Changer ou faire apparaitre le nom du robot dans la nouvelle parcelle
                this.parcelle.getParcelleGUI().setRobotGUI(this.robotGUI);
                return true;
            } else if ((parcelle.getLieu().getType_Lieu() == Lieu.Type_Lieu.PLAN_D_EAU)) {
                Alert probleme_plan_deau = new Alert(Alert.AlertType.WARNING);
                probleme_plan_deau.setTitle("Tentative de parcour d'un plan d'eau");
                probleme_plan_deau.setContentText("Attention, plan d'eau !");
                probleme_plan_deau.show();
                return false;
            } else if (parcelle.isPresence_robot()){
                // Ne pas afficher d'erreur à l'initialisation de la partie
                    Alert parcelle_occupee = new Alert(Alert.AlertType.WARNING);
                    parcelle_occupee.setTitle("Parcelle occupée");
                    parcelle_occupee.setContentText("Attention, un robot est déjà présent sur la parcelle !");
                    parcelle_occupee.show();
                    return false;
            }
        }
        return false;
    }

    public void setRandomParcelle(Map m)
    {
        Random generateur = new Random();
        int x = generateur.nextInt(0,10);
        int y = generateur.nextInt(0,10);
        this.setParcelle(m.getParcelle(x,y));
    }

    public void setCarte(Map carte) {
        this.carte = carte;
    }

    public Map getCarte() {
        return this.carte;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
    }

    public void setNombre_action(int nombre_action) {
        this.nombre_action = nombre_action;
    }

    public void setCapacite_minerai_max(int capacite_minerai_max) {
        this.capacite_minerai_max = capacite_minerai_max;
    }

    public void setCapacite_extraction(int capacite_extraction) {
        this.capacite_extraction = capacite_extraction;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setRobotGUI(RobotGUI robotGUI) {
        this.robotGUI = robotGUI;
    }

    public boolean Aller_en_bas() {
        // Récupérer les coordonées de la parcelle de droite
        int[] coor_parcelle_actuelle = this.getParcelle().getCoordonnees();
        if (coor_parcelle_actuelle[0] < 9 && this.nombre_action == 0) {
            // On déplace le robot dans celle d'en bas
            int[] coor_parcelle_bas = new int[]{coor_parcelle_actuelle[0] + 1, coor_parcelle_actuelle[1]};
            Parcelle p_bas = this.getCarte().getParcelle(coor_parcelle_bas[0], coor_parcelle_bas[1]);
            if (this.setParcelle(p_bas)) {
                this.setNombre_action(1);
                return true;
            }
        } else if (coor_parcelle_actuelle[0] == 9) {
            Alert limite_de_carte = new Alert(Alert.AlertType.WARNING);
            limite_de_carte.setTitle("Limite de la carte atteinte.");
            limite_de_carte.setContentText("Attention, limite de la carte atteinte !");
            limite_de_carte.show();
            return false;
        } else if (this.nombre_action == 1) {
            Alert probleme_double_action = new Alert(Alert.AlertType.WARNING);
            probleme_double_action.setTitle("Deuxième action du tour.");
            probleme_double_action.setContentText("Attention, le robot " + this.getNom() + " a déjà effectué son action de jeu !");
            probleme_double_action.show();
            return false;
        }
        return false;
    }

    public boolean Aller_en_haut() {
        int[] coor_parcelle_actuelle = this.getParcelle().getCoordonnees();
        if (coor_parcelle_actuelle[0] > 0 && this.nombre_action == 0) {
            // On déplace le robot dans celle d'en haut
            int[] coor_parcelle_haut = new int[]{coor_parcelle_actuelle[0] - 1, coor_parcelle_actuelle[1]};
            Parcelle p_haut = this.getCarte().getParcelle(coor_parcelle_haut[0], coor_parcelle_haut[1]);
            if (this.setParcelle(p_haut)) {
                this.setNombre_action(1);
                return true;
            }
        } else if (coor_parcelle_actuelle[0] == 0) {
            Alert limite_de_carte = new Alert(Alert.AlertType.WARNING);
            limite_de_carte.setTitle("Limite de la carte atteinte.");
            limite_de_carte.setContentText("Attention, limite de la carte atteinte !");
            limite_de_carte.show();
            return false;
        } else if (this.nombre_action == 1) {
            Alert probleme_double_action = new Alert(Alert.AlertType.WARNING);
            probleme_double_action.setTitle("Deuxième action du tour.");
            probleme_double_action.setContentText("Attention, le robot " + this.getNom() + " a déjà effectué son action de jeu !");
            probleme_double_action.show();
            return false;

        }
        return false;
    }

    public boolean Aller_a_gauche()
    {
        int[] coor_parcelle_actuelle = this.getParcelle().getCoordonnees();
        if(coor_parcelle_actuelle[1]>0 && this.nombre_action==0)
        {
            // On déplace le robot dans celle de gauche
            int[] coor_parcelle_gauche = new int[] {coor_parcelle_actuelle[0],coor_parcelle_actuelle[1] - 1};
            Parcelle p_gauche = this.getCarte().getParcelle(coor_parcelle_gauche[0],coor_parcelle_gauche[1]);
            if (this.setParcelle(p_gauche)) {
                this.setNombre_action(1);
                return true;
            }
        }
        else if (coor_parcelle_actuelle[1]==0) {
            Alert limite_de_carte = new Alert(Alert.AlertType.WARNING);
            limite_de_carte.setTitle("Limite de la carte atteinte.");
            limite_de_carte.setContentText("Attention, limite de la carte atteinte !");
            limite_de_carte.show();
            return false;
        }
        else if (this.nombre_action==1){
        Alert probleme_double_action = new Alert(Alert.AlertType.WARNING);
        probleme_double_action.setTitle("Deuxième action du tour.");
        probleme_double_action.setContentText("Attention, le robot " + this.getNom() + " a déjà effectué son action de jeu !");
        probleme_double_action.show();
        return false;
    }
        return false;
    }

    public boolean Aller_a_droite()
    {
        int[] coor_parcelle_actuelle = this.getParcelle().getCoordonnees();
        if(coor_parcelle_actuelle[1]<9 && this.nombre_action==0)
        {
            // On déplace le robot dans celle de droite
            int[] coor_parcelle_droite = new int[] {coor_parcelle_actuelle[0],coor_parcelle_actuelle[1] + 1};
            Parcelle p_droite = this.getCarte().getParcelle(coor_parcelle_droite[0],coor_parcelle_droite[1]);
            if (this.setParcelle(p_droite)) {
                this.setNombre_action(1);
                return true;
            }
        }
        else if (coor_parcelle_actuelle[1]==9) {
            Alert limite_de_carte = new Alert(Alert.AlertType.WARNING);
            limite_de_carte.setTitle("Limite de la carte atteinte.");
            limite_de_carte.setContentText("Attention, limite de la carte atteinte !");
            limite_de_carte.show();
            return false;
        }
        else if (this.nombre_action==1){
            Alert probleme_double_action = new Alert(Alert.AlertType.WARNING);
            probleme_double_action.setTitle("Deuxième action du tour.");
            probleme_double_action.setContentText("Attention, le robot " + this.getNom() + " a déjà effectué son action de jeu !");
            probleme_double_action.show();
            return false;
        }
        return false;
    }

    public Boolean deposer(){
        if (this.nombre_action==0) {
            // Vérifier la présence d'une parcelle attachée au robot
            if (this.parcelle == null) {
                return  false;
            }
            // Vérifier que la parcelle n'est pas vide
            else if (this.parcelle.getLieu() instanceof Terrain_vide) {
                Alert probleme_parcelle_sans_entrepot = new Alert(Alert.AlertType.WARNING);
                probleme_parcelle_sans_entrepot.setTitle("Parcelle sans entrepôt.");
                probleme_parcelle_sans_entrepot.setContentText("Attention, il n'y a pas d'entrepôt sur cette parcelle !");
                probleme_parcelle_sans_entrepot.show();
                return  false;
            }
            // Vérifier que la parcelle contient un entrepôt et non une mine.
            else if (this.parcelle.getLieu().getType_Lieu() == Lieu.Type_Lieu.MINE) {
                Alert probleme_depot_mine = new Alert(Alert.AlertType.WARNING);
                probleme_depot_mine.setTitle("Problème dépot dans mine.");
                probleme_depot_mine.setContentText("Attention, un robot ne peut pas déposer dans une mine !");
                probleme_depot_mine.show();
                return  false;
            } else if (this.parcelle.getLieu().getType_Lieu() == Lieu.Type_Lieu.ENTREPOT) {
                if (this.quantite_minerai == 0)
                {
                    Alert probleme_robot_vide = new Alert(Alert.AlertType.WARNING);
                    probleme_robot_vide.setTitle("Robot vide.");
                    probleme_robot_vide.setContentText("Attention, le robot " + this.getNom() + " est déjà vide !");
                    probleme_robot_vide.show();
                    return  false;
                }
                else
                {
                    Entrepot entrepot = (Entrepot) this.parcelle.getLieu();
                    int capacite_restante_entrepot = entrepot.getCapacite_stockage() - entrepot.getQuantite_minerai_actuelle();
                    if (capacite_restante_entrepot == 0) {
                        Alert probleme_entrepot_plein = new Alert(Alert.AlertType.WARNING);
                        probleme_entrepot_plein.setTitle("Entrepôt plein.");
                        probleme_entrepot_plein.setContentText("Attention, l'entrepôt "+entrepot.getNom()+" est déjà plein' !");
                        probleme_entrepot_plein.show();
                        return  false;
                    } else if (capacite_restante_entrepot < this.quantite_minerai) {
                        entrepot.setQuantite_minerai_actuelle(entrepot.getCapacite_stockage());
                        this.setQuantite_minerai(this.quantite_minerai - capacite_restante_entrepot);
                        this.setNombre_action(1);
                        return  true;
                    } else {
                        entrepot.setQuantite_minerai_actuelle(entrepot.getQuantite_minerai_actuelle() + this.getQuantite_minerai());
                        this.setQuantite_minerai(0);
                        this.setNombre_action(1);
                        return  true;
                    }
                }
            }
        }
        else {
            Alert probleme_double_action = new Alert(Alert.AlertType.WARNING);
            probleme_double_action.setTitle("Deuxième action du tour.");
            probleme_double_action.setContentText("Attention, le robot " + this.getNom() + " a déjà effectué son action de jeu !");
            probleme_double_action.show();
            return  false;
        }
        return  false;
    }
    public boolean collecter(){
        if (this.nombre_action==0)
        {
            if (this.quantite_minerai!=this.capacite_minerai_max)
            {
                // Vérifier la présence d'une parcelle attachée au robot
                if (this.parcelle == null) {
                    return false;
                }
                // Vérifier que la parcelle n'est pas vide
                else if (this.parcelle.getLieu() instanceof Terrain_vide) {
                    Alert probleme_parcelle_sans_mine = new Alert(Alert.AlertType.WARNING);
                    probleme_parcelle_sans_mine.setTitle("Problème collecte sur terrain vide.");
                    probleme_parcelle_sans_mine.setContentText("Attention, il n'y a pas de mine sur cette parcelle !");
                    probleme_parcelle_sans_mine.show();
                    return false;
                }
                // Vérifier que la parcelle contient une mine et non un entrepôt.
                else if (this.parcelle.getLieu().getType_Lieu() == Lieu.Type_Lieu.ENTREPOT) {
                    Alert probleme_recolter_entrepot = new Alert(Alert.AlertType.WARNING);
                    probleme_recolter_entrepot.setTitle("Récolte sur entrepôt.");
                    probleme_recolter_entrepot.setContentText("Attention, le robot ne peut pas récolter depuis un entrepôt !");
                    probleme_recolter_entrepot.show();
                    return false;
                } else if (this.parcelle.getLieu().getType_Lieu() == Lieu.Type_Lieu.MINE) {
                    Mine mine = (Mine) this.parcelle.getLieu();

                    //Vérifier que les spécialités conïncident
                    if (this.specialite != mine.getSpecialite()) {
                        Alert probleme_type_de_minerai = new Alert(Alert.AlertType.WARNING);
                        probleme_type_de_minerai.setTitle("Problème de type de minerai.");
                        probleme_type_de_minerai.setContentText("Types de minerai imcompatibles :" +
                                "\n|| Specialité du Robot = " + this.specialite +
                                "\n|| Spécialité de la Mine = " + mine.getSpecialite());
                        probleme_type_de_minerai.show();
                        return false;
                    }
                    // Vérifier que la mine n'est pas vide
                    else if (mine.getQuantite_minerai_restant()==0){
                        Alert probleme_mine_vide = new Alert(Alert.AlertType.WARNING);
                        probleme_mine_vide.setTitle("La mine " + mine.getNom() + " est pleine.");
                        probleme_mine_vide.setContentText("Attention, la mine " + mine.getNom() + " est déjà vide.");
                        probleme_mine_vide.show();
                    // Vérifier en fonction de la quantité de minerai restant dans la mine
                    } else if (mine.getQuantite_minerai_restant() >= this.capacite_extraction) {
                        // Vérifier la capacité restante dans le robot
                        int capacite_restante_robot = this.capacite_minerai_max - this.quantite_minerai;
                        if (capacite_restante_robot < this.capacite_extraction) {
                            this.setQuantite_minerai(this.quantite_minerai+capacite_restante_robot);
                            mine.setQuantite_minerai_restant(mine.getQuantite_minerai_restant() - capacite_restante_robot);
                            this.setNombre_action(1);
                            return true;
                        } else {
                            this.setQuantite_minerai(this.quantite_minerai + this.capacite_extraction);
                            mine.setQuantite_minerai_restant(mine.getQuantite_minerai_restant() - this.capacite_extraction);
                            this.setNombre_action(1);
                            return true;
                        }
                    } else if (mine.getQuantite_minerai_restant() < this.capacite_extraction) {
                        // Vérifier la capacité restante dans le robot
                        int capacite_restante_robot = this.capacite_minerai_max - this.quantite_minerai;
                        if (capacite_restante_robot < mine.getQuantite_minerai_restant()) {
                            this.setQuantite_minerai(this.quantite_minerai + capacite_restante_robot);
                            mine.setQuantite_minerai_restant(mine.getQuantite_minerai_restant() - capacite_restante_robot);
                            this.setNombre_action(1);
                            return true;
                        } else {
                            this.setQuantite_minerai(this.quantite_minerai+mine.getQuantite_minerai_restant());
                            mine.setQuantite_minerai_restant(0);
                            this.setNombre_action(1);
                            return true;
                        }
                    }
                }
            }
            else{
                Alert probleme_robot_plein = new Alert(Alert.AlertType.WARNING);
                probleme_robot_plein.setTitle("Robot plein.");
                probleme_robot_plein.setContentText("Attention, le robot " + this.getNom() + " est déjà plein !");
                probleme_robot_plein.show();
                return false;
            }
        }
        else {
            Alert probleme_double_action = new Alert(Alert.AlertType.WARNING);
            probleme_double_action.setTitle("Deuxième action du tour.");
            probleme_double_action.setContentText("Attention, le robot a déjà effectué son action de jeu !");
            probleme_double_action.show();
            return false;
        }
        return false;
    }

    // méthode d'affichage sur console
    public String toString()
    {
        return  "Nom : " + this.getNom() + "\n| Spécialité : "+this.getSpecialite() + " \n| Parcelle : "
                + "(" + this.getParcelle().getCoordonnees()[0] + ";" + this.getParcelle().getCoordonnees()[1] + ")"
                + "\n| Capacité de stockage : " + this.getCapacite_minerai_max()+" \n| Capacité d'extraction : "
                + this.getCapacite_extraction()+"\n| Quantité de minerai actuelle : "  + this.getQuantite_minerai();
    }

    public boolean equals(Object obj){
        if (!(obj instanceof Robot)){
            return false;
        }
        else {
            if (this.getNom().equals(((Robot) obj).getNom())){
                return true;
            }
            else{
                return false;
            }
        }
    }
}
