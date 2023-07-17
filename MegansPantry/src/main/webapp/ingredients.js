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
        let ingredientVegetarian = addIngredientFormData.get("ingredientVegetarian");
        if(ingredientVegetarian === null) {
            ingredientVegetarian = false;
        } else {
            ingredientVegetarian = true;
        }
        let ingredientVegan = addIngredientFormData.get("ingredientVegan");
        if(ingredientVegan !== null && ingredientVegetarian === true) {
            ingredientVegan = true;
        } else {
            ingredientVegan = false;
        }
        const ingredientAmountInPantry = addIngredientFormData.get("ingredientAmountInPantry");

        const ingredientPayload = {
            "name": ingredientName,
            "unit": ingredientUnit,
            "isVegetarian": ingredientVegetarian,
            "isVegan": ingredientVegan,
            "amountInPantry": ingredientAmountInPantry
        };

        console.log(ingredientPayload);

        fetch("/ingredients/add", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(ingredientPayload)
        })
        .then((response) => response.json())
        .then((ingredientData) => {
            alert(`Successfully added ${ingredientData.name} to the database!`);
        })
        .catch((error) => {
            console.error("Error: ", error);
        });


    });
});