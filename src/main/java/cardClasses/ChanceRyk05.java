package cardClasses;

/**
 * Dette chancekort bestemmer at spilleren rykkes 5 felter frem
 */
public class ChanceRyk05 extends Chancekort
{
    @Override
    public String toString() {
        return "ChanceRyk05{}";
    }

    public ChanceRyk05(String nvn, String inf)
    {
        super(nvn,inf);
    }

}
