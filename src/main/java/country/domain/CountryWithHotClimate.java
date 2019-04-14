package country.domain;

public class CountryWithHotClimate extends Country {
    private String hottestMonth;
    private double averageTemperature;

    public CountryWithHotClimate() {
    }

    public CountryWithHotClimate(String name, String language, String hottestMonth, double averageTemperature) {
        super(name, language);
        this.hottestMonth = hottestMonth;
        this.averageTemperature = averageTemperature;
    }

    public String getHottestMonth() {
        return hottestMonth;
    }

    public void setHottestMonth(String hottestMonth) {
        this.hottestMonth = hottestMonth;
    }

    public double getAverageTemperature() {
        return averageTemperature;
    }

    public void setAverageTemperature(double averageTemperature) {
        this.averageTemperature = averageTemperature;
    }

    @Override
    protected void initDiscriminator() {
        discriminator = Discriminator.HOT;
    }

    @Override
    public String toString() {
        return super.toString() +
                "; hottestMonth: " + hottestMonth +
                "; averageTemperature: " + averageTemperature;
    }
}
