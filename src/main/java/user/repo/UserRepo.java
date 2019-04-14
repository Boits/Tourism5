package user.repo;

import common.solution.repo.BaseRepo;
import user.domain.User;
import user.search.UserSearchCondition;

import java.util.List;

public interface UserRepo extends BaseRepo<User, Long> {
    List<? extends User> search(UserSearchCondition searchCondition);
}
