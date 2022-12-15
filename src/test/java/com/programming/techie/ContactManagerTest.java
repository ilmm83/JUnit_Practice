package com.programming.techie;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContactManagerTest {

    private ContactManager cm;

    @BeforeAll
    public void beforeAllSetUp() {
        System.out.println("Before all");
    }

    @AfterEach
    public void afterEachTest() {
        System.out.println("After Each test");
    }

    @BeforeEach         // Setup configuration before run tests
    public void setup() {
        cm = new ContactManager();
    }

    @Test
    public void shouldCreateContact() {
        cm.addContact("First", "Last", "0123456789");
        Assertions.assertFalse(cm.getAllContacts().isEmpty());
        Assertions.assertEquals(1, cm.getAllContacts().size());
    }

    @Test
    @DisplayName("Should not create contact when first name is NUll")
    public void shouldThrowExceptionWhenFirstNameIsNUll() {
        Assertions.assertThrows(RuntimeException.class,
                () -> cm.addContact(null, "last", "1234567890"));
    }

    // Conditional test
    @Test
    @DisplayName("Only Windows")
    @EnabledOnOs(value = OS.WINDOWS, disabledReason = "Private club")
    public void enableOnlyForWindows() {
        cm.addContact("First2", "Last2", "0123456789");
        Assertions.assertFalse(cm.getAllContacts().isEmpty());
        Assertions.assertEquals(1, cm.getAllContacts().size());
        Assertions.assertTrue(cm.getAllContacts().stream()
                .anyMatch(contact -> contact.getFirstName().equals("First2") &&
                        contact.getLastName().equals("Last2") &&
                        contact.getPhoneNumber().equals("1234567890")));
    }


    // Assumption test
    @Test
    @DisplayName("Test Contact Creation on Developer Machine")
    public void shouldTestContactCreationOnDEV() {
        assertEquals("DEV", System.getProperty("ENV"));
        cm.addContact("John", "Doe", "0123456789");
        assertFalse(cm.getAllContacts().isEmpty());
        assertEquals(1, cm.getAllContacts().size());
    }

    // Repeated test
    @DisplayName("Repeat creation of a Contact")
    @RepeatedTest(value = 5, name = "{currentRepetition} of {totalRepetitions}")
    public void createContactSpecifiedAmountOfTimes() {
        cm.addContact("John", "Doe", "0123456789");
        assertFalse(cm.getAllContacts().isEmpty());
        assertEquals(1, cm.getAllContacts().size());
    }

    // Parameterized test
    @DisplayName("Repeat contact creation")
    @ParameterizedTest
    @ValueSource(strings = {"0123456789", "0123456789", "0123456789", "+0123456789"})
    public void shouldTestContactCreationUsingValueSource(String phonenumber) {
        cm.addContact("John", "Doe", phonenumber);
        assertFalse(cm.getAllContacts().isEmpty());
        assertEquals(1, cm.getAllContacts().size());
    }

    @DisplayName("Method Source Case - Phone Number should match the required Format")
    @ParameterizedTest
    @MethodSource("phoneNumberList")
    public void shouldTestPhoneNumberFormatUsingMethodSource(String phoneNumber) {
        cm.addContact("John", "Doe", phoneNumber);
        assertFalse(cm.getAllContacts().isEmpty());
        assertEquals(1, cm.getAllContacts().size());
    }

    private static List<String> phoneNumberList() {
        return Arrays.asList("0123456789", "0123456798", "0123456897");
    }

    @Test
    @DisplayName("Test Should Be Disabled")
    @Disabled
    public void shouldBeDisabled() {
        throw new RuntimeException("Test Should Not be executed");
    }

    @AfterAll
    public void printAfterAll() {
        System.out.println("After All");
    }
}