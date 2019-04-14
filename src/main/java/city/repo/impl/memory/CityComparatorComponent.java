package city.repo.impl.memory;

import city.domain.City;
import city.search.CityOrderByField;

import java.util.*;

import static city.search.CityOrderByField.NAMECITY;
import static city.search.CityOrderByField.POPULATION;
import static common.business.repo.memory.CommonComparatorHolder.getComparatorForNullableStrings;


public final class CityComparatorComponent {
    private static final CityComparatorComponent INSTANCE = new CityComparatorComponent();
    private static Map<CityOrderByField, Comparator<City>> comparatorsByField = new HashMap<>();
    /**
     * For complex comparator only
     */
    private static Set<CityOrderByField> fieldComparePriorityOrder = new LinkedHashSet<>(Arrays.asList(NAMECITY, POPULATION));

    static {
        comparatorsByField.put(NAMECITY, getComparatorForNameCityField());
        //comparatorsByField.put(POPULATION, getComparatorForPopulationField());
    }

    private CityComparatorComponent() {
    }


    public static CityComparatorComponent getInstance() {
        return INSTANCE;
    }

    private static Comparator<City> getComparatorForNameCityField() {
        return new Comparator<City>() {
            @Override
            public int compare(City city1, City city2) {
                return getComparatorForNullableStrings().compare(city1.getNameCity(), city2.getNameCity());
            }
        };
    }

//    private static Comparator<City> getComparatorForPopulationField() {
//        return new Comparator<City>() {
//            @Override
//            public int compare(City city1, City city2) {
//                return getComparatorForNullableStrings().compare(city1.getPopulation(), city2.getPopulation());
//            }
//        };
//    }

    public Comparator<City> getComparatorForField(CityOrderByField field) {
        return comparatorsByField.get(field);
    }

    public Comparator<City> getComplexComparator(CityOrderByField field) {
        return new Comparator<City>() {

            @Override
            public int compare(City c1, City c2) {
                int result = 0;
                Comparator<City> cityComparator = comparatorsByField.get(field);

                if (cityComparator != null) {
                    result = cityComparator.compare(c1, c2);
                    //if records have same order priority, i want to order them in their group
                    if (result == 0) {

                        //loop through all possible sorting fields
                        for (CityOrderByField otherField : fieldComparePriorityOrder) {
                            //if i haven't sorted by field which is taken from parameter in function, i do sorting
                            if (!otherField.equals(field)) {

                                result = comparatorsByField.get(otherField).compare(c1, c2);
                                //if sort result detected that records are not equals - we exit from loop,
                                //else continue
                                if (result != 0) {
                                    break;
                                }
                            }
                        }

                    }
                }

                return result;
            }
        };
    }
}
