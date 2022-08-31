package com.example.ATMMachine;

import com.example.entities.CreditCard;
import com.example.entities.Login;
import com.example.entities.Transaction;
import com.example.entities.User;
import com.example.messages.MessagesLoggingIn;
import com.example.messages.MessagesOtherOptions;
import com.example.messages.MessagesUserWindow;
import com.example.service.ATMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@Component
public class MachineActions {

    private ATMService atmService;
    private DataOperations dataOperations;
    private MessagesOtherOptions messagesOtherOptions;
    private MessagesUserWindow messagesUserWindow;
    private MessagesLoggingIn messagesLoggingIn;

    private Scanner scanner = new Scanner(System.in);

    @Autowired
    public MachineActions(ATMService atmService, DataOperations dataOperations, MessagesOtherOptions messagesOtherOptions,
                          MessagesLoggingIn messagesLoggingIn, MessagesUserWindow messagesUserWindow) {
        this.atmService = atmService;
        this.dataOperations = dataOperations;
        this.messagesOtherOptions = messagesOtherOptions;
        this.messagesLoggingIn = messagesLoggingIn;
        this.messagesUserWindow = messagesUserWindow;
    }

    public int getInputBetweenMenuRanges(int upperBound, int lowerBound) {
        while (true) {
            String inputInString = scanner.next();
            if (dataOperations.isAValidInput(inputInString, upperBound, lowerBound))
                return Integer.parseInt(inputInString);
            System.out.println(messagesLoggingIn.notAvailableOptionMessage());
        }
    }

    public Double getCustomNumberInputForTransaction() {
        while (true) {
            String inputInString = scanner.next();
            if (dataOperations.stringConvertableToInteger(inputInString))
                return Double.parseDouble(inputInString);
            System.out.println(messagesLoggingIn.notAvailableOptionMessage());
        }
    }

    public long login() {
        List<String> loginInfo = getLoginData();
        if (dataOperations.stringListContainNullValue(loginInfo)) {
            System.out.println(messagesLoggingIn.loginDataInputErrorMessage());
            errorInGivenLineMessage(loginInfo);
            return 0L;
        } else {
            return dataOperations.getIdOfUsername(loginInfo.get(0));
        }
    }

    public void registration() {
        List<String> loginRegistrationData = getLoginRegistrationData();

        if (dataOperations.stringListContainNullValue(loginRegistrationData)) {
            writeLoginRegistrationErrorsToScreen(loginRegistrationData);
            return;
        }

        List<String> userRegistrationData = getUserRegistrationData();

        if (dataOperations.stringListContainNullValue(userRegistrationData)) {
            writeUserRegistrationErrorsToScreen(userRegistrationData);
            return;
        }

        List<String> creditCardRegistrationData = getCreditCardRegistrationData();

        if (dataOperations.stringListContainNullValue(creditCardRegistrationData)) {
            writeCreditCardRegistrationErrorsToScreen(creditCardRegistrationData);
            return;
        }

        atmService.registerDataToDatabase(
                createUserClassFromInput(userRegistrationData),
                createLoginClassFromInput(loginRegistrationData),
                createCreditCardClassFromInput(creditCardRegistrationData)
        );
    }

    public void newCardRegistration(User user) {
        List<String> creditCardRegistrationData = getCreditCardRegistrationData();
        if (dataOperations.stringListContainNullValue(creditCardRegistrationData)) {
            writeCreditCardRegistrationErrorsToScreen(creditCardRegistrationData);
        } else {
            atmService.registerCardToUser(user, createCreditCardClassFromInput(creditCardRegistrationData));
            System.out.println(messagesUserWindow.operationSuccessfulMessage());
        }

    }

    private void writeLoginRegistrationErrorsToScreen(List<String> loginRegistrationData) {
        if (loginRegistrationData.contains(null))
            System.out.println(messagesLoggingIn.loginDataInputErrorMessage());
        errorInGivenLineMessage(loginRegistrationData);
    }

