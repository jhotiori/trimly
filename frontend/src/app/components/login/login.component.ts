import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
    selector: 'app-login',
    imports: [FormsModule],
    templateUrl: './login.component.html',
    styleUrl: './login.component.scss'
})
export class LoginComponent {
    username = '';
    password = '';

    constructor(private router: Router) {}

    login() {
        if (this.hasValidCredentials()) {
            this.onLoginSuccess();
            return;
        }

        this.onLoginFailed();
    }

    onLoginSuccess() {
        console.log("User has successfully logged-in!")
        this.router.navigate(["/app/crud"]);
    }

    onLoginFailed() {
        console.warn("Login failed - username or password incorrect!")
    }

    hasValidCredentials() {
        return this.username == "admin" && this.password == "admin";
    }
}
