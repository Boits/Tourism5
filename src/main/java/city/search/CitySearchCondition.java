package city.search;

import common.business.search.BaseSearchCondition;

import static common.solution.utils.StringUtils.isNotBlank;

public class CitySearchCondition extends BaseSearchCondition<Long> {
    private String nameCity;
    private CityOrderByField orderByField;

    public CityOrderByField getOrderByField() {
        return orderByField;
    }

    public void setOrderByField(CityOrderByField orderByField) {
        this.orderByField = orderByField;
    }

    public boolean needOrdering() {
        return super.needOrdering() && orderByField != null;
    }

    public boolean searchByNameCity() {
        return isNotBlank(nameCity);
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }
}
