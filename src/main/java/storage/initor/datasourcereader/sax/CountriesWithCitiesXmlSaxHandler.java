package storage.initor.datasourcereader.sax;

import city.domain.City;
import country.domain.Country;
import country.domain.CountryWithColdClimate;
import country.domain.CountryWithHotClimate;
import country.domain.Discriminator;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

import static common.solution.utils.CollectionUtils.getLast;

public class CountriesWithCitiesXmlSaxHandler extends DefaultHandler {

    private StringBuilder stringBuilder = new StringBuilder();
    private List<Country> countries;
    private List<City> cities;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        stringBuilder.setLength(0);

        switch (qName) {
            case "countries": {
                countries = new ArrayList<>();
                break;
            }

            case "country": {
                if (isCountryWithColdClimate(attributes)) {
                    countries.add(new CountryWithColdClimate());
                } else {
                    countries.add(new CountryWithHotClimate());
                }
                break;
            }

            case "cities": {
                cities = new ArrayList<>();
                getLast(countries).setCities(cities);
                break;
            }

            case "city": {
                cities.add(new City());
                break;
            }
        }
    }

    private boolean isCountryWithColdClimate(Attributes attributes) {
        return Discriminator.COLD.equals(Discriminator.valueOf(attributes.getValue("type")));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String data = stringBuilder.toString();

        switch (qName) {

            case "polarNight": {
                getCountryWithColdClimate().setPolarNight(Boolean.parseBoolean(data));
                break;
            }

            case "hottestMonth": {
                getCountryWithHotClimate().setHottestMonth(data);
                break;
            }
            case "averageTemperature": {
                getCountryWithHotClimate().setAverageTemperature(Double.parseDouble(data));
                break;
            }

            case "name": {
                getLast(countries).setName(data);
                break;
            }

            case "language": {
                getLast(countries).setLanguage(data);
                break;
            }

            case "nameCity": {
                getLast(cities).setNameCity(data);
                break;
            }
            case "population": {
                getLast(cities).setPopulation(Double.parseDouble(data));
                break;
            }
            case "capital": {
                getLast(cities).setCapital(Boolean.parseBoolean(data));
                break;
            }
        }
    }

    private CountryWithHotClimate getCountryWithHotClimate() {
        return (CountryWithHotClimate) getLast(countries);
    }

    private CountryWithColdClimate getCountryWithColdClimate() {
        return (CountryWithColdClimate) getLast(countries);
    }

    public List<Country> getCountries() {
        return countries;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch, start, length);
        stringBuilder.append(value.replaceAll("\\n", ""));
    }

}
