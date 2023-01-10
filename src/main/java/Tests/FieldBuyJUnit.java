package Tests;

import TheBoard.Base;
import org.junit.jupiter.api.Test;
import player.MjPlayer;
import Files.FileReference;

import java.io.FileNotFoundException;

import static Files.FileReference.CostToOwnFieldF;
import static GameMechanics.textReaderClass.textRDR;
import static TheBoard.Base.AmountofPlayers;
import static TheBoard.Base.PlayerArray;
import static org.junit.jupiter.api.Assertions.*;

class FieldBuyJUnit {
    @Test           //Tests if field nr 8 subtracts the right amount
            public void shouldSubtractMoney() throws FileNotFoundException {
        int startBalance = 30000;
        int[] costOfFields = new int[Base.fieldNR()];
        for (int i = 0; i < Base.fieldNR(); i++) {
            costOfFields[i] = Integer.parseInt(textRDR(CostToOwnFieldF, String.valueOf(i)));
        }
        assertEquals(28000, startBalance - costOfFields[8]);
    }
}