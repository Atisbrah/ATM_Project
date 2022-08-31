package com.example.ATMMachine;

public enum MachineState {
    OFF,
    SYSTEMSTART,
    STARTINGWINDOW,
    LOGIN,
    REGISTRATION,
    USERWINDOW,
    WITHDRAW,
    DEPOSIT,
    OTHEROPERATIONS,
    CARDOPTIONS,
    PERSONALINFOOPTIONS,
    CHANGEPERSONALINFO
}