package com.example.ATMMachine;

import com.example.entities.CreditCard;
import com.example.entities.Login;
import com.example.entities.Transaction;
import com.example.entities.User;
import com.example.messages.MessagesOtherOptions;
import com.example.messages.MessagesUserWindow;
import com.example.service.ATMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class DataOperations {

    private ATMService atmService;
    private MessagesOtherOptions messagesOtherOptions;
    private MessagesUserWindow messagesUserWindow;

    private Scanner scanner = new Scanner(System.in);

    private final int INPUTMINLENGTH = 3;
    private final int USERNAMELENGTH = 25;
    private final int PASSWORDLENGTH = 25;

    private final int FIRSTNAMELENGTH = 25;
    private final int LASTNAMELENGTH = 25;
    private final int ADDRESSLENGTH = 100;
    private final int PHONENUMBERLENGTH = 9;
    private final int EMAILLENGTH = 50;
    private final int BIRTHDATELENGTH = 8;

    private final int PINCODELENGTH = 4;
    private final int CARDNUMBERLENGTH = 19;
    private final int CARDCOMPANYLENGTH = 50;

    private final double TRANSACTIONFEEINPERCENT = 1;

    @Autowired
    public DataOperations(@Lazy ATMService atmService, MessagesOtherOptions messagesOtherOptions, MessagesUserWindow messagesUserWindow) {
        this.atmService = atmService;
        this.messagesOtherOptions = messagesOtherOptions;
        this.messagesUserWindow = messagesUserWindow;
    }

    public DataOperations() {

    }

    public String formatDate(String date) {
        return date.replaceAll("\\.", "-");
    }

    public String formatAddress(String address) {
        return address.replaceAll("_", " ");
    }

    public boolean isAValidInput(String inputInString, int upperBound, int lowerBound) {
        return stringConvertableToInteger(inputInString) && isNumberBetweenValues(Integer.parseInt(inputInString), upperBound, lowerBound);
    }

    private boolean isNumberBetweenValues(int inputNumber, int upperBound, int lowerBound) {
        return inputNumber <= upperBound && inputNumber >= lowerBound;
    }

    public boolean stringConvertableToInteger(String inputString) {
        try {
            Integer.parseInt(inputString);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public boolean stringListContainNullValue(List<String> info) {
        return info.contains(null);
    }

    public boolean usernamePasswordPairIsValid(String username, String password) {
        List<Login> loginList = atmService.allLoginAsList();
        for (Login login : loginList) {
            if (login.getUsername().equals(username) && login.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public long getIdOfUsername(String username) {
       List<Login> loginList = atmService.allLoginAsList();

        for (Login login : loginList) {
            if (login.getUsername().equals(username)) {
                return login.getUserId();
            }
        }
        return 0L;
    }

    public boolean canRegisterUsername(String username) {
        return username.length() >= INPUTMINLENGTH && username.length() <= USERNAMELENGTH &&
                        isUniqueUsername(username) && doesntContainsSpecialCharacter(username);
    }

    public boolean isUniqueUsername(String username) {
        List<Login> loginList = atmService.allLoginAsList();
        long count = loginList.stream().filter(x -> x.getUsername().equals(username)).count();
        return count == 0;
    }

    public boolean canRegisterPassword(String password, String passwordAgain) {
        return password.length() >= INPUTMINLENGTH && password.length() <= PASSWORDLENGTH &&
                    password.equals(passwordAgain) && doesntContainsSpecialCharacter(password);
    }

    public boolean doesntContainsSpecialCharacter(String input) {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(input);
        return !matcher.find();
    }

    public boolean canRegisterFirstName(String firstname) {
        return firstname.length() >= INPUTMINLENGTH && firstname.length() <= FIRSTNAMELENGTH &&
                doesntContainsSpecialCharacter(firstname);
    }

    public boolean canRegisterLastName(String lastname) {
        return lastname.length() >= INPUTMINLENGTH && lastname.length() <= LASTNAMELENGTH &&
                doesntContainsSpecialCharacter(lastname);
    }

    public boolean canRegisterAddress(String address) {
        return address.length() >= INPUTMINLENGTH && address.length() <= ADDRESSLENGTH &&
                isUniqueAddress(address);
    }

    private boolean isUniqueAddress(String address) {
        List<User> userList = atmService.allUserAsList();
        long count = userList.stream().filter(x -> x.getAddress().equals(address)).count();
        return count == 0;
    }

    public boolean canRegisterPhoneNumber(String phoneNumberAsString) {
        return phoneNumberAsString.length() == PHONENUMBERLENGTH || phoneNumberAsString.length() == PHONENUMBERLENGTH - 1 &&
                isUniquePhoneNumber(Integer.parseInt(phoneNumberAsString)) && convertableToNumber(phoneNumberAsString);
    }

    private boolean isUniquePhoneNumber(int phoneNumber) {
        List<User> userList = atmService.allUserAsList();
        long count = userList.stream().filter(x -> (x.getPhone() == phoneNumber)).count();
        return count == 0;
    }

    public boolean convertableToNumber(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public boolean canRegisterEmail(String email) {
        return email.length() >= INPUTMINLENGTH && email.length() <= EMAILLENGTH &&
                           isAValidEmail(email) && isUniqueEmail(email);
    }

    private boolean isUniqueEmail(String email) {
        List<User> userList = atmService.allUserAsList();
        long count = userList.stream().filter(x -> x.getEmail().equals(email)).count();
        return count == 0;
    }

    public boolean isAValidEmail(String email) {
        String emailRegex = "^[a-zA-Z\\d_+&*-]+(?:\\." +
                "[a-zA-Z\\d_+&*-]+)*@" +
                "(?:[a-zA-Z\\d-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public boolean canRegisterBirthDate(int birthDate) {
        return String.valueOf(birthDate).length() == BIRTHDATELENGTH && isAValidDate(birthDate);
    }

    public String formatDateToString(int birthDate) {
        StringBuilder sb = new StringBuilder();
        String date = Integer.toString(birthDate);
        return (sb.append(date, 0, 4).append("-")
                .append(date, 4, 6).append("-")
                .append(date, 6, 8)).toString();
    }

    public boolean isAValidDate(int birthDate) {
        String formatString = "yyyy-MM-dd";
        String birthDateAsFormattedString = formatDateToString(birthDate);
        try {
            SimpleDateFormat format = new SimpleDateFormat(formatString);
            format.setLenient(false);
            format.parse(birthDateAsFormattedString);
        } catch (ParseException | IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public boolean canRegisterPin(String pin, String pinAgain) {
        return pin.length() == PINCODELENGTH && pin.equals(pinAgain);
    }

    public boolean canRegisterCardNumber(String cardNumber) {
        return cardNumber.length() == CARDNUMBERLENGTH && isCardNumberConvertableToLong(cardNumber) &&
                isCardNumberUnique(cardNumber);
    }

    public boolean isCardNumberUnique(String cardNumber) {
        List<CreditCard> creditCardList = atmService.allCreditCardAsList();
        long count = creditCardList.stream().filter(x -> x.getCardNumber().equals(cardNumber)).count();
        return count == 0;
    }

    public boolean isCardNumberConvertableToLong(String cardNumber) {
        String formattedCardNumber = cardNumber.replaceAll("-", "");
        try {
            Long.parseLong(formattedCardNumber);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public boolean canRegisterCardCompany(String cardCompany) {
        return cardCompany.length() >= INPUTMINLENGTH && cardCompany.length() <= CARDCOMPANYLENGTH;
    }

    public boolean twoValuesMatch(String oldData, String newData) {
        return oldData.equals(newData);
    }

    public int getLocalDateYearMonthDayAsInt(LocalDate date) {
        StringBuffer sb = new StringBuffer();
        sb.append(String.valueOf(date.getYear()));
        sb.append(String.valueOf(date.getMonthValue()));
        sb.append(String.valueOf(date.getDayOfMonth()));
        return Integer.parseInt(sb.toString());
    }

    public Double getTransactionFee(Double amount) {
        return TRANSACTIONFEEINPERCENT / 100 * amount;
    }

    public boolean isAPositiveNumber(Double amount) {
        return amount >= 0;
    }

    public boolean enoughCoverage(Double actBalance, Double amount) {
        return actBalance >= amount;
    }

    public Double getTotalTransactionCost(Transaction transaction) {
        Double transactionAmount = -transaction.getAmount();
        Double transactionFee = transaction.getTransactionFee();
        return transactionAmount + transactionFee;
    }

    public boolean isInRange(int input, int upperBound, int lowerBound) {
        return input >= lowerBound && input <= upperBound;
    }

    public boolean thereAreMoreCardsToChoose(List<CreditCard> creditCards) {
        return creditCards.size() > 1;
    }

    public boolean passwordMatches(User user) {
        System.out.println(messagesOtherOptions.askPasswordToDeleteUserMessage());
        String password = scanner.next();
        return password.equals(user.getLogin().getPassword());
    }

    public boolean confirmedUserDeletion() {
        System.out.println(messagesOtherOptions.confirmUserDeletionMessage());
        String chosenOption = getConfirmationForUserDeletion().toUpperCase();
        return (chosenOption.equals("Y"));
    }

    public String getConfirmationForUserDeletion() {
        boolean exit = false;
        String input = null;
        while(!exit) {
            input = scanner.next();
            if(input.equals("Y") || input.equals("N")) {
                exit = true;
            }
        }
        return input;
    }

    public boolean fulfillableWithdrawTransaction(CreditCard creditCard, Double totalCost) {
        return gotAccessForTransaction(creditCard) &&
                isAPositiveNumber(totalCost) &&
                enoughCoverage(creditCard.getBalance(), totalCost) &&
                isCardActivated(creditCard);
    }

    public boolean gotAccessForTransaction(CreditCard creditCard) {
        System.out.println(messagesUserWindow.getPinForCreditCardMessage());
        String pin = scanner.next();
        return inputPinMatchesWithCreditCardPin(creditCard, pin);
    }

    public boolean inputPinMatchesWithCreditCardPin(CreditCard creditCard, String inputPin) {
        return inputPin.equals(String.valueOf(creditCard.getPin()));
    }

    public boolean fulfillableDepositTransaction(CreditCard creditCard, Double amount) {
        return gotAccessForTransaction(creditCard) &&
                isAPositiveNumber(amount) &&
                creditCard.isActivated();
    }

    public boolean isCardActivated(CreditCard creditCard) {
        return creditCard.isActivated();
    }
}
