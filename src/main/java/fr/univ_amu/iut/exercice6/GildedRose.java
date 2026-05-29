package fr.univ_amu.iut.exercice6;

/// Exercice 6 - Gilded Rose (capstone).
///
/// Ceci est le code existant de gestion de l'inventaire de la Gilded Rose. La
/// direction veut ajouter les articles *Conjured* (qui se dégradent deux fois
/// plus vite que les articles normaux). Mais avant de le faire, elle demande
/// qu'on nettoie le code - parce qu'actuellement, personne n'ose y toucher.
///
/// Règles en vigueur :
///
/// - À la fin de chaque journée, `sellIn` et `quality` de chaque article sont
///   mis à jour
/// - Une fois `sellIn` passé (négatif), la qualité se dégrade **deux fois plus
///   vite**
/// - La qualité n'est jamais négative
/// - La qualité d'un article n'est jamais au-dessus de 50... sauf pour
///   "Sulfuras" qui est toujours à 80
/// - "Aged Brie" *augmente* en qualité avec le temps
/// - "Sulfuras, Hand of Ragnaros" ne doit jamais être vendu (sellIn ne change
///   pas) et jamais se dégrader (quality ne change pas)
/// - "Backstage passes to a TAFKAL80ETC concert" augmente en qualité :
///   - de 2 quand il reste 10 jours ou moins
///   - de 3 quand il reste 5 jours ou moins
///   - tombe à 0 après le concert (sellIn < 0)
///
/// Votre mission :
///
/// 1. Écrire des tests de caractérisation couvrant **toutes** les règles (déjà
///    fournis)
/// 2. Refactorer `updateQuality()` en gardant les tests verts - par exemple en
///    extrayant une classe par type d'article (polymorphisme)
/// 3. Ajouter le support des articles "Conjured" qui se dégradent deux fois plus
///    vite (test dédié à activer une fois votre refactoring prêt)
///
/// Contrainte : la classe [Item] ne doit pas changer (signature figée par
/// la direction).
public class GildedRose {

  private static final int SEUIL_BACKSTAGE_X3 = 6;
  private static final int SEUIL_BACKSTAGE_X2 = 11;
  private static final int QUALITE_MAX_DEFAUT = 50;
  private static final int QUALITE_MIN = 0;
  Item[] items;

  public GildedRose(Item[] items) {
    this.items = items;
  }

  public void updateQualityP() {
    for (int i = 0; i < items.length; i++) {
      Item currentItem = items[i];
      if (!isAgedBrie(currentItem) && !isBackstage(currentItem)) {
        if (currentItem.quality > QUALITE_MIN) {
          if (!isSulfura(currentItem)) {
            decreaseQuality(currentItem);
          }
        }
      } else {
        if (currentItem.quality < QUALITE_MAX_DEFAUT) {
          currentItem.quality = currentItem.quality + 1;

          if (isBackstage(currentItem)) {
            updateBackstage(currentItem);
          }
        }
      }

      if (!isSulfura(currentItem)) {
        decreaseSellIn(currentItem);
      }

      if (currentItem.sellIn < 0) {
        if (!isAgedBrie(currentItem)) {
          if (!isBackstage(currentItem)) {
            if (currentItem.quality > QUALITE_MIN) {
              if (!isSulfura(currentItem)) {
                decreaseQuality(currentItem);
              }
            }
          } else {
            zeroQuality(currentItem);
          }
        } else {
          if (currentItem.quality < QUALITE_MAX_DEFAUT) {
            increaseQuality(currentItem);
          }
        }
      }
    }
  }

  public void updateQuality() {
    for (int i = 0; i < items.length; i++) {
      Item currentItem = items[i];
      switch (currentItem.name) {
        case "Aged Brie":
          if (currentItem.quality < QUALITE_MAX_DEFAUT)
            currentItem.quality = currentItem.quality + 1;
          decreaseSellIn(currentItem);
          break;
        case "Backstage passes to a TAFKAL80ETC concert":
          if (currentItem.quality < QUALITE_MAX_DEFAUT)
            currentItem.quality = currentItem.quality + 1;
          updateBackstage(currentItem);
          decreaseSellIn(currentItem);
          break;
        case "Sulfuras, Hand of Ragnaros":
          break;
        default:
          if (currentItem.quality > QUALITE_MIN) decreaseQuality(currentItem);
          decreaseSellIn(currentItem);
          break;
      }
    }
  }

  private void increaseQuality(Item currentItem) {
    currentItem.quality += 1;
  }

  private void zeroQuality(Item currentItem) {
    currentItem.quality = 0;
  }

  private void decreaseSellIn(Item currentItem) {
    currentItem.sellIn -= 1;
  }

  private void decreaseQuality(Item currentItem) {
    currentItem.quality -= 1;
  }

  private boolean isAgedBrie(Item currentItem) {
    return currentItem.name.equals("Aged Brie");
  }

  private boolean isBackstage(Item currentItem) {
    return currentItem.name.equals("Backstage passes to a TAFKAL80ETC concert");
  }

  private boolean isSulfura(Item currentItem) {
    return currentItem.name.equals("Sulfuras, Hand of Ragnaros");
  }

  private void updateBackstage(Item currentItem) {
    if (currentItem.sellIn < SEUIL_BACKSTAGE_X2) {
      if (currentItem.quality < QUALITE_MAX_DEFAUT) {
        currentItem.quality = currentItem.quality + 1;
      }
    }

    if (currentItem.sellIn < SEUIL_BACKSTAGE_X3) {
      if (currentItem.quality < QUALITE_MAX_DEFAUT) {
        currentItem.quality = currentItem.quality + 1;
      }
    }
  }
}
