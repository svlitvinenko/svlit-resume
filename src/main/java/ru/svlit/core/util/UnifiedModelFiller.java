package ru.svlit.core.util;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import ru.svlit.core.globalization.Resource.TitleResource;
import ru.svlit.core.globalization.ResourceManager;

import java.util.Locale;

/**
 * Наполнитель модели.
 *
 * @author Sergei Litvinenko on 19.11.2020.
 */
@RequiredArgsConstructor
public abstract class UnifiedModelFiller {

    protected final NavigationContent navigationContent;
    protected final ResourceManager resourceManager;

    public void fill(Model model) {
        final String text = resourceManager.getText(new TitleResource(), Locale.forLanguageTag("en-GB"), null);
        model.addAttribute("text_title", text);
        model.addAttribute("navigationContent", navigationContent);
    }
}
