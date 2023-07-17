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

    const addIngredientForm = document.getElementById("addIngredientForm");
    addIngredientForm.addEventListener('submit', (event) => {
        event.preventDefault();

        const addIngredientFormData = new FormData(addIngredientForm);

        const ingredientName = addIngredientFormData.get("ingredientName");
        const ingredientUnit = addIngredientFormData.get("ingredientUnit");
        const ingredientVegetarian = addIngredientFormData.get("ingredientVegetarian");
        const ingredientVegan = addIngredientFormData.get("ingredientVegan");
        const ingredientAmountInPantry = addIngredientFormData.get("ingredientAmountInPantry");

        const ingredientPayload = {
            ingredientName,
            ingredientUnit,
            ingredientVegetarian,
            ingredientVegan,
            ingredientAmountInPantry
        };

        fetch("/ingredients/add", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(ingredientPayload)
        })
        .then((response) => response.json())
        .then((ingredientData) => {
            console.log("Returned: " + ingredientData);
        })
        .catch((error) => {
            console.error("Error: ", error);
        });
    })
});