import { Component, OnInit } from '@angular/core';
import { User } from '../User';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user: User = new User();

  constructor(private userService: UserService,
              private router: Router) { }

  ngOnInit(): void {
  }

  registerUser() {
    this.userService.addUser(this.user).subscribe(
      () => this.router.navigateByUrl('/acasa')
    );
  }
}
