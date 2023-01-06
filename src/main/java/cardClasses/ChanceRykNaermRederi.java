package cardClasses;

/**
 * Ryk spillerens bil til nærmeste rederi (det samme som færge)
 */
public class ChanceRykNaermRederi extends Chancekort
{
    private String felt="Rederi";

    ChanceRykNaermRederi (String nvn, String inf)
    {
        super(nvn, inf);
    }

    public String getFelt()
    {
        return felt;
    }
}
