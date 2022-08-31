package com.example.messages;

import com.example.entities.CreditCard;
import com.example.entities.Transaction;
import org.springframework.stereotype.Component;

@Component
public class MessagesUserWindow {

    public String userWindowMenu() {
        return "1. Egyenleg lekérdezése " + System.lineSeparator() +
                "2. Pénzfelvétel " + System.lineSeparator() +
                "3. Pénzletétel " + System.lineSeparator() +
                "4. PIN csere " + System.lineSeparator() +
                "5. Egyéb művelet " + System.lineSeparator() +
                "0. Vissza " + System.lineSeparator();
    }

    public String cardNotActivatedWarnMessage() {
        return "Figyelem, a jelenlegi kártyája még nincs aktiválva. Kérjük aktiválja a kártya opciókon belül. " + System.lineSeparator();

    }

    public String actualBalanceMessage(Double amount) {
        return "Az ön aktuális egyenlege: " + amount + " Ft.";
    }

    public String moneyWithdrawMenuMessage() {
        return "Kérem válassza ki a levenni kívánt összeget: " + System.lineSeparator() +
                "1. 1000" + System.lineSeparator() +
                "2. 5000" + System.lineSeparator() +
                "3. 10000" + System.lineSeparator() +
                "4. 25000" + System.lineSeparator() +
                "5. 50000" + System.lineSeparator() +
                "6. 100000" + System.lineSeparator() +
                "7. Más összeg" + System.lineSeparator() +
                "0. Vissza";
    }

    public String withdrawCannotBePerformedMessage() {
        return "A műveletet nem lehetett végrehajtani. A hiba lehetséges okai:" + System.lineSeparator() +
                "Nem megfelelő PIN kód" + System.lineSeparator() +
                "Nem megfelelő számformátum" + System.lineSeparator() +
                "Nincs elég fedezet" + System.lineSeparator() +
                "A kártya nincs aktiválva";
    }

    public String moneyDepositMenuMessage() {
        return "Kérem adja meg a befizetendő összeget: " + System.lineSeparator();
    }

    public String operationSuccessfulMessage() {
        return System.lineSeparator() + "A kért művelet sikeresen befejeződött." + System.lineSeparator();
    }

    public String cardAlreadyActivatedMessage() {
        return "Ez a kártya már aktiválva van.";
    }

    public String logOutMessage() {
        return "Kijelentkezés.";
    }

    public String goodByeMessage() {
        return "Köszönjük, hogy az ATM terminálunkat használta. Viszont látásra!";
    }

    public String getPinForCreditCardMessage() {
        return "Kérem adja meg a kártyához tartozó pin kódot.";
    }

    public String inputCustomNumberMessage() {
        return "Kérem adja meg a felvenni kívánt összeget: ";
    }

    public String depositCannotBePerformedMessage() {
        return "A műveletet nem lehetett végrehajtani. A hiba lehetséges okai:" + System.lineSeparator() +
                "Nem megfelelő PIN kód" + System.lineSeparator() +
                "Nem megfelelő számformátum" + System.lineSeparator() +
                "A kártya nincs aktiválva.";
    }

    public String creditCardToStringMessage(CreditCard creditCard, int placeInList) {
    return  "|-" + placeInList + ". Bankkártya: " +
            "-| |-Kártya id: " + creditCard.getCardId() +
            "-| |-Kártya szám: " + creditCard.getCardNumber() +
            "-| |-Kártyát kibocsátó bank: " + creditCard.getCreditCardCompany() +
            "-| |-Kártya egyenlege: " + creditCard.getBalance() +
            " Ft.-| |-Kártya PIN kódja: " + creditCard.getPin() +
            "-| |-Kártya aktiválás státusza: " + (creditCard.isActivated() ? " Aktiválva." : " Nincs aktiválva.") + "-|";
    }

    public String transactionToStringMessage(Transaction transaction, int placeInList) {
        return  "|-" + placeInList + ". Tranzakció: " +
                "-| |-Tranzakció id: " + transaction.getTransactionId() +
                "-| |-Tranzakció időpontja: " + transaction.getTransactionDate() +
                "-| |-Tranzakció összege: " + transaction.getAmount() +
                " Ft.-| |-Tranzakció költsége: " + transaction.getTransactionFee() + " Ft.-|";
    }
}
