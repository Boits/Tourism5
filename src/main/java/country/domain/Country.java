package country.domain;

import city.domain.City;
import common.business.domain.BaseDomain;

import java.util.List;

public abstract class Country extends BaseDomain<Long> {
    protected String name;
    protected String language;
    protected List<City> cities;
    protected int telephoneCode;
    protected Discriminator discriminator;

    public Country() {
    }

    public Country(String name, String language) {
        this.name = name;
        this.language = language;
        initDiscriminator();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public int getTelephoneCode() {
        return telephoneCode;
    }

    public void setTelephoneCode(int telephoneCode) {
        this.telephoneCode = telephoneCode;
    }

    protected abstract void initDiscriminator();

    public Discriminator getDiscriminator() {
        return discriminator;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Id: ").append(id).append("; Country: ").append(name).append("; Language: ").append(language).toString();
    }
//    @Override
//    public String toString() {
//        return id + " " + name + " " + language;
//    }

}