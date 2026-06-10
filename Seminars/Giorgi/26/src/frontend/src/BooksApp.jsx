import {useEffect, useState} from "react";
import BookList from "./components/BookList";

const BooksApp = () => {
    const [books, setBooks] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8084/book')
            .then(data => data.json())
            .then(fetchedBooks => setBooks(fetchedBooks))
            .catch(_ => alert("Books not found"));
    }, []);

    return (
        <div className="app">
            <header className="app-header">
                <h1>📚 Book Library</h1>
                <p>{books.length} book{books.length !== 1 ? 's' : ''} available</p>
            </header>
            <BookList books={books}/>
        </div>
    );
};

export default BooksApp;
