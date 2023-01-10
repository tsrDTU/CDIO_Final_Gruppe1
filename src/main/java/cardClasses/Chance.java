package cardClasses;
import EgneGuiKlasser.MGUI;
import gui_main.GUI;

import gui_fields.*;

import java.util.Objects;
import EgneGuiKlasser.*;


public  class Chance {
    private static Chancekort[] chanceCards=new Chancekort[46];
    int testKortMode=0;

    /**
     * Initialisering af chancekort
     */

    public Chance()
    {
        //System.out.println("Chance initialiseres");

        chanceCards[0]=new ChanceBetalBanken("Fuldt stop", "De har kørt frem for “fuldt stop”, Betal 1000 kroner i bøde", 1000);
        chanceCards[1]=new ChanceBetalBanken("Vognvask og smøring", "Betal for vognvask og smøring kr 300", 300);
        chanceCards[2]=new ChanceBetalBanken("2 kasser øl","Betal kr 200 for levering af 2 kasser øl", 200);
        chanceCards[3]=new ChanceBetalBanken("Reparation1 af deres vogn", "Betal 3000 for reparation af deres vogn", 3000);
        chanceCards[4]=new ChanceBetalBanken("Reparation2 af deres vogn", "Betal 3000 for reparation af deres vogn", 3000);
        chanceCards[5]=new ChanceBetalBanken("4 nye dæk", "De har købt 4 nye dæk til Deres vogn, betal kr 1000", 1000);
        chanceCards[6]=new ChanceBetalBanken("Parkeringsbøde", "De har fået en parkeringsbøde, betal kr 200 i bøde", 200);
        chanceCards[7]=new ChanceBetalBanken("bilforsikring", "Betal deres bilforsikring, kr 1000", 1000);
        chanceCards[8]=new ChanceBetalBanken("Smøger", "De har været udenlands og købt for mange smøger, betal kr 200 i told.", 200);
        chanceCards[9]=new ChanceBetalBanken("Tandlægeregning", "Tandlægeregning, betal kr 2000.", 2000);
        chanceCards[10]=new ChanceModtagFraBanken("klasselotteriet1", "De har vundet i klasselotteriet. Modtag 500 kr.", 500);
        chanceCards[11]=new ChanceModtagFraBanken("klasselotteriet2", "De har vundet i klasselotteriet. Modtag 500 kr.", 500);
        chanceCards[12]=new ChanceModtagFraBanken("Aktieudbytte1", "De modtager Deres aktieudbytte. Modtag kr 1000 af banken", 1000);
        chanceCards[13]=new ChanceModtagFraBanken("Aktieudbytte2", "De modtager Deres aktieudbytte. Modtag kr 1000 af banken", 1000);
        chanceCards[14]=new ChanceModtagFraBanken("Aktieudbytte3", "De modtager Deres aktieudbytte. Modtag kr 1000 af banken", 1000);
        chanceCards[15]=new ChanceModtagFraBanken("Eftergivet skat", "Kommunen har eftergivet et kvartals skat. Hæv i banken 3000 kr.", 3000);
        chanceCards[16]=new ChanceModtagFraBanken("Elleve rigtige", "De have en række med elleve rigtige i tipning, modtag kl 1000", 1000);
        chanceCards[17]=new ChanceModtagFraBanken("Gageforhøjelse", "Grundet dyrtiden har De fået gageforhøjelse, modtag kr 1000.", 1000);
        chanceCards[18]=new ChanceModtagFraBanken("Præmieobligation1", "Deres præmieobligation er udtrykket. De modtager 1000 kr af banken.", 1000);
        chanceCards[19]=new ChanceModtagFraBanken("Præmieobligation2", "Deres præmieobligation er udtrykket. De modtager 1000 kr af banken.", 1000);
        chanceCards[20]=new ChanceModtagFraBanken("Gamle møbler", "De har solg nogle gamle møbler på auktion. Modtag 1000 kr af banken.", 1000);
        chanceCards[21]=new ChanceModtagFraBanken("Nyttehaven", "Værdien af egen avl fra nyttehaven udgør 200 som de modtager af banken", 200);


        /*
        chanceCards[0]=new ChanceBetalbankenHusHotel("Oliepriserne","Oliepriserne er steget, og De skal betale kr 500 pr hus og kr 2000 pr hotel", 500, 2000);
        chanceCards[1]=new ChanceBetalbankenHusHotel("Ejendomsskat","Ejendomsskatten er steget. Ekstraudgifterne er: 800 kr pr hus, 2300 kr pr hotel.",800,2300);
        chanceCards[2]=new ChanceBetalBanken("Fuldt stop", "De har kørt frem for “fuldt stop”, Betal 1000 kroner i bøde", 1000);
        chanceCards[3]=new ChanceBetalBanken("Vognvask og smøring", "Betal for vognvask og smøring kr 300", 300);
        chanceCards[4]=new ChanceBetalBanken("2 kasser øl","Betal kr 200 for levering af 2 kasser øl", 200);
        chanceCards[5]=new ChanceBetalBanken("Reparation1 af deres vogn", "Betal 3000 for reparation af deres vogn", 3000);
        chanceCards[6]=new ChanceBetalBanken("Reparation2 af deres vogn", "Betal 3000 for reparation af deres vogn", 3000);
        chanceCards[7]=new ChanceBetalBanken("4 nye dæk", "De har købt 4 nye dæk til Deres vogn, betal kr 1000", 1000);
        chanceCards[8]=new ChanceBetalBanken("Parkeringsbøde", "De har fået en parkeringsbøde, betal kr 200 i bøde", 200);
        chanceCards[9]=new ChanceBetalBanken("bilforsikring", "Betal deres bilforsikring, kr 1000", 1000);
        chanceCards[10]=new ChanceBetalBanken("Smøger", "De har været udenlands og købt for mange smøger, betal kr 200 i told.", 200);
        chanceCards[11]=new ChanceBetalBanken("Tandlægeregning", "Tandlægeregning, betal kr 2000.", 2000);
        chanceCards[12]=new ChanceModtagFraBanken("klasselotteriet1", "De har vundet i klasselotteriet. Modtag 500 kr.", 500);
        chanceCards[13]=new ChanceModtagFraBanken("klasselotteriet2", "De har vundet i klasselotteriet. Modtag 500 kr.", 500);
        chanceCards[14]=new ChanceModtagFraBanken("Aktieudbytte1", "De modtager Deres aktieudbytte. Modtag kr 1000 af banken", 1000);
        chanceCards[15]=new ChanceModtagFraBanken("Aktieudbytte2", "De modtager Deres aktieudbytte. Modtag kr 1000 af banken", 1000);
        chanceCards[16]=new ChanceModtagFraBanken("Aktieudbytte3", "De modtager Deres aktieudbytte. Modtag kr 1000 af banken", 1000);
        chanceCards[17]=new ChanceModtagFraBanken("Eftergivet skat", "Kommunen har eftergivet et kvartals skat. Hæv i banken 3000 kr.", 3000);
        chanceCards[18]=new ChanceModtagFraBanken("Elleve rigtige", "De have en række med elleve rigtige i tipning, modtag kl 1000", 1000);
        chanceCards[19]=new ChanceModtagFraBanken("Gageforhøjelse", "Grundet dyrtiden har De fået gageforhøjelse, modtag kr 1000.", 1000);
        chanceCards[20]=new ChanceModtagFraBanken("Præmieobligation1", "Deres præmieobligation er udtrykket. De modtager 1000 kr af banken.", 1000);
        chanceCards[21]=new ChanceModtagFraBanken("Præmieobligation2", "Deres præmieobligation er udtrykket. De modtager 1000 kr af banken.", 1000);
        chanceCards[22]=new ChanceModtagFraBanken("Gamle møbler", "De har solg nogle gamle møbler på auktion. Modtag 1000 kr af banken.", 1000);
        chanceCards[23]=new ChanceModtagFraBanken("Nyttehaven", "Værdien af egen avl fra nyttehaven udgør 200 som de modtager af banken", 200);
        chanceCards[24]=new ChanceMatadorLegat("Matador-legatet", "De modtager “Matador-legatet” på kr 40.000, men kun hvis værdier ikke overstiger 15.000 kr");
        chanceCards[25]=new ChanceModtagFraHverSpiller("Fødselsdag", "Det er deres fødselsdag. Modtag af hver medspiller 200 kr.", 200);
        chanceCards[26]=new ChanceModtagFraHverSpiller("Sammenskudsgilde", "De har lagt penge ud til et sammenskudsgilde. Mærkværdigvis betaler alle straks. Modtag fra hver medspiller 500 kr.", 500);
        chanceCards[27]=new ChanceModtagFraHverSpiller("Familiefest", "De skal holde familiefest og får et tilskud fra hver medspiller på 500 kr.", 500);
        chanceCards[28]=new ChanceRykFremTilFelt("START1", "Ryk frem til START", 0);
        chanceCards[29]=new ChanceRykFremTilFelt("START2", "Ryk frem til START", 0);
        chanceCards[30]=new ChanceRykFelter("Tre frem", "Ryk tre felter frem", 3);
        chanceCards[31]=new ChanceRykFelter("Tre tilbage 1", "Ryk tre felter tilbage", -3);
        chanceCards[32]=new ChanceRykFelter("Tre tilbage 2", "Ryk tre felter tilbage", -3);
        chanceCards[33]=new ChanceRykFremTilFelt("Frederiksberg Allé", "Ryk frem til Frederiksberg Allé. Hvis De passere START, indkasser da 4000 kr.", 11);
        chanceCards[34]=new ChanceRykNaermRederi("Nærmeste rederi 1", "Ryk frem til det nærmeste rederi og betal ejeren to gange den leje han ellers er berettiget til, hvis selskabet ikke ejes af nogen kan De købe det af banken.");
        chanceCards[35]=new ChanceRykNaermRederi("Nærmeste rederi 2", "Ryk frem til det nærmeste rederi og betal ejeren to gange den leje han ellers er berettiget til, hvis selskabet ikke ejes af nogen kan De købe det af banken.");
        chanceCards[36]=new ChanceRykFremTilFelt("Mols-Linien", "Tag med Mols-Linien, flyt brikken frem og hvis De passerer START indkassér da kr 4000.", 15);
        chanceCards[37]=new ChanceRykFremTilFelt("Grønningen", "Ryk frem til Grønningen, hvis De passerer start indkasser da kr 4000", 24);
        chanceCards[38]=new ChanceRykFremTilFelt("Vimmelskaftet", "Ryk frem til Vimmelskaftet, hvis de passerer start indkasser da kr 4000", 32);
        chanceCards[39]=new ChanceRykNaermRederi("Nærmeste færge", "Tag med den nærmeste færge, hvis de passerer start indkasser da kr 4000");
        chanceCards[40]=new ChanceRykFremTilFelt("Strandvejen", "Ryk frem til Strandvejen. Hvis De passere START, indkasser da 4000 kr.", 19);
        chanceCards[41]=new ChanceRykFremTilFelt("Rådhuspladsen", "Tag til Rådhuspladsen", 39);
        chanceCards[42]=new ChanceAmnistiFeng("kongens fødselsdag 1","I anledning af kongens fødselsdag benådes De herved for fængsel. Dette kort kan opbevares indtil De får brug for det, eller De kan sælge det.");
        chanceCards[43]=new ChanceAmnistiFeng("kongens fødselsdag 2","I anledning af kongens fødselsdag benådes De herved for fængsel. Dette kort kan opbevares indtil De får brug for det, eller De kan sælge det.");
        chanceCards[44]=new ChanceGaaIFaengsel("Fængsel", "Gå i fængsel, De indkasserer ikke 4000 kr for at passere start.");
        chanceCards[45]=new ChanceGaaIFaengsel("Fængsel", "Gå i fængsel, De indkasserer ikke 4000 kr for at passere start.");



         */
/*


        chanceCards[0]=new ChanceOverdragelseskort(".Til Bil","Dette chance kort er givet til Bilen. Tag et chancekort mere Bil: På din næste tur skal du drøne frem til et hvilket som helst ledigt felt og købe det. Hvis det ikke er nogen ledige felter skal du købe et fra en anden spiller!", "Bil");

        chanceCards[1]=new ChanceRykFremTilFelt("Start","Du rykkes frem til start.", 0);
        chanceCards[2]=new ChanceRyk05("Ryk05","Du Rykkes op til 5 felter frem");
        chanceCards[3]=new ChanceKortFarve("Ryk orange","Gratis felt. Du RykKes frem til et orange felt. Hvis det der ledigt, får du det gratis. Ellers skal du betale leje ejeren.", 3);
        chanceCards[4]=new ChanceRyk1ElChMemere("Ryk 1 eller change mere","Ryk 1 felt frem eller tag et chancekort mere");
        chanceCards[5]=new ChanceOverdragelseskort("Til Skibet","Dette kort er givet til skibet. Tag et chancekort mere. Skib: På den næste tur skal du sejle frem til hvilket som helst ledigt felt og købe det. Hvis der ikke er nogen ledige felter, skal du købe et af en anden spiller.","Skib");
        chanceCards[6]=new ChanceBanktrans("For meget slik","Du har spist for meget slik. Betal M2 til banken.", -2,1,0);
        chanceCards[7]=new Chance2Farver("Vælg orange eller grønt felt","Gratis felt. Ryk frem til et orange eller grønt felt. Hvis det der ledigt, får du det gratis. Ellers skal du betale leje ejeren.", 3, 8);
        chanceCards[8]=new ChanceKortFarve("Ryk lyseblåt","Gratis felt. Ryk frem til et lyseblåt felt. Hvis det der ledigt, får du det gratis. Ellers skal du betale leje ejeren.",5);
        chanceCards[9]=new ChanceAmnistiFeng("Amnesti","Du løslades uden omkostninger. Du har dette kort til du får brug for det.");
        chanceCards[10]=new ChanceRykFremTilFelt("ryk tilStrandpromenaden","Du Rykkes frem til Strandpromenaden.", 23);
        chanceCards[11]=new ChanceOverdragelseskort("Til Katten","Dette kort er givet til Katten. Tag et chancekort mere. Kat: På den næste tur skal du sejle frem til hvilket som helst ledigt felt og købe det. Hvis der ikke er nogen ledige felter, skal du købe et af en anden spiller.","Kat");
        chanceCards[12]=new ChanceOverdragelseskort("Til Hunden","Dette kort er givet til Hunden.Tag et chancekort mere. Hund: På den næste tur skal du sejle frem til hvilket som helst ledigt felt og købe det. Hvis der ikke er nogen ledige felter, skal du købe et af en anden spiller.","Hund");
        chanceCards[13]=new ChanceBanktrans("Fødselsdag","Det er din fødselsdag. Alle giver dig 1 M. Tillyke med fødselsdagen",1,0,2);
        chanceCards[14]=new Chance2Farver("Ryk til Pink eller Mørkeblåt","Gratis felt. Du Rykkes frem til et pink eller mørkeblåt felt. Hvis det der ledigt, får du det gratis. Ellers skal du betale leje ejeren.",4,2);
        chanceCards[15]=new ChanceBanktrans("Alle lektier lavet","Du har lavet alle dine lektier. Modtag 2M fra banken",2,0,1);
        chanceCards[16]=new ChanceKortFarve("Ryk til rødt felt","Gratis felt. du rykkes Ryk frem til et rødt felt. Hvis det der ledigt, får du det gratis. Ellers skal du betale leje ejeren.",1);
        chanceCards[17]=new ChanceRykFremTilFelt("Ryk frem til Skaterparken","Gratis felt. Du Rykkes frem til Skaterparken for at lave det perfekte grind. Hvis ingen ejer den, får du den gratis. Ellers skal du betale leje ejeren.",10);
        chanceCards[18]=new Chance2Farver("Ryk frem til lyseblåt eller rødt","Gratis felt. Du Rykkes frem til et lyseblåt eller rødt felt. Hvis det der ledigt, får du det gratis. Ellers skal du betale leje ejeren.",5,1);
        chanceCards[19]=new Chance2Farver("Ryk frem til brunt eller gult felt","Gratis felt. Du Rykkes frem til et brunt eller gult felt. Hvis det der ledigt, får du det gratis. Ellers skal du betale leje ejeren.",7,6);



 */
    }

