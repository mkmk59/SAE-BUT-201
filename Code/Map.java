public class Map
{
    /* Attribut */
    private Parcelle[][] monde;

    /* Constructor */

    public Map(Parcelle[][] parcelles)
    {
        this.monde=parcelles;
    }

    public Map()
    {
        this.monde=new Parcelle[10][10];
        for (int x=0;x<10;x++)
        {
            for (int y=0;y<10;y++)
            {
                this.monde[x][y]=new Parcelle(x,y);
            }
        }
    }

    /* Méthodes */
    public void afficherMap()
    {
        int n=0;
        while (n<this.monde.length-1){
            System.out.print(" ͟͟͟͟");
            n++;
        }
        System.out.print(" ͟͟͟͟ \n");
        for(Parcelle[] ligne_parcelle : this.monde){
            for (Parcelle p : ligne_parcelle){
                System.out.print("│ "+p.getCases()[0][0]+p.getCases()[0][1]+" ");
            }
            System.out.print("│\n");
            for (Parcelle p : ligne_parcelle){
                System.out.print("│ "+p.getCases()[1][0]+p.getCases()[1][1]+" ");
            }
            System.out.print("│\n");
            int lb=0;
            while (lb<this.monde.length-1){
                System.out.print(" ̅̅̅̅");
                lb++;
            }
            System.out.print(" ̅̅̅̅\n");
        }
    }

    public Parcelle getParcelle(int x, int y)
    {
        return this.monde[x][y];
    }

    public Parcelle[][] getMonde() {return this.monde;}

}
