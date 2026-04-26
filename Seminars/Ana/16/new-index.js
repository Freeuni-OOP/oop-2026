// =============================================================
// new-index.js — Student Registry Logic
//
// WHAT IS document AND HOW DOES JAVASCRIPT ACCESS IT?
//
// When the browser loads an HTML file, it reads every tag and
// builds a giant JavaScript OBJECT out of them called the DOM
// (Document Object Model).
//
// The DOM is a TREE of objects — each HTML element becomes a
// JavaScript object with properties and methods you can use.
//
// Example: this HTML...
//
//   <body>
//     <form id="students-form">
//       <input id="first-name"/>
//     </form>
//     <table id="students-table"></table>
//   </body>
//
// ...becomes this tree of objects in memory:
//
//   document
//   └── body
//       ├── form  (id: "students-form")
//       │   └── input  (id: "first-name")
//       └── table  (id: "students-table")
//
// `document` is the ROOT of this tree — the entry point.
// It is a GLOBAL variable the browser creates automatically.
// You never have to import or create it. It's just always there.
//
// To reach any element, you start from document and navigate down:
//   document.getElementById("first-name")
//   → starts at document (root)
//   → searches the whole tree
//   → finds the element with id="first-name"
//   → returns it as a JavaScript object
//
// That object has PROPERTIES (data you can read/write):
//   .value       — text the user typed into an input
//   .innerHTML   — HTML content inside an element
//   .textContent — plain text inside an element
//   .className   — the element's CSS class
//   .id          — the element's id
//
// And METHODS (actions you can perform):
//   .insertRow()    — adds a row to a table
//   .insertCell()   — adds a cell to a row
//   .appendChild()  — adds a child element
//   .remove()       — removes the element from the page
//   .focus()        — moves the cursor into an input
//   .addEventListener() — listens for user events (click, keydown...)
//
// So the flow is always:
//   1. Use document to FIND an element
//   2. Read or change its PROPERTIES
//   3. Or call its METHODS to perform actions
// =============================================================


// ─── FINDING ELEMENTS ────────────────────────────────────────
//
// We find all the elements we need ONCE at the top.
// Storing them in variables is more efficient than calling
// getElementById() every single time we need them.

const firstNameInput = document.getElementById("first-name");
//
// document         — the root object representing the whole page
// .getElementById  — a METHOD on document. Searches the entire DOM
//                    tree for an element with a matching id attribute.
// ("first-name")   — the id we're searching for
//
// Returns: the <input id="first-name"> element as a JS object.
// If no element has that id: returns null (nothing found).
//
// We store it in `firstNameInput` so we can use it later.
// const — this variable will never be reassigned.

const lastNameInput = document.getElementById("last-name");
const tableBody = document.getElementById("students-tbody");
//
// We now target <tbody id="students-tbody"> specifically.
// Targeting <tbody> means new rows are always added in the
// body section — the header row is completely safe.


// ─── THE MAIN FUNCTION ───────────────────────────────────────

