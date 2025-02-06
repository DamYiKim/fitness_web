import { Component } from '@angular/core';
import { SharedModule } from '../../shared/shared.module'; 
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';
import { UserService } from '../../service/user.service'; 
import { NzCalendarModule } from 'ng-zorro-antd/calendar';

@Component({
  selector: 'app-activity',
  standalone: true,
  imports: [SharedModule, NzCalendarModule], 
  templateUrl: './activity.component.html',
  styleUrl: './activity.component.scss',
})
export class ActivityComponent {
  gridStyle = {
    width: '100%',   
    textAlign: 'center'  
  };
  
  activityForm!: FormGroup;
  activities: any[] = [];
  activityMap: { [key: string]: any[] } = {}; 

  listOfType: any[] = [
    "없음",
    "헬스",
    "걷기",
    "달리기",
    "클라이밍",
    "필라테스",
    "요가",
    "축구",
    "농구",
    "야구",
    "댄스",
    "수영",
    "자전거", 
    "복싱",
    "크로스핏",
    "스트레칭",
    "무술",
    "등산",
    "기타",
];


  constructor(
    private fb: FormBuilder,
    private message: NzMessageService,
    private userService: UserService
  ) {}

  ngOnInit() {
    this.initializeForm();
    this.loadActivities();
  }

  private initializeForm() {
    this.activityForm = this.fb.group({
      caloriesBurned: [null, [Validators.required, Validators.min(0)]],
      steps: [null, [Validators.required, Validators.min(0)]],
      distance: [null, [Validators.required, Validators.min(0)]],
      date: [null, [Validators.required]],
      type: [null, Validators.required], 
      duration: [null, Validators.required] 
    });
  }

  private processActivities(activities: any[]) {
    this.activityMap = {};
    activities.forEach(activity => {
      const utcDate = new Date(activity.date);
      const localDate = new Date(
        utcDate.getUTCFullYear(),
        utcDate.getUTCMonth(),
        utcDate.getUTCDate()
      );
      
      const dateKey = this.getDateKey(localDate);
      if (!this.activityMap[dateKey]) {
        this.activityMap[dateKey] = [];
      }
      this.activityMap[dateKey].push(activity);
    });
  }

  private getDateKey(date: Date): string {
    date.setHours(0, 0, 0, 0); 
    return date.toISOString().split('T')[0];
  }

  getActivitiesForDate(date: Date): any[] {
    const dateKey = this.getDateKey(date); 
    return this.activityMap[dateKey] || [];
  }

  submitForm() {
    if (this.activityForm.valid) {
      const rawDate = new Date(this.activityForm.value.date);
    const formattedDate = `${rawDate.getFullYear()}-${(rawDate.getMonth() + 1).toString().padStart(2, '0')}-${rawDate.getDate().toString().padStart(2, '0')}`;

    const formData = {
      ...this.activityForm.value,
      date: formattedDate 
    };

      this.userService.postActivity(formData).subscribe({
        next: (res) => {
          this.message.success('활동 기록 성공!');
          this.activityForm.reset();
          this.loadActivities();
        },
        error: (err) => {
          this.message.error('기록 저장 실패');
          console.error(err);
        }
      });
    }
  }

  
  private loadActivities() {
    this.userService.getActivities().subscribe({
      next: (res) => {
        this.activities = res;
        this.processActivities(res);
      },
      error: (err) => {
        this.message.error('데이터 불러오기 실패');
        console.error(err);
      }
    });
  }

  deleteActivity(id: number) {
    this.userService.deleteActivity(id).subscribe({
      next: () => {
        this.message.success('활동이 삭제되었습니다!');
        this.loadActivities();  
      },
      error: (err) => {
        this.message.error('활동 삭제에 실패했습니다.');
        console.error(err);
      },
    });
  }
  editActivity(id: number){

  }
}

