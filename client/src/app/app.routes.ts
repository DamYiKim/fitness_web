import { Routes } from '@angular/router';
import { ActivityComponent } from './components/activity/activity.component';
import { GoalComponent } from './components/goal/goal.component';
import { ChallengeComponent } from './components/challenge/challenge.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';

export const routes: Routes = [
    {path: "activity",component:ActivityComponent},
    {path: "goal", component:GoalComponent},
    {path: "challenge", component:ChallengeComponent},
    {path: "dashboard", component:DashboardComponent},
];
