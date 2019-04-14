package storage.initor.datasourcereader.sax;

import country.domain.Country;
import common.solution.parser.XmlParser;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.List;

public class CountriesWithCitiesXmlSaxParser implements XmlParser<List<Country>> {
    @Override
    public List<Country> parseFile(String file) throws Exception {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();

        CountriesWithCitiesXmlSaxHandler handler = new CountriesWithCitiesXmlSaxHandler();
        saxParser.parse(new File(file), handler);

        return handler.getCountries();
    }
}
