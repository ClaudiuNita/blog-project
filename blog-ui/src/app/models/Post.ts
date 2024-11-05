import { User } from "./User";

export class Post {
    id!: bigint;
    content!: string;
    localDateTime!: Date;
    author!: User;
    editable!: boolean;
}