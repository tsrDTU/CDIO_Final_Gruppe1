package cardClasses;
import gui_main.GUI;
import player.MjPlayer;




public  class Chance {
    private static Chancekort[] chanceCards=new Chancekort[20];
    int testKortMode=0;



    public Chance()
    {
        System.out.println("Chance initialiseres");

        chanceCards[0]=new ChanceOverdragelseskort(".Til Bil","Dette chance kort er givet til Bilen. Tag et chancekort mere Bil: På din næste tur skal du drøne frem til et hvilket som helst ledigt felt og købe det. Hvis det ikke er nogen ledige felter skal du købe et fra en anden spiller!", "Bil");

        chanceCards[1]=new ChanceRykFremTilFelt("Start","Du rykkes frem til start.", 0);
        chanceCards[2]=new ChanceRyk05("Ryk05","Ryk op til 5 felter frem");
        chanceCards[3]=new ChanceKortFarve("Ryk orange","Gratis felt. Ryk frem til et orange felt. Hvis det der ledigt, får du det gratis. Ellers skal du betale leje ejeren.", 3);
        chanceCards[4]=new ChanceRyk1ElChMemere("Ryk 1 eller change mere","Ryk 1 felt frem eller tag et chancekort mere");
        chanceCards[5]=new ChanceOverdragelseskort("Til Skibet","Dette kort er givet til skibet. Tag et chancekort mere. Skib: På den næste skal du sejle frem til hvilket som helst ledigt felt og købe det. Hvis der ikke er nogen ledige felter, skal du købe et af en anden spiller.","Skibet");
        chanceCards[6]=new ChanceBanktrans("For meget slik","Du har spist for meget slik. Betal M2 til banken.", -2,1,0);
        chanceCards[7]=new Chance2Farver("Vælg orange eller grønt felt","Gratis felt. Ryk frem til et orange eller grønt felt. Hvis det der ledigt, får du det gratis. Ellers skal du betale leje ejeren.", 3, 8);
        chanceCards[8]=new ChanceKortFarve("Ryk lyseblåt","Gratis felt. Ryk frem til et lyseblåt felt. Hvis det der ledigt, får du det gratis. Ellers skal du betale leje ejeren.",5);
        chanceCards[9]=new ChanceAmnistiFeng("Amnesti","Du løslades uden omkostninger. Behold dette kort til du får brug for det.");
        chanceCards[10]=new ChanceRykFremTilFelt("ryk tilStrandpromenaden","Du Rykkes frem til Strandpromenaden.", 23);
        chanceCards[11]=new ChanceOverdragelseskort("Til Katten","Dette kort er givet til Katten. Tag et chancekort mere. Kat: På den næste skal du sejle frem til hvilket som helst ledigt felt og købe det. Hvis der ikke er nogen ledige felter, skal du købe et af en anden spiller.","Katten");
        chanceCards[12]=new ChanceOverdragelseskort("Til Hunden","Dette kort er givet til Hunden.Tag et chancekort mere. Hund: På den næste skal du sejle frem til hvilket som helst ledigt felt og købe det. Hvis der ikke er nogen ledige felter, skal du købe et af en anden spiller.","Hunden");
        chanceCards[13]=new ChanceBanktrans("Fødselsdag","Det er din fødselsdag. Alle giver dig 1 M. Tillyke med fødselsdagen",1,0,2);
        chanceCards[14]=new Chance2Farver("Ryk til Pink eller Mørkeblåt","Gratis felt. Ryk frem til et pink eller mørkeblåt felt. Hvis det der ledigt, får du det gratis. Ellers skal du betale leje ejeren.",4,2);
        chanceCards[15]=new ChanceBanktrans("Alle lektier lavet","Du har lavet alle dine lektier. Modtag 2M fra banken",2,0,1);
        chanceCards[16]=new ChanceKortFarve("Ryk til rødt felt","Gratis felt. Ryk frem til et rødt felt. Hvis det der ledigt, får du det gratis. Ellers skal du betale leje ejeren.",1);
        chanceCards[17]=new ChanceRykFremTilFelt("Ryk frem til Skateparken","Gratis felt. Du Rykkes frem til Skaterparken for at lave det perfekte grind. Hvis ingen ejer den, får du den gratis. Ellers skal du betale leje ejeren.",10);
        chanceCards[18]=new Chance2Farver("Ryk frem til lyseblåt eller rødt","Gratis felt. Ryk frem til et lyseblåt eller rødt felt. Hvis det der ledigt, får du det gratis. Ellers skal du betale leje ejeren.",5,1);
        chanceCards[19]=new Chance2Farver("Ryk frem til brunt eller gylt felt","Gratis felt. Ryk frem til et brunt eller gult felt. Hvis det der ledigt, får du det gratis. Ellers skal du betale leje ejeren.",9,6);


    }

