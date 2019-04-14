package storage.initor.datasourcereader.dom;

import city.domain.City;
import country.domain.Country;
import country.domain.CountryWithColdClimate;
import country.domain.CountryWithHotClimate;
import country.domain.Discriminator;
import org.w3c.dom.*;
import common.solution.parser.XmlParser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static common.solution.utils.XmlDomUtils.getOnlyElementTextContent;
import static java.lang.Boolean.parseBoolean;
import static java.lang.Double.parseDouble;

public class CountriesWithCitiesXmlDomParser implements XmlParser<List<Country>> {

    @Override
    public List<Country> parseFile(String file) throws Exception {
        if (!new File(file).exists() || new File(file).isDirectory()) {
            throw new Exception("No such file");
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document doc = documentBuilder.parse(new File(file));

        Element root = (Element) doc.getElementsByTagName("countries").item(0);

        NodeList xmlCountries = root.getElementsByTagName("country");
        List<Country> result = new ArrayList<>();

        for (int i = 0; i < xmlCountries.getLength(); i++) {
            result.add(getCountryFromXmlElement((Element) xmlCountries.item(i)));
        }
        return result;
    }


    private Country getCountryFromXmlElement(Element xmlCountry) {

        String type = xmlCountry.getAttribute("type");

        Country country = null;
        switch (Discriminator.valueOf(type)) {

            case COLD: {
                country = new CountryWithColdClimate();
                CountryWithColdClimate coldClimate = (CountryWithColdClimate) country;
                coldClimate.setPolarNight(parseBoolean(getOnlyElementTextContent(xmlCountry, "polarNight")));
                break;
            }
            case HOT: {
                country = new CountryWithHotClimate();
                CountryWithHotClimate hotClimate = (CountryWithHotClimate) country;
                hotClimate.setHottestMonth(getOnlyElementTextContent(xmlCountry, "hottestMonth"));
                hotClimate.setAverageTemperature(parseDouble(getOnlyElementTextContent(xmlCountry, "averageTemperature")));
                break;
            }
        }

        country.setName(getOnlyElementTextContent(xmlCountry, "name"));
        country.setLanguage(getOnlyElementTextContent(xmlCountry, "language"));

        NodeList cities = xmlCountry.getElementsByTagName("city");
        if (cities.getLength() > 0) {
            country.setCities(new ArrayList<>());

            for (int i = 0; i < cities.getLength(); i++) {
                City city = getCityFromXmlElement(cities.item(i));
                country.getCities().add(city);
            }
        }

        return country;
    }

    private City getCityFromXmlElement(Node xmlCity) {

        City city = new City();

        city.setNameCity(getOnlyElementTextContent((Element) xmlCity, "nameCity"));
        city.setPopulation(parseDouble(getOnlyElementTextContent((Element) xmlCity, "population")));
        city.setCapital(parseBoolean(getOnlyElementTextContent((Element) xmlCity, "capital")));

        return city;
    }
}