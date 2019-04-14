package country.domain;

public class CountryWithColdClimate extends Country {
    private boolean polarNight;

    public CountryWithColdClimate() {
    }

    public CountryWithColdClimate(String name, String language, boolean polarNight) {
        super(name, language);
        this.polarNight = polarNight;
    }

    public boolean isPolarNight() {
        return polarNight;
    }

    public void setPolarNight(boolean polarNight) {
        this.polarNight = polarNight;
    }

    @Override
    protected void initDiscriminator() {
        discriminator = Discriminator.COLD;
    }

    @Override
    public String toString() {
        return super.toString() +
                "; polarNight: " + polarNight;
    }

}
