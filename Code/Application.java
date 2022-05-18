public class Application
{
    public static void main(String[] args) {
        Partie partie = new Partie();
        partie.initialiserPartie(1,1,1,1);
        partie.getCarte().afficherMap();
        partie.afficherInformationsTour();
        partie.jouerPartie();
    }
}
