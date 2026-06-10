export default function hello() {
    console.log("Hi");
}

hello.language = "English";

// --------------- prototypal inheritance -----------------

const vehicle = {
    move() {
        console.log("Vehicle is moving");
    }
};

const car = Object.create(vehicle);
car.wheels = 4;

car.drive = function () {
    console.log("Car is driving");
};

const tesla = Object.create(car);
tesla.battery = 100;

tesla.autoPilot = function () {
    console.log("Autopilot enabled");
};