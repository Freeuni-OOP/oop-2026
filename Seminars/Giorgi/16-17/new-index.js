"use strict";
const addStudent = () => {
    let firstName = document.getElementById("first-name").value;
    const lastName = document.getElementById("last-name").value;
    if (firstName === "" || lastName === "") {
        alert("Empty fields!");
        return;
    }
    const table = document.getElementById("students-table");
    const newStudentRow = table.insertRow();
    const firstNameCell = newStudentRow.insertCell(0);
    const lastNameCell = newStudentRow.insertCell(1);
    firstNameCell.innerHTML = firstName;
    lastNameCell.innerHTML = lastName;
};
