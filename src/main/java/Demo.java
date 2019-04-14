import city.service.CityService;
import city.domain.City;
import common.business.application.StorageType;
import common.business.application.servicefactory.ServiceSupplier;
import country.domain.Country;
import country.domain.CountryWithColdClimate;
import country.domain.CountryWithHotClimate;
import country.service.CountryService;
import order.domain.Order;
import order.service.OrderService;
import reporting.DetailedReportAboutUsersAndTheirOrders;
import storage.initor.StorageInitializer;
import user.domain.User;
import user.service.UserService;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static common.solution.utils.RandomUtils.getRandomInt;

public class Demo {
    static {
        ServiceSupplier.newInstance(StorageType.MEMORY_COLLECTION);
    }

    private static final String INIT_DATA_XML_FILE = "/Users/boits/IdeaProjects/Epam/Tourism5/data.xml";
    private static final String INIT_DATA_XML_FILE2 = "/Users/boits/IdeaProjects/Epam/Tourism5/data2.xml";

    private static UserService userService = ServiceSupplier.getInstance().getUserService();
    private static OrderService orderService = ServiceSupplier.getInstance().getOrderService();
    private static CountryService countryService = ServiceSupplier.getInstance().getCountryService();
    private static CityService cityService = ServiceSupplier.getInstance().getCityService();

    private static void printUsers() {
        userService.printAll();
    }

    private static void printCities() {
        cityService.printAll();
    }

    private static void printCountries() {
        countryService.printAll();
    }

    private static void printOrders() {
        orderService.printAll();
    }

    private static void showAll() {
        System.out.println("----------Users------------");
        printUsers();

        System.out.println("----------Cities------------");
        printCities();

        System.out.println("----------Countries------------");
        printCountries();

        System.out.println("----------Orders------------");
        printOrders();
    }

    private static void fillStorageAll() {
        City city1 = new City("Moscow", 12, true);
        City city2 = new City("Saint-Petersburg", 5, false);
        City city3 = new City("San Francisco", 0.8, false);
        City city4 = new City("Dresden", 0.5, false);
        City city5 = new City("Berlin", 3.5, true);
        City city6 = new City("Paris", 2.2, true);

        Country country1 = new CountryWithColdClimate("Russia", "RU", true);
        List<City> cityListForCountry1 = new ArrayList<>();
        cityListForCountry1.add(city1);
        cityListForCountry1.add(city2);
        country1.setCities(cityListForCountry1);
        countryService.add(country1);


        Country country2 = new CountryWithColdClimate("USA", "EN", false);
        List<City> cityList2 = new ArrayList<>();
        cityList2.add(city3);
        country2.setCities(cityList2);
        countryService.add(country2);

        Country country3 = new CountryWithHotClimate("Germany", "GE", "July", 20);
        List<City> cityList3 = new ArrayList<>();
        cityList3.add(city4);
        cityList3.add(city5);
        country3.setCities(cityList3);
        countryService.add(country3);


        Country country4 = new CountryWithHotClimate("France", "FR", "June", 20);
        List<City> cityList4 = new ArrayList<>();
        cityList4.add(city6);
        country4.setCities(cityList4);
        countryService.add(country4);

        //countryService.add(new Country("Armenia", "HY"));
        //countryService.add(new Country("Belgium", "NL"));

        //cityService.add(new City("London", 8.1, true));

        User user1 = new User("Name1", "LastName1", 1111);
        User user2 = new User("Name2", "LastName2", 3333);
        User user3 = new User("Name3", "LastName3", 2222);
        User user4 = new User("Name4", "LastName4", 5555);
        User user5 = new User("Name5", "LastName5", 4444);
        User user6 = new User("Name6", "LastName6", 6666);
        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);
        userService.add(user5);
        userService.add(user6);

        Order order1 = new Order(user1, country1, city2, 5000);
        orderService.add(order1);

        Order order2 = new Order(user2, country2, city3, 10000);
        orderService.add(order2);

        Order order3 = new Order(user4, country4, city6, 4000);
        orderService.add(order3);

        Order order4 = new Order(user2, country3, city4, 10000);
        orderService.add(order4);

        Order order5 = new Order(user6, country4, city6, 4000);
        orderService.add(order5);

