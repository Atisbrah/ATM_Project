package com.example.messages;

import org.springframework.stereotype.Component;

@Component
public class MessagesLoggingIn {

    public String welcomeMessage() {
        return "Üdvözöljük ATM terminálunknál! " + System.lineSeparator();
    }

    public String loggingInWindowMessage() {
        return chooseOptionMessage() + System.lineSeparator()
                + "1. Bejelentkezés" + System.lineSeparator()
                + "2. Regisztráció"  + System.lineSeparator()
                + "0. Kilépés"  + System.lineSeparator();
    }

    public String chooseOptionMessage() {
        return System.lineSeparator() + "Kérem válasszon az alábbi menüpontok közül: " + System.lineSeparator();
    }

    public String loginMessage() {
        return "Bejelentkezés";
    }

    public String registrationMessage() {
        return "Regisztráció";
    }

    public String usernameMenuMessage() {
        return "1. Felhasználónév: ";
    }

    public String passwordMenuMessage() {
        return "2. Jelszó: ";
    }

    public String usernameRegistrationMenuMessage() {
        return "Kérem adja meg felhasználónevét (Min. 3, Max. 25 karakter hosszú, nem tartalmazhat speciális, ill. ékezetes karaktert.)" + System.lineSeparator() +
                "1. Felhasználónév: ";
    }

    public String passwordRegistrationMenuMessage() {
        return "Kérem adja meg jelszavát (Min. 3, Max. 25 karakter hosszú, nem tartalmazhat speciális, ill. ékezetes karaktert.)" + System.lineSeparator() +
                "2. Jelszó: ";
    }

    public String passwordAgainRegistrationMenuMessage() {
        return "2. Jelszó ismét: ";
    }

    public String passwordDoesNotMatchMessage() {
        return "A két jelszó nem egyezik meg.";
    }

    public String pinDoesNotMatchMessage() {
        return "A két PIN nem egyezik meg.";
    }

    public String notAvailableOptionMessage() {
        return "Nem létező opció.";
    }

    public String firstNameMenuMessage() {
        return "Kérem adja meg keresztnevét (Min. 3, Max. 25 karakter hosszú, nem tartalmazhat speciális karaktert, ékezetes betűket.)" + System.lineSeparator() +
                "1. Keresztnév: ";
    }
    public String lastNameMenuMessage() {
        return "Kérem adja meg vezetéknevét (Min. 3, Max. 25 karakter hosszú, nem tartalmazhat speciális karaktert, ékezetes betűket.)" + System.lineSeparator() +
                "2. Vezetéknév: ";
    }
    public String addressMenuMessage() {
        return "Kérem adja meg a címét (Min. 3, Max. 100 karakter hosszú (pl.: 3000, Hatvan, Valamilyen utca 10.)" + System.lineSeparator() +
                "3. Cím: ";
    }
    public String phoneNumberMenuMessage() {
        return "Kérem adja meg telefonszámát (9 számjegy hosszú, 06-os előhívó szám nélkül)" + System.lineSeparator() +
                "4. Telefonszám: ";
    }
    public String emailMenuMessage() {
        return "Kérem adja meg email címét (Min. 3, Max. 50 karakter hosszú)" + System.lineSeparator() +
                "5. Email: ";
    }
    public String birthDateMenuMessage() {
        return "Kérem adja meg a születési dátumát a minta alapján (formátum: ÉÉÉÉHHNN, pl.: 19910312)" + System.lineSeparator() +
                "6. Születési dátum: ";
    }

    public String pinMenuMessage() {
        return "Kérem adja meg PIN kódját (4 karakter hosszú)" + System.lineSeparator() +
                "1. PIN: ";
    }

    public String pinAgainMenuMessage() {
        return "Kérem adja meg PIN kódját ismét (4 karakter hosszú)" + System.lineSeparator() +
                "1. PIN újra: ";
    }

    public String cardNumberMenuMessage() {
        return "Kérem adja meg bankkártya számát (19 karakter hosszú, pl.: 0000-0000-0000-0000)" + System.lineSeparator() +
                "2. Bankkártya szám: ";
    }

    public String cardCompanyMenuMessage() {
        return "Kérem adja meg a bankkártyát kibocsátó intézmény nevét (Min. 3, Max. 50 karakter hosszú)" + System.lineSeparator() +
                "3. Bankkártyát kibocsátó intézmény neve: ";
    }

    public String notValidInputMessage(int input) {
        return "A(z) " + input + ". mező bevitele nem megfelelő.";
    }

    public String loginDataInputErrorMessage() {
        return "A bejelentkezési adatok beivtele során az alábbi menüpontok nem voltak megfelelően kitöltve: " + System.lineSeparator();
    }

    public String userDataInputErrorMessage() {
        return "A felhasználói adatok beivtele során az alábbi menüpontok nem voltak megfelelően kitöltve: " + System.lineSeparator();
    }

    public String cardDataInputErrorMessage() {
        return "A bankkártya adatok beivtele során az alábbi menüpontok nem voltak megfelelően kitöltve: " + System.lineSeparator();
    }

    public String userDoesNotExistMessage() {
        return "Ez a felhasználónév - jelszó pár még nincs regisztrálva. Kérjük próbálja meg ismét." + System.lineSeparator();
    }
}
