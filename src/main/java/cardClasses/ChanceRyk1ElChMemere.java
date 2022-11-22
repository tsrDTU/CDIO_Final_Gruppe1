package cardClasses;

/**
 * Dette chancekort giver spilleren et valg mellem at rykke et felt eller tage et chancekort mere
 */
public class ChanceRyk1ElChMemere extends Chancekort
{
    @Override
    public String toString() {
        return "ChanceRyk1ElChMemere{}";
    }

    public ChanceRyk1ElChMemere(String inf, String nvn)
    {
        super(inf,nvn);
    }

}
