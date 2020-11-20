package ru.svlit.core.user.adapter.persistence;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@Document(collection = "users")
public class UserModel {

    @Id
    private final String id;
    private final String username;
    private final String email;
    private final String password;
    private final Set<String> roles;
    private final boolean isActive;
    private final String locale;
}
