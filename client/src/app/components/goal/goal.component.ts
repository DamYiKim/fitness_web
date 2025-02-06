import { Component } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../../service/user.service';
import { NzMessageService } from 'ng-zorro-antd/message';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faTimes } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-goal',
  imports: [SharedModule, FontAwesomeModule],
  templateUrl: './goal.component.html',
  styleUrl: './goal.component.scss'
})
export class GoalComponent {
  gridStyle = {
    width: '100%',   
    textAlign: 'center'  
  };
  
  faTimes = faTimes;
  goalForm! : FormGroup
  goals :any;

  constructor(private fb:FormBuilder,
    private message: NzMessageService,
    private userService: UserService
  ){}

    ngOnInit(){
      this.goalForm = this.fb.group({
        description: [null, [Validators.required]],
        startDate : [null, [Validators.required]],
        endDate: [null, [Validators.required]],
      });

      this.getAllGoals();
    }

    submitForm(){
      this.userService.postGoal(this.goalForm.value).subscribe(res=>{
        this.message.success("목표 등록 성공!", {nzDuration:5000});
        this.goalForm.reset();
        this.getAllGoals();
      }, error =>{
        this.message.error("목표 등록 중 에러가 발생하였습니다",{nzDuration:5000});
      })
    }

    getAllGoals(){
      this.userService.getGoals().subscribe(res=>{
        this.goals = res;
        console.log(this.goals);
      })
    }

    updateGoalStatus(id: number) {
      this.userService.updateGoalStatus(id).subscribe(res=>{
        this.message.success("목표 등록 성공!", {nzDuration:5000});
        this.getAllGoals();
      }, error=>{
        this.message.error("목표 등록 실패", {nzDuration:5000});
      })
    }

    deleteGoal(id: number): void { 
      this.userService.deleteGoal(id).subscribe({
        next: () => {
          this.message.success('활동이 삭제되었습니다!');
          this.getAllGoals();  
        },
        error: (err) => {
          this.message.error('활동 삭제에 실패했습니다.');
          console.error(err);
        }
      });
    }
}
