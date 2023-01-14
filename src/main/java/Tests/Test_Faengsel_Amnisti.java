package Tests;

import java.io.IOException;

/**
 * Test af at amnesti chancekort kan få dig ud af fængslet. Plyer1 trækker først et amnesti chancekort og derefter et "Gå i fængsel chancekort"
 * som skal hjælpe med at komme ud af fængslet
 */


public class Test_Faengsel_Amnisti {
    public static void main(String[] args) throws IOException {
        {
            int[] pos_pl1= new int[5];
            int[] pos_pl2= new int[5];
            int[] pos_pl3= new int[5];
            int[] slag= new int[5];
            int[] chancekort_nr= new int[5];
            int slag1;
// slag 0
            slag1=0;
            pos_pl1[slag1]=0;
            pos_pl2[slag1]=0;
            pos_pl3[slag1]=0;
            slag[slag1]=2;
            chancekort_nr[slag1]=43;


            slag1=1;
            pos_pl1[slag1]=2;
            pos_pl2[slag1]=0;
            pos_pl3[slag1]=0;
            slag[slag1]=5;
            chancekort_nr[slag1]=44;

            slag1=2;
            pos_pl1[slag1]=10;
            pos_pl2[slag1]=0;
            slag[slag1]=2;
            chancekort_nr[slag1]=43;



            TestSituation_FlereSlag testSituation_flere_slag=new TestSituation_FlereSlag(pos_pl1, pos_pl2, pos_pl3, slag,chancekort_nr,2,30000,30000,30000);

        }
    }
}
