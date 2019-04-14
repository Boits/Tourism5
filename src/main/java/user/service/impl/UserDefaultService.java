package user.service.impl;

import order.domain.Order;
import order.repo.OrderRepo;
import user.domain.User;
import user.exception.UserExceptionMeta;
import user.exception.checked.UserHasNoOrdersException;
import user.repo.UserRepo;
import user.search.UserSearchCondition;
import user.service.UserService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static user.exception.UserExceptionMeta.USER_HAS_NO_ORDERS_EXCEPTION;

public class UserDefaultService implements UserService {

    private final UserRepo userRepo;
    private final OrderRepo orderRepo;

    public UserDefaultService(UserRepo userRepo, OrderRepo orderRepo) {
        this.userRepo = userRepo;
        this.orderRepo = orderRepo;
    }

    @Override
    public void add(User user) {
        if (user != null) {
            userRepo.add(user);
        }
    }

    @Override
    public void add(Collection<User> users) {
        if (users != null && !users.isEmpty()) {
            userRepo.add(users);
        }
    }
    /*
    // this add() not good

    @Override
    public void add(User user) {
        if (user != null) {
            userRepo.add(user);

            if (user.getOrders() != null) {
                for (Order order : user.getOrders()) {
                    if (order != null) {
                        orderRepo.add(order);
                    }
                }
            }
        }
    }
    */

//    @Override
//    public User findById(Long id) {
//        if (id != null) {
//            return userRepo.findById(id);
//        } else {
//            return null;
//        }
//    }

    @Override
    public Optional<User> findById(Long id) {
        if (id != null) {
            return userRepo.findById(id);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void delete(User user) {
        if (user.getId() != null) {
            try {
                deleteOrdersByUser(user);
            } catch (UserHasNoOrdersException e) {
                System.out.println(e.getMessage());
            }
        }
        deleteById(user.getId());
    }

    private void deleteOrdersByUser(User user) throws UserHasNoOrdersException {

        if (user.getOrders() != null) {
            for (Order order : user.getOrders()) {
                orderRepo.deleteById(order.getId());
            }
        } else {
            throw new UserHasNoOrdersException(USER_HAS_NO_ORDERS_EXCEPTION);
        }
    }

    @Override
    public List<? extends User> search(UserSearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            return userRepo.findById((Long) searchCondition.getId()).map(Collections::singletonList).orElse(emptyList()); //?
        } else {
            return userRepo.search(searchCondition);
        }
    }
    @Override
    public void update(User user) {
        if (user.getId() != null) {
            userRepo.update(user);
        }
    }

    @Override
    public void deleteById(Long id) {
        if (id != null) {
            userRepo.deleteById(id);
        }
    }

    @Override
    public void printAll() {
        userRepo.printAll();
    }

    @Override
    public List<User> getAll() {
        return userRepo.getAll();
    }
}