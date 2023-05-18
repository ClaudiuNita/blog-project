import { Component, OnInit } from '@angular/core';
import { FormControl } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";
import { User } from "../User";
import { UserService } from "../user.service";

@Component({
  selector: 'app-acasa',
  templateUrl: './acasa.component.html',
  styleUrls: ['./acasa.component.css']
})
export class AcasaComponent implements OnInit {

  user?: User;
  form = new FormControl('');
  error: any;

  constructor(private route: ActivatedRoute,
              private userService: UserService) { }

  ngOnInit(): void {
  }

  getUser(): void {
    // @ts-ignore
    this.userService.getUser(BigInt(this.form.value)).subscribe(
      user => {
          this.user = user
          this.error = ''
       },

      err => {
          console.log(err.error);
          this.error = err.error.status;
          this.user = undefined;
      }
    );
  }

}
