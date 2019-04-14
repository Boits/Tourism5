package storage.initor;

import country.domain.Country;
import country.service.CountryService;
import common.solution.parser.XmlParser;
import storage.initor.datasourcereader.stax.CountriesWithCitiesXmlStaxParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StorageInitializer {
    private CountryService countryService;

    public StorageInitializer(CountryService countryService) {
        this.countryService = countryService;
    }

    public enum DataSourceType {
        TXT_FILE, XML_FILE
    }

    /*
        public void initStorageWithCountriesAndCities(String filePath, DataSourceType dataSourceType) throws Exception {

            List<Country> countryList = getCountriesFromStorage(filePath, dataSourceType);

            if (!countryList.isEmpty()) {
                for (Country country : countryList) {
                    countryService.add(country);
                }
            }
        }

        private List<Country> getCountriesFromStorage(String filePath, DataSourceType dataSourceType) throws Exception {

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
    */

    public void initStorageWithCountriesAndCities(List<File> files, DataSourceType dataSourceType) throws Exception {
        List<CountryCityFileParser> countryCityFileParser = prepareAsyncParsers(files, dataSourceType);
        List<Country> countriesToPersist = asyncParseFilesAndWaitForResult(countryCityFileParser);
        countryService.add(countriesToPersist);
    }

    private List<CountryCityFileParser> prepareAsyncParsers(List<File> files, DataSourceType dataSourceType) {
        List<CountryCityFileParser> countryCityFileParser = new ArrayList<>();
        for (File file : files) {
            countryCityFileParser.add(new CountryCityFileParser(dataSourceType, file));
        }
        return countryCityFileParser;
    }

    private List<Country> asyncParseFilesAndWaitForResult(List<CountryCityFileParser> workers) throws Exception {
        for (CountryCityFileParser worker : workers) {
            worker.asyncParseCountries();
        }

        List<Country> countriesToPersist = new ArrayList<>();
        for (CountryCityFileParser worker : workers) {
            worker.blockUntilJobIsFinished();
            countriesToPersist.addAll(worker.getCountries());
        }

        return countriesToPersist;

    }


}
