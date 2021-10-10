package ru.adm123.gRPC.client;

import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import ru.adm123.gRPC.ServiceUserOuterClass;
import ru.adm123.gRPC.client.serivce.ServiceUser;
import ru.adm123.gRPC.model.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * @author Dmitry Ushakov 09.10.2021
 * <br>
 * Класс, стартующий клиента
 */
public class StartClient {

    private static ServiceUser serviceUser;

    public static void main(String[] args) {
        Channel channel = ManagedChannelBuilder
                .forTarget("localhost:8090")
                .usePlaintext()
                .build();
        serviceUser = new ServiceUser(channel);
        System.out.println("-------- user by id = 1 -----------");
        printUserById(1);
        System.out.println("-------- user by name = \"Sveta\" ---");
        printUsersByName("Sveta");
        System.out.println("--------- all users ---------------");
        printAllUsers();
    }

    private static void printUserById(int id) {
        printUser(serviceUser.getUserById(id));
    }

    private static void printUsersByName(@Nonnull String name) {
        printUsers(serviceUser.getUsersByName(name));
    }

    private static void printAllUsers() {
        printUsers(serviceUser.getAllUsers());
    }

    private static void printUser(@Nullable User user) {
        System.out.println(user == null ? "NULL" : user);
    }

    private static void printUsers(@Nonnull List<User> users) {
        for (User user : users) {
            printUser(user);
        }
    }

}
