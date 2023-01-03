package cardClasses;

public class ChanceModtagFraHverSpiller extends Chance
{
    private int beloeb;

    ChanceModtagFraHverSpiller(String nvn, String inf, int bel)
    {
        super(nvn, inf);
        beloeb=bel;
    }

    public int getBeloeb()
    {
        return beloeb;
    }

}
