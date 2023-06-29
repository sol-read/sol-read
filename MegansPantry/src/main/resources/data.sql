insert into ingredient (name, unit, vegetarian, vegan) values ('onion', 'HALVES',true, true);
insert into ingredient (name, unit, vegetarian, vegan) values ('garlic', 'CLOVES',true, true);
insert into ingredient (name, unit, vegetarian, vegan) values ('eggs', 'NONE',true, false);
insert into ingredient (name, unit, vegetarian, vegan) values ('milk', 'MILLILITRES',true, false);
insert into ingredient (name, unit, vegetarian, vegan) values ('bell peppers', 'NONE',true, true);
insert into ingredient (name, unit, vegetarian, vegan) values ('chicken breast', 'NONE',false, false);
insert into ingredient (name, unit, vegetarian, vegan) values ('black beans', 'TINS',true, true);
insert into ingredient (name, unit, vegetarian, vegan) values ('tomatoes', 'NONE', true, true);
insert into ingredient (name, unit, vegetarian, vegan) values ('spinach', 'NONE', true, true);
insert into ingredient (name, unit, vegetarian, vegan) values ('pasta', 'GRAMS', true, true);
insert into ingredient (name, unit, vegetarian, vegan) values ('spaghetti', 'GRAMS', true, true);
insert into ingredient (name, unit, vegetarian, vegan) values ('pancetta', 'GRAMS', false, false);
insert into ingredient (name, unit, vegetarian, vegan) values ('cheese of choice', 'NONE', true, false);
insert into ingredient (name, unit, vegetarian, vegan) values ('butter', 'GRAMS', true, false);
insert into ingredient (name, unit, vegetarian, vegan) values ('sweetcorn', 'GRAMS', true, true);
insert into ingredient (name, unit, vegetarian, vegan) values ('spices of choice', 'NONE', true, true);
insert into ingredient (name, unit, vegetarian, vegan) values ('tofu', 'GRAMS', true, true);

insert into recipe (name, vegetarian, vegan) values ('spaghetti carbonara', false, false);
insert into recipe (name, vegetarian, vegan) values ('bean fajitas', true, false);
insert into recipe (name, vegetarian, vegan) values ('tofu curry', true, true);

-- Carbonara
insert into recipe_ingredient (recipe_id, ingredient_id, amount) values (1,2,2);
insert into recipe_ingredient (recipe_id, ingredient_id, amount) values (1,3,3);
insert into recipe_ingredient (recipe_id, ingredient_id, amount) values (1,12,100);
insert into recipe_ingredient (recipe_id, ingredient_id, amount) values (1,13,0);
insert into recipe_ingredient (recipe_id, ingredient_id, amount) values (1,11,350);
insert into recipe_ingredient (recipe_id, ingredient_id, amount) values (1,14,50);

-- Fajitas
insert into recipe_ingredient (recipe_id, ingredient_id, amount) values (2,1,2);
insert into recipe_ingredient (recipe_id, ingredient_id, amount) values (2,2,3);
insert into recipe_ingredient (recipe_id, ingredient_id, amount) values (2,5,1);
insert into recipe_ingredient (recipe_id, ingredient_id, amount) values (2,7,2);
insert into recipe_ingredient (recipe_id, ingredient_id, amount) values (2,8,1);
insert into recipe_ingredient (recipe_id, ingredient_id, amount) values (2,13,0);
insert into recipe_ingredient (recipe_id, ingredient_id, amount) values (2,15,50);

-- Tofu Curry
insert into recipe_ingredient (recipe_id, ingredient_id, amount) values (3,17,250);
insert into recipe_ingredient (recipe_id, ingredient_id, amount) values (3,1,2);
insert into recipe_ingredient (recipe_id, ingredient_id, amount) values (3,2,3);
insert into recipe_ingredient (recipe_id, ingredient_id, amount) values (3,8,1);
insert into recipe_ingredient (recipe_id, ingredient_id, amount) values (3,9,100);
insert into recipe_ingredient (recipe_id, ingredient_id, amount) values (3,16,0);