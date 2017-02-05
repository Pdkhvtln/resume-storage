package com.urise.webapp.model;

import java.util.Objects;

/**
 * Created by andrew on 05.02.17.
 */
public class TextSection extends Section{
    private final String content;

    public String getContent() {
        return content;
    }

    public TextSection(String content) {
        Objects.requireNonNull(content,"content must be not null");
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextSection that = (TextSection) o;

        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }

    @Override
    public String toString() {
        return content.toString();
    }
}
