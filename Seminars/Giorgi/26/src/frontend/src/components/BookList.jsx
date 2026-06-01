import BookItem from "./BookItem";

const BookList = ({books}) => {
    if (books.length === 0) {
        return (
            <div className="empty-state">
                <span className="icon">📭</span>
                <p>No books found</p>
            </div>
        );
    }

    return (
        <div className="book-list">
            {books.map((book) => (
                <BookItem key={book.title} book={book}/>
            ))}
        </div>
    );
};

export default BookList;

