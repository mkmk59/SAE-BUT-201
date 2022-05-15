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
    public void setParcelle(Parcelle parcelle) {
        if (this.parcelle != null) {
            System.out.println("Ce Plan d'eau est déjà placé sur la carte!");
        } else if (!parcelle.isPresence_lieu()) {
            this.parcelle = parcelle;
            this.parcelle.setPresence_lieu(true);
            this.parcelle.setLieu(this);
            this.getParcelle().setCases(new String[][] {{"X","X"},{"X","X"}});
        }
    }
}
