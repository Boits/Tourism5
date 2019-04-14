package order.repo.impl.memory;

import common.solution.utils.ArrayUtils;
import common.solution.utils.OptionalUtils;
import order.domain.Order;
import order.repo.OrderRepo;
import order.search.OrderSearchCondition;
import storage.SequenceGenerator;

import java.util.*;
import java.util.stream.IntStream;

import static storage.Storage.orderArray;


public class OrderMemoryArrayRepo implements OrderRepo {
    private static final Order[] EMPTY_ORDERS_ARR = new Order[0];
    private int orderIndex = -1;

    @Override
    public void add(Order order) {
        if (orderIndex == orderArray.length - 1) {
            Order[] newArrOrders = new Order[orderArray.length * 2];
            System.arraycopy(orderArray, 0, newArrOrders, 0, orderArray.length);
            orderArray = newArrOrders;
        }

        orderIndex++;
        order.setId(SequenceGenerator.getNextValue());
        orderArray[orderIndex] = order;
    }

    @Override
    public void add(Collection<Order> orders) {
        for (Order order : orders) {
            add(order);
        }
    }

//    @Override
//    public Order findById(Long id) {
//        Integer orderIndex = findOrderIndexById(id);
//        if (orderIndex != null) {
//            return orderArray[orderIndex];
//        }
//
//        return null;
//    }

    @Override
    public Optional<Order> findById(Long id) {
        return findOrderIndexById(id).map(index -> orderArray[index]);
    }

    @Override
    public void update(Order entity) {
        //
    }

    @Override
    public void deleteById(Long id) {
        findOrderIndexById(id).ifPresent(index -> deleteOrderByIndex(index));
    }

//    @Override
//    public void deleteById(Long id) {
//        Integer orderIndex = findOrderIndexById(id);
//
//        if (orderIndex != null) {
//            deleteOrderByIndex(orderIndex);
//        }
//    }

    @Override
    public void printAll() {
        for (Order order : orderArray) {
            if (order != null) {
                System.out.println(order);
            }
        }
    }

    @Override
    public List<Order> search(OrderSearchCondition searchCondition) {
        return Collections.emptyList();
    }

    private void deleteOrderByIndex(int index) {
        ArrayUtils.removeElement(orderArray, index);
        orderIndex--;
    }

//    private Integer findOrderIndexById(long orderId) {
//        for (int i = 0; i < orderArray.length; i++) {
//            if (orderArray[i] != null && Long.valueOf(orderId).equals(orderArray[i].getId())) {
//                return i;
//            }
//        }
//        return null;
//    }

    private Optional<Integer> findOrderIndexById(long orderId) {
        OptionalInt optionalInt = IntStream.range(0, orderArray.length).filter(i ->
                orderArray[i] != null && Long.valueOf(orderId).equals(orderArray[i].getId())
        ).findAny();

        return OptionalUtils.valueOf(optionalInt);
    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public List<Order> findByUserId(long userId) {
        List<Order> foundOrders = new ArrayList<>();

        for (Order order : orderArray) {
            if (order.getUser().getId().equals(userId)) {
                foundOrders.add(order);
            }
        }

        return foundOrders;
    }
}
