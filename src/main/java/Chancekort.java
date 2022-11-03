public class Chancekort
{
    private int kortType;
    private String kortNavn=new String();
    private  String kortInfo=new String();
    private String modtager=new String();
    private int action;
    private boolean aktivt;


    Chancekort(String navn, String info)
    {
        kortNavn=navn;
        kortInfo=info;

    }
}

