package cardClasses;

public class ChanceRykFremTilFelt extends Chancekort
{
    private int destinationsFelt;

    public ChanceRykFremTilFelt(String nvn,String inf,int dest)
    {
        super(nvn,inf);
        destinationsFelt=dest;
    }
}
