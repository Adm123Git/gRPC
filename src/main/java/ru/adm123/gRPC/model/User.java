package ru.adm123.gRPC.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.Nonnull;

/**
 * @author Dmitry Ushakov 09.10.2021
 * <br>
 * Класс юзера. Общий для сервера и клиента.
 */
@Setter
@Getter
@ToString
public class User {

    private int id;
    @Nonnull
    private String name;
    private int age;
    /**
     * Protofab не разрешает отправлять null. Поэтому делаем специальный объект,
     * обозначающий null-юзера и соответствующую проверку
     */
    @Nonnull
    public static final User nullUser = new User(-1, "", -1);

    public User(int id,
                @Nonnull String name,
                int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    /**
     * Protofab не разрешает отправлять null. Поэтому делаем специальный объект,
     * обозначающий null-юзера и соответствующую проверку
     */
    public boolean isNull() {
        return id < 0 || age < 0 || name.isEmpty();
    }

}
