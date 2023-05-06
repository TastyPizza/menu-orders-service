
insert into menu_item(description, name, new_badge, type)
values ('просто маргарита', 'Маргарита', true, 1),
       ('картошка', 'Картошка фри', false, 2);


insert into menu_item_option(carbohydrates, count, fats, name, nutritional,
                             picture_id, price, proteins, traditional_dough,
                             weight, menu_item_id)

values (100, 10, 10, 'Маленькая маргарита', 10, 'picture', 100, 100, true,
        100, 1),
        (100, 10, 10, 'Средняя маргарита', 10, 'picture', 100, 100, true,
        700, 1),
         (100, 10, 10, 'Большая маргарита', 10, 'picture', 100, 100, true,
        1000, 1),
       (100, 10, 10, 'Картошка фри', 10, 'picture', 100, 100, true,
        1000, 2);
