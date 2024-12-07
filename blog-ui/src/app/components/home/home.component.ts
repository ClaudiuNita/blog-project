import { Component, OnInit } from '@angular/core';
import { Post } from 'src/app/models/Post';
import { PostComment } from 'src/app/models/PostComment';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
import { PostService } from 'src/app/services/post/post.service';
import { PostCommentsService } from 'src/app/services/postComments/post-comments.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  posts: Post[] = [];
  comments = new Map<BigInt, PostComment[]>();
  commentSectionState = new Map<BigInt, boolean>();
  editComment = new Map<BigInt, boolean>();
  searchText = '';
  
  constructor(private postService: PostService,
              private postCommentsService: PostCommentsService,
              public authService: AuthenticationService) { }

  ngOnInit(): void {
    this.getPosts();
  }
 
  getPosts(): void {
    this.postService.getPosts().subscribe(
      posts => {
        this.posts = posts.sort((a, b) => new Date(b.localDateTime).getTime() - new Date(a.localDateTime).getTime());
      }
    );
  }

  getPostCommentsByPostId(postId: bigint): void {
    this.postCommentsService.getPostCommentsByPostId(postId).subscribe(
      comments => {
        comments.sort((a, b) => new Date(b.localDateTime).getTime() - new Date(a.localDateTime).getTime());
        this.comments.set(postId, comments);

        for (let comment of comments) {
          this.editComment.set(comment.id, false);
        }

        if (this.commentSectionState.has(postId))
          this.commentSectionState.set(postId, !this.commentSectionState.get(postId));
        else
          this.commentSectionState.set(postId, true);
      },

      err => {
        if (err.status == 404) {
          const comm: PostComment[] = [new PostComment("Empty")];
          this.comments.set(postId, comm);
          
          if (this.commentSectionState.has(postId))
            this.commentSectionState.set(postId, !this.commentSectionState.get(postId));
          else
            this.commentSectionState.set(postId, true);
        }
      }
    )
  }

  savePostComment(comment: string, postId: bigint): void {
    this.postCommentsService.savePostComment(comment, postId, this.authService.currentUserUsername).subscribe(
      () => window.location.reload()
    )
  }

  updateComment(id: bigint, comment: string) {
    this.postCommentsService.updatePostComment(id, comment).subscribe(
      () => window.location.reload()
    )
  }
  
  deletePostComment(id: bigint): void {
    this.postCommentsService.deletePostComment(id).subscribe(
      () => window.location.reload()
    )
  }
}
