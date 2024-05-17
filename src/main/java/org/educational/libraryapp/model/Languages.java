package org.educational.libraryapp.model;

public enum Languages {
  INGLES("en"),
  FRANCES("fr"),
  ESPANOL("es"),
  TAGALO("tl");

  private String bookLanguage;

  Languages(String bookLanguage) {
    this.bookLanguage = bookLanguage;
  }

  public static Languages fromString(String txt) {
    for (Languages languages : Languages.values()) {
      if (languages.bookLanguage.equalsIgnoreCase(txt)) {
        return languages;
      }
    }
    throw new IllegalArgumentException("Idioma no encontrado " + txt);
  }

}
