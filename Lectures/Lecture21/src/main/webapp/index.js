import hello from "./prototypes.js";
import animal from "./lion.json" with {type: "json"};

//------------------ check execution order-----------------------
console.log("1");

setTimeout(() => console.log("2"), 0);

Promise.resolve().then(() => console.log("3"));

console.log("4");

function logFive() {
    console.log("5");
}

function logFiveAndSix() {
    logFive();
    console.log("6");
}

logFiveAndSix();

hello();
console.log(hello.language);
console.log(animal);

//---------------------------------------------------------------

// AJAX (https://www.w3schools.com/js/js_ajax_intro.asp)

function loadUsers() {
    const $result = $('#result');
    const $status = $('#status');

    fetch('/controller')
        .then(response => response.json())
        .then(data => {
            $result.text(JSON.stringify(data, null, 2));
            $status.text('');
        })
        .catch(error => {
            $result.text('Error: ' + error);
        });
}

function addUser() {
    const $input = $('#userName');
    const $status = $('#status');
    const name = String($input.val() || '').trim();

    if (!name) {
        $status.text('Please enter a user name.');
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
                $status.text('Error: ' + data.error);
                return;
            }

            $input.val('');
            $status.text('User added: ' + data.name);
            loadUsers();
        })
        .catch(error => {
            $status.text('Error: ' + error);
        });
}

// `index.js` is an ES module; expose handlers for inline HTML attributes.
window.loadUsers = loadUsers;
window.addUser = addUser;
