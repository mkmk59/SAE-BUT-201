package com.example.sae_robots_mineur;

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

    public Parcelle getParcelle(int x, int y)
    {
        return this.monde[x][y];
    }

    public Parcelle[][] getMonde() {return this.monde;}

}
