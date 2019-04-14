package storage.initor.datasourcereader.stax;

import city.domain.City;
import country.domain.Country;
import country.domain.CountryWithColdClimate;
import country.domain.CountryWithHotClimate;
import country.domain.Discriminator;
import common.solution.parser.XmlParser;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import java.util.List;

import static country.domain.Discriminator.COLD;
import static storage.initor.datasourcereader.stax.CustomStaxReader.readContent;

public class CountriesWithCitiesXmlStaxParser implements XmlParser<List<Country>> {

    @Override
    public List<Country> parseFile(String file) throws Exception {
        try (CustomStaxReader customStaxReader = CustomStaxReader.newInstance(file)) {
            return readDocument(customStaxReader.getReader());
        }
    }

    private List<Country> readDocument(XMLStreamReader reader) throws XMLStreamException {
        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {
                case XMLStreamReader.START_ELEMENT: {
                    String tag = reader.getLocalName();

                    if ("countries".equals(tag)) {
                        return readCountries(reader);
                    }
                    break;
                }

                case XMLStreamReader.END_ELEMENT: {
                    break;
                }
            }
        }

        throw new RuntimeException("I didn't find suitable end tag");
    }

    private List<Country> readCountries(XMLStreamReader reader) throws XMLStreamException {
        List<Country> countries = new ArrayList<>();

        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {

                case XMLStreamReader.START_ELEMENT: {
                    String tag = reader.getLocalName();

                    if ("country".equals(tag)) {
                        countries.add(readCountry(reader));
                    }
                    break;
                }

                case XMLStreamConstants.END_ELEMENT: {
                    return countries;
                }
            }
        }
        throw new RuntimeException("I didn't find suitable end tag");
    }

    private Country readCountry(XMLStreamReader reader) throws XMLStreamException {
        String type = reader.getAttributeValue(null, "type");

        Discriminator discriminator = Discriminator.valueOf(type);
        Country country = null;

        if (COLD.equals(discriminator)) {
            country = new CountryWithColdClimate();
        } else {
            country = new CountryWithHotClimate();
        }

        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {

                case XMLStreamReader.START_ELEMENT: {
                    String tag = reader.getLocalName();

                    switch (tag) {
                        case "name": {
                            country.setName(readContent(reader));
                            break;
                        }
                        case "language": {
                            country.setLanguage(readContent(reader));
                            break;
                        }
                        case "cities": {
                            country.setCities(readCities(reader));
                            break;
                        }
                    }
                    getInformationAboutTypeOfCountry(country, tag, reader);
                    break;
                }

                case XMLStreamConstants.END_ELEMENT: {
                    return country;
                }
            }
        }
        throw new RuntimeException("I didn't find suitable end tag");
    }

    private void getInformationAboutTypeOfCountry(Country country, String tag, XMLStreamReader reader) throws XMLStreamException {
        if (country instanceof CountryWithColdClimate) {
            switch (tag) {
                case "polarNight": {
                    ((CountryWithColdClimate) country).setPolarNight(Boolean.parseBoolean(readContent(reader)));
                    break;
                }
            }

        } else {
            switch (tag) {
                case "hottestMonth": {
                    ((CountryWithHotClimate) country).setHottestMonth(readContent(reader));
                    break;
                }
                case "averageTemperature": {
                    ((CountryWithHotClimate) country).setAverageTemperature(Double.parseDouble(readContent(reader)));
                    break;
                }
            }
        }
    }

    private List<City> readCities(XMLStreamReader reader) throws XMLStreamException {
        List<City> cities = new ArrayList();
        while (reader.hasNext()) {
            int eventType = reader.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT: {
                    cities.add(readCity(reader));
                    break;
                }
                case XMLStreamConstants.END_ELEMENT: {
                    return cities;
                }
            }
        }

        throw new RuntimeException("I didn't find suitable end tag");
    }

    private City readCity(XMLStreamReader reader) throws XMLStreamException {
        City city = new City();

        while (reader.hasNext()) {
            int eventType = reader.next();
            switch (eventType) {

                case XMLStreamReader.START_ELEMENT: {
                    String tag = reader.getLocalName();
                    switch (tag) {
                        case "nameCity": {
                            city.setNameCity(readContent(reader));
                            break;
                        }
                        case "population": {
                            city.setPopulation(Double.parseDouble(readContent(reader)));
                            break;
                        }
                        case "capital": {
                            city.setCapital(Boolean.parseBoolean(readContent(reader)));
                            break;
                        }
                    }
                    break;
                }
                case XMLStreamConstants.END_ELEMENT: {
                    return city;
                }
            }

        }

        throw new RuntimeException("I didn't find suitable end tag");
    }
}
