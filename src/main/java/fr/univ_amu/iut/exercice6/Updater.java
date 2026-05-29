package fr.univ_amu.iut.exercice6;

public abstract class Updater {
  private final Item item;
  private final int QUALITE_MAX_DEFAUT = 50;
  private final int QUALITE_MIN = 0;

  public Updater(Item item) {
    this.item = item;
  }

  public Item getItem() {
    return item;
  }

  public int getQUALITE_MAX_DEFAUT() {
    return QUALITE_MAX_DEFAUT;
  }

  public int getQUALITE_MIN() {
    return QUALITE_MIN;
  }

  public abstract void update();

  public static void increaseQuality(Item currentItem) {
    currentItem.quality += 1;
  }

  public static void zeroQuality(Item currentItem) {
    currentItem.quality = 0;
  }

  public static void decreaseSellIn(Item currentItem) {
    currentItem.sellIn -= 1;
  }

  public static void decreaseQuality(Item currentItem) {
    currentItem.quality -= 1;
  }

  public static Updater creer(Item item) {
    switch (item.name) {
      case "Aged Brie":
        return new UpdaterBrie(item);
      case "Backstage passes to a TAFKAL80ETC concert":
        return new UpdaterBackstage(item);
      case "Sulfuras, Hand of Ragnaros":
        return new UpdaterSulfuras(item);
      case "Conjured Mana Cake":
        return new UpdaterConjured(item);
      default:
        return new UpdaterNormal(item);
    }
  }
}
