var request = new XMLHttpRequest();

const API = "https://learnwebcode.github.io/json-example/animals-";
let animalGroupNum = 1;

function handleButtonClick(button) {
    request.open("GET", API + animalGroupNum + ".json");

    request.onload = () => {
        if (request.status >= 200 && request.status < 400) {
            const animals = JSON.parse(request.responseText);
            for (const animal of animals) {
                addNewAnimal(animal);
            }

            animalGroupNum++;

            if (animalGroupNum === 4) {
                button.classList.add("hidden");
            }
        }
    };

    request.onerror = (err) => {
        alert(err);
    };

    request.send();
}

function handleButtonClickV2(button) {
    fetch(API + animalGroupNum + ".json")
        .then(response => {
            if (response.status >= 200 && response.status < 400) {
                return response.json();
            }
            throw new Error("Not found");
        })
        .then(animals => {
            for (const animal of animals) {
                addNewAnimal(animal);
            }

            animalGroupNum++;

            if (animalGroupNum === 4) {
                button.classList.add("hidden");
            }
        })
        .catch(error => {
            alert(error);
        })
}

function addNewAnimal(animal) {
    $('#animals').append(JSON.stringify(animal, null, 2));
}

document.addEventListener("DOMContentLoaded", function () {
    const btn = document.getElementById('btn');
    btn.addEventListener('click', () => handleButtonClickV2(btn));
});