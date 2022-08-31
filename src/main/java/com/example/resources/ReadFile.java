package com.example.resources;

import com.example.ATMMachine.DataOperations;
import com.example.entities.CreditCard;
import com.example.entities.Login;
import com.example.entities.User;
import com.example.service.ATMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class ReadFile {

    private ATMService atmService;
    private DataOperations dataOperations;

    private final String USERFILEPATH = "C:\\Users\\User\\IdeaProjects\\spring_test\\src\\files\\users.csv";
    private final String LOGINFILEPATH = "C:\\Users\\User\\IdeaProjects\\spring_test\\src\\files\\logins.csv";
    private final String CREDITCARDFILEPATH = "C:\\Users\\User\\IdeaProjects\\spring_test\\src\\files\\creditcards.csv";

    @Autowired
    public ReadFile(ATMService atmService, DataOperations dataOperations) {
        this.atmService = atmService;
        this.dataOperations = dataOperations;
    }

    public void fillDatabaseWithInitialData() {

        //törölni a teljes adatbázist
        //atmService.deleteAllRecordsFromAllRepos();

        if (atmService.areAllTablesEmpty()) {

            List<User> users = getUserFileAsList();
            List<Login> logins = getLoginFileAsList();
            List<CreditCard> creditCards = getCreditCardFileAsList();

            users.forEach(System.out::println);
            logins.forEach(System.out::println);
            creditCards.forEach(System.out::println);

            for (int i = 0; i < users.size(); ++i) {
                atmService.registerDataToDatabase(users.get(i), logins.get(i), creditCards.get(i));
            }

        }
    }

    private List<User> getUserFileAsList() {
        List<User> users = new ArrayList<>();

        try (Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(USERFILEPATH), StandardCharsets.ISO_8859_1)))) {

            scanner.useDelimiter(";|\\r\\n");
            scanner.nextLine();

            while (scanner.hasNext()) {
                User user = new User();
                user.setFirstname(scanner.next());
                user.setLastname(scanner.next());
                user.setAddress(dataOperations.formatAddress(scanner.next()));
                user.setPhone(scanner.nextInt());
                user.setEmail(scanner.next());
                user.setBirthDate(LocalDate.parse(dataOperations.formatDate(scanner.next())));
                users.add(user);
            }

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }


        return users;
    }

    private List<Login> getLoginFileAsList() {
        List<Login> logins = new ArrayList<>();

        try (Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(LOGINFILEPATH), StandardCharsets.ISO_8859_1)))) {

            scanner.useDelimiter(";|\\r\\n");
            scanner.nextLine();

            while (scanner.hasNext()) {
                Login login = new Login();
                login.setUsername(scanner.next());
                login.setPassword(scanner.next());
                logins.add(login);
            }

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }

        return logins;
    }

    private List<CreditCard> getCreditCardFileAsList() {
        List<CreditCard> creditCards = new ArrayList<>();
        try (Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(CREDITCARDFILEPATH), StandardCharsets.ISO_8859_1)))) {

            scanner.useDelimiter(";|\\r\\n");
            scanner.nextLine();

            while (scanner.hasNext()) {
                CreditCard creditCard = new CreditCard();
                creditCard.setPin(scanner.nextInt());
                creditCard.setCardNumber(scanner.next());
                creditCard.setCreditCardCompany(scanner.next());
                creditCards.add(creditCard);
            }

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }

        return creditCards;
    }
}
