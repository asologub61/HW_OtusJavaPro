package classes;

public enum Banknotes {
    N50(50),
    N100(100),
    N200(200),
    N500(500),
    N1000(1000),
    N2000(2000),
    N5000(5000);

    private final int nominal;

    Banknotes(int nominal) {
        this.nominal = nominal;
    }

    public int getNominal() {
        return nominal;
    }
}
