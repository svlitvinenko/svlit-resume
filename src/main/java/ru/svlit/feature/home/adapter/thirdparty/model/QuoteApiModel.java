
package ru.svlit.feature.home.adapter.thirdparty.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuoteApiModel {
    private String baseurl;
    private Contents contents;
    private Copyright copyright;
    private Success success;
}
