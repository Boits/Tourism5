package user.service;

import common.solution.service.BaseService;
import user.domain.User;
import user.search.UserSearchCondition;

import java.util.List;

public interface UserService extends BaseService<User, Long> {

    List<? extends User> search(UserSearchCondition searchCondition);
}
