const handle500Error = (json) => {
  if (json.status && json.status === 500) {
    throw new Error(json.message)
  }
  return json
}

const fetchAllRecipes = (cb) => {
    fetch("/recipes/all")
    .then(res => res.json())
    .then(handle500Error)
    .then(json => cb(json))
    .catch(renderError);
}

const addNewRecipe = (cb) => {
  const addRecipeForm = document.getElementById("addNewRecipe").elements;
  const name = addRecipeForm["name"].value;
  const type = addRecipeForm[""].value;
  const boot = {
    name,
    ingredients
  }
  fetch("/recipes/add", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(boot)
  })
    .then(res => res.json())
    .then(handle500Error)
    .then(json => {
      alert(`Successfully added boot with id ${json.id}: (${JSON.stringify(json)})`);
      fetchAllBoots(cb);
    })
    .catch(renderError);
}