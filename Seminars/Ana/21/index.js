// Creates an XMLHttpRequest object — the old-school way to make HTTP requests.
// Declared with 'var' so it's globally scoped and reused across calls.
var request = new XMLHttpRequest();

// Base URL for the API. A number and ".json" will be appended to form the full URL.
// e.g., animals-1.json, animals-2.json, etc.
const API = "https://learnwebcode.github.io/json-example/animals-";

// Tracks which group of animals to fetch next. Starts at 1, increments after each load.
let animalGroupNum = 1;

// ─── VERSION 1: XHR-based fetch (not actually used — see V2 below) ───────────

function handleButtonClick(button) {
    // Build the full URL and open a GET request (doesn't send it yet)
    request.open("GET", API + animalGroupNum + ".json");

    // Callback that fires when the response finishes loading
    request.onload = () => {
        // Check for a successful HTTP status (200–399)
        if (request.status >= 200 && request.status < 400) {

            // Parse the raw JSON string into a JavaScript array
            const animals = JSON.parse(request.responseText);
            // Loop through each animal and render it on the page
            for (const animal of animals) {
                addNewAnimal(animal);
            }

            // Move to the next group for the next button click
            animalGroupNum++;

            // All 3 groups loaded — hide the button so it can't be clicked again
            if (animalGroupNum === 4) {
                button.classList.add("hidden");
            }
        }
    };

    // Fires if the request fails due to a network/connection error (not a bad status code)
    request.onerror = (err) => {
        alert(err);
    };

    // Actually send the request (everything above just configured it)
    request.send();
}

// ─── VERSION 2: Fetch-based (modern, Promise-based — this one is actually used) ─

const handleButtonClickV2 = (button) => {
    // fetch() is the modern alternative to XHR; returns a Promise
    fetch(API + animalGroupNum + ".json")
        // First .then: parse the response body as JSON (also returns a Promise)
        .then(res => res.json())

        // Second .then: receives the parsed array of animals
        .then(animals => {
            // Loop through each animal and render it on the page
            for (const animal of animals) {
                addNewAnimal(animal);
            }

            // Move to the next group for the next button click
            animalGroupNum++;

            // All 3 groups loaded — hide the button using jQuery
            if (animalGroupNum === 4) {
                $('#btn').hide();
            }
        })

        // .catch handles any errors (network failure, bad JSON, etc.)
        .catch(err => {
            // NOTE: 'err.json' is likely a bug — errors don't have a .json property.
            // This should probably be 'err.message' instead.
            const reason = err.json;
            alert(reason);
        });
};

// ─── Renders a single animal onto the page ───────────────────────────────────

const addNewAnimal = (animal) => {
    // Build a simple <p> tag containing the animal's name
    let result = "<p>" + animal.name + "</p>";

    // Append it to the #animals element using jQuery
    $('#animals').append(result);
};

// ─── Entry point: runs after the full HTML document has loaded ───────────────

document.addEventListener("DOMContentLoaded", function () {
    // Find the button element by its ID
    let button = document.getElementById("btn");

    // Attach a click listener — passes the button into the handler so it can
    // be hidden once all animal groups have been loaded
    button.addEventListener("click", () => handleButtonClickV2(button));
});