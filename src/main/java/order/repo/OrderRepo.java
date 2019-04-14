package order.repo;

import common.solution.repo.BaseRepo;
import order.domain.Order;
import order.search.OrderSearchCondition;

import java.util.List;

public interface OrderRepo extends BaseRepo<Order, Long> {

    List<Order> search(OrderSearchCondition searchCondition);

    List<Order> findByUserId(long userId);
}