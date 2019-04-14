package country.service;

import common.solution.service.BaseService;
import country.domain.Country;
import country.search.CountrySearchCondition;

import java.util.List;

public interface CountryService extends BaseService<Country, Long> {

    List<? extends Country> search(CountrySearchCondition searchCondition);

}
