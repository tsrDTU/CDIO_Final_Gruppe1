package cardClasses;

public class ChanceRykFelter extends Chance
{
    private int antal_felter;

    ChanceRykFelter(String nvn, String inf, int ant_fel)
    {
        super(nvn, inf);
        antal_felter=ant_fel;
    }

    public int getAntal_felter()
    {
        return antal_felter;
    }
}