    private void writeUserRegistrationErrorsToScreen(List<String> userRegistrationData) {
        if (userRegistrationData.contains(null))
            System.out.println(messagesLoggingIn.userDataInputErrorMessage());
        errorInGivenLineMessage(userRegistrationData);
    }

    private void writeCreditCardRegistrationErrorsToScreen(List<String> creditCardRegistrationData) {
        if (creditCardRegistrationData.contains(null))
            System.out.println(messagesLoggingIn.cardDataInputErrorMessage());
        errorInGivenLineMessage(creditCardRegistrationData);
    }

    private void errorInGivenLineMessage(List<String> inputList) {
        for (int i = 0; i < inputList.size(); ++i) {
            if (inputList.get(i) == null) {
                System.out.println(messagesLoggingIn.notValidInputMessage(i + 1));
            }
        }
    }

    private List<String> getLoginData() {
        List<String> loginInfo = new ArrayList<>();
        loginInfo.add(getUsernameForLoginFromInput());
        loginInfo.add(getPasswordForLoginFromInput(loginInfo.get(0)));
        return loginInfo;
    }

    private List<String> getLoginRegistrationData() {
        List<String> loginRegistrationInfo = new ArrayList<>();
        loginRegistrationInfo.add(getUsernameFromInput());
        loginRegistrationInfo.add(getPasswordFromInput());
        return loginRegistrationInfo;
    }

    private List<String> getUserRegistrationData() {
        List<String> userRegistrationInfo = new ArrayList<>();
        userRegistrationInfo.add(getFirstnameFromInput());
        userRegistrationInfo.add(getLastNameFromInput());
        userRegistrationInfo.add(getAddressFromInput());
        userRegistrationInfo.add(getPhoneNumberFromInput());
        userRegistrationInfo.add(getEmailFromInput());
        userRegistrationInfo.add(getBirthDateFromInput());
        return userRegistrationInfo;
    }

    private List<String> getCreditCardRegistrationData() {
        List<String> creditCardRegistrationInfo = new ArrayList<>();
        creditCardRegistrationInfo.add(getPinFromInput());
        creditCardRegistrationInfo.add(getCardNumberFromInput());
        creditCardRegistrationInfo.add(getCardCompanyFromInput());
        return creditCardRegistrationInfo;
    }

    private Login createLoginClassFromInput(List<String> loginInfo) {
        Login login = new Login();
        login.setUsername(loginInfo.get(0));
        login.setPassword(loginInfo.get(1));
        return login;
    }

    private User createUserClassFromInput(List<String> userInfo) {
        User user = new User();
        user.setFirstname(userInfo.get(0));
        user.setLastname(userInfo.get(1));
        user.setAddress(userInfo.get(2));
        user.setPhone(Integer.parseInt(userInfo.get(3)));
        user.setEmail(userInfo.get(4));
        user.setBirthDate(LocalDate.parse(userInfo.get(5)));
        return user;
    }

    private CreditCard createCreditCardClassFromInput(List<String> creditCardInfo) {
        CreditCard creditCard = new CreditCard();
        creditCard.setPin(Integer.parseInt(creditCardInfo.get(0)));
        creditCard.setCardNumber(creditCardInfo.get(1));
        creditCard.setCreditCardCompany(creditCardInfo.get(2));
        return creditCard;
    }

    private String getUsernameForLoginFromInput() {
        System.out.println(messagesLoggingIn.usernameMenuMessage());
        String username = scanner.next();
        return !dataOperations.isUniqueUsername(username) ? username : null;
    }

    private String getPasswordForLoginFromInput(String username) {
        System.out.println(messagesLoggingIn.passwordMenuMessage());
        String password = scanner.next();
        return dataOperations.usernamePasswordPairIsValid(username, password) ? password : null;
    }

