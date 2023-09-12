import { Injectable } from '@angular/core';
import {User} from "./User";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import { Text } from './Text';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = '/blog';
  private usersUrl = `${this.baseUrl}/users`;
  private userUrl = `${this.baseUrl}/user`;
  private usernameUrl = `${this.baseUrl}/username`;

  constructor(private http: HttpClient) { }

  getUsers(): Observable<User[]>{
    return this.http.get<User[]>(this.usersUrl);
  }

  getUser(id: bigint): Observable<User>{
    const url = `${this.usersUrl}/${id}`;
    return this.http.get<User>(url);
  }

  addUser(mail: string): Observable<any>{
    return this.http.post(this.userUrl, mail);
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
