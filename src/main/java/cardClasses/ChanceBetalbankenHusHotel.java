package cardClasses;

public class ChanceBetalbankenHusHotel extends Chance
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
