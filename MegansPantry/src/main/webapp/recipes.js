const handle500Error = (json) => {
  if (json.status && json.status === 500) {
    throw new Error(json.message)
  }
  return json
}

const renderError = (message) => {
  alert(`Error calling Ingredients API: ${message}`);
}

function fetchData(apiEndpoint) {
  return fetch(apiEndpoint)
    .then((response) => response.json())
    .then((data) => data)
    .catch((error) => {
      console.error("Error fetching data:", error);
      throw error;
    });
}

function showIngredients(ingredients) {
    const recipeIngredientBody = document.getElementById("ingredientForRecipeBody");
    recipeIngredientBody.innerHTML = "";

    ingredients.forEach((ingredient) => {
        const ingredientItem = document.createElement("tr");
        ingredientItem.innerHTML = `
        <td>${ingredient.id}</td>
        <td>${ingredient.name}</td>
        <td>${ingredient.amountNeeded}</td>
        <td>${ingredient.unit}</td>
        <td>${ingredient.amountInPantry}</td>
        `;
        recipeIngredientBody.appendChild(ingredientItem);
    });
}

function populateTableWithRecipes(recipes) {
  const tableBody = document.getElementById("recipesTableBody");
  tableBody.innerHTML = "";

  recipes.forEach((recipe) => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${recipe.id}</td>
      <td><a href="#recipeIngredientSection" class="recipe-link">${recipe.name}</a></td>
      <td>${recipe.vegetarian}</td>
      <td>${recipe.vegan}</td>
    `;
    tableBody.appendChild(row);

    const recipeLink = row.querySelector(".recipe-link");
    recipeLink.addEventListener('click', () => {
        fetchData(`/recipes/${recipe.id}/ingredients`)
        .then((ingredients) => {
            showIngredients(ingredients);
        })
        .catch((error) => {
            console.error("Error displaying ingredients: ", error);
            throw error;
        });
    });
  });
}

fetchData("/recipes/all")
  .then((recipes) => {
    populateTableWithRecipes(recipes);
  })
  .catch((error) => {
    console.error("Error fetching data: ", error);
    throw error;
  });

function applyFilters() {
  const vegetarianFilter = document.getElementById("vegetarianFilter");
  const veganFilter = document.getElementById("veganFilter");
  const canMakeFilter = document.getElementById("canMakeFilter");

  const filterParams = new URLSearchParams();
  if (vegetarianFilter.checked) {
    filterParams.set("vegetarian", "true");
  }
  if (veganFilter.checked) {
    filterParams.set("vegan", "true");
  }
  if(canMakeFilter.checked) {
    filterParams.set("canMake","true");
  }

  fetchData(`/recipes/all?${filterParams.toString()}`)
    .then((recipes) => {
      populateTableWithRecipes(recipes);
    })
    .catch((error) => {
      console.error("Error fetching data: ", error);
      throw error;
    });
}

window.addEventListener("DOMContentLoaded", () => {
    const applyFiltersButton = document.getElementById("applyFiltersButton");
    if (applyFiltersButton) {
        applyFiltersButton.addEventListener('click',applyFilters);
    }
});