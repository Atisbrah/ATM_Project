package com.example.messages;

import com.example.entities.User;
import org.springframework.stereotype.Component;

@Component
public class MessagesOtherOptions {

    public String otherOptionsMessage() {
        return "1. Kártyával kapcsolatos opciók" + System.lineSeparator() +
                "2. Felhasználói adattal kapcsolatos opciók" + System.lineSeparator() +
                "0. Vissza";
    }

    public String cardDataMenuMessage() {
        return "1. Regisztrált kártyák listája" + System.lineSeparator() +
                "2. Új kártya regisztráció" + System.lineSeparator() +
                "3. Kártyaváltás" + System.lineSeparator() +
                "4. Kártya törlése" + System.lineSeparator() +
                "5. Kártya aktiválás" + System.lineSeparator() +
                "6. Tranzakció lista" + System.lineSeparator() +
                "0. Vissza";
    }

    public String dataChangeMenuMessage() {
        return "1. Keresztnév módosítás" + System.lineSeparator() +
                "2. Vezetéknév módosítás" + System.lineSeparator() +
                "3. Cím módosítás" + System.lineSeparator() +
                "4. Telefonszám módosítás" + System.lineSeparator() +
                "5. Email módosítás" + System.lineSeparator() +
                "6. Születési dátum módosítás" + System.lineSeparator() +
                "7. Felhasználónév módosítás" + System.lineSeparator() +
                "0. Vissza";
    }

    public String userDataMenuMessage() {
        return "1. Felhasználói adatok megtekintése" + System.lineSeparator() +
                "2. Felhasználói adat módosítás" + System.lineSeparator() +
                "3. Jelszó módosítás" + System.lineSeparator() +
                "4. Felhasználó törlése" + System.lineSeparator() +
                "0. Vissza";
    }

    public String firstNameChangeMessage() {
        return "Kérem adja meg új keresztnevét (Min. 3, Max. 25 karakter hosszú, nem tartalmazhat speciális, ill. ékezetes karaktert.)" + System.lineSeparator() +
                "Új keresztnév: ";
    }
    public String lastNameChangeMessage() {
        return "Kérem adja meg új vezetéknevét (Min. 3, Max. 25 karakter hosszú, nem tartalmazhat speciális, ill. ékezetes karaktert.)" + System.lineSeparator() +
                "Új vezetéknév: ";
    }
    public String addressChangeMessage() {
        return "Kérem adja meg új címét (Min. 3, Max. 100 karakter hosszú (pl.: 3000, Hatvan, Valamilyen utca 10.)" + System.lineSeparator() +
                "Új cím: ";
    }
    public String phoneChangeMessage() {
        return "Kérem adja meg új telefonszámát (9 számjegy hosszú, 06-os előhívó szám nélkül)" + System.lineSeparator() +
                "Új telefonszám: ";
    }
    public String emailChangeMessage() {
        return "Kérem adja meg új email címét (Min. 3, Max. 50 karakter hosszú)" + System.lineSeparator() +
                "Új email: ";
    }
    public String birthDateChangeMessage() {
        return "Kérem adja meg új születési dátumát a minta alapján (formátum: ÉÉÉÉHHNN, pl.: 19910312)" + System.lineSeparator() +
                "Új születési dátum: ";
    }
    public String userNameChangeMessage() {
        return "(Max. 25 karakter hosszú)" + System.lineSeparator() +
                "Új felhasználónév: ";
    }

    public String wrongInputMessage() {
        return "A megadott adat nem felel meg a követelmény(ek)nek.";
    }

    public String sameDataMessage() {
        return "A frissíteni kívánt adat megegyezik a jelenlegi adattal.";
    }

    public String oldPasswordDoesntMatchMessage() {
        return "Az ön által megadott jelszó nem egyezik meg a felhasználó jelszavával.";
    }

    public String oldPasswordChangeMessage() {
        return "Régi jelszó: ";
    }

    public String newPasswordChangeMessage() {
        return "(Max. 25 karakter hosszú, nem tartalmazhat speciális karaktert.)" +
                System.lineSeparator() + "Új jelszó: ";
    }

    public String newPasswordAgainChangeMessage() {
        return "(Max. 25 karakter hosszú, nem tartalmazhat speciális karaktert.)" +
                System.lineSeparator() + "Új jelszó ismét: ";
    }

    public String oldPinDoesntMatchMessage() {
        return "Az ön által megadott pin kód nem egyezik meg a kártya pin kódjával.";
    }

    public String oldPinChangeMessage() {
        return "Régi pin: ";
    }

    public String newPinChangeMessage() {
        return "(4 karakter hosszú)" + System.lineSeparator() +
                "Új pin: ";
    }

    public String newPinAgainChangeMessage() {
        return "(4 karakter hosszú)" + System.lineSeparator() +
                "Új pin ismét: ";
    }

    public String noOtherCardsMessage() {
        return "Önnek nincs más regisztrált kártyája.";
    }

    public String noCardToDeleteMessage() {
        return "Csak több regisztrált kártya esetén érhető el az opció. ";
    }

    public String askPasswordToDeleteUserMessage() {
        return "Kérem adja meg jelszavát a felhasználói fiók törlésének folytatásához: ";
    }

    public String backToLoginMessage() {
        return "Sikeres művelet. Visszatérés a a bejelentkezéshez. ";
    }

    public String deleteInterruptedMessage() {
        return "Felhasználótörlés megszakítva.";
    }

    public String incorrectPasswordMessage() {
        return "Nem megfelelő jelszó.";
    }

    public String confirmUserDeletionMessage() {
        return "Biztosan törölni szeretné felhasználói fiókját?" +
                "Ezzel minden ön által rögzített adat elvész. Y/N";
    }

    public String showUserDataMessage(User actUser) {
        return "Neve: " + actUser.getFirstname() + ", " + actUser.getLastname() + System.lineSeparator() +
                "Született: " + actUser.getBirthDate() + System.lineSeparator() +
                "Címe: " + actUser.getAddress() + System.lineSeparator() +
                "E-mail címe: " + actUser.getEmail() + System.lineSeparator() +
                "Telefonszáma: " + actUser.getPhone() + System.lineSeparator();
    }

    public String noCommittedTransactionMessage() {
        return "Ezzel a bankkártyával még nincs végrehajtott tranzakció." + System.lineSeparator();
    }
}
