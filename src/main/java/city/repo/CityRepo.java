package city.repo;

import city.domain.City;
import city.search.CitySearchCondition;
import common.solution.repo.BaseRepo;

import java.util.List;

public interface CityRepo extends BaseRepo<City, Long> {

    List<City> search(CitySearchCondition searchCondition);
}
