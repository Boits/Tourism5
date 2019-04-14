package common.business.application.servicefactory;

import city.service.CityService;
import country.service.CountryService;
import order.service.OrderService;
import user.service.UserService;

public interface ServiceFactory {
    CityService getCityService();
    CountryService getCountryService();
    OrderService getOrderService();
    UserService getUserService();
}
