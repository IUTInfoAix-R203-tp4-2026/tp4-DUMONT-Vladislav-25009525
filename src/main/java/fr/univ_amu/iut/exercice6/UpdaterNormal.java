package fr.univ_amu.iut.exercice6;

public class UpdaterNormal extends Updater {

  public UpdaterNormal(Item item) {
    super(item);
  }

  @Override
  public void update() {
    if (getItem().quality > getQUALITE_MIN()) getItem().quality = decreaseQuality(getItem());
    getItem().sellIn = decreaseSellIn(getItem());
    if (getItem().sellIn < 0) getItem().quality = decreaseQuality(getItem());
  }
}
