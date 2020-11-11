package ru.svlit.feature.sweater.adapter.persistence.datasource.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.AUTO;

@Data
@Entity(name = "message")
@NoArgsConstructor
@AllArgsConstructor
public class MessageModel {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Integer id;
    private String text;
    private String tag;

    public MessageModel(String text, String tag) {
        this.text = text;
        this.tag = tag;
    }
}
