public class Parcelle {
    /* Attribut */
    private int abscisse,ordonnee;
    private boolean presence_lieu;
    private boolean presence_robot;
    private String[][] cases;
    private Lieu lieu;
    private Robot robot;
    private Map map;

    /* Constructor */
    public Parcelle(int x, int y) {
        this.abscisse=x;
        this.ordonnee=y;
        this.presence_lieu = false;
        this.presence_robot = false;
        this.map = null;
        this.lieu = new Terrain_vide(this);
        this.robot = null;
        this.cases=new String[][] {{" "," "},{" "," "}};
    }

    public Parcelle() {
        this.abscisse=0;
        this.ordonnee=0;
        this.lieu = new Terrain_vide(this);
        this.robot = null;
        this.map = null;
        this.presence_lieu = false;
        this.presence_robot = false;
        this.cases=new String[][] {{" "," "},{" "," "}};
    }

    public Parcelle(Parcelle p) {
        this.abscisse=p.abscisse;
        this.ordonnee = p.ordonnee;
        this.lieu = p.lieu;
        this.robot = p.robot;
        this.map = p.map;
        this.presence_lieu = p.presence_lieu;
        this.presence_robot = p.presence_robot;
        this.cases=p.cases;
    }

    /* Méthodes */
    public int[] getCoordonnees() {
        int[] pos=new int[] {this.abscisse,this.ordonnee};
        return pos;
    }

    public boolean isPresence_lieu() {
        return this.presence_lieu;
    }

    public String[][] getCases() {return this.cases;}

    public boolean isPresence_robot() {
        return this.presence_robot;
    }

    public void setPresence_lieu(boolean presence_lieu) {
        this.presence_lieu = presence_lieu;
    }

    public void setCases(String[][] cases) {
        this.cases=cases;
    }

    public void setCasesLieuOuRobot(boolean choix_cases,String[] cases) {
        if (!choix_cases) {
            this.cases[0] = cases;
        }
        else{
            this.cases[1] = cases;
        }
    }

    public void setPresence_robot(boolean presence_robot) {
        this.presence_robot = presence_robot;
    }

    public Lieu getLieu() {
            return this.lieu;
    }

    public Robot getRobot() {
        return this.robot;
    }

    public Map getMap() {
        return this.map;
    }

    public void setAbcisse(int a) {
        this.abscisse = a;
    }

    public void setOrdonnee(int o){
        this.ordonnee=o;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public void setMap(Map map) {this.map = map;}

    public void afficherBordureHauteParcelle(){
        System.out.print(" ͟͟͟ ");
    }
    public void afficherLieuParcelle(){
        System.out.print("│ "+this.cases[0][0]+this.cases[0][1]+" │");
    }

    public void afficherRobotParcelle(){
        System.out.print("│ "+this.cases[1][0]+this.cases[1][1]+" │");
    }


    public String toString(){
        return "Coordonnée : ("+this.getCoordonnees()[0]+":"+this.getCoordonnees()[1]+")"
                +"\n | Robot : "+this.getRobot().getNom();
    }
}