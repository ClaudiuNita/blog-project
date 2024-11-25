export class PostComment {
    id!: bigint;
    comment!: string;
    localDateTime!: Date;
    user!: string;
    postId!: bigint;

    constructor(comment: string) {
        this.comment = comment;
    }
}