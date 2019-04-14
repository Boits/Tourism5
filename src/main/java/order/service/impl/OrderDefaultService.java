package order.service.impl;

import order.domain.Order;
import order.repo.OrderRepo;
import order.search.OrderSearchCondition;
import order.service.OrderService;


import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

public class OrderDefaultService implements OrderService {

    private final OrderRepo orderRepo;

    public OrderDefaultService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public void add(Order order) {
        if (order != null) {
            orderRepo.add(order);
        }
    }

    @Override
    public void add(Collection<Order> orders) {
        if (orders != null && !orders.isEmpty()) {
            orderRepo.add(orders);
        }
    }

    //    @Override
//    public Order findById(Long id) {
//        if (id != null) {
//            return orderRepo.findById(id);
//        } else {
//            return null;
//        }
//    }
    @Override
    public Optional<Order> findById(Long id) {
        if (id != null) {
            return orderRepo.findById(id);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void update(Order entity) {
        //
    }

    @Override
    public void delete(Order order) {
        if (order.getId() != null) {
            deleteById(order.getId());
        }
    }

//    @Override
//    public List<Order> search(OrderSearchCondition searchCondition) {
//        if (searchCondition.getId() != null) {
//            return Collections.singletonList(orderRepo.findById(searchCondition.getId()));
//        } else {
//            return orderRepo.search(searchCondition);
//        }
//    }

    @Override
    public List<Order> search(OrderSearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            return orderRepo.findById(searchCondition.getId()).map(Collections::singletonList).orElse(emptyList());
        } else {
            return orderRepo.search(searchCondition);
        }
    }

    @Override
    public void deleteById(Long id) {
        if (id != null) {
            orderRepo.deleteById(id);
        }
    }

    @Override
    public void printAll() {
        orderRepo.printAll();
    }

    @Override
    public List<Order> getAll() {
        return orderRepo.getAll();
    }

    @Override
    public List<Order> getOrdersByUser(Long userId) {
        if (userId != null) {
            return orderRepo.findByUserId(userId);
        }

        return Collections.emptyList();
    }

}