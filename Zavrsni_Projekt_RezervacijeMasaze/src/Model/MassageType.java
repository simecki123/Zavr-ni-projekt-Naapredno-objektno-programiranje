package Model;

/**
 * Class enum of massage types with values(prices).
 */
public enum MassageType {
    SweedishMassage(50), ThaiMassage(55), SportsMassage(65), PoolMassage(80), DeepTissueMassage(40), HotStoneMassage(75);

    private int i;
    MassageType(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }
}
