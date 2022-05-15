import java.util.Random;

public class Mine extends Lieu{
    /* Attribut */
    private int numero;
    private String nom;
    private Robot.Specialite specialite;
    private int quantite_minerai_restant;
    private int quantite_minerai_max;


    /* Constructor */
    public Mine(int numero) {
        super();
        this.numero = numero;
        this.nom ="M"+this.numero;
        this.type_Lieu = Type_Lieu.MINE;
        this.parcelle = null;
        this.specialite=null;
        Random generateur = new Random();
        this.quantite_minerai_max=generateur.nextInt(50,101);
        this.quantite_minerai_restant=this.quantite_minerai_max;
    }

    public Mine(int numero, Robot.Specialite specialite, int quantite_minerai_restant,
                int quantite_minerai_max,Parcelle parcelle)
    {
        super();
        this.numero = numero;
        this.nom = "M"+this.numero;
        this.specialite = specialite;
        this.quantite_minerai_restant = quantite_minerai_restant;
        this.quantite_minerai_max = quantite_minerai_max;
        this.parcelle=parcelle;
        this.type_Lieu=Type_Lieu.MINE;
    }

    /* Méthodes */
    public int getNumero() {
        return this.numero;
    }

    public String getNom() {
        return this.nom;
    }

    public void setParcelle(Parcelle parcelle) {
        if (this.parcelle != null) {
            System.out.println("Cette mine est déjà placé sur la carte!");
        }
        else if (!parcelle.isPresence_lieu()) {
            this.parcelle = parcelle;
            this.parcelle.setPresence_lieu(true);
            this.parcelle.setLieu(this);
            this.getParcelle().setCasesLieuOuRobot(false, new String[] {"M",String.valueOf(this.numero)});
        }
    }

    public void setSpecialite(Robot.Specialite type_mine) {
        this.specialite = type_mine;
    }

    public void setQuantite_minerai_max(int quantite_minerai_max) {
        this.quantite_minerai_max = quantite_minerai_max;
    }

    public void setQuantite_minerai_restant(int quantite_minerai_restant) {
        this.quantite_minerai_restant = quantite_minerai_restant;
    }

    public Robot.Specialite getSpecialite() {
        return this.specialite;
    }

    public int getQuantite_minerai_restant() {
        return this.quantite_minerai_restant;
    }

    public int getQuantite_minerai_max() {
        return this.quantite_minerai_max;
    }

}
