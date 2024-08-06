import { Component, OnInit } from '@angular/core';
import { Post } from 'src/app/models/Post';
import { PostService } from 'src/app/services/post/post.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  posts: Post[] = [];

  constructor(private postService: PostService) { }

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
}
