package Tests;

import GameMechanics.Fields;
import TheBoard.Base;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Fields_FindOwnerNumberTest {
    @Test
    public void FindOwnerNum(){
        int[] OwnerList = new int[Base.fieldNR()];
        for (int i = 0; i < Base.fieldNR(); i++) {OwnerList[i] = 0;} // empty array
        int CS1 = 0; // Current Space 1
        int CS2 = 5;
        int CS3 = 4;
        int CS4 = 10;
        OwnerList[CS1]=2; // setting owners
        OwnerList[CS2]=1;
        OwnerList[CS3]=3;
        OwnerList[CS4]=0;
        int OwnerOutput1 = Fields.FindOwnerNumber(OwnerList, CS1); // using method
        int OwnerOutput2 = Fields.FindOwnerNumber(OwnerList, CS2);
        int OwnerOutput3 = Fields.FindOwnerNumber(OwnerList, CS3);
        int OwnerOutput4 = Fields.FindOwnerNumber(OwnerList, CS4);
        int x = 0;                                                //}
        for (int i = 15; i < Base.fieldNR(); i++) {               //}   counts x up for each value !=0
            if (0 != Fields.FindOwnerNumber(OwnerList, i))        //}   from space 15-40.
                x++;}                                             //}
        int OwnerOutput5 = x;
        assertEquals(2,OwnerOutput1); // test result
        assertEquals(1,OwnerOutput2);
        assertEquals(3,OwnerOutput3);
        assertEquals(0,OwnerOutput4);
        assertEquals(0,x);            // tests that the fields from 15-40 are empty
    }
}