    private String getUsernameFromInput() {
        System.out.println(messagesLoggingIn.usernameRegistrationMenuMessage());
        String username = scanner.next();
        return dataOperations.canRegisterUsername(username) ? username : null;
    }

    private String getPasswordFromInput() {
        System.out.println(messagesLoggingIn.passwordRegistrationMenuMessage());
        String password = scanner.next();
        System.out.println(messagesLoggingIn.passwordAgainRegistrationMenuMessage());
        String passwordAgain = scanner.next();
        return dataOperations.canRegisterPassword(password, passwordAgain) ? password : null;
    }

    private String getFirstnameFromInput() {
        System.out.println(messagesLoggingIn.firstNameMenuMessage());
        String firstname = scanner.next();
        return dataOperations.canRegisterFirstName(firstname) ? firstname : null;
    }

    private String getLastNameFromInput() {
        System.out.println(messagesLoggingIn.lastNameMenuMessage());
        String lastname = scanner.next();
        return dataOperations.canRegisterLastName(lastname) ? lastname : null;
    }

    private String getAddressFromInput() {
        System.out.println(messagesLoggingIn.addressMenuMessage());
        scanner = new Scanner(System.in);
        String address = scanner.nextLine();
        return dataOperations.canRegisterAddress(address) ? address : null;
    }

    private String getPhoneNumberFromInput() {
        System.out.println(messagesLoggingIn.phoneNumberMenuMessage());
        String phoneNumberAsString = scanner.next();
        return dataOperations.canRegisterPhoneNumber(phoneNumberAsString) ? phoneNumberAsString : null;
    }

    private String getEmailFromInput() {
        System.out.println(messagesLoggingIn.emailMenuMessage());
        String email = scanner.next();
        return dataOperations.canRegisterEmail(email) ? email : null;
    }

    private String getBirthDateFromInput() {
        System.out.println(messagesLoggingIn.birthDateMenuMessage());
        int birthDate = scanner.nextInt();
        return dataOperations.canRegisterBirthDate(birthDate) ? dataOperations.formatDateToString(birthDate) : null;
    }

    private String getPinFromInput() {
        System.out.println(messagesLoggingIn.pinMenuMessage());
        String pin = scanner.next();
        System.out.println(messagesLoggingIn.pinAgainMenuMessage());
        String pinAgain = scanner.next();
        return dataOperations.canRegisterPin(pin, pinAgain) ? pin : null;
    }

    private String getCardNumberFromInput() {
        System.out.println(messagesLoggingIn.cardNumberMenuMessage());
        String cardNumber = scanner.next();
        return dataOperations.canRegisterCardNumber(cardNumber) ? cardNumber : null;
    }

    private String getCardCompanyFromInput() {
        System.out.println(messagesLoggingIn.cardCompanyMenuMessage());
        scanner = new Scanner(System.in);
        String cardCompany = scanner.nextLine();
        return dataOperations.canRegisterCardCompany(cardCompany) ? cardCompany : null;
    }

    public void moneyWithdrawCustomNumberOption(CreditCard creditCard) {
        System.out.println(messagesUserWindow.inputCustomNumberMessage());
        Double amount = scanner.nextDouble();
        if (amount == 0.0) {
            return;
        }
        moneyWithdrawOption(creditCard, amount);
    }

    public void moneyWithdrawOption(CreditCard creditCard, Double amount) {
        Transaction transaction = createWithdrawTransaction(amount);
        Double totalCost = dataOperations.getTotalTransactionCost(transaction);
        if (dataOperations.fulfillableWithdrawTransaction(creditCard, totalCost)) {
            atmService.saveTransaction(creditCard, transaction);
            System.out.println(messagesUserWindow.operationSuccessfulMessage());
            return;
        }
        System.out.println(messagesUserWindow.withdrawCannotBePerformedMessage());
    }

    public Transaction createWithdrawTransaction(Double amount) {
        Transaction transaction = new Transaction();
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setAmount(-amount);
        transaction.setTransactionFee(dataOperations.getTransactionFee(amount));
        return transaction;
    }

