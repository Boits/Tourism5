package country.dto;

import country.domain.Discriminator;

public class CountryWithHotClimateDto extends CountryDto {
    private String hottestMonth;
    private double averageTemperature;

    public CountryWithHotClimateDto() {
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
