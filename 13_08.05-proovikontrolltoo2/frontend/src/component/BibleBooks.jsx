import { useEffect, useState } from "react";

function BibleBooks() {

    const [books, setBooks] = useState([]);

    useEffect(() => {

        fetch("http://localhost:8081/api/it-books")
            .then(response => response.json())
            .then(data => {
                setBooks(data);
            });

    }, []);

    return (
        <div>
            <h1>Bible Books</h1>

            {books.map(book => (
                <div key={book.bible_id}>
                    <h3>{book.language}</h3>
                    <p>{book.version}</p>
                    <hr />
                </div>
            ))}
        </div>
    );
}

export default BibleBooks;