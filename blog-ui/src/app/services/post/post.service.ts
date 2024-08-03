import { getLocaleDateFormat } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Post } from 'src/app/models/Post';
import { User } from 'src/app/models/User';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  private baseUrl = '/blog';

  constructor(private http: HttpClient) { }

  getPosts(): Observable<Post[]> {
    return this.http.get<Post[]>(this.baseUrl + '/posts');
  }

  savePost(content: string, username: string): Observable<any> {
    return this.http.post(this.baseUrl + '/post', { content, author: { username } } );
  }
}
