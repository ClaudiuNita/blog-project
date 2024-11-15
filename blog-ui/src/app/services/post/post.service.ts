import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Post } from 'src/app/models/Post';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  private baseUrl = '/blog';

  constructor(private http: HttpClient) { }

  getPosts(): Observable<Post[]> {
    return this.http.get<Post[]>(this.baseUrl + '/posts');
  }

  savePost(title: string, content: string, username: string): Observable<any> {
    return this.http.post(this.baseUrl + '/post', { title, content, author: { username } });
  }

  updatePost(post: Post) {
    return this.http.put(this.baseUrl + '/post', post);
  }
  
  deletePost(id: bigint) {
    return this.http.delete(this.baseUrl + '/post/' + id);
  }
}
