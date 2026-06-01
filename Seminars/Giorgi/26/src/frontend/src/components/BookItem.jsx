const BookItem = ({book}) => {
    return (
        <div className="book-card">
            <p className="book-card__title">{book.title}</p>
        </div>
    );
};

export default BookItem;