package cardClasses;

/**
 * Håndterer chancekort som bestemmer at spilleren skal flytte til et felt, som har en bestemt farve
 */
public class ChanceKortFarve extends Chancekort
{
    private int farvekode;

    @Override
    public String toString() {
        return "ChanceKortFarve{" +
                "farvekode=" + farvekode +
                '}';
    }

    public ChanceKortFarve(String nvn, String inf, int fako)
    {
        super(nvn,inf);
        farvekode=fako;

    }

    public int getFarvekode() {
        return farvekode;
    }



}
