import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserDetails } from 'src/app/models/UserDetails';

@Injectable({
  providedIn: 'root'
})
export class UserDetailsService {

  private baseUrl = '/blog';
  private userDetailsUrl = `${this.baseUrl}/user-details`;

  constructor(private http: HttpClient) { }

  getUserDetails(id: number): Observable<UserDetails> {
    const url = `${this.userDetailsUrl}/${id}`;
    return this.http.get<UserDetails>(url);
  }

  getUserDetailsId(username: string): Observable<number> {
    let params = new HttpParams().set('username', username);
    return this.http.get<number>(this.userDetailsUrl, {params: params});
  }

  updateUserDetails(userDetails: UserDetails): Observable<UserDetails> {
    return this.http.put<UserDetails>(this.userDetailsUrl, userDetails);
  } 
}
