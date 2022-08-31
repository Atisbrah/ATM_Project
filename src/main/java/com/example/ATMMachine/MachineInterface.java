package com.example.ATMMachine;

import com.example.entities.CreditCard;
import com.example.entities.User;
import com.example.messages.MessagesLoggingIn;
import com.example.messages.MessagesOtherOptions;
import com.example.messages.MessagesUserWindow;
import com.example.resources.ReadFile;
import com.example.service.ATMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MachineInterface {

    private MachineActions machineActions;
    private ReadFile readFile;
    private ATMService atmService;

    private DataOperations dataOperations;
    private MessagesLoggingIn messagesLoggingIn;
    private MessagesUserWindow messagesUserWindow;
    private MessagesOtherOptions messagesOtherOptions;

    private MachineState state;

    private long actUserId;

    private User actUser;
    private CreditCard actCreditCard;

    private final int LOGINMENUUPPERBOUND = 2;
    private final int LOGINMENULOWERBOUND = 0;

    private final int USERMENUUPPERBOUND = 5;
    private final int USERMENULOWERBOUND = 0;

    private final int WITHDRAWMENUUPPERBOUND = 7;
    private final int WITHDRAWMENULOWERBOUND = 0;

    private final int DATACHANGEMENUUPPERBOUND = 7;
    private final int DATACHANGEMENULOWERBOUND = 0;

    private final int CARDMENUUPPERBOUND = 6;
    private final int CARDMENULOWERBOUND = 0;

    private final int OTHEROPTIONSMENUUPPERBOUND = 2;
    private final int OTHEROPTIONSMENULOWERBOUND = 0;

    private final int DATAMENUUPPERBOUND = 4;
    private final int DATAMENULOWERBOUND = 0;

    @Autowired
    public MachineInterface(ReadFile readFile, MachineActions machineActions, ATMService atmService, DataOperations dataOperations,
                            MessagesLoggingIn messagesLoggingIn, MessagesUserWindow messagesUserWindow, MessagesOtherOptions messagesOtherOptions) {
        this.readFile = readFile;
        this.machineActions = machineActions;
        this.atmService = atmService;
        this.dataOperations = dataOperations;

        this.messagesLoggingIn = messagesLoggingIn;
        this.messagesUserWindow = messagesUserWindow;
        this.messagesOtherOptions = messagesOtherOptions;

        readFile.fillDatabaseWithInitialData();
        state = SetMachineState.setMachineStateSystemStart();
        System.out.println(messagesLoggingIn.welcomeMessage());
    }

    public void run() {
        startingMenu();
    }

    private void startingMenu() {
        state = SetMachineState.setMachineStateStartingWindow();

        System.out.println(messagesLoggingIn.loggingInWindowMessage());
        int input = machineActions.getInputBetweenMenuRanges(LOGINMENUUPPERBOUND, LOGINMENULOWERBOUND);

        switch (input) {
            case 1 -> loggingInMenu();
            case 2 -> registrationMenu();
            case 0 -> {
                state = SetMachineState.setMachineStateOff();
                System.out.println(messagesUserWindow.goodByeMessage());
            }
            default -> System.out.println(messagesLoggingIn.notAvailableOptionMessage());
        }
    }

    private void loggingInMenu() {
        System.out.println(messagesLoggingIn.loginMessage() + System.lineSeparator());
        state = SetMachineState.setMachineStateLogin();

        actUserId = machineActions.login();
        if (actUserId == 0) {
            System.out.println(messagesLoggingIn.userDoesNotExistMessage());
            return;
        }

        this.actUser = atmService.findUserById(actUserId);
        this.actCreditCard = atmService.getFirstCreditCardOfUser(actUserId);
        userMenu();
    }

    private void registrationMenu() {
        System.out.println(messagesLoggingIn.registrationMessage() + System.lineSeparator());
        state = SetMachineState.setMachineStateRegistration();
        machineActions.registration();
    }

    private void userMenu() {
        state = SetMachineState.setMachineStateUserWindow();

        boolean exit = false;
        boolean deletedUser;
        int input;

        while (!exit) {
            state = SetMachineState.setMachineStateUserWindow();
            System.out.println(messagesLoggingIn.chooseOptionMessage());
            machineActions.warnIfActCardIsNotActivated(actCreditCard);
            System.out.println(messagesUserWindow.userWindowMenu());

            input = machineActions.getInputBetweenMenuRanges(USERMENUUPPERBOUND, USERMENULOWERBOUND);

            switch (input) {
                case 1 -> currentBalanceMenu();
                case 2 -> withdrawMenu();
                case 3 -> depositMenu();
                case 4 -> machineActions.changeUsersCardPin(actCreditCard);
                case 5 -> {
                    deletedUser = otherOptionsMenu();
                    if (deletedUser) {
                        return;
                    }
                }
                case 0 -> {
                    state = SetMachineState.setMachineStateStartingWindow();
                    System.out.println(messagesUserWindow.logOutMessage());
                    actUserId = 0L;
                    exit = true;
                }
                default -> {
                    System.out.println(messagesLoggingIn.notAvailableOptionMessage());
                }
            }
        }
    }

    private void currentBalanceMenu() {
        Double actBalance = actCreditCard.getBalance();
        System.out.println(messagesUserWindow.actualBalanceMessage(actBalance));
    }

    private void withdrawMenu() {
        state = SetMachineState.setMachineStateWithdraw();
        System.out.println(messagesUserWindow.moneyWithdrawMenuMessage());

        int input = machineActions.getInputBetweenMenuRanges(WITHDRAWMENUUPPERBOUND, WITHDRAWMENULOWERBOUND);

        switch (input) {
            case 1 -> machineActions.moneyWithdrawOption(actCreditCard, 1000.0);
            case 2 -> machineActions.moneyWithdrawOption(actCreditCard, 5000.0);
            case 3 -> machineActions.moneyWithdrawOption(actCreditCard, 10000.0);
            case 4 -> machineActions.moneyWithdrawOption(actCreditCard, 25000.0);
            case 5 -> machineActions.moneyWithdrawOption(actCreditCard, 50000.0);
            case 6 -> machineActions.moneyWithdrawOption(actCreditCard, 100000.0);
            case 7 -> machineActions.moneyWithdrawCustomNumberOption(actCreditCard);
            case 0 -> {
                return;
            }
            default -> System.out.println(messagesLoggingIn.notAvailableOptionMessage());
        }

    }

    private void depositMenu() {
        state = SetMachineState.setMachineStateDeposit();
        System.out.println(messagesUserWindow.moneyDepositMenuMessage());

        Double input = machineActions.getCustomNumberInputForTransaction();
        if(input == 0) return;
        machineActions.moneyDepositOption(actCreditCard, input);

    }

    private boolean otherOptionsMenu() {
        state = SetMachineState.setMachineStateOtherOptions();

        int input;
        boolean exit = false;
        boolean deletedUser;

        while (!exit) {
            state = SetMachineState.setMachineStateOtherOptions();
            System.out.println(messagesLoggingIn.chooseOptionMessage());
            System.out.println(messagesOtherOptions.otherOptionsMessage());
            input = machineActions.getInputBetweenMenuRanges(OTHEROPTIONSMENUUPPERBOUND, OTHEROPTIONSMENULOWERBOUND);

            switch (input) {
                case 1 -> cardMenu();
                case 2 -> {
                    deletedUser = userDataMenu();
                    if (deletedUser) {
                        return true;
                    }
                }
                case 0 -> exit = true;
                default -> System.out.println(messagesLoggingIn.notAvailableOptionMessage());
            }
        }
        return false;
    }

    private void cardMenu() {
        state = SetMachineState.setMachineStateCardOptions();

        int input;
        boolean exit = false;

        while (!exit) {

            state = SetMachineState.setMachineStateCardOptions();
            System.out.println(messagesLoggingIn.chooseOptionMessage());
            System.out.println(messagesOtherOptions.cardDataMenuMessage());
            input = machineActions.getInputBetweenMenuRanges(CARDMENUUPPERBOUND, CARDMENULOWERBOUND);

            switch (input) {
                case 1 -> machineActions.listUsersCards(actUser, actCreditCard);
                case 2 -> machineActions.newCardRegistration(actUser);
                case 3 -> actCreditCard = machineActions.changeActCreditCard(actUser, actCreditCard);
                case 4 -> actCreditCard = machineActions.deleteCard(actUser, actCreditCard);
                case 5 -> machineActions.actCardActivation(actCreditCard);
                case 6 -> machineActions.listCardsTransactions(actCreditCard);
                case 0 -> exit = true;
                default -> System.out.println(messagesLoggingIn.notAvailableOptionMessage());
            }
        }
    }

    private boolean userDataMenu() {
        state = SetMachineState.setMachineStatePersonalInfoOptions();

        int input;
        boolean exit = false;

        while (!exit) {

            state = SetMachineState.setMachineStatePersonalInfoOptions();
            System.out.println(messagesLoggingIn.chooseOptionMessage());
            System.out.println(messagesOtherOptions.userDataMenuMessage());

            input = machineActions.getInputBetweenMenuRanges(DATAMENUUPPERBOUND, DATAMENULOWERBOUND);

            switch (input) {
                case 1 -> System.out.println(messagesOtherOptions.showUserDataMessage(actUser));
                case 2 -> dataChangeMenu();
                case 3 -> machineActions.changeUsersLoginPassword(actUser);
                case 4 -> {
                    boolean deleteCompleted = machineActions.deleteUserOption(actUser);
                    if(deleteCompleted) {
                        state = SetMachineState.setMachineStateStartingWindow();
                        System.out.println(messagesUserWindow.logOutMessage());
                        actUserId = 0L;
                        return true;
                    }
                }
                case 0 -> exit = true;
                default -> System.out.println(messagesLoggingIn.notAvailableOptionMessage());
            }
        }
        return false;
    }

    private void dataChangeMenu() {
        state = SetMachineState.setMachineStateChangePersonalInfo();

        int input;
        boolean exit = false;

        while (!exit) {
            state = SetMachineState.setMachineStateChangePersonalInfo();
            System.out.println(messagesOtherOptions.dataChangeMenuMessage());
            input = machineActions.getInputBetweenMenuRanges(DATACHANGEMENUUPPERBOUND, DATACHANGEMENULOWERBOUND);

            switch (input) {
                case 1 -> machineActions.changeUsersFirstname(actUser);
                case 2 -> machineActions.changeUsersLastname(actUser);
                case 3 -> machineActions.changeUsersAddress(actUser);
                case 4 -> machineActions.changeUsersPhone(actUser);
                case 5 -> machineActions.changeUsersEmail(actUser);
                case 6 -> machineActions.changeUsersBirthDate(actUser);
                case 7 -> machineActions.changeUsersLoginUsername(actUser);
                case 0 -> exit = true;
                default -> System.out.println(messagesLoggingIn.notAvailableOptionMessage());
            }
        }
    }

    public boolean isWorking() {
        return state != MachineState.OFF;
    }
}
