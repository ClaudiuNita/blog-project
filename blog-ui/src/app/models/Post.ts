import { User } from "./User";

export interface Post {
    id: bigint;
    content: string;
    localDateTime: Date;
    author: User;
}