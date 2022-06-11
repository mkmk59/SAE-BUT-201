package com.example.sae_robots_mineur;

public class Plan_d_eau extends Lieu{
    /* Attribut */
    private Parcelle parcelle;

    /* Constructor */
    public Plan_d_eau() {
        super();
        this.type_Lieu=Type_Lieu.PLAN_D_EAU;
        this.parcelle=null;
    }

    /* Méthodes */
    public boolean setParcelle(Parcelle parcelle) {
        if (this.parcelle != null) {
            return false;
        } else if (!parcelle.isPresence_lieu() && !parcelle.isPresence_robot()) {
            this.parcelle = parcelle;
            this.parcelle.setPresence_lieu(true);
            this.parcelle.setLieu(this);
            return true;
        }
        return false;
    }
}