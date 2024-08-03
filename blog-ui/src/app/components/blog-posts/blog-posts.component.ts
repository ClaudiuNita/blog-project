import { Component, OnInit } from '@angular/core';
import { AppComponent } from 'src/app/app.component';
import { Post } from 'src/app/models/Post';
import { PostService } from 'src/app/services/post/post.service';

@Component({
  selector: 'app-blog-posts',
  templateUrl: './blog-posts.component.html',
  styleUrls: ['./blog-posts.component.css']
})
export class BlogPostsComponent implements OnInit {

  posts: Post[] = [];
  currentUserUsername = this.appComponent.currentUserUsername;

  constructor(private postService: PostService,
              private appComponent: AppComponent) { }

  ngOnInit(): void {
    this.getPosts();
  }

  getPosts(): void {
    this.postService.getPosts().subscribe(
      posts => {
        posts.sort((a, b) => new Date(b.localDateTime).getTime() - new Date(a.localDateTime).getTime());
        this.posts = posts.filter(post => post.author.username === this.currentUserUsername);
      }
    );
  }

  savePost(content: string): void {
    this.postService.savePost(content, this.currentUserUsername).subscribe(
      () => window.location.reload()
    );
  }
}
