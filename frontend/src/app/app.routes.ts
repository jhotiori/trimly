import { Routes } from '@angular/router';
import { CrudComponent } from './components/crud/crud.component';
import { LoginComponent } from './components/login/login.component';

export const routes: Routes = [
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: 'app',
        children: [
            {
                path: 'crud',
                component: CrudComponent
            }
        ]
    },
    {
        path: '**',
        redirectTo: 'login'
    }
];
