package ru.svlit.entry.home.adapter.web.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.web.servlet.ModelAndView;

@Getter
@ToString
@RequiredArgsConstructor
public class MotdModel extends ModelAndView {
    private final String message;
    private final String author;
}
