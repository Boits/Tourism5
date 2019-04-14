package country.search;

public class CountryWithHotClimateSearchCondition extends CountrySearchCondition {
    private String hottestMonth;
    private Double averageTemperature;

    public boolean searchByHottestMonth() {
        return hottestMonth != null;
    }

    public boolean searchByAverageTemperature() {
        return averageTemperature != null;
    }

    public String getHottestMonth() {
        return hottestMonth;
    }

    public void setHottestMonth(String hottestMonth) {
        this.hottestMonth = hottestMonth;
    }

    public Double getAverageTemperature() {
        return averageTemperature;
    }

    public void setAverageTemperature(Double averageTemperature) {
        this.averageTemperature = averageTemperature;
    }
}
