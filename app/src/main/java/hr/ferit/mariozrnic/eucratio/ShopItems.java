package hr.ferit.mariozrnic.eucratio;

public class ShopItems {
    private String naziv;
    private Double cijenaHr;
    private Double cijenaEur;

    public ShopItems(String naziv, Double cijenaHr) {
        this.naziv = naziv;
        this.cijenaHr = cijenaHr;
        this.cijenaEur = cijenaHr / 7.53450; //fiksni kurs od strane HNB koji ce se primjenjivati na sve cijene.
    }

    public String getNaziv() {
        return naziv;
    }

    public Double getCijenaHr() {
        return cijenaHr;
    }

    public Double getCijenaEur() {
        return cijenaEur;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setCijenaHr(Double cijenaHr) {
        this.cijenaHr = cijenaHr;
    }

    public void setCijenaEur(Double cijenaEur) {
        this.cijenaEur = cijenaEur;
    }
}
