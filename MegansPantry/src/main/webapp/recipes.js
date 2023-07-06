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