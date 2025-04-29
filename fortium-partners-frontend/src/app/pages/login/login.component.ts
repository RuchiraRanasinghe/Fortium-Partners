import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  email = '';
  password = '';
  message = '';

  constructor(private authService: AuthService, private router: Router) {}

  login(): void {
    this.authService.login(this.email, this.password).subscribe({
      next: user => {
        this.message = 'Login successful';
        // Store user/token if needed
        this.router.navigate(['/main-page']); 
      },
      error: err => this.message = err.error.message || 'Login failed'
    });
  }

  switchToRegister() {
    this.router.navigate(['/register']);
  }  
}