    public void moneyDepositOption(CreditCard creditCard, Double amount) {
        Transaction transaction = createDepositTransaction(amount);
        if (dataOperations.fulfillableDepositTransaction(creditCard, amount)) {
            atmService.saveTransaction(creditCard, transaction);
            System.out.println(messagesUserWindow.operationSuccessfulMessage());
            return;
        }
        System.out.println(messagesUserWindow.depositCannotBePerformedMessage());
    }

    public Transaction createDepositTransaction(Double amount) {
        Transaction transaction = new Transaction();
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setAmount(amount);
        transaction.setTransactionFee(dataOperations.getTransactionFee(amount));
        return transaction;
    }

    public void changeUsersFirstname(User user) {
        System.out.println(messagesOtherOptions.firstNameChangeMessage());
        String actFirstname = user.getFirstname();
        String newFirstname = scanner.next();

        if (dataOperations.twoValuesMatch(actFirstname, newFirstname)) {
            System.out.println(messagesOtherOptions.sameDataMessage());
            return;
        } else if (dataOperations.canRegisterFirstName(newFirstname)) {
            atmService.changeUserFirstname(user, newFirstname);
            System.out.println(messagesUserWindow.operationSuccessfulMessage());
            return;
        }
        System.out.println(messagesOtherOptions.wrongInputMessage());
    }

    public void changeUsersLastname(User user) {
        System.out.println(messagesOtherOptions.lastNameChangeMessage());
        String actLastname = user.getLastname();
        String newLastname = scanner.next();

        if (dataOperations.twoValuesMatch(actLastname, newLastname)) {
            System.out.println(messagesOtherOptions.sameDataMessage());
            return;
        } else if (dataOperations.canRegisterLastName(newLastname)) {
            atmService.changeUserLastname(user, newLastname);
            System.out.println(messagesUserWindow.operationSuccessfulMessage());
            return;
        }
        System.out.println(messagesOtherOptions.wrongInputMessage());
    }

    public void changeUsersAddress(User user) {
        System.out.println(messagesOtherOptions.addressChangeMessage());
        String actAddress = user.getAddress();
        scanner.nextLine();
        String newAddress = scanner.nextLine();

        if (dataOperations.twoValuesMatch(actAddress, newAddress)) {
            System.out.println(messagesOtherOptions.sameDataMessage());
            return;
        } else if (dataOperations.canRegisterAddress(newAddress)) {
            atmService.changeUserAddress(user, newAddress);
            System.out.println(messagesUserWindow.operationSuccessfulMessage());
            return;
        }
        System.out.println(messagesOtherOptions.wrongInputMessage());
    }

    public void changeUsersPhone(User user) {
        System.out.println(messagesOtherOptions.phoneChangeMessage());
        int actPhone = user.getPhone();
        String newPhone = scanner.next();

        if (dataOperations.twoValuesMatch(String.valueOf(actPhone), String.valueOf(newPhone))) {
            System.out.println(messagesOtherOptions.sameDataMessage());
            return;
        } else if (dataOperations.canRegisterPhoneNumber(newPhone)) {
            atmService.changeUserPhone(user, Integer.parseInt(newPhone));
            System.out.println(messagesUserWindow.operationSuccessfulMessage());
            return;
        }
        System.out.println(messagesOtherOptions.wrongInputMessage());
    }

    public void changeUsersEmail(User user) {
        System.out.println(messagesOtherOptions.emailChangeMessage());
        String actEmail = user.getEmail();
        String newEmail = scanner.next();

        if (dataOperations.twoValuesMatch(actEmail, newEmail)) {
            System.out.println(messagesOtherOptions.sameDataMessage());
            return;
        } else if (dataOperations.canRegisterEmail(newEmail)) {
            atmService.changeUserEmail(user, newEmail);
            System.out.println(messagesUserWindow.operationSuccessfulMessage());
            return;
        }
        System.out.println(messagesOtherOptions.wrongInputMessage());
    }

