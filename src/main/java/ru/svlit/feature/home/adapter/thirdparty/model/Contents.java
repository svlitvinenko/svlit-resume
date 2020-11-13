
package ru.svlit.feature.home.adapter.thirdparty.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contents {
    private List<Quote> quotes;
}
