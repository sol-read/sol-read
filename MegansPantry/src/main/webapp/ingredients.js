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

function populateTableWithIngredients(ingredients) {
  const tableBody = document.getElementById("ingredientsTableBody");
  tableBody.innerHTML = "";

  ingredients.forEach((ingredient) => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${ingredient.id}</td>
      <td>${ingredient.name}</td>
      <td>${ingredient.unit}</td>
      <td>${ingredient.vegetarian}</td>
      <td>${ingredient.vegan}</td>
      <td>${ingredient.amountInPantry}</td>
    `;
    tableBody.appendChild(row);
  });
}

fetchData("/ingredients/all")
  .then((ingredients) => {
    populateTableWithIngredients(ingredients);
  })
  .catch((error) => {
    console.error("Error fetching data: ", error);
    throw error;
  });

function populateWithIngredientsInPantry() {
  fetchData("/ingredients/inPantry")
  .then((ingredients) => {
    populateTableWithIngredients(ingredients);
  })
  .catch((error) => {
    console.error("Error fetching data: ", error);
    throw error;
  });
}

window.addEventListener("DOMContentLoaded", () => {
  const inPantryButton = document.getElementById("inPantryButton");
  if(inPantryButton) {
    inPantryButton.addEventListener('click',populateWithIngredientsInPantry);
  }
});