    public Chancekort traekEtChanceKort()
    {
        int kort_nr;
        int  kOk=0;

        do {
            kOk=0;

            if (testKortMode==0) {
                kort_nr = (int) (Math.random() * 20);
                System.out.println("Normalt kort trukket");
            }
            else
            {
                kort_nr=testKortMode;
                testKortMode=0;
                System.out.println("Testkort trukket");
            }
            System.out.println("Chancekort nr: "+kort_nr+" trukket");

            if (kort_nr==0|| kort_nr == 5 || kort_nr == 11 || kort_nr == 12 || kort_nr == 9)
            {


                if (chanceCards[kort_nr].getClass().equals(ChanceOverdragelseskort.class))
                {
                    if (chanceCards[kort_nr] instanceof ChanceOverdragelseskort==true)
                    {
                        if(((ChanceOverdragelseskort) chanceCards[kort_nr]).getAktivt()) kOk=11;
                    }

                }
                else if (chanceCards[kort_nr] instanceof ChanceAmnistiFeng)
                {
                    if (!((ChanceAmnistiFeng) chanceCards[kort_nr]).getAktivt()) kOk=11;
                }
                else kOk=11;



            }

        } while (kOk > 0);

        return chanceCards[kort_nr];



    }


    public int chanceFieldIsHit(MjPlayer actPlayer,MjPlayer[] players, int actField,
                                int AmountofPlayers, int AmountofSpaces, GUI gui)
    {
        int i, bilPos;
        int slut=0;

        bilPos=actField;
        System.out.println("bilPos "+bilPos);

        do {

            slut=0;

            System.out.println("Chancekort trækkes");
            Chancekort actKort = traekEtChanceKort();

            gui.showMessage(actKort.getKortInfo());

            if (actKort instanceof ChanceOverdragelseskort==true)
            {
      //          gui.showMessage(actKort.getKortInfo());
                actPlayer.setKortModtaget(true);
                actPlayer.setActChancekort(actKort);
                slut=11;
                System.out.println("ChanceOverdragelseskort eksekveret");
            }
            if (actKort instanceof ChanceAmnistiFeng==true) {

                actPlayer.setAmnistkortHaves(true);
                System.out.println("ChanceAmnistiFeng");
            }

           if (actKort instanceof ChanceRykFremTilFelt==true) {

                gui.showMessage(actKort.getKortInfo());

                bilPos = ((ChanceRykFremTilFelt) actKort).getDestinationsFelt();
                System.out.println("ChanceRykFremTilFelt. bilPos "+ bilPos);

            }
            if (actKort instanceof ChanceRyk1ElChMemere==true) {
                String valg = gui.getUserButtonPressed("Vælg", "En chance mere", "Ryk et felt frem");
                if (valg.equals("En chance mere")) {
                   slut=11;
                } else {
                    bilPos = actField + 1;
                }
                System.out.println("ChanceRyk1ElChMemere");


            }

        }while (slut > 1);
        System.out.println("While slut");
        System.out.println("bilPos "+bilPos);
        return bilPos;

    }

    public void setTestKortMode(int testNr)
    {
        testKortMode=testNr;
    }


}


