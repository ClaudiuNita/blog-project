import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/User';
import { UserService } from 'src/app/services/user.service';
import { AppComponent } from 'src/app/app.component';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users: User[] = [];
  error: any;
  isAdmin = this.appComponent.isAdmin;
  isLoggedIn = this.appComponent.isLoggedIn;

  constructor(private userService: UserService,
              private appComponent: AppComponent) { }

  ngOnInit(): void {
    this.getUsers()
  }

  getUsers(): void {
      this.userService.getUsers().subscribe(
        users => {this.users = users;
                  this.error = ''},
        err => {this.error = 'User not logged in!'});
  }

  addUser(email: string): void {
    this.userService.addUserByEmail(email).subscribe(
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
