import { Component, OnInit } from '@angular/core';
import { Post } from 'src/app/models/Post';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-blog-posts',
  templateUrl: './blog-posts.component.html',
  styleUrls: ['./blog-posts.component.css']
})
export class BlogPostsComponent implements OnInit {

  posts: Post[] = [];
  username: string = '';

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getPosts();
    this.getUsername();
  }

  getPosts(): void {
    this.userService.getPosts().subscribe(
      posts => this.posts = posts
    );
    this.posts
  }

  getUsername() {
    this.userService.getUsername().subscribe(
      user => this.username = user.info
    );
  }

  savePost(content: string): void {
    this.userService.savePost(content, this.username).subscribe(
      () => window.location.reload()
    );
  }
}