    public void changeUsersBirthDate(User user) {
        System.out.println(messagesOtherOptions.birthDateChangeMessage());
        int actBirthDate = dataOperations.getLocalDateYearMonthDayAsInt(user.getBirthDate());
        int newBirthDate = scanner.nextInt();

        if (dataOperations.twoValuesMatch(String.valueOf(actBirthDate), String.valueOf(newBirthDate))) {
            System.out.println(messagesOtherOptions.sameDataMessage());
            return;
        } else if (dataOperations.canRegisterBirthDate(newBirthDate)) {
            LocalDate date = LocalDate.parse(dataOperations.formatDateToString(newBirthDate));
            atmService.changeUserBirthDate(user, date);
            System.out.println(messagesUserWindow.operationSuccessfulMessage());
            return;
        }
        System.out.println(messagesOtherOptions.wrongInputMessage());
    }

    public void changeUsersLoginUsername(User user) {
        System.out.println(messagesOtherOptions.userNameChangeMessage());
        String actUsername = user.getLogin().getUsername();
        String newUsername = scanner.next();

        if (dataOperations.twoValuesMatch(actUsername, newUsername)) {
            System.out.println(messagesOtherOptions.sameDataMessage());
            return;
        } else if (dataOperations.canRegisterUsername(newUsername)) {
            atmService.changeUserLoginUsername(user, newUsername);
            System.out.println(messagesUserWindow.operationSuccessfulMessage());
            return;
        }
        System.out.println(messagesOtherOptions.wrongInputMessage());
    }

    public void changeUsersLoginPassword(User user) {
        String actPassword = user.getLogin().getPassword();
        System.out.println(messagesOtherOptions.oldPasswordChangeMessage());
        String actPasswordFromInput = scanner.next();

        if (dataOperations.twoValuesMatch(actPassword, actPasswordFromInput)) {

            System.out.println(messagesOtherOptions.newPasswordChangeMessage());
            String newPassword = scanner.next();

            System.out.println(messagesOtherOptions.newPasswordAgainChangeMessage());
            String newPasswordAgain = scanner.next();

            if (dataOperations.canRegisterPassword(newPassword, newPasswordAgain)) {
                atmService.changeUserLoginPassword(user, newPassword);
                System.out.println(messagesUserWindow.operationSuccessfulMessage());
                return;
            }

            System.out.println(messagesLoggingIn.passwordDoesNotMatchMessage());
            return;
        }

        System.out.println(messagesOtherOptions.oldPasswordDoesntMatchMessage());

    }

    public void changeUsersCardPin(CreditCard actCreditCard) {
        Integer actPin = actCreditCard.getPin();
        System.out.println(messagesOtherOptions.oldPinChangeMessage());
        String actPinFromInput = scanner.next();

        if (dataOperations.twoValuesMatch(String.valueOf(actPin), actPinFromInput)) {

            System.out.println(messagesOtherOptions.newPinChangeMessage());
            String newPin = scanner.next();

            System.out.println(messagesOtherOptions.newPinAgainChangeMessage());
            String newPinAgain = scanner.next();

            if (dataOperations.canRegisterPin(newPin, newPinAgain)) {
                atmService.changeUsersCreditCardPin(actCreditCard, Integer.parseInt(newPin));
                System.out.println(messagesUserWindow.operationSuccessfulMessage());
                return;
            }

            System.out.println(messagesLoggingIn.pinDoesNotMatchMessage());
            return;
        }

        System.out.println(messagesOtherOptions.oldPinDoesntMatchMessage());
    }

    public void actCardActivation(CreditCard creditCard) {
        if (!creditCard.isActivated()) {
            atmService.activateCard(creditCard);
            System.out.println(messagesUserWindow.operationSuccessfulMessage());
            return;
        }
        System.out.println(messagesUserWindow.cardAlreadyActivatedMessage());
    }

