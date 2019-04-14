package order.repo.impl.memory;

import common.business.search.Paginator;
import common.solution.utils.CollectionUtils;
import order.domain.Order;
import order.repo.OrderRepo;
import order.search.OrderSearchCondition;
import storage.SequenceGenerator;

import java.util.*;

import static storage.Storage.orderList;

public class OrderMemoryCollectionRepo implements OrderRepo {

    @Override
    public void add(Order order) {
        order.setId(SequenceGenerator.getNextValue());
        orderList.add(order);
    }

    @Override
    public void add(Collection<Order> orders) {
        for (Order order : orders) {
            add(order);
        }
    }

//    @Override
//    public Order findById(Long id) {
//        return findOrderById(id);
//    }

    @Override
    public Optional<Order> findById(Long id) {
        return findOrderById(id);
    }

    @Override
    public void update(Order entity) {
        //
    }

    @Override
    public List<Order> search(OrderSearchCondition searchCondition) {
        List<Order> orders = doSearch(searchCondition);

        if (!orders.isEmpty() && searchCondition.shouldPaginate()) {
            orders = getPageableData(orders, searchCondition.getPaginator());
        }

        return orders;
    }

    private List<Order> doSearch(OrderSearchCondition searchCondition) {
        return orderList;
    }

    private List<Order> getPageableData(List<Order> orders, Paginator paginator) {
        return CollectionUtils.getPageableData(orders, paginator.getLimit(), paginator.getOffset());
    }

//    @Override
//    public void deleteById(Long id) {
//        Order found = findOrderById(id);
//
//        if (found != null) {
//            orderList.remove(found);
//        }
//    }

    @Override
    public void deleteById(Long id) {
        findOrderById(id).map(order -> orderList.remove(order));
    }


    @Override
    public void printAll() {
        for (Order order : orderList) {
            System.out.println(order);
        }
    }

    @Override
    public List<Order> getAll() {
        return orderList;
    }

//    private Order findOrderById(long orderId) {
//        for (Order order : orderList) {
//            if (Long.valueOf(orderId).equals(order.getId())) {
//                return order;
//            }
//        }
//        return null;
//    }

    private Optional<Order> findOrderById(long orderId) {
        return orderList.stream().filter(order -> Long.valueOf(orderId).equals(order.getId())).findAny();
    }

    @Override
    public List<Order> findByUserId(long userId) {
        List<Order> ordersWithUserId = new ArrayList<>();

        for (Order order : orderList) {
            if (order.getUser().getId().equals(userId)) {
                ordersWithUserId.add(order);
            }
        }

        return ordersWithUserId;
    }

}