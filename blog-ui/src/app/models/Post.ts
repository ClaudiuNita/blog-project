import { User } from "./User";

export class Post {
    id!: bigint;
    title!: string;
    content!: string;
    localDateTime!: Date;
    author!: User;
    editable!: boolean;
}