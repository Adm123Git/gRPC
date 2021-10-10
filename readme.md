gRPC API
***
Пример реализации клиент-серверного взаимодействия (напр. для микросервисов) с использованием
gRPC API (альтернатива REST API).

Server - "серверная" часть, Client - "клиетская".<br>proto-файл и model используем общие для простоты.
Для простоты же клиента и сервера делаем в рамках одного проекта.
***
Учитываем, что gRPC не подходит для обмена данными с клиентом-браузером, т.к. браузеры пока не понимают протокол HTTP2.<br>
Поэтому такой подход годится для взаимодействия между двумя серверами, один из которых "сервер", другой - "клиент"
(напр. для микросервисной архитектуры приложения).<br>
Естественно, ничто не мешает каждой части выполнять функции и "клиента" и "сервера", предоставляя API одним ("клиентам")
и используя API других ("серверов").