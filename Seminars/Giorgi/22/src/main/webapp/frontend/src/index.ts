import { BookService } from "./service/bookService";

const service = new BookService();

async function getBook() {
    const input = document.getElementById("bookId") as HTMLInputElement;
    const resultBox = document.getElementById("result")!;
    const id = Number(input.value);

    const book = await service.findBook(id);

    if (!book) {
        resultBox.innerText = "Book not found";
        return;
    }

    resultBox.innerText = `ID: ${book.id}\nTitle: ${book.title}\nSource: ${book.source}`;
}

async function addBook() {
    const input = document.getElementById("bookTitle") as HTMLInputElement;
    const resultBox = document.getElementById("addResult")!;
    const title = input.value.trim();

    if (!title) {
        resultBox.innerText = "Please enter a title";
        return;
    }

    const success = await service.createBook(title);

    if (success) {
        resultBox.innerText = `Book "${title}" added successfully!`;
        input.value = "";
    } else {
        resultBox.innerText = "Failed to add book";
    }
}

document.getElementById("btnGet")!.addEventListener("click", getBook);
document.getElementById("btnAdd")!.addEventListener("click", addBook);
