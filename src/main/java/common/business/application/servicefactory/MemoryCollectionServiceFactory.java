package common.business.application.servicefactory;

import city.repo.impl.memory.CityMemoryArrayRepo;
import city.repo.impl.memory.CityMemoryCollectionRepo;
import city.service.CityService;
import city.service.impl.CityDefaultService;
import country.repo.impl.memory.CountryMemoryCollectionRepo;
import country.service.CountryService;
import country.service.impl.CountryDefaultService;
import order.repo.impl.memory.OrderMemoryArrayRepo;
import order.repo.impl.memory.OrderMemoryCollectionRepo;
import order.service.OrderService;
import order.service.impl.OrderDefaultService;
import user.repo.impl.memory.UserMemoryCollectionRepo;
import user.service.UserService;
import user.service.impl.UserDefaultService;

public class MemoryCollectionServiceFactory implements ServiceFactory {

    @Override
    public CityService getCityService() {
        return new CityDefaultService(new CityMemoryCollectionRepo(), new OrderMemoryCollectionRepo());
    }

    @Override
    public CountryService getCountryService() {
        return new CountryDefaultService(new CountryMemoryCollectionRepo(), new CityMemoryCollectionRepo(), new OrderMemoryCollectionRepo(), new CityDefaultService(new CityMemoryArrayRepo(), new OrderMemoryArrayRepo()));
    }

    @Override
    public OrderService getOrderService() {
        return new OrderDefaultService(new OrderMemoryCollectionRepo());
    }

    @Override
    public UserService getUserService() {
        return new UserDefaultService(new UserMemoryCollectionRepo(), new OrderMemoryCollectionRepo());
    }
}