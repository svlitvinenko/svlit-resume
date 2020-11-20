package ru.svlit.feature.authentication.adapter.persistence;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Документо MongoDB "Код активации".
 *
 * @author Sergei Litvinenko on 18.11.2020.
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@Document(collection = "activations")
public class ActivationCodeModel {
    @Id
    private final String activationCode;
    private final String userId;
}
