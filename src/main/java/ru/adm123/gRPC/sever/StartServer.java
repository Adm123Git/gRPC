package ru.adm123.gRPC.sever;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import ru.adm123.gRPC.sever.data.DataUser;
import ru.adm123.gRPC.sever.service.ServiceUserImpl;

import java.io.IOException;

/**
 * @author Dmitry Ushakov 09.10.2021
 * <br>
 * Класс, стартующий сервер
 */
public class StartServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        DataUser dataUser = DataUser.getInstance();
        Server server = ServerBuilder
                .forPort(8090)
                .addService(new ServiceUserImpl(dataUser))
                .build();
        server.start();
        server.awaitTermination();
    }

}
