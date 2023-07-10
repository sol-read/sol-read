const handle500Error = (json) => {
  if (json.status && json.status === 500) {
    throw new Error(json.message)
  }
  return json
}

const renderError = (message) => {
  alert(`Error calling Ingredients API: ${message}`);
}

// const fetchAllRecipes = (cb) => {
//     fetch("/recipes/all")
//     .then(res => res.json())
//     .then(handle500Error)
//     .then(json => cb(json))
//     .catch(renderError);
// }

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

// const renderRecipeListCallback = (recipesTableBody) => (recipes) => {
    
//   recipes.forEach((recipe) => {
//     const recipesRow = document.createElement("tr");
//     recipesRow.innerHTML = `
//       <td>${recipe.id}</td>
//       <td>${recipe.name}</td>
//       <td>${recipe.vegetarian}</td>
//       <td>${recipe.vegan}</td>
//     `;
//     recipesTableBody.appendChild(recipesRow);
//   });
// }

// fetchAllRecipes(
//   renderRecipeListCallback(
//     document.getElementById("recipesTableBody")
//   )
// );

function renderListAsTable(list, tableBodyElement) {
  // Clear existing table rows
  tableBodyElement.innerHTML = "";

  // Iterate over the list and create table rows
  list.forEach((item) => {
    const row = document.createElement("tr");

    // Iterate over the properties of each item and create table cells
    Object.values(item).forEach((value) => {
      const cell = document.createElement("td");
      cell.textContent = value;
      row.appendChild(cell);
    });

    tableBodyElement.appendChild(row);
  });
}

function fetchListAndRenderTable(apiEndpoint, tableBodyElement) {
  fetch(apiEndpoint)
    .then((response) => response.json())
    .then((list) => renderListAsTable(list, tableBodyElement))
    .catch((error) => console.error("Error fetching data:", error));
}



function fetchAllRecipesNew() {
  const tableBodyElement = document.getElementById("recipesTableBody");
  fetchListAndRenderTable("/recipes/all", tableBodyElement);
}

// function populateRecipesTable() {
    
//   fetch("/recipes/all")
//     .then(response => response.json())
//     .then(data => {
      
//       const tableBody = document.getElementById("recipesTableBody");

      
//       data.forEach(recipe => {
//         const row = document.createElement("tr");
//         row.innerHTML = `
//           <td>${recipe.id}</td>
//           <td>${recipe.name}</td>
//           <td>${recipe.vegetarian}</td>
//           <td>${recipe.vegan}</td>
//         `;
//         tableBody.appendChild(row);
//       });
//     })
//     .catch(error => console.error("Error fetching data:", error));
// }

// // Call the function to populate the table when the page loads
// window.addEventListener("load", populateRecipesTable);

// function applyFilters() {
//   const vegetarianFilter = document.getElementById("vegetarianFilter");
//   const veganFilter = document.getElementById("veganFilter");
  
//   // Prepare filter query parameters based on checkbox states
//   const filterParams = new URLSearchParams();
//   if (vegetarianFilter.checked) {
//     filterParams.set("vegetarian", "true");
//   }
//   if (veganFilter.checked) {
//     filterParams.set("vegan", "true");
//   }

//   fetch(`/recipes/all?${filterParams.toString()}`)
//     .then((res) => res.json())
//     .then(handle500Error)
//     .then((json) => {
//       const recipesTableBody = document.getElementById("recipesTableBody");
//       recipesTableBody.innerHTML = ""; // Clear existing table rows
      
//       // Render filtered recipes
//       renderRecipeListCallback(recipesTableBody)(json);
//     })
//     .catch(renderError);
// }

// const applyFiltersButton = document.getElementById("applyFiltersButton");
// applyFiltersButton.addEventListener("click", applyFilters);