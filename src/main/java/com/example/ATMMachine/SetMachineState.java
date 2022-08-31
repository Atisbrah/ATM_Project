package com.example.ATMMachine;

public class SetMachineState {

    public static MachineState setMachineStateOff() {
        return MachineState.OFF;
    }

    public static MachineState setMachineStateSystemStart() {
        return MachineState.SYSTEMSTART;
    }

    public static MachineState setMachineStateStartingWindow() {
        return MachineState.STARTINGWINDOW;
    }

    public static MachineState setMachineStateLogin() {
        return MachineState.LOGIN;
    }

    public static MachineState setMachineStateRegistration() {
        return MachineState.REGISTRATION;
    }

    public static MachineState setMachineStateUserWindow() {
        return MachineState.USERWINDOW;
    }

    public static MachineState setMachineStateWithdraw() {
        return MachineState.WITHDRAW;
    }

    public static MachineState setMachineStateDeposit() {
        return MachineState.DEPOSIT;
    }

    public static MachineState setMachineStateOtherOptions() {
        return MachineState.OTHEROPERATIONS;
    }

    public static MachineState setMachineStateCardOptions() {
        return MachineState.CARDOPTIONS;
    }

    public static MachineState setMachineStatePersonalInfoOptions() {
        return MachineState.PERSONALINFOOPTIONS;
    }

    public static MachineState setMachineStateChangePersonalInfo() {
        return MachineState.CHANGEPERSONALINFO;
    }
}
