const addStudent = (): void => {
    let firstName: string = (document.getElementById("first-name") as HTMLInputElement).value;
    const lastName: string = (document.getElementById("last-name") as HTMLInputElement).value;

    if (firstName === "" || lastName === "") {
        alert("Empty fields!");
        return;
    }

    const table = document.getElementById("students-table");

    const newStudentRow: HTMLTableRowElement = (table as HTMLTableElement).insertRow();
    const firstNameCell: HTMLTableCellElement = newStudentRow.insertCell(0);
    const lastNameCell: HTMLTableCellElement = newStudentRow.insertCell(1);

    firstNameCell.innerHTML = firstName;
    lastNameCell.innerHTML = lastName;
};