package city.repo.impl.memory;

import city.domain.City;
import city.repo.CityRepo;
import city.search.CitySearchCondition;
import common.solution.utils.ArrayUtils;
import common.solution.utils.OptionalUtils;
import storage.SequenceGenerator;

import java.util.*;
import java.util.stream.IntStream;

import static storage.Storage.cityArray;

public class CityMemoryArrayRepo implements CityRepo {
    private int cityIndex = -1;

    @Override
    public void add(City city) {
        if (cityIndex == cityArray.length - 1) {
            City[] newArrCities = new City[cityArray.length * 2];
            System.arraycopy(cityArray, 0, newArrCities, 0, cityArray.length);
            cityArray = newArrCities;
        }

        cityIndex++;
        city.setId(SequenceGenerator.getNextValue());
        cityArray[cityIndex] = city;
    }

    @Override
    public void add(Collection<City> cities) {
        for (City city : cities) {
            add(city);
        }
    }

    @Override
    public Optional<City> findById(Long id) {
        return findCityIndexById(id).map(cityIndex -> cityArray[cityIndex]);
    }

//    @Override
//    public City findById(Long id) {
//        Integer cityIndex = findCityIndexById(id);
//        if (cityIndex != null) {
//            return cityArray[cityIndex];
//        }
//
//        return null;
//    }


    @Override
    public List<City> search(CitySearchCondition searchCondition) {
        return Collections.emptyList();
    }

    @Override
    public void update(City city) {
        //
    }

    @Override
    public void deleteById(Long id) {
        findCityIndexById(id).ifPresent(index -> deleteCityByIndex(index));
    }

//    @Override
//    public void deleteById(Long id) {
//        Integer cityIndex = findCityIndexById(id);
//
//        if (cityIndex != null) {
//            deleteCityByIndex(cityIndex);
//        }
//    }

    private void deleteCityByIndex(int index) {
        ArrayUtils.removeElement(cityArray, index);
        cityIndex--;
    }

//    private Integer findCityIndexById(long cityId) {
//        for (int i = 0; i < cityArray.length; i++) {
//            if (cityArray[i] != null && Long.valueOf(cityId).equals(cityArray[i].getId())) {
//                return i;
//            }
//        }
//        return null;
//    }

    private Optional<Integer> findCityIndexById(long cityId) {
        OptionalInt optionalInt = IntStream.range(0, cityArray.length).filter(i ->
                cityArray[i] != null && Long.valueOf(cityId).equals(cityArray[i].getId())).findAny();

        return OptionalUtils.valueOf(optionalInt);
    }


    @Override
    public void printAll() {
        for (City city : cityArray)
            if (city != null) {
                System.out.println(city);
            }
    }

    @Override
    public List<City> getAll() {
        return null;
    }
}
