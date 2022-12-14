package com.programming.techie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ContactManagerTest {

    @Test
    public void shouldCreateContact() {
        ContactManager cm = new ContactManager();
        cm.addContact("First", "Last", "0123456789");
        Assertions.assertFalse(cm.getAllContacts().isEmpty());
        Assertions.assertEquals(1, cm.getAllContacts().size());
    }

    @Test
    @DisplayName("Should not create contact when first name is NUll")
    public void shouldThrowExceptionWhenFirstNameIsNUll() {
        ContactManager cm = new ContactManager();
        Assertions.assertThrows(RuntimeException.class,
                () -> cm.addContact(null, "last", "1234567890"));
    }
}