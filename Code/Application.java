public class Application
{
    public static void main(String[] args) {
        Map carte = new Map();
        Robot robot1 = new Robot(1);
        robot1.setRandomParcelle(carte);
        System.out.println(robot1);
        System.out.println(carte.getMonde()[robot1.getParcelle().getCoordonnees()[0]][robot1.getParcelle().getCoordonnees()[1]]);
        System.out.println("*******************************************************************");
        /* carte.afficherMap();*/
    }
}
