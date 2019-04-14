package country.repo;

import common.solution.repo.BaseRepo;
import country.domain.Country;
import country.search.CountrySearchCondition;

import java.util.List;

public interface CountryRepo extends BaseRepo<Country, Long> {

    List<? extends Country> search(CountrySearchCondition searchCondition);
}
