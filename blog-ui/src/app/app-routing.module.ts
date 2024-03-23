import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AcasaComponent } from "./components/acasa/acasa.component";
import { UsersComponent } from "./components/users/users.component";
import { BlogPostsComponent } from "./components/blog-posts/blog-posts.component";
import { UserDetailsComponent } from './components/user-details/user-details.component';
import { RegisterComponent } from './components/register/register.component';

const routes: Routes = [
  {path: 'acasa', component: AcasaComponent},
  {path: 'users', component: UsersComponent},
  {path: 'blogposts', component: BlogPostsComponent},
  {path: 'user-details/:id', component: UserDetailsComponent},
  {path: 'register', component: RegisterComponent},
  {path: '**', component: AcasaComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
