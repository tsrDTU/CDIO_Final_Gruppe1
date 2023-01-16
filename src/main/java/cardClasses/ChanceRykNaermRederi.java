package cardClasses;

/**
 * Ryk spillerens bil til nærmeste rederi (det samme som færge)
 */
public class ChanceRykNaermRederi extends Chancekort
{
    private int gange_leje;
    private String felt="Rederi";

   public ChanceRykNaermRederi (String nvn, String inf, int gng_leje)
    {
        super(nvn, inf);
        gange_leje=gng_leje;
    }

    public String getFelt()
    {
        return felt;
    }

    public int getGange_leje()
    {
        return gange_leje;
    }
}
