import type { Book } from "../model/book";
import { BookApi } from "../api/bookApi";

export class BookService {

    private api = new BookApi();

    async findBook(id: number): Promise<Book | null> {
        try {
            return await this.api.getBookById(id);
        } catch (e) {
            console.error("Error fetching book", e);
            return null;
        }
    }

    async createBook(title: string): Promise<boolean> {
        try {
            await this.api.createBook(title);
            return true;
        } catch (e) {
            console.error("Error creating book", e);
            return false;
        }
    }
}