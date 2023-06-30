insert into ingredient (name, unit, vegetarian, vegan, amount_in_pantry) values ('onion', 'HALVES',true, true,0);
insert into ingredient (name, unit, vegetarian, vegan, amount_in_pantry) values ('garlic', 'CLOVES',true, true,0);
insert into ingredient (name, unit, vegetarian, vegan, amount_in_pantry) values ('eggs', 'NONE',true, false,0);
insert into ingredient (name, unit, vegetarian, vegan, amount_in_pantry) values ('milk', 'MILLILITRES',true, false,0);
insert into ingredient (name, unit, vegetarian, vegan, amount_in_pantry) values ('bell peppers', 'NONE',true, true,0);
insert into ingredient (name, unit, vegetarian, vegan, amount_in_pantry) values ('chicken breast', 'NONE',false, false,0);
insert into ingredient (name, unit, vegetarian, vegan, amount_in_pantry) values ('black beans', 'TINS',true, true,0);
insert into ingredient (name, unit, vegetarian, vegan, amount_in_pantry) values ('tomatoes', 'NONE', true, true,0);
insert into ingredient (name, unit, vegetarian, vegan, amount_in_pantry) values ('spinach', 'NONE', true, true,0);
insert into ingredient (name, unit, vegetarian, vegan, amount_in_pantry) values ('pasta', 'GRAMS', true, true,0);
insert into ingredient (name, unit, vegetarian, vegan, amount_in_pantry) values ('spaghetti', 'GRAMS', true, true,0);
insert into ingredient (name, unit, vegetarian, vegan, amount_in_pantry) values ('pancetta', 'GRAMS', false, false,0);
insert into ingredient (name, unit, vegetarian, vegan, amount_in_pantry) values ('cheese of choice', 'NONE', true, false,0);
insert into ingredient (name, unit, vegetarian, vegan, amount_in_pantry) values ('butter', 'GRAMS', true, false,0);
insert into ingredient (name, unit, vegetarian, vegan, amount_in_pantry) values ('sweetcorn', 'GRAMS', true, true,0);
insert into ingredient (name, unit, vegetarian, vegan, amount_in_pantry) values ('spices of choice', 'NONE', true, true,0);
insert into ingredient (name, unit, vegetarian, vegan, amount_in_pantry) values ('tofu', 'GRAMS', true, true,0);

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