    /**
     * Træk et chancekort.
     * @return
     */
    public Chancekort traekEtChanceKort()
    {
        int kort_nr;
        int  kOk=0;

        do {
            kOk=0;

            if (testKortMode==0) {
                kort_nr = (int) (Math.random() * 22);
                //System.out.println("Normalt kort trukket");
            }
            else
            {
                //Et specifikt chancekort skal trækkes for teste dette
                kort_nr=testKortMode;
                testKortMode=0;
                //System.out.println("Testkort trukket");
            }
            //System.out.println("Chancekort nr: "+kort_nr+" trukket");
/*
            if (kort_nr==0|| kort_nr == 5 || kort_nr == 11 || kort_nr == 12 || kort_nr == 9)
            {


                if (chanceCards[kort_nr].getClass().equals(ChanceOverdragelseskort.class))
                {
                    if (chanceCards[kort_nr] instanceof ChanceOverdragelseskort==true)
                    {
                        // Hvis kortet allede er trukket og en anden spiller har det, skal dette træk ignoreres og et nyt kort skal trækkes
                        if(((ChanceOverdragelseskort) chanceCards[kort_nr]).getAktivt()) kOk=11;
                    }

                }
                else if (chanceCards[kort_nr] instanceof ChanceAmnistiFeng)
                {
                    // Hvis kortet allede er trukket og en anden spiller har det, skal dette træk ignoreres og et nyt kort skal trækkes
                    if (((ChanceAmnistiFeng) chanceCards[kort_nr]).getAktivt()) kOk=11;
                }
                else kOk=11;



            }

 */

        } while (kOk > 0);



        return chanceCards[kort_nr];



    }

