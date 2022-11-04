package cardClasses;

public class Chance2Farver extends Chancekort
{
    private int farve1, farve2;

    public Chance2Farver(String nvn,String inf, int f1, int f2)
    {
        super(nvn,inf);
        farve1=f1;
        farve2=f2;
    }

}
