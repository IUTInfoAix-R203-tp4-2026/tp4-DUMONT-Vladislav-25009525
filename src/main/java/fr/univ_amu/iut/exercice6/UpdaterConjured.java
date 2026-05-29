package fr.univ_amu.iut.exercice6;

public class UpdaterConjured extends Updater {

  public UpdaterConjured(Item item) {
    super(item);
  }

  @Override
  public void update() {
    for (int i = 0; i < 2; ++i)
      if (getItem().quality > getQUALITE_MIN()) decreaseQuality(getItem());
    if (getItem().sellIn > 0) decreaseSellIn(getItem());
    if (getItem().sellIn == 0)
      for (int i = 0; i < 2; ++i)
        if (getItem().quality > getQUALITE_MIN()) decreaseQuality(getItem());
  }
}
