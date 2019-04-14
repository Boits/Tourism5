package user.repo.impl.memory;

import common.business.search.Paginator;
import common.solution.utils.ArrayUtils;
import common.solution.utils.CollectionUtils;
import common.solution.utils.OptionalUtils;
import storage.SequenceGenerator;
import user.domain.User;
import user.repo.UserRepo;
import user.search.UserSearchCondition;

import static storage.Storage.userArray;

import java.util.*;
import java.util.stream.IntStream;

public class UserMemoryArrayRepo implements UserRepo {
    private int userIndex = -1;

    @Override
    public void add(User user) {
        if (userIndex == userArray.length - 1) {
            User[] newArrUsers = new User[userArray.length * 2];
            System.arraycopy(userArray, 0, newArrUsers, 0, userArray.length);
            userArray = newArrUsers;
        }

        userIndex++;
        user.setId(SequenceGenerator.getNextValue());
        userArray[userIndex] = user;

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
//        Integer userIndex = findUserIndexById(id);
//
//        if (userIndex != null) {
//            return userArray[userIndex];
//        }
//
//        return null;
//    }

    @Override
    public Optional<User> findById(Long id) {
        return findUserIndexById(id).map(userInd -> userArray[userIndex]);
    }


    @Override
    public List<? extends User> search(UserSearchCondition searchCondition) {
        List<? extends User> users = doSearch(searchCondition);

        if (!users.isEmpty() && searchCondition.shouldPaginate()) {
            users = getPageableData(users, searchCondition.getPaginator());
        }

        return users;
    }

    private List<User> doSearch(UserSearchCondition searchCondition) {
        return Collections.emptyList();
    }

    private List<? extends User> getPageableData(List<? extends User> users, Paginator paginator) {
        return CollectionUtils.getPageableData(users, paginator.getLimit(), paginator.getOffset());
    }

//    @Override
//    public void deleteById(Long id) {
//        Integer userIndex = findUserIndexById(id);
//
//        if (userIndex != null) {
//            deleteUserByIndex(userIndex);
//        }
//    }

    @Override
    public void deleteById(Long id) {
        findUserIndexById(id).ifPresent(index -> deleteUserByIndex(index));
    }


    private void deleteUserByIndex(int index) {
        ArrayUtils.removeElement(userArray, index);
        userIndex--;
    }

    @Override
    public void printAll() {
        for (User user : userArray) {
            if (user != null) {
                System.out.println(user);
            }
        }
    }

//    private Integer findUserIndexById(long userId) {
//        for (int i = 0; i < userArray.length; i++) {
//            if (userArray[i] != null && Long.valueOf(userId).equals(userArray[i].getId())) {
//                return i;
//            }
//        }
//        return null;
//    }

    private Optional<Integer> findUserIndexById(long userId) {
        OptionalInt optionalInt = IntStream.range(0, userArray.length).filter(i ->
                userArray[i] != null && Long.valueOf(userId).equals(userArray[i].getId())
        ).findAny();

        return OptionalUtils.valueOf(optionalInt);
    }

    @Override
    public List<User> getAll() {
        return null;
    }

}