function addStudent() {
    //
    // function — Defines a reusable block of code.
    // addStudent — The name. Called by onclick="addStudent()" in HTML.
    // () — No parameters. This function reads inputs itself.
    // { } — Everything between these braces is the function body.
    //
    // This function runs every time the Submit button is clicked.


    // STEP 1: Read what the user typed
    // ─────────────────────────────────
    const firstName = firstNameInput.value;
    //
    // .value — A PROPERTY on input elements.
    // Contains the current text the user has typed.
    // If the user typed "Giorgi" → firstName = "Giorgi"
    // If the user typed nothing  → firstName = ""

    const lastName = lastNameInput.value;


    // STEP 2: Validate — check fields are not empty
    // ──────────────────────────────────────────────
    if (firstName === "" || lastName === "") {
        //
        // if (condition) { } — Only runs the block if condition is TRUE.
        //
        // firstName === "" — Checks if firstName is an empty string.
        // === is STRICT equality: checks both VALUE and TYPE.
        // (Always use === instead of ==. == has confusing edge cases.)
        //
        // || — OR operator.
        // The whole condition is true if EITHER side is true.
        // Meaning: if first name is empty OR last name is empty → block runs.

        alert("Please fill in both fields!");
        //
        // alert() — A built-in browser function (not DOM, not document).
        // Shows a popup dialog box with a message and an OK button.
        // Pauses everything until the user clicks OK.
        // Fine for simple projects, but real apps use custom error UI instead.

        return;
        //
        // return — Exits the function immediately.
        // Nothing below this line runs.
        // This is called an EARLY RETURN or GUARD CLAUSE.
        // It stops bad/empty data from ever reaching the table.
    }


    // STEP 3: Create a new row in the table
    // ──────────────────────────────────────
    const newRow = tableBody.insertRow();
    //
    // tableBody — our <tbody> element (found at the top of the file).
    //
    // .insertRow() — A METHOD on table and tbody elements.
    // It does three things automatically:
    //   1. Creates a new <tr> element
    //   2. Appends it to the END of the tbody
    //   3. Returns the new <tr> so we can work with it
    //
    // After this line, a new empty row exists in the table:
    //   <tbody>
    //     <tr></tr>   ← the new empty row
    //   </tbody>


    // STEP 4: Create cells inside that row
    // ──────────────────────────────────────
    const firstNameCell = newRow.insertCell(0);
    //
    // .insertCell(index) — A METHOD on table row elements.
    // Creates a new <td> at the given column position.
    // 0 = first column (indexes always start at 0 in JavaScript).
    // Returns the new <td> element.
    //
    // After this line:
    //   <tr>
    //     <td></td>   ← firstNameCell (empty so far)
    //   </tr>

    const lastNameCell = newRow.insertCell(1);
    //
    // 1 = second column.
    //
    // After this line:
    //   <tr>
    //     <td></td>   ← firstNameCell
    //     <td></td>   ← lastNameCell (empty so far)
    //   </tr>


    // STEP 5: Put the text into the cells
    // ─────────────────────────────────────
    firstNameCell.textContent = firstName;
    //
    // .textContent — A PROPERTY that sets or gets the TEXT inside an element.
    // We set it to firstName ("Giorgi"), so the cell now contains that text.
    //
    // FIX: The original used .innerHTML — we changed it to .textContent.
    //
    // WHY .textContent IS SAFER THAN .innerHTML:
    // Imagine a user types: <img src=x onerror="alert('hacked')">
    //
    // .innerHTML   → treats it as real HTML → runs the script → DANGEROUS
    // .textContent → treats it as plain text → displays it literally → SAFE
    //
    // Rule: Use .textContent when inserting user-typed data. Always.
    // Use .innerHTML only when you control the content yourself.

    lastNameCell.textContent = lastName;
    //
    // Final result in the DOM:
    //   <tr>
    //     <td>Giorgi</td>
    //     <td>Beridze</td>
    //   </tr>


    // STEP 6: Clear the input fields
    // ────────────────────────────────
    firstNameInput.value = "";
    lastNameInput.value = "";
    //
    // We can WRITE to .value just like we READ from it.
    // Setting it to "" empties the input field.
    // Now the user can immediately type the next student without
    // having to manually delete what they typed before.

    firstNameInput.focus();
    //
    // .focus() — Moves the cursor back into the first input field.
    // Quality-of-life improvement: user can type the next name immediately
    // without clicking the field again.
}


// =============================================================
// ALTERNATIVE: Cleaner event handling (no onclick in HTML)
//
// The HTML uses: onclick="addStudent()"
// This works, but it mixes HTML and JavaScript together.
// A cleaner approach keeps them separate:
//
//   Remove onclick="addStudent()" from the button in HTML.
//   Change type="button" to type="submit" on the button.
//   Then use this code instead:
//
//   const form = document.getElementById("students-form");
//
//   form.addEventListener("submit", function(event) {
//       event.preventDefault();
//       // "submit" fires when button is clicked OR Enter is pressed.
//       // event.preventDefault() stops the page from reloading.
//       addStudent();
//   });
//
// addEventListener("submit", ...) also means pressing ENTER in
// any input automatically submits — you get that for free.
// =============================================================