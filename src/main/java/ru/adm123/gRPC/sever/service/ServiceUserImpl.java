package ru.adm123.gRPC.sever.service;

import io.grpc.stub.StreamObserver;
import ru.adm123.gRPC.ServiceUserGrpc;
import ru.adm123.gRPC.ServiceUserOuterClass;
import ru.adm123.gRPC.model.User;
import ru.adm123.gRPC.sever.data.DataUser;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

/**
 * @author Dmitry Ushakov 09.10.2021
 */
public class ServiceUserImpl extends ServiceUserGrpc.ServiceUserImplBase {

    @Nonnull
    private final DataUser dataUser;

    public ServiceUserImpl(@Nonnull DataUser dataUser) {
        this.dataUser = dataUser;
    }

    @Override
    public void getUserById(@Nonnull ServiceUserOuterClass.RequestUserById request,
                            @Nonnull StreamObserver<ServiceUserOuterClass.ResponseUser> responseObserver) {
        sendResponse(responseObserver, Collections.singletonList(dataUser.findById(request.getId())));
    }

    @Override
    public void getUsersByName(@Nonnull ServiceUserOuterClass.RequestUserByName request,
                              @Nonnull StreamObserver<ServiceUserOuterClass.ResponseUser> responseObserver) {
        sendResponse(responseObserver, dataUser.findByName(request.getName()));

    }

    @Override
    public void getAllUsers(@Nonnull ServiceUserOuterClass.RequestAllUsers request,
                            @Nonnull StreamObserver<ServiceUserOuterClass.ResponseUser> responseObserver) {
        sendResponse(responseObserver, dataUser.findAll());
    }

    @Nonnull
    private ServiceUserOuterClass.ResponseUser buildResponseUser(@Nonnull User user) {
         return ServiceUserOuterClass.ResponseUser.newBuilder()
                .setId(user.getId())
                .setName(user.getName())
                .setAge(user.getAge())
                .build();
    }

    private void sendResponse(@Nonnull StreamObserver<ServiceUserOuterClass.ResponseUser> responseObserver,
                              @Nonnull List<User> userList) {
        for (User user : userList) {
            responseObserver.onNext(buildResponseUser(user));
        }
        responseObserver.onCompleted();
    }

}
