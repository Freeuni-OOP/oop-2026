function addStudent() {
    const firstNameInput = document.getElementById("first-name").value;
    const lastNameInput = document.getElementById("last-name").value;

    if (firstNameInput === "" || lastNameInput === "") {
        alert("Please fill in both fields.");
    }
    const tableBody = document.getElementById("students-tbody");
    const newRow = tableBody.insertRow();
    const firstNameCell = newRow.insertCell(0);
    const lastNameCell = newRow.insertCell(1);
    firstNameCell.textContent = firstNameInput;
    lastNameCell.textContent = lastNameInput;

    firstNameInput.value = "";
    lastNameInput.value = "";

    firstNameInput.focus()
}