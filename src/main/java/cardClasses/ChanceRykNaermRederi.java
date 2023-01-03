package cardClasses;

public class ChanceRykNaermRederi extends Chance
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
