package com.programming.techie;

import org.junit.jupiter.api.*;

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

    @AfterAll
    public void printAfterAll() {
        System.out.println("After All");
    }
}