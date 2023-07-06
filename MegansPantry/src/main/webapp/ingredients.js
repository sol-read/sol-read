const handle500Error = (json) => {
    if (json.status && json.status === 500) {
      throw new Error(json.message)
    }
    return json
  }

  const renderError = (message) => {
    alert(`Error calling Ingredients API: ${message}`);
  }

const fetchAllIngredients = (cb) => {
    fetch("/ingredients/all")
    .then(res => res.json())
    .then(handle500Error)
    .then(json => cb(json))
    .catch(renderError);
}

const addNewIngredient = (cb) => {
    const addIngredientForm = document.getElementById("addNewIngredient").elements;
    const name = addIngredientForm["name"].value;
    const unit = addIngredientForm["unit"].value;
    const vegetarian = addIngredientForm["vegetarian"].value;
    const vegan = addIngredientForm["vegan"].value;
    const ingredient = {
      name,
      unit,
      vegetarian,
      vegan
    }
    fetch("/ingredients/add", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(ingredient)
    })
      .then(res => res.json())
      .then(handle500Error)
      .then(json => {
        alert(`Successfully added ingredient with id ${json.id}: (${JSON.stringify(json)})`);
        fetchAllIngredients(cb);
      })
      .catch(renderError);
  }


  const renderIngredientsListCallback = (ingredientsTableBody) => (ingredients) => {
    while (ingredientsTableBody.firstChild) {
      ingredientsTableBody.removeChild(ingredientsTableBody.firstChild);
    }
    ingredients.forEach((ingredient) => {
      const ingredientsRow = document.createElement("tr");
      ingredientsRow.innerHTML = `
        <td>${ingredient.id}</td>
        <td>${ingredient.name}</td>
        <td>${ingredient.unit}</td>
        <td>${ingredient.vegetarian}</td>
        <td>${ingredient.vegan}</td>
      `;
      ingredientsTableBody.appendChild(ingredientsRow);
    });
  }

  fetchAllIngredients(
    renderIngredientsListCallback(
      document.getElementById("ingredientsTableBody")
    )
  );


function populateIngredientsTable() {
    
    fetch("/ingredients/all")
      .then(response => response.json())
      .then(data => {
        
        const tableBody = document.getElementById("ingredientsTableBody");
  
        
        data.forEach(ingredient => {
          const row = document.createElement("tr");
          row.innerHTML = `
            <td>${ingredient.id}</td>
            <td>${ingredient.name}</td>
            <td>${ingredient.unit}</td>
            <td>${ingredient.vegetarian}</td>
            <td>${ingredient.vegan}</td>
          `;
          tableBody.appendChild(row);
        });
      })
      .catch(error => console.error("Error fetching data:", error));
  }
  
  // Call the function to populate the table when the page loads
  window.addEventListener("load", populateIngredientsTable);
  