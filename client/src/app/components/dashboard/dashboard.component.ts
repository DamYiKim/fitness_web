import { Component, OnInit } from '@angular/core';
import { UserService } from '../../service/user.service';
import { SharedModule } from '../../shared/shared.module';

@Component({
  selector: 'app-dashboard',
  imports: [SharedModule],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  gridStyle = {
    width: '100%',
    textAlign: 'center'
  };
  
  statsData: any = {}; 
  activities: any;
  totalCaloriesBurned: number | undefined;

  constructor(private userService: UserService) {}

  ngOnInit() {
    this.getStats();
  }

  getStats() {
    this.userService.getStats().subscribe(res => {
      console.log(res);
      this.statsData = res;
    });
  }
}