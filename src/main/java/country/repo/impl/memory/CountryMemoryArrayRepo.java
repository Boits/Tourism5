package country.repo.impl.memory;

import city.repo.impl.memory.CityOrderingComponent;
import common.business.search.Paginator;
import common.solution.utils.ArrayUtils;
import common.solution.utils.CollectionUtils;
import common.solution.utils.OptionalUtils;
import country.domain.Country;
import country.domain.CountryWithColdClimate;
import country.domain.CountryWithHotClimate;
import country.domain.Discriminator;
import country.repo.CountryRepo;
import country.search.CountrySearchCondition;
import country.search.CountryWithColdClimateSearchCondition;
import country.search.CountryWithHotClimateSearchCondition;
import storage.SequenceGenerator;

import java.util.*;
import java.util.stream.IntStream;

import static common.solution.utils.StringUtils.isNotBlank;
import static storage.Storage.countryArray;
import static storage.Storage.countryList;

public class CountryMemoryArrayRepo implements CountryRepo {
    private static final Country[] EMPTY_COUNTRIES_ARR = new Country[0];
    private CityOrderingComponent orderingComponent = new CityOrderingComponent();
    private int countryIndex = -1;

    @Override
    public void add(Country country) {
        if (countryIndex == countryArray.length - 1) {
            Country[] newArr = new Country[countryArray.length * 2];
            System.arraycopy(countryArray, 0, newArr, 0, countryArray.length);
            countryArray = newArr;
        }

        countryIndex++;
        country.setId(SequenceGenerator.getNextValue());
        countryArray[countryIndex] = country;
    }

    @Override
    public void add(Collection<Country> countries) {
        for (Country country : countries) {
            add(country);
        }
    }

//    @Override
//    public Country findById(Long id) {
//        Integer countryIndex = findCountryIndexById(id);
//        if (countryIndex != null) {
//            return countryArray[countryIndex];
//        }
//
//        return null;
//    }

    @Override
    public Optional<Country> findById(Long id) {
        return findCountryIndexById(id).map(markIndex -> countryArray[countryIndex]);
    }

//    private Integer findCountryIndexById(long countryId) {
//        for (int i = 0; i < countryArray.length; i++) {
//            if (countryArray[i] != null && Long.valueOf(countryId).equals(countryArray[i].getId())) {
//                return i;
//            }
//        }
//        return null;
//    }

    private Optional<Integer> findCountryIndexById(long countryId) {
        OptionalInt optionalInt = IntStream.range(0, countryArray.length).filter(i ->
                countryArray[i] != null && Long.valueOf(countryId).equals(countryArray[i].getId())
        ).findAny();

        return OptionalUtils.valueOf(optionalInt);
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

    @Override
    public void update(Country country) {
        //
    }

//    @Override
//    public void deleteById(Long id) {
//        Integer countryIndex = findCountryIndexById(id);
//
//        if (countryIndex != null) {
//            deleteCountryByIndex(countryIndex);
//        }
//    }

    @Override
    public void deleteById(Long id) {
        findCountryIndexById(id).ifPresent(index -> deleteCountryByIndex(index));
    }

    private void deleteCountryByIndex(int index) {
        ArrayUtils.removeElement(countryArray, index);
        countryIndex--;
    }

    @Override
    public void printAll() {
        for (Country country : countryArray) {
            if (country != null) {
                System.out.println(country);
            }
        }
    }

    @Override
    public List<Country> getAll() {
        return null;
    }


}
