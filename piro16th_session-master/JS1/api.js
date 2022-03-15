axios.get('https://jsonplaceholder.typicode.com/users', { params: { name: 'Leanne Graham' } })
.then(function (response) {
  // handle success
  console.log(response, 'success');
})
.catch(function (error) {
  // handle error
  console.log(error, 'error');
})