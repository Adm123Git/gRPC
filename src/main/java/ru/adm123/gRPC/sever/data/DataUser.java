package ru.adm123.gRPC.sever.data;

import lombok.Getter;
import ru.adm123.gRPC.model.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dmitry Ushakov 09.10.2021
 * <br>
 * БД делать не будем. Этот класс будет отдавать даные юзеров, т.е. игоать роль репозитория.
 */
public class DataUser {

    @Nonnull
    private final List<User> userList = new ArrayList<>();

    @Getter
    public static DataUser instance = new DataUser();

    private DataUser() {
        userList.add(new User(1, "Dima", 45));
        userList.add(new User(2, "Sveta", 46));
        userList.add(new User(3, "Kostik", 9));
        userList.add(new User(4, "Oleg", 6));
    }

    @Nonnull
    public User findById(int id) {
        return userList.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(User.nullUser);
    }

    @Nonnull
    public List<User> findByName(@Nonnull String name) {
        return userList.stream()
                .filter(user -> user.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Nonnull
    public List<User> findAll() {
        return userList;
    }

}
