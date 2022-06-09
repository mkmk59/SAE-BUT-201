package com.example.java301fx;

public abstract class Lieu {
    /* Attributs */
    protected Parcelle parcelle;
    protected Type_Lieu type_Lieu;

    protected enum Type_Lieu {ENTREPOT, PLAN_D_EAU, MINE, TERRAIN_VIDE}

    /* Methodes */
    public Parcelle getParcelle() {
        return this.parcelle;
    }

    public Type_Lieu getType_Lieu() {
        return this.type_Lieu;
    }
}

