package domein;

import java.util.Random;

public class Dobbelsteen
{
    private static final Random random = new Random();
    
    private int aantalOgen = 1;

    public int getAantalOgen()
    {
        return this.aantalOgen;
    }

    public void setAantalOgen(int aantalOgen)
    {
        if (aantalOgen < 1 || aantalOgen > 6) {
            throw new IllegalArgumentException("Ongeldig aantal ogen");
        }
        this.aantalOgen = aantalOgen;
    }

    public void rol()
    {
        setAantalOgen(1 + random.nextInt(6));
    }
}
