package city.repo.impl.memory;

import city.domain.City;
import city.repo.CityRepo;
import city.search.CitySearchCondition;
import common.business.search.Paginator;
import common.solution.utils.CollectionUtils;
import storage.SequenceGenerator;
import storage.Storage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static storage.Storage.cityList;

public class CityMemoryCollectionRepo implements CityRepo {
    private CityOrderingComponent orderingComponent = new CityOrderingComponent();

    @Override
    public void add(City city) {
        city.setId(SequenceGenerator.getNextValue());
        cityList.add(city);
    }

    @Override
    public void add(Collection<City> cities) {
        for (City city : cities) {
            add(city);
        }
    }

    @Override
    public Optional<City> findById(Long id) {
        return findCityById(id);
    }

//    @Override
//    public City findById(Long id) {
//        return findCityById(id);
//    }

    @Override
    public List<City> search(CitySearchCondition searchCondition) {
        List<City> result = doSearch(searchCondition);

        boolean needOrdering = !result.isEmpty() && searchCondition.needOrdering();
        if (needOrdering) {
            orderingComponent.applyOrdering(result, searchCondition);
        }

        if (!result.isEmpty() && searchCondition.shouldPaginate()) {
            result = getPageableData(result, searchCondition.getPaginator());
        }

        return result;
    }

    private List<City> doSearch(CitySearchCondition searchCondition) {
        List<City> result = new ArrayList<>();

        List<City> cityList1 = Storage.cityList;
        for (City city : cityList1) {
            if (city != null) {
                boolean found = true;

                if (searchCondition.searchByNameCity()) {
                    found = searchCondition.getNameCity().equals(city.getNameCity());
                }

                if (found) {
                    result.add(city);
                }
            }
        }

        return result;
    }

    private List<City> getPageableData(List<City> cities, Paginator paginator) {
        return CollectionUtils.getPageableData(cities, paginator.getLimit(), paginator.getOffset());
    }

    @Override
    public void update(City city) {
        //
    }

    @Override
    public void deleteById(Long id) {
        findCityById(id).map(model -> cityList.remove(model));
    }

//    @Override
//    public void deleteById(Long id) {
//        City found = findCityById(id);
//
//        if (found != null) {
//            cityList.remove(found);
//        }
//    }

    @Override
    public void printAll() {
        for (City city : cityList) {
            System.out.println(city);
        }
    }

    private Optional<City> findCityById(long modelId) {
        return cityList.stream().filter(model -> Long.valueOf(modelId).equals(model.getId())).findAny();
    }
//    private City findCityById(long cityId) {
//        for (City city : cityList) {
//            if (Long.valueOf(cityId).equals(city.getId())) {
//                return city;
//            }
//        }
//        return null;
//    }

    @Override
    public List<City> getAll() {
        return cityList;
    }

}

