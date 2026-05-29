package fr.univ_amu.iut.exercice6;

public class UpdaterBrie extends Updater {

  public UpdaterBrie(Item item) {
    super(item);
  }

  @Override
  public void update() {
    if (getItem().quality < getQUALITE_MAX_DEFAUT()) increaseQuality(getItem());
    decreaseSellIn(getItem());
    if (getItem().sellIn < 0) increaseQuality(getItem());
  }
}
