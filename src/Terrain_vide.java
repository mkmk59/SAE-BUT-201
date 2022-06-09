package com.example.java301fx;

public class Terrain_vide extends Lieu{
    /* Attribut */
    private Parcelle parcelle;

    /* Constructor */
    public Terrain_vide(Parcelle parcelle) {
        super();
        this.type_Lieu=Type_Lieu.TERRAIN_VIDE;
        this.parcelle=parcelle;
    }
}