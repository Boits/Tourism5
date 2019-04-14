package country.search;

public class CountryWithColdClimateSearchCondition extends CountrySearchCondition {
    private Boolean polarNight;

    public boolean searchByPolarNight() {
        return polarNight != null;
    }

    public Boolean getPolarNight() {
        return polarNight;
    }

    public void setPolarNight(Boolean polarNight) {
        this.polarNight = polarNight;
    }

}
