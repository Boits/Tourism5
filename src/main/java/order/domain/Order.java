package order.domain;

import city.domain.City;
import common.business.domain.BaseDomain;
import country.domain.Country;
import user.domain.User;

import java.util.Objects;

public class Order extends BaseDomain<Long> {
    private User user;
    private Country country;
    private City city;
    private double price;

    public Order() {
    }

    public Order(User user, Country country, City city, double price) {
        this.user = user;
        this.country = country;
        this.city = city;
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return new StringBuilder().append("Id: ").append(id).append("; User: ").append(user.getLastName()).append("; Country: ").append(country.getName()).append("; City: ").append(city.getNameCity()).append("; Price: ").append(price).toString();
    }
//    @Override
//    public String toString() {
//        return id + " " + user.getLastName() + " " + country.getNameCity() + " " + city.getNameCity() + " " + price;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Double.compare(order.price, price) == 0 &&
                Objects.equals(user, order.user) &&
                Objects.equals(country, order.country) &&
                Objects.equals(city, order.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, country, city, price);
    }
}
