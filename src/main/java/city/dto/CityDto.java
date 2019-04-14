package city.dto;

import common.business.dto.BaseDto;

public class CityDto extends BaseDto<Long> {
    private String nameCity;
    private double population;
    private boolean capital;

    public CityDto() {
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public double getPopulation() {
        return population;
    }

    public void setPopulation(double population) {
        this.population = population;
    }

    public boolean isCapital() {
        return capital;
    }

    public void setCapital(boolean capital) {
        this.capital = capital;
    }

}
