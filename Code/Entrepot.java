import java.util.Random;

public class Entrepot extends Lieu{

    /* Attribut */
    private int numero22;
    private String nom;
    private Robot.Specialite specialite;
    private int quantite_minerai_actuelle;
    private int capacite_stockage;

    /* Constructor */
    public Entrepot(int numero) {
        super();
        this.numero = numero;
        this.nom = "E"+this.numero;
        this.type_Lieu = Type_Lieu.ENTREPOT;
        this.parcelle = null;
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

    public void setParcelle(Parcelle parcelle) {
        if (this.parcelle != null) {
            System.out.println("Cette entrepôt est déjà placé sur la carte!");
        }
        else if (!parcelle.isPresence_lieu()) {
            this.parcelle = parcelle;
            this.parcelle.setPresence_lieu(true);
            this.parcelle.setLieu(this);
            this.getParcelle().setCasesLieuOuRobot(false, new String[]{"E",String.valueOf(this.numero)});
        }
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
        this.type_Lieu = type_Lieu;
    }

    public void setQuantite_minerai_actuelle(int quantite_minerai_actuelle) {
        this.quantite_minerai_actuelle = quantite_minerai_actuelle;
    }

    public void setCapacite_stockage(int capacite_stockage) {
        this.capacite_stockage = capacite_stockage;
    }


}
