package cardClasses;

/**
 * Håndterer chancekort som bestemmer at spilleren kan flytte til et felt, som har en af de to farver
 */
public class Chance2Farver extends Chancekort
{
    private int farve1, farve2;

    @Override
    public String toString() {
        return "Chance2Farver{" +
                "farve1=" + farve1 +
                ", farve2=" + farve2 +
                '}';
    }

    public int getFarve1() {
        return farve1;
    }
    public int getFarve2() {
        return farve2;
    }

    public Chance2Farver(String nvn, String inf, int f1, int f2)
    {
        super(nvn,inf);
        farve1=f1;
        farve2=f2;
    }

}
