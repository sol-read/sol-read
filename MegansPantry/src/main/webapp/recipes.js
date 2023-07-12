const handle500Error = (json) => {
  if (json.status && json.status === 500) {
    throw new Error(json.message)
  }
  return json
}

const renderError = (message) => {
  alert(`Error calling Ingredients API: ${message}`);
}

// const addNewRecipe = (cb) => {
//   const addRecipeForm = document.getElementById("addNewRecipe").elements;
//   const name = addRecipeForm["name"].value;
//   const vegetarian = addRecipeForm["vegetarian"].value;
//   const vegan = addRecipeForm["vegan"].value;
//   const recipe = {
//     name,
//     ingredients
//   }
//   fetch("/recipes/add", {
//     method: "POST",
//     headers: { "Content-Type": "application/json" },
//     body: JSON.stringify(boot)
//   })
//     .then(res => res.json())
//     .then(handle500Error)
//     .then(json => {
//       alert(`Successfully added boot with id ${json.id}: (${JSON.stringify(json)})`);
//       fetchAllBoots(cb);
//     })
//     .catch(renderError);
// }

function fetchData(apiEndpoint) {
  return fetch(apiEndpoint)
    .then((response) => response.json())
    .then((data) => data)
    .catch((error) => {
      console.error("Error fetching data:", error);
      throw error;
    });
}

function populateTableWithRecipes(recipes) {
  const tableBody = document.getElementById("recipesTableBody");
  tableBody.innerHTML = "";

  recipes.forEach((recipe) => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${recipe.id}</td>
      <td><a href="/recipes/${recipe.id}/ingredients">${recipe.name}</a></td>
      <td>${recipe.vegetarian}</td>
      <td>${recipe.vegan}</td>
    `;
    tableBody.appendChild(row);
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
    filterParams.set("canMake","true")
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