import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PostComment } from 'src/app/models/PostComment';

@Injectable({
  providedIn: 'root'
})
export class PostCommentsService {

  private baseUrl = '/blog';
  private commentUrl = `${this.baseUrl}/comment`;

  constructor(private http: HttpClient) { }

  getPostCommentsByPostId(postId: bigint): Observable<PostComment[]> {
    return this.http.get<PostComment[]>(this.commentUrl + '/post/' + postId);
  }

  savePostComment(comment: string, postId: bigint, user: string): Observable<PostComment> {
    return this.http.post<PostComment>(this.commentUrl, { comment, postId, user }); 
  }

  updatePostComment(id: bigint, comment: string): Observable<PostComment> {
    return this.http.put<PostComment>(this.commentUrl, { id, comment });
  }

  deletePostComment(id: bigint): Observable<PostComment> {
    return this.http.delete<PostComment>(this.commentUrl + '/' + id);
  }
}
