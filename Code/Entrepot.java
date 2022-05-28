package com.example.java301fx;

import java.util.Random;

public class Entrepot extends Lieu{

    /* Attribut */
    private int numero;
    private String nom;
    private Robot.Specialite specialite;
    private int quantite_minerai_actuelle;
    private int capacite_stockage;

    /* Constructor */
    public Entrepot(int numero, Robot.Specialite specialite) {
        super();
        this.numero = numero;
        this.nom = "E"+this.numero;
        this.type_Lieu = Type_Lieu.ENTREPOT;
        this.parcelle = null;
        this.specialite=specialite;
        Random generateur = new Random();
        this.capacite_stockage=generateur.nextInt(50,101);
        this.quantite_minerai_actuelle=0;
    }

    /* Méthodes */
    public int getNumero() {
        return numero;
    }

    public String getNom() {
        return this.nom;
    }

    public boolean setParcelle(Parcelle parcelle) {
        if (this.parcelle != null) {
            return false;
        }
        else if (!parcelle.isPresence_lieu()) {
            this.parcelle = parcelle;
            this.parcelle.setPresence_lieu(true);
            this.parcelle.setLieu(this);
            return true;
        }
        return false;
    }

    public Robot.Specialite getSpecialite() {
        return this.specialite;
    }

    public int getQuantite_minerai_actuelle() {
        return this.quantite_minerai_actuelle;
    }


    public int getCapacite_stockage() {
        return this.capacite_stockage;
    }


    public void setType_entrepot(Type_Lieu type_lieu) {
        this.type_Lieu = type_lieu;
    }

    public void setQuantite_minerai_actuelle(int quantite_minerai_actuelle) {
        this.quantite_minerai_actuelle = quantite_minerai_actuelle;
    }

    public void setCapacite_stockage(int capacite_stockage) {
        this.capacite_stockage = capacite_stockage;
    }

    public Boolean estPlein(){
        if (this.quantite_minerai_actuelle==this.capacite_stockage){
            return Boolean.TRUE;
        }
        else {
            return Boolean.FALSE;
        }
    }
}