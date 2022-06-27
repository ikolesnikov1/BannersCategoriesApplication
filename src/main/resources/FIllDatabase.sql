use yourDatabaseName;

insert into category (category_id, name, request, deleted)
values (1, 'Sport', 'sport', 0),
       (2, 'Computer', 'computer', 0),
       (3, 'Game', 'game', 0),
       (4, 'Programming', 'Programming', 0),
       (5, 'Travel', 'Travel', 0);

insert into banner (banner_id, name, price, category_id, content, deleted)
values (1, 'Learning Java', 199.99, 4, 'Start learning Java today.', 0),
       (2, 'С++', 1000.50, 4, 'Oh, dont you dare jump into it!', 0),
       (3, 'Kazakhstan', 1.0, 5, 'Dont wait, just move towards your happiness.', 0),
       (4, 'Poker', 99.90, 3, 'Try to play, but you can lose all....', 0),
       (5, 'Hockey', 0.90, 1, 'New arena in Novosibirsk waiting for young stars of hockey!', 0),
       (6, 'Repair', 99.90, 2, 'We can repair all, but also can broke it.... ', 0);