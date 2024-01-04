package org.utcn.springproject.models;

public enum BookType {

    HARDCOVER("Hardcover"),
    PAPERBACK("Paperback"),
    EBOOK("Ebook"),
    AUDIOBOOK("Audiobook");

    private final String displayName;

    BookType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
