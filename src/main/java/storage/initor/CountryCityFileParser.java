package storage.initor;

import common.solution.parser.XmlParser;
import country.domain.Country;
import storage.initor.datasourcereader.dom.CountriesWithCitiesXmlDomParser;
import storage.initor.datasourcereader.sax.CountriesWithCitiesXmlSaxParser;
import storage.initor.datasourcereader.stax.CountriesWithCitiesXmlStaxParser;

import java.io.File;
import java.util.List;

public class CountryCityFileParser implements Runnable {

    private StorageInitializer.DataSourceType dataSourceType;
    private List<Country> countries;
    private Thread thread;
    private File fileToParse;

    public CountryCityFileParser(StorageInitializer.DataSourceType dataSourceType, File file) {
        this.dataSourceType = dataSourceType;
        thread = new Thread(this);
        fileToParse = file;
    }

    @Override
    public void run() {
        try {
            countries = getCountriesFromStorage(fileToParse.getAbsolutePath(), dataSourceType);
        } catch (Exception e) {
            System.out.println("Error while parser file with countries");
        }
    }

    public synchronized List<Country> getCountries() {
        return countries;
    }

    public void asyncParseCountries() {
        thread.start();
    }

    public void blockUntilJobIsFinished() throws InterruptedException {
        thread.join();
    }

    private List<Country> getCountriesFromStorage(String filePath, StorageInitializer.DataSourceType dataSourceType) throws Exception {

        XmlParser<List<Country>> dataSourceReader = null;

        switch (dataSourceType) {

            case XML_FILE: {
//                dataSourceReader = new CountriesWithCitiesXmlDomParser();
//                dataSourceReader = new CountriesWithCitiesXmlSaxParser();
                dataSourceReader = new CountriesWithCitiesXmlStaxParser();
                break;
            }
        }

        return dataSourceReader.parseFile(filePath);
    }

}
