import { Injectable } from '@angular/core';
import {User} from "./User";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private getUsersUrl = 'http://localhost:8090/blog/getUsers';
  private getUserUrl = 'http://localhost:8090/blog/getUser';
  private addUserUrl = 'http://localhost:80890/blog/postUser';
  private updateUserUrl = 'http://localhost:8090/blog/updateUser';
  private deleteUserUrl = 'http://localhost:8090/blog/deleteUser';

  constructor(private http: HttpClient) { }

  getUsers(): Observable<User[]>{
    console.log(this.http.get<User[]>(this.getUsersUrl));
    return this.http.get<User[]>(this.getUsersUrl);
  }

  getUser(id: bigint): Observable<User>{
    const url = `${this.getUserUrl}/${id}`;
    return this.http.get<User>(url);
  }

  addUser(mail: string): Observable<any>{
    console.log(mail);
    let url = this.addUserUrl;
    return this.http.post(url, mail);
  }

  updateUser(id: bigint, email: string) {
    console.log(id + email);
    let url = this.updateUserUrl + "?id=" + id + "&email=" + email;
    return this.http.put(this.updateUserUrl, {id, email});
  }

  deleteUser(id: bigint) {
    return this.http.delete(this.deleteUserUrl, {body:id});
  }
}
