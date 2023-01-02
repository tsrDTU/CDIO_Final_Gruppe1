package cardClasses;

public class ChanceBetalBanken extends Chance
{
    private int beloeb;

    ChanceBetalBanken(String nvn, String inf, int bel)
    {
        super(nvn, inf);
        beloeb=bel;
    }

    public int getBeloeb()
    {
        return beloeb;
    }
}
