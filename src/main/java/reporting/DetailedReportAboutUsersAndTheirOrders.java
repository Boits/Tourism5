package reporting;

import order.domain.Order;
import order.service.OrderService;
import user.domain.User;
import user.service.UserService;

import java.io.*;
import java.util.List;

public class DetailedReportAboutUsersAndTheirOrders {

    public static final String FILE_PATH = "/Users/boits/IdeaProjects/Epam/Tourism5/InformationAboutUsers";

    public static void getInformationAboutUsers(UserService userService, OrderService orderService) {

        List<User> users = userService.getAll();

        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            for (User user : users) {

                writer.write("User:" + '\n');
                writer.write("Full name: " + user.getFirstName() + " " + user.getLastName() + '\n');
                if (users != null && !users.isEmpty()) {
                    List<Order> orders = orderService.getOrdersByUser(user.getId());

                    writer.write("Total orders: " + orders.size() + '\n');
                    if (orders.size() != 0) {
                        writer.write("Orders:" + '\n');
                        int count = 1;
                        for (Order order : orders) {
                            writer.write((count++) + ". Country: " + order.getCountry().getName() + "; City: " + order.getCity().getNameCity() + "; Price: " + order.getPrice());
                            writer.append('\n');
                        }
                    } else {
                        writer.write("User hasn't orders");
                        writer.append('\n');
                    }
                }

                writer.append("==============================" + '\n');
                writer.flush();
            }

        } catch (IOException e) {
        }
    }
}


