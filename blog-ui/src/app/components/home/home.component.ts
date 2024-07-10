import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { Post } from 'src/app/models/Post';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  posts: Post[] = [];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getPosts();
  }
 
  getPosts(): void {
    this.userService.getPosts().subscribe(
      posts => {
        this.posts = posts;
        this.posts.sort((a, b) => new Date(b.localDateTime).getTime() - new Date(a.localDateTime).getTime());
      }
    );
  }
}
