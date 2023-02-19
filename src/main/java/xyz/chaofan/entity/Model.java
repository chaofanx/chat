package xyz.chaofan.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Model {
    DAVINCI("text-davinci-003"),
    ;
    private final String name;
}
