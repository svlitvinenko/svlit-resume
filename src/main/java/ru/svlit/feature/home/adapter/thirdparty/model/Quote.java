
package ru.svlit.feature.home.adapter.thirdparty.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quote {
    private String author;
    private String background;
    private String category;
    private String date;
    private String id;
    private String language;
    private String length;
    private String permalink;
    private String quote;
    private List<String> tags;
    private String title;
}
