package country.repo.impl.memory;

import city.repo.impl.memory.CityOrderingComponent;
import common.business.search.Paginator;
import common.solution.utils.CollectionUtils;
import country.domain.Country;
import country.domain.CountryWithColdClimate;
import country.domain.CountryWithHotClimate;
import country.domain.Discriminator;
import country.repo.CountryRepo;
import country.search.CountrySearchCondition;
import country.search.CountryWithColdClimateSearchCondition;
import country.search.CountryWithHotClimateSearchCondition;
import storage.SequenceGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static storage.Storage.countryList;

public class CountryMemoryCollectionRepo implements CountryRepo {
    private CityOrderingComponent orderingComponent = new CityOrderingComponent();

    @Override
    public void add(Country country) {
        country.setId(SequenceGenerator.getNextValue());
        countryList.add(country);
    }

    @Override
    public void add(Collection<Country> countries) {
        for (Country country : countries) {
            add(country);
        }
    }
//    @Override
//    public Country findById(Long id) {
//        return findCountryById(id);
//    }

//    private Country findCountryById(long countryId) {
//        for (Country country : countryList) {
//            if (Long.valueOf(countryId).equals(country.getId())) {
//                return country;
//            }
//        }
//        return null;
//    }

    @Override
    public Optional<Country> findById(Long id) {
        return findCountryById(id);
    }

    private Optional<Country> findCountryById(long countryId) {
        return countryList.stream().filter(country -> Long.valueOf(countryId).equals(country.getId())).findAny();
    }

    @Override
    public void update(Country country) {
        //
    }

    @Override
    public List<? extends Country> search(CountrySearchCondition searchCondition) {

        Discriminator discriminator = searchCondition.getDiscriminator();

        List<? extends Country> result = countryList;

        switch (discriminator) {
            case COLD: {
                result = searchCountryWithColdClimate((CountryWithColdClimateSearchCondition) searchCondition);
                break;
            }
            case HOT: {
                result = searchCountryWithHotClimate((CountryWithHotClimateSearchCondition) searchCondition);
                break;
            }
        }

        if (!result.isEmpty() && searchCondition.shouldPaginate()) {
            result = getPageableData(result, searchCondition.getPaginator());
        }
        return result;
    }

    private List<? extends Country> getPageableData(List<? extends Country> countries, Paginator paginator) {
        return CollectionUtils.getPageableData(countries, paginator.getLimit(), paginator.getOffset());
    }

    private List<CountryWithColdClimate> searchCountryWithColdClimate(CountryWithColdClimateSearchCondition searchCondition) {
        List<CountryWithColdClimate> result = new ArrayList<>();

        for (Country country : countryList) {

            if (Discriminator.COLD.equals(country.getDiscriminator())) {
                CountryWithColdClimate countryWithColdClimate = (CountryWithColdClimate) country;

                boolean found = true;
                if (searchCondition.searchByPolarNight()) {
                    found = searchCondition.getPolarNight().equals(countryWithColdClimate.isPolarNight());
                }

                if (found) {
                    result.add(countryWithColdClimate);
                }
            }

        }

        return result;
    }

    private List<CountryWithHotClimate> searchCountryWithHotClimate(CountryWithHotClimateSearchCondition searchCondition) {
        List<CountryWithHotClimate> result = new ArrayList<>();

        for (Country country : countryList) {

            if (Discriminator.HOT.equals(country.getDiscriminator())) {
                CountryWithHotClimate countryWithHotClimate = (CountryWithHotClimate) country;

                boolean found = true;
                if (searchCondition.searchByHottestMonth()) {
                    found = searchCondition.getHottestMonth().equals(countryWithHotClimate.getHottestMonth());
                }

                if (found && searchCondition.searchByAverageTemperature()) {
                    found = searchCondition.getAverageTemperature().equals(countryWithHotClimate.getAverageTemperature());
                }

                if (found) {
                    result.add(countryWithHotClimate);
                }
            }
        }

        return result;
    }

//    @Override
//    public void deleteById(Long id) {
//        Country found = findCountryById(id);
//
//        if (found != null) {
//            countryList.remove(found);
//        }
//    }

    @Override
    public void deleteById(Long id) {
        Optional<Country> foundOptional = findCountryById(id);
        foundOptional.map(country -> countryList.remove(country));
    }

    @Override
    public void printAll() {
        for (Country country : countryList) {
            System.out.println(country);
        }
    }

    @Override
    public List<Country> getAll() {
        return countryList;
    }
}
