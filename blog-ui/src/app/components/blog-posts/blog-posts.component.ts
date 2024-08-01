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
    this.getUsername();
    this.getPosts();
  }

  getPosts(): void {
    this.userService.getPosts().subscribe(
      posts => {
        posts.sort((a, b) => new Date(b.localDateTime).getTime() - new Date(a.localDateTime).getTime());
        this.posts = posts.filter(post => post.author.username === this.username);
      }
    );
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
