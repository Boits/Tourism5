package country.service.impl;

import city.service.CityService;
import country.domain.Country;
import city.domain.City;
import country.repo.CountryRepo;
import city.repo.CityRepo;
import country.search.CountrySearchCondition;
import country.service.CountryService;
import order.domain.Order;
import order.repo.OrderRepo;


import java.util.*;

public class CountryDefaultService implements CountryService {
    private final CountryRepo countryRepo;
    private final CityRepo cityRepo;
    private final OrderRepo orderRepo;
    private final CityService cityService;

    public CountryDefaultService(CountryRepo countryRepo, CityRepo cityRepo, OrderRepo orderRepo, CityService cityService) {
        this.countryRepo = countryRepo;
        this.cityRepo = cityRepo;
        this.orderRepo = orderRepo;
        this.cityService = cityService;
    }

    /*
      @Override
      public void add(Country country) {
          if (country != null) {
              countryRepo.add(country);

              if (country.getCities() != null && !country.getCities().isEmpty()) {

                  for (City city : country.getCities()) {
                      city.setCityId(country.getId());
                      cityService.add(city);
                  }

              }
          }
      }

      @Override
      public void add(Collection<Country> countries) {
          if (countries != null && !countries.isEmpty()) {
              for (Country country : countries) {
                  countryRepo.add(country);

                  if (country.getCities() != null && !country.getCities().isEmpty()) {
                      country.getCities().replaceAll(city -> {
                          city.setCityId(country.getId());
                          return city;
                      });
                      cityService.add(country.getCities());
                  }
              }
          }
      }
       */

    @Override
    public void add(Country country) {
        if (country != null) {
            countryRepo.add(country);

            if (country.getCities() != null) {
                for (City city : country.getCities()) {
                    if (city != null) {
                        cityRepo.add(city);
                    }
                }
            }
        }
    }

    @Override
    public void add(Collection<Country> countries) {
        if (countries != null && !countries.isEmpty()) {
            for (Country country : countries) {
                countryRepo.add(country);

                if (country.getCities() != null && !country.getCities().isEmpty()) {
                    cityRepo.add(country.getCities());
                }
            }
        }
    }

//    @Override
//    public Country findById(Long id) {
//        if (id != null) {
//            return countryRepo.findById(id);
//        } else {
//            return null;
//        }
//    }

    @Override
    public Optional<Country> findById(Long id) {
        if (id != null) {
            return countryRepo.findById(id);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void delete(Country country) {
        if (country.getId() != null) {
            deleteOrdersByCountry(country);
            deleteById(country.getId());
        }
    }

    private void deleteOrdersByCountry(Country country) {

        List<Order> orders = orderRepo.getAll();
        Iterator<Order> iterator = orders.iterator();

        while (iterator.hasNext()) {
            Order order = iterator.next();
            if (country.getId().equals(order.getCountry().getId())) {
                iterator.remove();
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        if (id != null) {
            countryRepo.deleteById(id);
        }
    }

    @Override
    public void printAll() {
        countryRepo.printAll();
    }

    @Override
    public List<Country> getAll() {
        return countryRepo.getAll();
    }


    @Override
    public List<? extends Country> search(CountrySearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            return countryRepo.findById(searchCondition.getId()).map(Collections::singletonList).orElse(Collections.emptyList());
        } else {
            return countryRepo.search(searchCondition);
        }
    }

    @Override
    public void update(Country country) {
        if (country.getId() != null) {
            countryRepo.update(country);
        }
    }
}
