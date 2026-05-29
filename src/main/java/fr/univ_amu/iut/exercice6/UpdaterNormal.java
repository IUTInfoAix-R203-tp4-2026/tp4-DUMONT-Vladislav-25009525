package fr.univ_amu.iut.exercice6;

public class UpdaterNormal extends Updater {

  public UpdaterNormal(Item item) {
    super(item);
  }

  @Override
  public void update() {
    if (getItem().quality > getQUALITE_MIN()) decreaseQuality(getItem());
    decreaseSellIn(getItem());
    if (getItem().sellIn < 0) if (getItem().quality > getQUALITE_MIN()) decreaseQuality(getItem());
  }
}
