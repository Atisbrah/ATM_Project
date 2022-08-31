package com.example.ATMMachine;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataOperationsTest {

    @Test
    void formatDate() {
        DataOperations dataOperations = new DataOperations();
        assertEquals("1996-11-02", dataOperations.formatDate("1996.11.02"));
    }

    @Test
    void formatAddress() {
        DataOperations dataOperations = new DataOperations();
        assertEquals("3000, Hatvan, Valamilyen utca 10.", dataOperations.formatAddress("3000,_Hatvan,_Valamilyen_utca_10."));
    }

    @Test
    void stringConvertableToInteger() {
        DataOperations dataOperations = new DataOperations();
        assertTrue(dataOperations.stringConvertableToInteger("3"));
    }

    @Test
    void stringListContainNullValue() {
        DataOperations dataOperations = new DataOperations();
        List<String> stringList = Arrays.asList("string", "string", null, "string");
        assertTrue(dataOperations.stringListContainNullValue(stringList));
    }

    @Test
    void doesntContainsSpecialCharacter() {
        DataOperations dataOperations = new DataOperations();
        assertTrue(dataOperations.doesntContainsSpecialCharacter("atis"));
    }

    @Test
    void canRegisterFirstName() {
        DataOperations dataOperations = new DataOperations();
        assertTrue(dataOperations.canRegisterFirstName("atis"));
    }

    @Test
    void canRegisterLastName() {
        DataOperations dataOperations = new DataOperations();
        assertTrue(dataOperations.canRegisterLastName("Lajko"));
    }

    @Test
    void canRegisterPhoneNumber() {
        DataOperations dataOperations = new DataOperations();
        assertTrue(dataOperations.canRegisterPhoneNumber("204113324"));
    }

    @Test
    void convertableToNumber() {
        DataOperations dataOperations = new DataOperations();
        assertTrue(dataOperations.convertableToNumber("204113324"));
    }

    @Test
    void isAValidEmail() {
        DataOperations dataOperations = new DataOperations();
        assertTrue(dataOperations.isAValidEmail("atis@valamilyenemail.com"));
    }

    @Test
    void canRegisterBirthDate() {
        DataOperations dataOperations = new DataOperations();
        assertTrue(dataOperations.canRegisterBirthDate(19961102));
    }

    @Test
    void formatDateToString() {
        DataOperations dataOperations = new DataOperations();
        assertEquals("1996-11-02", dataOperations.formatDateToString(19961102));
    }

    @Test
    void isAValidDate() {
        DataOperations dataOperations = new DataOperations();
        assertTrue(dataOperations.isAValidDate(19961102));
    }

    @Test
    void canRegisterPin() {
        DataOperations dataOperations = new DataOperations();
        assertTrue(dataOperations.canRegisterPin("1234", "1234"));
    }

    @Test
    void canRegisterCardNumber() {
        DataOperations dataOperations = new DataOperations();
        assertTrue(dataOperations.isCardNumberConvertableToLong("1234-1234-1234-1234"));
    }

    @Test
    void getLocalDateYearMonthDayAsInt() {
        DataOperations dataOperations = new DataOperations();
        LocalDate date = LocalDate.parse("1996-11-02");
        assertEquals(1996112, dataOperations.getLocalDateYearMonthDayAsInt(date));
    }

    @Test
    void getTransactionFee() {
        DataOperations dataOperations = new DataOperations();
        assertEquals(1.0, dataOperations.getTransactionFee(100.0));
    }

    @Test
    void isAPositiveNumber() {
        DataOperations dataOperations = new DataOperations();
        assertTrue(dataOperations.isAPositiveNumber(5.0));
    }

    @Test
    void enoughCoverage() {
        DataOperations dataOperations = new DataOperations();
        assertTrue(dataOperations.enoughCoverage(10000.0, 100.0));
    }

    @Test
    void isInRange() {
        DataOperations dataOperations = new DataOperations();
        assertTrue(dataOperations.isInRange(5, 10, 0));
    }
}