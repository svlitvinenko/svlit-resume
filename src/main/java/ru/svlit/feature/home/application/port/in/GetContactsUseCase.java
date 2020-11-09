package ru.svlit.feature.home.application.port.in;

import ru.svlit.feature.home.application.entity.Contact;

import java.util.List;

public interface GetContactsUseCase {
    List<Contact> perform();
}
