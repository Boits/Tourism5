package common.business.application.servicefactory;

import city.service.CityService;
import city.repo.impl.memory.CityMemoryArrayRepo;
import city.service.impl.CityDefaultService;
import country.repo.impl.memory.CountryMemoryArrayRepo;
import country.service.CountryService;
import country.service.impl.CountryDefaultService;
import order.repo.impl.memory.OrderMemoryArrayRepo;
import order.service.OrderService;
import order.service.impl.OrderDefaultService;
import user.repo.impl.memory.UserMemoryArrayRepo;
import user.service.UserService;
import user.service.impl.UserDefaultService;

public class MemoryArrayServiceFactory implements ServiceFactory {

    @Override
    public CityService getCityService() {
        return new CityDefaultService(new CityMemoryArrayRepo(), new OrderMemoryArrayRepo());
    }

    @Override
    public CountryService getCountryService() {
        return new CountryDefaultService(new CountryMemoryArrayRepo(), new CityMemoryArrayRepo(), new OrderMemoryArrayRepo(), new CityDefaultService(new CityMemoryArrayRepo(), new OrderMemoryArrayRepo()));
    }

    @Override
    public OrderService getOrderService() {
        return new OrderDefaultService(new OrderMemoryArrayRepo());
    }

    @Override
    public UserService getUserService() {
        return new UserDefaultService(new UserMemoryArrayRepo(), new OrderMemoryArrayRepo());
    }
}