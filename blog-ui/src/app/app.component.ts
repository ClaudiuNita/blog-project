import { Component } from '@angular/core';
import { Router } from "@angular/router";
import { AuthenticationService } from './services/authentication/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'blog';

  constructor(private router: Router,
              public authService: AuthenticationService) {}

  ngOnInit() {
    this.authService.authUser();
  }

  goAcasa() {
    this.router.navigate(['/acasa']);
  }
}
