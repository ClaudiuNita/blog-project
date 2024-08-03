import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/User';
import { UserService } from 'src/app/services/user/user.service';
import { AppComponent } from 'src/app/app.component';
import { FormControl } from "@angular/forms";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users: User[] = [];
  user?: User;
  form = new FormControl('');
  error = '';
  isAdmin = this.appComponent.isAdmin;
  isLoggedIn = this.appComponent.isLoggedIn;

  constructor(private userService: UserService,
              private appComponent: AppComponent) { }

  ngOnInit(): void {
    this.getUsers()
  }

  getUsers(): void {
      this.userService.getUsers().subscribe(
        users => {
          this.users = users;
          this.error = ''
        },

        err => {
          if (err.statusText == 'Not Found')
            this.error = 'User not logged in!';
          else 
            this.error = err.statusText
        }
      );
  }

  getUser(): void {
    // @ts-ignore
    this.userService.getUser(this.form.value? this.form.value : null).subscribe(
      user => {
          this.user = user;
          this.error = ''
      },

      err => {
          if (this.isLoggedIn) {
            this.error = err.statusText;
          } else {
            this.error = "User not logged in!";
          }
          this.user = undefined;
      }
    );
  }

  getUserByUsername(username: string): void {
    this.userService.getUserByUsername(username).subscribe(
      user => {
        this.user = user;
        this.error = ''
      },

      err => {
        this.error = err.statusText;
        this.user = undefined;
      }
    );
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

  clearUser() {
    this.user = undefined;
  }
}
