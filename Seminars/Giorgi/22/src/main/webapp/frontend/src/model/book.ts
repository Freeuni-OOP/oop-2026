export interface Book {
    id: number;
    title: string;
    author?: string;
    price?: number;
    source?: "MYSQL" | "REDIS";
}