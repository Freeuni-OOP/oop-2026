//------------------ check execution order-----------------------
console.log("1");

setTimeout(() => console.log("2"), 0);

Promise.resolve().then(() => console.log("3"));

console.log("4");

//---------------------------------------------------------------

// AJAX (https://www.w3schools.com/js/js_ajax_intro.asp)

function loadUsers() {
    fetch('/controller')
        .then(response => response.json())
        .then(data => {
            document.getElementById('result').textContent = JSON.stringify(data, null, 2);
            document.getElementById('status').textContent = '';
        })
        .catch(error => {
            document.getElementById('result').textContent = 'Error: ' + error;
        });
}

function addUser() {
    const input = document.getElementById('userName');
    const name = input.value.trim();

    if (!name) {
        document.getElementById('status').textContent = 'Please enter a user name.';
        return;
    }

    fetch('/controller', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
        },
        body: 'name=' + encodeURIComponent(name)
    })
        .then(response => response.json())
        .then(data => {
            if (data.error) {
                document.getElementById('status').textContent = 'Error: ' + data.error;
                return;
            }

            input.value = '';
            document.getElementById('status').textContent = 'User added: ' + data.name;
            loadUsers();
        })
        .catch(error => {
            document.getElementById('status').textContent = 'Error: ' + error;
        });
}
