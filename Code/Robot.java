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
    }

    /* Méthodes */
    public String getNom() {
        return this.nom;
    }

    public Specialite getSpecialite() {
        return this.specialite;
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
            System.out.println("Le robot est déjà sur cette parcelle!");
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
                this.parcelle.setCasesLieuOuRobot(true, new String[]{"R", String.valueOf(this.numero)});
                return true;
            } else if ((parcelle.getLieu().getType_Lieu() == Lieu.Type_Lieu.PLAN_D_EAU)) {
                System.out.println("La parcelle désignée est un plan d'eau!");
                return false;
            } else {
                System.out.println("La parcelle est déjà occupée par un autre robot!");
                return false;
            }
        }
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

    public boolean Aller_en_bas()
    {
        // Récupérer les coordonées de la parcelle de droite
            int[] coor_parcelle_actuelle = this.getParcelle().getCoordonnees();
            if(coor_parcelle_actuelle[0]<9 && this.nombre_action==0)
            {
                // On déplace le robot dans celle d'en bas
                int[] coor_parcelle_bas = new int[] {coor_parcelle_actuelle[0] + 1,coor_parcelle_actuelle[1]};
                Parcelle p_bas = this.getCarte().getParcelle(coor_parcelle_bas[0],coor_parcelle_bas[1]);
                if (this.setParcelle(p_bas)) {
                    this.setNombre_action(1);
                    return true;
                }
            }
            else {
                System.out.println("Vous ne pouvez pas aller en bas : Limite de la carte atteinte.");
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
        } else {
            System.out.println("Vous ne pouvez pas aller en haut : Limite de la carte atteinte.");
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
        else {
            System.out.println("Vous ne pouvez pas aller à gauche: Limite de la carte atteinte.");
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
        else {
            System.out.println("Vous ne pouvez pas aller à droite : Limite de la carte atteinte.");
            return false;
        }
        return false;
    }

    public boolean deposer(){
        if (this.nombre_action==0) {
            // Vérifier la présence d'une parcelle attachée au robot
            if (this.parcelle == null) {
                System.out.println("Le robot n'est pas sur la carte!");
                return false;
            }
            // Vérifier que la parcelle n'est pas vide
            else if (this.parcelle.getLieu() instanceof Terrain_vide) {
                System.out.println("Il n'y a pas d'entrepôt sur cette parcelle!");
                return false;
            }
            // Vérifier que la parcelle contient un entrepôt et non une mine.
            else if (this.parcelle.getLieu().getType_Lieu() == Lieu.Type_Lieu.MINE) {
                System.out.println("Attention vous ne pouvez pas déposer dans une mine!");
                return false;
            } else if (this.parcelle.getLieu().getType_Lieu() == Lieu.Type_Lieu.ENTREPOT) {
                if (this.quantite_minerai == 0)
                {
                    System.out.println("Le robot est vide!");
                    return false;
                }
                else
                {
                    Entrepot entrepot = (Entrepot) this.parcelle.getLieu();
                    int capacite_restante_entrepot = entrepot.getCapacite_stockage() - entrepot.getQuantite_minerai_actuelle();
                    if (capacite_restante_entrepot == 0) {
                        System.out.println("L'entrepôt " + entrepot.getNom() + "est plein !");
                        return false;
                    } else if (capacite_restante_entrepot < this.quantite_minerai) {
                        this.setQuantite_minerai(this.quantite_minerai - capacite_restante_entrepot);
                        entrepot.setQuantite_minerai_actuelle(entrepot.getCapacite_stockage());
                        return true;
                    } else {
                        this.setQuantite_minerai(0);
                        entrepot.setQuantite_minerai_actuelle(entrepot.getQuantite_minerai_actuelle() + this.getQuantite_minerai());
                        return true;
                    }
                }
            }
        }
        else {
            System.out.println("Le robot a déjà réalisé une action pendant ce tour!");
            return false;
        }
        return false;
    }

    public boolean collecter(){
        if (this.nombre_action==0)
        {
            if (this.quantite_minerai!=this.capacite_minerai_max)
            {
                // Vérifier la présence d'une parcelle attachée au robot
                if (this.parcelle == null) {
                    System.out.println("Le robot n'est pas sur la carte!");
                    return false;
                }
                // Vérifier que la parcelle n'est pas vide
                else if (this.parcelle.getLieu() instanceof Terrain_vide) {
                    System.out.println("Il n'y a pas de mine sur cette parcelle!");
                    return false;
                }
                // Vérifier que la parcelle contient une mine et non un entrepôt.
                else if (this.parcelle.getLieu().getType_Lieu() == Lieu.Type_Lieu.ENTREPOT) {
                    System.out.println("Attention vous ne pouvez pas récolter depuis un Entrepôt!");
                    return false;
                } else if (this.parcelle.getLieu().getType_Lieu() == Lieu.Type_Lieu.MINE) {
                    Mine mine = (Mine) this.parcelle.getLieu();
                    //Vérifier que les spécialités conïncident
                    if (this.specialite != mine.getSpecialite()) {
                        System.out.println("Type de minerai imcompatible :" +
                                "\n|| Specialité du Robot = " + this.specialite +
                                "\n|| Spécialité de la Mine = " + mine.getSpecialite());
                        return false;
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
                System.out.println("Le robot est déjà plein!");
                return false;
            }
        }
        else {
            System.out.println("Le robot a déjà réalisé une action pendant ce tour!");
            return false;
        }
        return false;
    }

    public String toString()
    {
        return  "Nom : " + this.getNom() + "\n| Spécialité : "+this.getSpecialite() + " \n| Parcelle : "
                + "(" + this.getParcelle().getCoordonnees()[0] + ";" + this.getParcelle().getCoordonnees()[1] + ")"
                + "\n| Capacité de stockage : " + this.getCapacite_minerai_max()+" \n| Capacité d'extraction : "
                + this.getCapacite_extraction()+"\n| Quantité de minerai actuelle : "  + this.getQuantite_minerai();
    }

}

