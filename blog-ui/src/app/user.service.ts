import { Injectable } from '@angular/core';
import {User} from "./User";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import { Text } from './Text';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private getUsernameUrl = '/blog/username';
  private getUsersUrl = '/blog/getUsers';
  private getUserUrl = '/blog/getUser';
  private addUserUrl = '/blog/postUser';
  private updateUserUrl = '/blog/updateUser';
  private deleteUserUrl = '/blog/deleteUser';

  constructor(private http: HttpClient) { }

  getUsername() {
    return this.http.get<Text>(this.getUsernameUrl);
  }

  getUsers(): Observable<User[]>{
    return this.http.get<User[]>(this.getUsersUrl);
  }

  getUser(id: bigint): Observable<User>{
    const url = `${this.getUserUrl}/${id}`;
    return this.http.get<User>(url);
  }

  addUser(mail: string): Observable<any>{
    let url = this.addUserUrl;
    return this.http.post(url, mail);
  }

  updateUser(id: bigint, email: string) {
    let url = this.updateUserUrl + "?id=" + id + "&email=" + email;
    return this.http.put(this.updateUserUrl, {id, email});
  }

  deleteUser(id: bigint) {
    return this.http.delete(this.deleteUserUrl, {body:id});
  }
}
