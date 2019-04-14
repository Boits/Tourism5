package country.dto;

import country.domain.Discriminator;

public class CountryWithColdClimateDto extends CountryDto {
    private boolean polarNight;

    public CountryWithColdClimateDto() {
    }

    @Override
    protected void initDiscriminator() {
        discriminator = Discriminator.COLD;
    }

    public boolean isPolarNight() {
        return polarNight;
    }

    public void setPolarNight(boolean polarNight) {
        this.polarNight = polarNight;
    }


    @Override
    public String toString() {
        return super.toString() +
                "; polarNight: " + polarNight;
    }
}
