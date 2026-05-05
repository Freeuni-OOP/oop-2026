//------------------ check execution order-----------------------
console.log("1");

setTimeout(() => console.log("2"), 0);

Promise.resolve().then(() => console.log("3"));

console.log("4");

//---------------------------------------------------------------

// AJAX fetch API
function loadUsers() {
    fetch('http://localhost:8080/controller')
        .then(response => response.json())
        .then(data => {
            $('#result').empty();
            $('#result').append(JSON.stringify(data, null, 2));
        })
        .catch(error => {
            document.getElementById('result').textContent = 'Error: ' + error;
        });
}