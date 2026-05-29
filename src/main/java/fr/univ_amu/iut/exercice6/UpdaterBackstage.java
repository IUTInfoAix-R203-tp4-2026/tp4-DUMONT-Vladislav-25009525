package fr.univ_amu.iut.exercice6;

public class UpdaterBackstage extends Updater {

  private final int SEUIL_BACKSTAGE_X3 = 6;
  private final int SEUIL_BACKSTAGE_X2 = 11;

  public UpdaterBackstage(Item item) {
    super(item);
  }

  @Override
  public void update() {
    if (getItem().quality < getQUALITE_MAX_DEFAUT()) increaseQuality(getItem());
    if (getItem().sellIn < SEUIL_BACKSTAGE_X2) {
      if (getItem().quality < getQUALITE_MAX_DEFAUT()) {
        increaseQuality(getItem());
      }
    }

    if (getItem().sellIn < SEUIL_BACKSTAGE_X3) {
      if (getItem().quality < getQUALITE_MAX_DEFAUT()) {
        increaseQuality(getItem());
      }
    }
    decreaseSellIn(getItem());
    if (getItem().sellIn < 0) zeroQuality(getItem());
  }
}
