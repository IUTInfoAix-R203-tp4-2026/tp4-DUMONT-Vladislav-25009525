package fr.univ_amu.iut.exercice3;

import java.util.ArrayList;
import java.util.List;

public class Historique {
  private static final int tailleMax = 10;
  private final List<String> historique = new ArrayList<>();

  public void enregistrer(String string) {
    historique.add(string);
    if (historique.size() > tailleMax) {
      historique.remove(0);
    }
  }

  public List<String> asList() {
    return historique;
  }

  public String afficher() {
    StringBuilder sb = new StringBuilder();
    sb.append("--- Historique ---\n");
    for (String h : historique) {
      sb.append("- ").append(h).append("\n");
    }
    return sb.toString();
  }
}
