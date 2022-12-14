import { Component, OnInit } from '@angular/core';
import {User} from "../User";
import {UserService} from "../user.service";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users: User[] = [];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getUsers()
  }

  getUsers(): void{
      this.userService.getUsers()
        .subscribe(users => this.users = users);
  }

  addUser(email: string): void {
    this.userService.addUser(email).subscribe(
      () => window.location.reload()
    );
  }

  updateUser(id: bigint, email: string) {
    this.userService.updateUser(id, email).subscribe(
      () => window.location.reload()
    );
  }

  deleteUser(id: bigint) {
    this.userService.deleteUser(id).subscribe(
      () => window.location.reload()
    );
  }
}
