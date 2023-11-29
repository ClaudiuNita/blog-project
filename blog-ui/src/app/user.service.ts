import { Injectable } from '@angular/core';
import { User } from "./User";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Text } from './Text';
import { UserDetails } from './UserDetails';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = '/blog';
  private usersUrl = `${this.baseUrl}/users`;
  private userUrl = `${this.baseUrl}/user`;
  private newUserUrl = `${this.baseUrl}/new-user`
  private userDetailsUrl = `${this.baseUrl}/user-details`;
  private usernameUrl = `${this.baseUrl}/username`;

  constructor(private http: HttpClient) { }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.usersUrl);
  }

  getUser(id: bigint): Observable<User> {
    const url = `${this.usersUrl}/${id}`;
    return this.http.get<User>(url);
  }

  getUserDetails(id: number): Observable<UserDetails> {
    const url = `${this.userDetailsUrl}/${id}`;
    return this.http.get<UserDetails>(url);
  }

  updateUserDetails(userDetails: UserDetails): Observable<UserDetails> {
    return this.http.put<UserDetails>(this.userDetailsUrl, userDetails);
  } 

  addUser(user: User): Observable<any> {
    return this.http.post(this.newUserUrl, user);
  }

  addUserByEmail(email: string): Observable<any> {
    return this.http.post(this.userUrl, email);
  }

  updateUser(id: bigint, email: string) {
    return this.http.put(this.userUrl, {id, email});
  }

  deleteUser(id: bigint) {
    return this.http.delete(this.userUrl, {body:id});
  }

  getUsername() {
    return this.http.get<Text>(this.usernameUrl);
  }
}
