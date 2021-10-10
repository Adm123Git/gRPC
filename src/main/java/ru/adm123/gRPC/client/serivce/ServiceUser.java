package ru.adm123.gRPC.client.serivce;

import io.grpc.Channel;
import ru.adm123.gRPC.ServiceUserGrpc;
import ru.adm123.gRPC.ServiceUserOuterClass;
import ru.adm123.gRPC.model.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Dmitry Ushakov 10.10.2021
 */
public class ServiceUser {

    @Nonnull
    private final ServiceUserGrpc.ServiceUserBlockingStub stub;

    public ServiceUser(@Nonnull Channel channel) {
        this.stub = ServiceUserGrpc.newBlockingStub(channel);
    }

    @Nullable
    public User getUserById(int id) {
        ServiceUserOuterClass.RequestUserById request = ServiceUserOuterClass.RequestUserById.newBuilder()
                .setId(id)
                .build();
        return userFromResponse(stub.getUserById(request));
    }

    @Nonnull
    public List<User> getUsersByName(@Nonnull String name) {
        ServiceUserOuterClass.RequestUserByName request = ServiceUserOuterClass.RequestUserByName.newBuilder()
                .setName(name)
                .build();
        return usersFromResponse(stub.getUsersByName(request));
    }

    @Nonnull
    public List<User> getAllUsers() {
        ServiceUserOuterClass.RequestAllUsers request = ServiceUserOuterClass.RequestAllUsers.newBuilder()
                .build();
        return usersFromResponse(stub.getAllUsers(request));
    }

    @Nullable
    private User userFromResponse(@Nonnull ServiceUserOuterClass.ResponseUser response) {
        User user = new User(response.getId(), response.getName(), response.getAge());
        return user.isNull() ? null : user;
    }

    @Nonnull
    private List<User> usersFromResponse(@Nonnull Iterator<ServiceUserOuterClass.ResponseUser> response) {
        List<User> list = new ArrayList<>();
        while (response.hasNext()) {
            User user = userFromResponse(response.next());
            if (user != null) {
                list.add(user);
            }
        }
        return list;
    }

}
