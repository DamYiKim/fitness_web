import { Component, OnInit } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';
import { UserService } from '../../service/user.service';

interface Challenge {
  id: number;
  title: string;
  description: string; 
  completed: boolean;
  completedDates: Set<string>;
}

@Component({
  selector: 'app-challenge',
  standalone: true,
  imports: [SharedModule],
  templateUrl: './challenge.component.html',
  styleUrls: ['./challenge.component.scss'],
})
export class ChallengeComponent implements OnInit {
  gridStyle = {
    width: '100%',
    textAlign: 'center'
  };

  challengeForm!: FormGroup;
  challenges: Challenge[] = [];
  daysInMonth: Date[] = [];

  constructor(
    private fb: FormBuilder,
    private message: NzMessageService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.challengeForm = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required]
    });
    this.loadChallenges();
    this.generateDaysInMonth();
  }

  submitForm(): void {
    if (this.challengeForm.valid) {
      this.userService.postChallenge(this.challengeForm.value).subscribe(res => {
        this.message.success("챌린지 등록 성공!", { nzDuration: 5000 });
        this.loadChallenges();
        this.challengeForm.reset();
      }, error => {
        this.message.error("챌린지 등록 실패", { nzDuration: 5000 });
      });
    }
  }

  generateDaysInMonth(): void {
    const now = new Date();
    const year = now.getFullYear();
    const month = now.getMonth();
    const daysInMonth = new Date(year, month + 1, 0).getDate();

    this.daysInMonth = [];
    for (let i = 1; i <= daysInMonth; i++) {
      this.daysInMonth.push(new Date(year, month, i));
    }
  }

  loadChallenges(): void {
    this.userService.getCompletedDates().subscribe((challenges: any[]) => {
      this.challenges = challenges.map(challenge => ({
        ...challenge,
        completedDates: new Set(challenge.completedDates)
      }));
    });
  }

  isCompleted(challenge: Challenge, date: Date): boolean {
    return challenge.completedDates.has(date.toISOString().split('T')[0]);
  }

  toggleCompletion(challenge: Challenge, date: Date): void {
    const dateString = date.toISOString().split('T')[0];
    const completed = !challenge.completedDates.has(dateString);
    this.userService.updateCompletionStatus(challenge.id, dateString, completed).subscribe(() => {
      if (completed) {
        challenge.completedDates.add(dateString);
      } else {
        challenge.completedDates.delete(dateString);
      }
    });
  }

  deleteChallenge(id: number): void {
    this.userService.deleteChallenge(id).subscribe({
      next: () => {
        this.message.success('활동이 삭제되었습니다!');
        this.loadChallenges();  
      },
      error: (err) => {
        this.message.error('활동 삭제에 실패했습니다.');
        console.error(err);
      },
    });
  }
}