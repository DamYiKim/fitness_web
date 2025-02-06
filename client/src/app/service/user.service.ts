import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const BASIC_URL = "http://localhost:8080/";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http : HttpClient) { }

  postActivity(activityDTO:any): Observable<any>{
    return this.http.post(BASIC_URL + "api/activity", activityDTO);
  }

  getActivities(): Observable<any>{
    return this.http.get(BASIC_URL + "api/activities");
  }

  deleteActivity(id: number): Observable<any> {
    return this.http.delete<any>(`${BASIC_URL}api/activities/${id}`);
}

  postWorkout(workoutDto:any): Observable<any>{
    return this.http.post(BASIC_URL + "api/workout", workoutDto);
  }

  getWorkouts(): Observable<any>{
    return this.http.get(BASIC_URL + "api/workouts");
  }

  postGoal(goalDto:any): Observable<any>{
    return this.http.post(BASIC_URL + "api/goal", goalDto);
  }

  getGoals(): Observable<any>{
    return this.http.get(BASIC_URL + "api/goals");
  }

  updateGoalStatus(id: number): Observable<any> {
    return this.http.get(BASIC_URL + "api/goal/status/"+id);
  }

  deleteGoal(id : number): Observable<any> {
    return this.http.delete<any>(`${BASIC_URL}api/goals/${id}`);
  }

  postChallenge(challengeDto: any): Observable<any> {
    return this.http.post(BASIC_URL + "api/challenge", challengeDto);
  }

  getCompletedDates(): Observable<any> {
    return this.http.get(BASIC_URL + "api/challenges");
  }

  updateCompletionStatus(challengeId: number, date: string, completed: boolean): Observable<any> {
    return this.http.post( `${BASIC_URL}api/challenges/${challengeId}/completion`, { date, completed });
  }

  deleteChallenge(id: number): Observable<any> {
    return this.http.delete<any>(`${BASIC_URL}api/challenges/${id}`);
}

  getStats(): Observable<any>{
    return this.http.get(BASIC_URL + "api/stats");
  }

  getGraphStats(): Observable<any>{
    return this.http.get(BASIC_URL + "api/graphs");
  }
}
  
