import { Component, OnInit } from '@angular/core';
import { Post } from 'src/app/models/Post';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
import { PostService } from 'src/app/services/post/post.service';

@Component({
  selector: 'app-blog-posts',
  templateUrl: './blog-posts.component.html',
  styleUrls: ['./blog-posts.component.css']
})
export class BlogPostsComponent implements OnInit {
  
  posts: Post[] = [];
  error = '';

  constructor(private postService: PostService,
              public authService: AuthenticationService) { }

  ngOnInit(): void {
    this.getPosts();
    if (!this.authService.isLoggedIn) 
      this.error = 'User not logged in!';
  }

  getPosts(): void {
    this.postService.getPosts().subscribe(
      posts => {
        posts.sort((a, b) => new Date(b.localDateTime).getTime() - new Date(a.localDateTime).getTime());
        this.posts = posts.filter(post => post.author.username === this.authService.currentUserUsername);
      }
    );
  }

  savePost(content: string): void {
    this.postService.savePost(content, this.authService.currentUserUsername).subscribe(
      () => window.location.reload()
    );
  }

  updatePost(post: Post) {  
    this.postService.editPost(post).subscribe(
      () => window.location.reload()
    );
  }

  deletePost(id: bigint) {
    this.postService.deletePost(id).subscribe(
      () => window.location.reload()
    );
  } 
  
  editPost(post: Post) {
    post.editable = !post.editable;
  }
}