    public void listUsersCards(User actUser, CreditCard actCreditCard) {
        List<CreditCard> creditCards = atmService.allCreditCardAsListByUserId(actUser.getUserId());
        for (int i = 0; i < creditCards.size(); ++i) {
            if (Objects.equals(creditCards.get(i).getCardId(), actCreditCard.getCardId())) {
                System.out.print("Aktuális: ");
            }
            System.out.println(messagesUserWindow.creditCardToStringMessage(creditCards.get(i), i));
        }
    }

    public void listCardsTransactions(CreditCard creditCard) {
        List<Transaction> transactions = atmService.allTransactionAsListByCardId(creditCard.getCardId());
        int cardListSize = transactions.size();
        if(cardListSize > 0) {
            for (int i = 0; i < cardListSize; ++i) {
                System.out.println(messagesUserWindow.transactionToStringMessage(transactions.get(i), i));
            }
        } else {
            System.out.println(messagesOtherOptions.noCommittedTransactionMessage());
        }
    }

    public CreditCard changeActCreditCard(User user, CreditCard actCreditCard) {
        List<CreditCard> creditCards = atmService.allCreditCardAsListByUserId(user.getUserId());
        int changeCardMenuLowerBound = 0;
        int changeCardMenuUpperBound = creditCards.size() - 1;
        int chosenCardNumber;

        if (dataOperations.thereAreMoreCardsToChoose(creditCards)) {
            listUsersCards(user, actCreditCard);
            chosenCardNumber = getInputBetweenMenuRanges(changeCardMenuUpperBound, changeCardMenuLowerBound);

            if (dataOperations.isInRange(chosenCardNumber, changeCardMenuUpperBound, changeCardMenuLowerBound)) {
                System.out.println(messagesUserWindow.operationSuccessfulMessage());
                return creditCards.get(chosenCardNumber);
            }
            return actCreditCard;
        }
        System.out.println(messagesOtherOptions.noOtherCardsMessage());
        return actCreditCard;
    }

    public CreditCard deleteCard(User actUser, CreditCard actCreditCard) {
        List<CreditCard> creditCards = atmService.allCreditCardAsListByUserId(actUser.getUserId());

        int deleteCardMenuLowerBound = 0;
        int deleteCardMenuUpperBound = creditCards.size() - 1;
        int chosenCardNumber;

        if (dataOperations.thereAreMoreCardsToChoose(creditCards)) {
            listUsersCards(actUser, actCreditCard);
            chosenCardNumber = getInputBetweenMenuRanges(deleteCardMenuUpperBound, deleteCardMenuLowerBound);

            if (dataOperations.isInRange(chosenCardNumber, deleteCardMenuUpperBound, deleteCardMenuLowerBound)) {
                System.out.println(messagesUserWindow.operationSuccessfulMessage());
                return atmService.deleteGivenCard(actUser, actCreditCard, chosenCardNumber);
            }
            return actCreditCard;
        }
        System.out.println(messagesOtherOptions.noCardToDeleteMessage());
        return actCreditCard;
    }

    public boolean deleteUserOption(User user) {
        boolean passwordMatchesForUserDeletion = dataOperations.passwordMatches(user);

        if(passwordMatchesForUserDeletion) {
            boolean userDeletionConfirmed = dataOperations.confirmedUserDeletion();

            if(userDeletionConfirmed) {
                atmService.deleteUser(user);
                System.out.println(messagesOtherOptions.backToLoginMessage());
                return true;
            }

            System.out.println(messagesOtherOptions.deleteInterruptedMessage());
            return false;
        }
        System.out.println(messagesOtherOptions.incorrectPasswordMessage());
        return false;
    }

    public void warnIfActCardIsNotActivated(CreditCard actCreditCard) {
        if (!actCreditCard.isActivated()) {
            System.out.println(messagesUserWindow.cardNotActivatedWarnMessage());
        }
    }
}