    /**
     * Spilleren har lige slået og ramt et chancefelt. Et chancekort skal trækkes
     * @param actPlayer: Spillr som har slået.
     * @param players: Spillerliste
     * @param actField: Spillerens position på brættet
     * @param AmountofPlayers: Antallet af spillere
     * @param AmountofSpaces
     * @param gui: GUI
     * @param fields: Liste over felter
     * @return: Bilens position på brættet efter at chancekort er håndteret. det kan samme position som eller en anden.
     */
    public int chanceFieldIsHit(MGUI_Player actPlayer, MGUI_Player[] players, int actField,
                                int AmountofPlayers, int AmountofSpaces, MGUI gui, MGUI_Street[] fields)
    {
        int i, j, bilPos;
        int slut=0;
        String farvCod, modtRolle, valg;
        MGUI_Player playModt;

        bilPos=actField;
        //System.out.println("bilPos "+bilPos);

        do {

            slut=0;

            //System.out.println("Chancekort trækkes");
            Chancekort actKort = traekEtChanceKort();

            gui.showMessage(actKort.getKortInfo());

            if (actKort instanceof Chance2Farver==true)
            {
                if (((Chance2Farver) actKort).getFarve1()== 3 && ((Chance2Farver) actKort).getFarve2()==8)
                   valg = gui.getUserButtonPressed("Vælg om du vil rykke til", "Orange felt", "Grønt felt");
                else if (((Chance2Farver) actKort).getFarve1()==4)
                    valg = gui.getUserButtonPressed("Vælg om du vil rykke til", "Pink felt", "Mørkeblåt felt");
                else if (((Chance2Farver) actKort).getFarve1()==5)
                   valg = gui.getUserButtonPressed("Vælg om du vil rykke til", "Lyseblå felt", "Rødt felt");
                else //if (((Chance2Farver) actKort).getFarve1()==9)
                   valg = gui.getUserButtonPressed("Vælg om du vil rykke til", "Brunt felt", "Gult felt");


                if (valg.equals("Orange felt") || valg.equals ("Pink felt") || valg.equals("Lyseblå felt")|| valg.equals("Brunt felt"))
                {
              //      farvCod=(""+((Chance2Farver) actKort).getFarve1());
                    farvCod=String.valueOf(((Chance2Farver) actKort).getFarve1());
       //             System.out.println("Farve 1 valgt kode "+farvCod);
                }
                else if (valg.equals("Grønt felt") || valg.equals ("Mørkeblåt felt") || valg.equals("Rødt felt")|| valg.equals("Gult felt"))
                {
               //     farvCod=(""+((Chance2Farver) actKort).getFarve2());
                    farvCod=String.valueOf(((Chance2Farver) actKort).getFarve2());
      //              System.out.println("Farve 2 valgt");
                }
                else farvCod="Ingen farve";
                i=bilPos;
                j=0;
  //              System.out.println("Brugt farve kode "+farvCod);
                do
                {
                    i++;
                    j++;
                    if (i>23) i=0;
      //              System.out.println(Base.fields[i].getDescription());

                }while( fields[i].getDescription().equals(farvCod)==false && j < 25);

                bilPos=i;


            }

            if (actKort instanceof ChanceOverdragelseskort==true)
            {
      //          gui.showMessage(actKort.getKortInfo());
     //           actPlayer.setKortModtaget(true);
      //          actPlayer.setActChancekort(actKort);
                modtRolle=((ChanceOverdragelseskort) actKort).getModtager();
      //          System.out.println("Chanceoverdragelse. Modtager: "+modtRolle+" Amount of players: "+AmountofPlayers);
                for (i=0;i<AmountofPlayers;i++)
     //               for (i=0;i<Base.AmountofPlayers;i++)
     //           for (i=0;i<4;i++)
                {
      //              System.out.println("Test player "+players[i].getUserRole());
                    if (players[i].getUserRole().equals(((ChanceOverdragelseskort) actKort).getModtager()))
                    {
                        players[i].setActChancekort((ChanceOverdragelseskort) actKort);
                        players[i].setKortModtaget(true);
                        System.out.println("Chancekort er overdraget til spiller nr. "+i+". Role: "+ players[i].getUserRole());
                    }
                    /*
                    else
                    {
                        System.out.println("Modtager af overdragelseskort er ikke fundet. Ingen spillere har rollen "+((ChanceOverdragelseskort) actKort).getModtager());
                        //System.out.println("Modtager af overdragelseskort er ikke fundet. Ingen spillere har rollen"+((ChanceOverdragelseskort) actKort).getModtager());
                    }

                     */
                }
                slut=11;
                //System.out.println("ChanceOverdragelseskort eksekveret");
            }


            if (actKort instanceof ChanceAmnistiFeng==true) {

                actPlayer.setAmnistkortHaves(true);
                ((ChanceAmnistiFeng) actKort).setAktivt(true);
                ((ChanceAmnistiFeng) actKort).setIndehaver(actPlayer.getNumber());
                //System.out.println("ChanceAmnistiFeng");
            }


            if (actKort instanceof ChanceBanktrans==true)
            {
                //  Betal 2 til banken.
                if (Objects.equals(actKort.getKortNavnavn(),chanceCards[6].getKortNavnavn()))
                    actPlayer.setBalance(actPlayer.getBalance()-2);
                //  Alle Betaler til 1 person.
                else if (Objects.equals(actKort.getKortNavnavn(),chanceCards[13].getKortNavnavn())){
                    for (int k = 0; k < AmountofPlayers; k++)
                        if (players[k].getUserRole()==actPlayer.getUserRole()) // Hvis spilleren er den som har kortet
                            players[k].setBalance(players[k].getBalance()+AmountofPlayers-1);
                        else players[k].setBalance(players[k].getBalance()-1);//    hvis spiller ikke har kortet
                    }
                //  Få 2 fra banken.
                else if (Objects.equals(actKort.getKortNavnavn(),chanceCards[15].getKortNavnavn())){
                    actPlayer.setBalance(actPlayer.getBalance()+2);
                    //System.out.println("+2 has been registered");
                }
            }

            if (actKort instanceof ChanceRyk05==true)
            {
                bilPos+=5;
            }

            if (actKort instanceof ChanceKortFarve==true)
            {

                //System.out.println("ChanceKortFarve");

                farvCod=(""+((ChanceKortFarve) actKort).getFarvekode());
                i=bilPos;
                j=0;
                do
                {
                    i++;
                    j++;
                    if (i>23) i=0;

                }while( fields[i].getDescription().equals(farvCod)==false && j < 25);


                        System.out.println(fields[bilPos+1].getDescription());

                bilPos=i;
            }

           if (actKort instanceof ChanceRykFremTilFelt==true) {


                bilPos = ((ChanceRykFremTilFelt) actKort).getDestinationsFelt();
                //System.out.println("ChanceRykFremTilFelt. bilPos "+ bilPos);

            }
            if (actKort instanceof ChanceRyk1ElChMemere==true) {
                valg = gui.getUserButtonPressed("Vælg", "En chance mere", "Ryk et felt frem");
                if (valg.equals("En chance mere")) {
                   slut=11;
                } else {
                    bilPos = actField + 1;
                }
                //System.out.println("ChanceRyk1ElChMemere");


            }

        }while (slut > 1);
        //System.out.println("While slut");
        //System.out.println("bilPos "+bilPos);
        return bilPos;

    }

    /**
     * Sætter er specifikt chancekort, som skal trækkes næste gang. Bruges til testformål.
     * @param testNr: chancekortnummer
     */
    public void setTestKortMode(int testNr)
    {
        testKortMode=testNr;
    }


}


