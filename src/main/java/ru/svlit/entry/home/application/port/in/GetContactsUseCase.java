package ru.svlit.entry.home.application.port.in;

import ru.svlit.entry.home.application.entity.Contact;

import java.util.List;

public interface GetContactsUseCase {
    List<Contact> perform();
}
