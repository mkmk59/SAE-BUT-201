package com.example.sae_robots_mineur;

public abstract class Lieu {
    /* Attributs */
    protected Parcelle parcelle;
    protected Type_Lieu type_Lieu;

    public enum Type_Lieu {ENTREPOT, PLAN_D_EAU, MINE, TERRAIN_VIDE}

    /* Methodes */
    public Parcelle getParcelle() {
        return this.parcelle;
    }

    public Type_Lieu getType_Lieu() {
        return this.type_Lieu;
    }
}