        Order order6 = new Order(user4, country1, city2, 5000);
        orderService.add(order6);

        List<Order> orderListForUser1 = new ArrayList<>();
        orderListForUser1.add(order1);
        user1.setOrders(orderListForUser1);

        List<Order> orderListForUser2 = new ArrayList<>();
        orderListForUser2.add(order2);
        orderListForUser2.add(order4);
        user2.setOrders(orderListForUser2);

        List<Order> orderListForUser4 = new ArrayList<>();
        orderListForUser4.add(order3);
        orderListForUser4.add(order6);
        user4.setOrders(orderListForUser4);

        List<Order> orderListForUser6 = new ArrayList<>();
        orderListForUser6.add(order5);
        user6.setOrders(orderListForUser6);

        showAll();

//        System.out.println("============== Delete orders =====================");
//        orderService.delete(order1);
//
//        System.out.println("============== Delete users ======================");
//        userService.delete(user2);
//         userService.delete(user3); // throw UserExceptionMeta
//        System.out.println("============== Delete cities =====================");
//        cityService.delete(city1);  // without order
//        cityService.delete(city6);

//        System.out.println("============== Delete countries ====================");
//        countryService.delete(country1);
//        countryService.delete(country4);
//
        showAll();
    }

    private static void addUsers() {
        User user1 = new User("Name1", "LastName1", 1111);
        User user2 = new User("Name2", "LastName2", 3333);
        User user3 = new User("Name3", "LastName3", 2222);
        User user4 = new User("Name4", "LastName4", 5555);
        User user5 = new User("Name5", "LastName5", 4444);
        User user6 = new User("Name6", "LastName6", 6666);
        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);
        userService.add(user5);
        userService.add(user6);
    }

    private static List<File> getFilesWithDataToInit() {
        List<File> result = new ArrayList<>();

        result.add(new File(INIT_DATA_XML_FILE));
        result.add(new File(INIT_DATA_XML_FILE2));

        return result;
    }

    private static void fillStorageWithCountriesAndCities() throws Exception {

        StorageInitializer storageInitor = new StorageInitializer(countryService);
        //File fileWithInitData;
        List<File> filesWithInitData = null;
        try {
            //fileWithInitData = new File(INIT_DATA_XML_FILE);
            filesWithInitData = getFilesWithDataToInit();
            storageInitor.initStorageWithCountriesAndCities(filesWithInitData, StorageInitializer.DataSourceType.XML_FILE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
//        finally {
//            if (filesWithInitData != null) {
//                for (File file : filesWithInitData) {
//                    Files.delete(Paths.get(file.toURI()));
//                }
//            }
//        }
    }

    private static void addOrders() {
        List<Country> countries = countryService.getAll();
        List<User> users = userService.getAll();

        List<Order> orders = new ArrayList<>();
        for (User user : users) {
            orders.add(prepareOrderForUser(user, countries));
        }

        for (Order order : orders) {
            orderService.add(order);
        }
    }

    private static Order prepareOrderForUser(User user, List<Country> countries) {
        Order order = new Order();
        order.setUser(user);
        Country country = countries.get(getRandomInt(0, countries.size() - 1));
        order.setCountry(country);
        order.setCity(country.getCities().get(getRandomInt(0, country.getCities().size() - 1)));
        order.setPrice(getRandomInt(1, 100000));

//        Order order = new Order();
//        order.setUser(user);
//        order.setCountry(countries.get(count));
//        order.setCity(countries.get(count).getCities().get(countCity++));
//        count++;
//        order.setPrice(getRandomInt(1, 100000));

//        for (Country country : countries) {
//            order.setCountry(country);
//            for (City city : country.getCities()) {
//                order.setCity(city);
//                order.setPrice(getRandomInt(1, 100000));
//            }
//        }

        return order;
    }

    public static void main(String[] args) throws Exception {
        //fillStorageAll();

        addUsers();

        fillStorageWithCountriesAndCities();

        addOrders();

        showAll();

        System.out.println('\n' + "Add information about users and orders to file 'InformationAboutUsers.txt' ");

        DetailedReportAboutUsersAndTheirOrders.getInformationAboutUsers(userService, orderService);

    }

}