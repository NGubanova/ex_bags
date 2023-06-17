# Курсовая работа. Сайт Ex(bags)

В этой курсовой работе я разрабатывала потенциальный сайт для ресейл-магазина люксовых сумок. Компания занимается покупкой и перепродажей брендовых б/у сумок.

Для этого я создала базу данных и развернула ее на сервере с помощью PhpMyAdmin. Сам сайт я разрабатывала с помощью Spring Framework. 
Я реализвовала следующий функционал:
* Регистрация нового пользователя по имени, электронной почте и пароля(Шифрование BCrypt 8). Установлена валидация на пустоту каждого поля и валидаю почту и пароля(латинский алфавит, не менее 6 символов, заглавные и строчные буквы, цифры и спец. символы).
![reg](https://sun9-46.userapi.com/impg/pVup6cZfCQf19sDllMoydi69m3SHrlb1KZFxfg/WNE5KOOJ6ZQ.jpg?size=1872x1018&quality=96&sign=fa73c81bad82717ac515ccd2e4d0ec85&type=album)
* Авторизация по почте и паролю.
![auth](https://sun9-6.userapi.com/impg/Yt68MfnJnl5TlYsPXNxxjOQnqtUpVYk9D9UhdA/OnFp0I6BI-U.jpg?size=1919x1019&quality=96&sign=a782c117dbe3f38d84b39302a0e4ffbf&type=album)
* Разграничение прав доступа по ролям.
* Добавление сумки в корзину в случае, если пользователь атворизирован.
![add in card](https://sun9-39.userapi.com/impg/v5Vpwf9vZnLv9foi6z2pdZM3uMHZ-gt5fxfUKw/95y_3ahw7L4.jpg?size=1873x1023&quality=96&sign=3767afe725b16682f3c57e3868f3d8e4&type=album)
* Удаление товара из корзины.
* Оформление заказа.
![add order](https://sun9-35.userapi.com/impg/-ywWgQJ_5ct4eBfeDkcuYDPhYGiue-uhpZdIFQ/Zz5A9qy-oK4.jpg?size=1919x1020&quality=96&sign=04a148dc723ae0a3c2c8ba2359a3dd03&type=album)
* Личный кабинет пользователя. 
    * Личные данные. Можно изменить электронную почту, ФИО, добавить номер телефона, пароль.
    ![data user](https://sun9-14.userapi.com/impg/QrErNezPYOGKlKcc9dLkDuWf96gBr8I-dbFsaw/Xp669gTeHWA.jpg?size=1280x695&quality=96&sign=f508d4e27e916923290b1d5df928ee5b&type=album)
    * Вывод заказнных сумок.
    ![bad in order](https://sun9-40.userapi.com/impg/ZkTtSLeh-ge50WBXm8w8bsYmeKiG7X753BrlAA/y0yAXJT2p-g.jpg?size=1280x678&quality=96&sign=2b3acbe6f27a1bbfa3233420b79e4d61&type=album)
* Администратор. Вывод статистики по сумкам в магазине. Создание Back-up.
![admin](https://sun9-71.userapi.com/impg/ylgqzE3bcr59Zh8Pp5A-B35BKaaZsb9TJlvQZQ/rHgkcOlfyMU.jpg?size=1280x678&quality=96&sign=bdb15104e9f906868ca59f20177523e8&type=album)
* HR. Добавление новых пользователей: администратор, пользователь, курьер, менеджер, кладовщик, закупщик. Добавление новых должностей. CRUD действия с пользователями и должностями.
![hr](https://sun9-61.userapi.com/impg/2Q4qXvNIPrTJE3ISh0tIMhnYsANDFiip1_SUzg/nXMOooRtilE.jpg?size=1280x681&quality=96&sign=3b29d1ba3ca1d046e6ea216c9cef38c7&type=album)
* Курьер. Просмотр и изменение статуса доставки.
![courier](https://sun9-16.userapi.com/impg/OmPg7Oqhrci3Wom7FKMpVXxsMRiG1LEqp3F9Ag/Fho7l7yc-OY.jpg?size=1280x684&quality=96&sign=6dc2c12f26fe912ed61391fcc5f7cec9&type=album)
* Кладовщик. CRUD действия для ячеек и сумок.
![](https://sun9-43.userapi.com/impg/16cfJ39qX0yb6EWAtFDZOhEWXu0qs9cTDhNiiQ/wd0P4AuCEho.jpg?size=1280x680&quality=96&sign=d1aa827ec944cca1d56f352d1b1474e3&type=album)
* Менеджер. CRUD действия для брендов, типов сумок и закупок.
![manager](https://sun9-60.userapi.com/impg/tCOjfq0wiX33NwDDE8-Ftc7_lbAi97dIdyZXPg/ZWMjYljXjOU.jpg?size=1919x1021&quality=96&sign=1b03f1248045a2a9644cffba0af15b9d&type=album)
* Поиск по таблицам.
* Выход из аккаунта.

