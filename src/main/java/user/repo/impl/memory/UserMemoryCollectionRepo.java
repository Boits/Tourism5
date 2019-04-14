package user.repo.impl.memory;

import static storage.Storage.userList;

import common.business.search.Paginator;
import common.solution.utils.CollectionUtils;
import storage.SequenceGenerator;
import user.domain.User;
import user.repo.UserRepo;
import user.search.UserSearchCondition;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserMemoryCollectionRepo implements UserRepo {
    @Override
    public void add(User user) {
        user.setId(SequenceGenerator.getNextValue());
        userList.add(user);
    }

    @Override
    public void add(Collection<User> users) {
        for (User user : users) {
            add(user);
        }
    }

    @Override
    public void update(User user) {
        //
    }

//    @Override
//    public User findById(Long id) {
//        return findUserById(id);
//    }

    @Override
    public Optional<User> findById(Long id) {
        return findUserById(id);
    }

    @Override
    public List<? extends User> search(UserSearchCondition searchCondition) {
        List<? extends User> users = doSearch(searchCondition);

        if (!users.isEmpty() && searchCondition.shouldPaginate()) {
            users = getPageableData(users, searchCondition.getPaginator());
        }

        return users;
    }

    private List<? extends User> getPageableData(List<? extends User> users, Paginator paginator) {
        return CollectionUtils.getPageableData(users, paginator.getLimit(), paginator.getOffset());
    }

    private List<User> doSearch(UserSearchCondition searchCondition) {
        return userList;
    }

//    @Override
//    public void deleteById(Long id) {
//        User found = findUserById(id);
//
//        if (found != null) {
//            userList.remove(found);
//        }
//    }

    @Override
    public void deleteById(Long id) {
        findUserById(id).map(user -> userList.remove(user));
    }


    @Override
    public void printAll() {
        for (User user : userList) {
            System.out.println(user);
        }
    }

//    private User findUserById(long userId) {
//        for (User user : userList) {
//            if (Long.valueOf(userId).equals(user.getId())) {
//                return user;
//            }
//        }
//        return null;
//    }

    private Optional<User> findUserById(long userId) {
        return userList.stream().filter(user -> Long.valueOf(userId).equals(user.getId())).findAny();
    }


    @Override
    public List<User> getAll() {
        return userList;
    }
}