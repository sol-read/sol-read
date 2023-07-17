function fetchData(apiEndpoint) {
    return fetch(apiEndpoint)
      .then((response) => response.json())
      .then((data) => data)
      .catch((error) => {
        console.error("Error fetching data:", error);
        throw error;
      });
  }

function addIngredientsToSelectionList(ingredients) {
    const ingredientSelection = document.getElementById("ingredientSelection");
    ingredientSelection.innerHTML = "";

    ingredients.forEach((ingredient) => {
        const option = document.createElement("option");
        option.value = ingredient.name;
        option.setAttribute("data-id", ingredient.id);
        option.setAttribute("data-unit", ingredient.unit);
        option.textContent = `${ingredient.name} (${ingredient.unit})`
        ingredientSelection.appendChild(option);
    });
}

window.onload = function() {
    fetchData("/ingredients/all")
    .then((ingredients) => {
        addIngredientsToSelectionList(ingredients);
    });
};

window.addEventListener("DOMContentLoaded", () => {
    const ingredientSelection = document.getElementById("ingredientSelection");
    const addIngredientToListButton = document.getElementById("addIngredientToListButton");
    const selectedIngredientList = document.getElementById("selectedIngredientList");

    let ingredientIdList = [];
    let ingredientAmountList = [];

    addIngredientToListButton.addEventListener('click', () => {
        const selectedOptions = Array.from(ingredientSelection.selectedOptions);
        selectedOptions.forEach((option) => {

            const ingredientId = parseInt(option.getAttribute("data-id"));
            const ingredientUnit = option.getAttribute("data-unit");
            const ingredientName = option.value;

            const ingredientAmount = parseInt(window.prompt(`Enter amount of ${ingredientName} in unit ${ingredientUnit}: `));

            if(ingredientAmount !== null) {

                // For the purpose of the post request array
                ingredientIdList.push(ingredientId);
                ingredientAmountList.push(ingredientAmount);

                // Doing the front end thing
                const listItem = document.createElement("li");
                listItem.textContent = `${ingredientName} (${ingredientAmount} ${ingredientUnit})`;
                selectedIngredientList.appendChild(listItem);

                // Making the option unavailable for re-selection.
                option.remove();
            };

        });
    });


    const addRecipe = document.getElementById("addThisRecipe");
    addRecipe.addEventListener('click', () => {
        const recipeName = document.getElementById("recipeName").value;
        const requestBody = {
            recipeName: recipeName,
            ingredientIds: ingredientIdList,
            ingredientAmounts: ingredientAmountList
        };

        console.log(JSON.stringify(requestBody));

        fetch("/recipes/add", {
            method: 'POST',
            headers: {
              "Content-type": "application/json"
            },
            body: JSON.stringify(requestBody)
        });

    });

});

