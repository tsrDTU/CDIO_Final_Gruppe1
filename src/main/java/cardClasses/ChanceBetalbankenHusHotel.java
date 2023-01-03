package cardClasses;

/**
 * Spillerens betaling til banken, hvor den er afhængig af hvor mange huse og hoteller spilleren ejer
 */
public class ChanceBetalbankenHusHotel extends Chancekort
{
    private int beloebHus, beloebHotel;

    ChanceBetalbankenHusHotel(String nvn, String inf, int belhus,int belHot)
    {
        super(nvn,inf);
        beloebHus=belhus;
        beloebHotel=belHot;
    }

    public int getBeloebHotel()
    {
        return beloebHotel;
    }

    public int getBeloebHus()
    {
        return beloebHus;
    }
}
