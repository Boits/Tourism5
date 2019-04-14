package order.dto;

import city.dto.CityDto;
import common.business.dto.BaseDto;
import country.dto.CountryDto;
import user.dto.UserDto;

public class OrderDto extends BaseDto<Long> {
    private UserDto user;
    private CountryDto country;
    private CityDto city;
    private double price;

    OrderDto() {

    }


    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public CountryDto getCountry() {
        return country;
    }

    public void setCountry(CountryDto country) {
        this.country = country;
    }

    public CityDto getCity() {
        return city;
    }

    public void setCity(CityDto city) {
        this.city = city;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
