package cardClasses;

public class ChanceRykFremTilFelt extends Chancekort
{
    private int destinationsFelt;

    @Override
    public String toString() {
        return "ChanceRykFremTilFelt{" +
                "destinationsFelt=" + destinationsFelt +
                '}';
    }

    public ChanceRykFremTilFelt(String nvn, String inf, int dest)
    {
        super(nvn,inf);
        destinationsFelt=dest;
    }

    public int getDestinationsFelt() {
        return destinationsFelt;
    }

}
