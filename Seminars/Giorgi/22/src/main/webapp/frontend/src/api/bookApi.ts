import type { Book } from "../model/book";

export class BookApi {

    private baseUrl = "http://localhost:8080";

    async getBookById(id: number): Promise<Book> {

        const response = await fetch(
            `${this.baseUrl}/book?id=${id}`
        );

        if (!response.ok) {
            throw new Error("Network error");
        }

        return await response.json();
    }

    async createBook(title: string): Promise<void> {
        const response = await fetch(`${this.baseUrl}/book?title=${encodeURIComponent(title)}`, {
            method: "POST"
        });

        if (!response.ok) {
            throw new Error("Failed to create book");
        }
    }
}