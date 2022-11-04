package cardClasses;

public class ChanceOverdragelseskort extends Chancekort
{
    private String modtager=new String();
    private boolean aktivt;

    public ChanceOverdragelseskort(String nvn,String inf,String modt)
    {
        super(nvn,inf);
        modtager=modt;
    }
}
