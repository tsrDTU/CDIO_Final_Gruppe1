package cardClasses;

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
