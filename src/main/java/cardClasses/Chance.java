package cardClasses;

import gui_main.GUI;
import gui_fields.GUI_Player;







public  class Chance {
    private static Chancekort[] chanceCards=new Chancekort[20];



    public Chance()
    {
       System.out.println("Chance initialiseres");

       chanceCards[0]=new ChanceOverdragelseskort("Til Bil","Bil: På din næste tur skal du drøne frem til et hvilket som helst ledigt felt og købe det. Hvis det ikke er nogen ledige felter skal du købe et fra en anden spiller!", "Bil");

       chanceCards[1]=new ChanceRykFremTilFelt("Start","Ryk frem til start.", 0);
       chanceCards[2]=new ChanceRyk05("Ryk05","Ryk op til 5 felter frem");
       chanceCards[3]=new ChanceKortFarve("Ryk orange","Gratis felt. Ryk frem til et orange felt. Hvis det der ledigt, får du det gratis. Ellers skal du betale leje ejeren.", 3);
       chanceCards[4]=new ChanceRyk1ElChMemere("Ryk 1 eller change mere","Ryk 1 felt frem eller tag et chancekort mere");
       chanceCards[5]=new ChanceOverdragelseskort("Til Skibet","Giv dette kort til skibet og tag et chancekort mere. Skib: På den næste skal du sejle frem til hvilket som helst ledigt felt og købe det. Hvis der ikke er nogen ledige felter, skal du købe et af en anden spiller.","Skibet");
       chanceCards[6]=new ChanceBanktrans("For meget slik","Du har spist for meget slik. Betal M2 til banken.", -2,1,0);
        chanceCards[7]=new Chance2Farver("Vælg orange eller grønt felt","Gratis felt. Ryk frem til et orange eller grønt felt. Hvis det der ledigt, får du det gratis. Ellers skal du betale leje ejeren.", 3, 8);
       chanceCards[8]=new ChanceKortFarve("Ryk lyseblåt","Gratis felt. Ryk frem til et lyseblåt felt. Hvis det der ledigt, får du det gratis. Ellers skal du betale leje ejeren.",5);
       chanceCards[9]=new ChanceAmnistiFeng("Amnesti","Du løslades uden omkostninger. Behold dette kort til du får brug for det.");
        chanceCards[10]=new ChanceRykFremTilFelt("ryk tilStrandpromenaden","Ryk frem til Strandpromenaden.", 23);
       chanceCards[11]=new ChanceOverdragelseskort("Til Katten","Giv dette kort til Katten og tag et chancekort mere. Kat: På den næste skal du sejle frem til hvilket som helst ledigt felt og købe det. Hvis der ikke er nogen ledige felter, skal du købe et af en anden spiller.","Katten");
        chanceCards[12]=new ChanceOverdragelseskort("Til Hunden","Giv dette kort til Hunden og tag et chancekort mere. Hund: På den næste skal du sejle frem til hvilket som helst ledigt felt og købe det. Hvis der ikke er nogen ledige felter, skal du købe et af en anden spiller.","Hunden");
       chanceCards[13]=new ChanceBanktrans("Fødselsdag","Det er din fødselsdag. Alle giver dig 1 M. Tillyke med fødselsdagen",1,0,2);
       chanceCards[14]=new Chance2Farver("Ryk til Pink eller Mørkeblåt","Gratis felt. Ryk frem til et pink eller mørkeblåt felt. Hvis det der ledigt, får du det gratis. Ellers skal du betale leje ejeren.",4,2);
       chanceCards[15]=new ChanceBanktrans("Alle lektier lavet","Du har lavet alle dine lektier. Modtag 2M fra banken",2,0,1);
        chanceCards[16]=new ChanceKortFarve("Ryk til rødt felt","Gratis felt. Ryk frem til et rødt felt. Hvis det der ledigt, får du det gratis. Ellers skal du betale leje ejeren.",1);
       chanceCards[17]=new ChanceRykFremTilFelt("Ryk frem til Skateparken","Gratis felt. Ryk frem til Skaterparken for at lave det perfekte grind. Hvis ingen ejer den, får du den gratis. Ellers skal du betale leje ejeren.",10);
       chanceCards[18]=new Chance2Farver("Ryk frem til lyseblåt eller rødt","Gratis felt. Ryk frem til et lyseblåt eller rødt felt. Hvis det der ledigt, får du det gratis. Ellers skal du betale leje ejeren.",5,1);
       chanceCards[19]=new Chance2Farver("Ryk frem til brunt eller gylt felt","Gratis felt. Ryk frem til et brunt eller gult felt. Hvis det der ledigt, får du det gratis. Ellers skal du betale leje ejeren.",9,6);

 //   System.out.println(chanceCards[18].getClass());
    }

    public Chancekort traekEtChanceKort()
    {
        int kort_nr;
        boolean kOk=false;

       do {

          kort_nr = (int)(Math.random() * 20);
           System.out.println(kort_nr);

           if (kort_nr==0|| kort_nr == 5 || kort_nr == 11 || kort_nr == 12 || kort_nr == 9)
           {


               if (chanceCards[kort_nr].getClass().equals(ChanceOverdragelseskort.class))
               {
                   if (chanceCards[kort_nr] instanceof ChanceOverdragelseskort)
                   {
                       if(!((ChanceOverdragelseskort) chanceCards[kort_nr]).getAktivt()) kOk=true;
                   }

               }
                else if (chanceCards[kort_nr] instanceof ChanceAmnistiFeng)
                {
                   if (!((ChanceAmnistiFeng) chanceCards[kort_nr]).getAktivt()) kOk=true;
                }
                else kOk=true;



        }

      } while (!kOk);

    return chanceCards[kort_nr];



    }

    public void chanceFieldIsHit(int field_nr,GUI_Player actPlayer, GUI gui)
    {
      Chancekort actKort=traekEtChanceKort();

       gui.showMessage(actKort.getKortInfo());
    }

}


