package com.devs.kero.team7.learningrxjava.presenter;

import com.devs.kero.team7.data.mapper.TaskMapper;
import com.devs.kero.team7.domain.UseCases.AddTaskUseCase;
import com.devs.kero.team7.domain.UseCases.BaseCompletableUseCase;
import com.devs.kero.team7.domain.UseCases.DeleteActiveTaskUseCase;
import com.devs.kero.team7.domain.UseCases.DeleteAllTasksUseCase;
import com.devs.kero.team7.domain.UseCases.DeleteTasksUseCase;
import com.devs.kero.team7.domain.UseCases.UpdateUseCase;
import com.devs.kero.team7.domain.UseCases.getActiveTaskUseCase;
import com.devs.kero.team7.domain.entities.Task;
import com.devs.kero.team7.learningrxjava.Models.TaskView;
import com.devs.kero.team7.learningrxjava.Utils.TaskMpper;
import com.devs.kero.team7.learningrxjava.base.Presenter;
import com.devs.kero.team7.learningrxjava.contract.ActiveTasksContract;
import com.devs.kero.team7.learningrxjava.di.PerActivity;
import com.devs.kero.team7.learningrxjava.ui.dialog.UpdateDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;

@PerActivity
public class ActiveTasksPresenter extends MainScreenPresenter<getActiveTaskUseCase, ActiveTasksContract.View>  implements  ActiveTasksContract.PRESENTER{
  @Inject
  DeleteActiveTaskUseCase deleteuseCase ;
@Inject
    UpdateUseCase updateUseCase ;
    @Inject
    public ActiveTasksPresenter(getActiveTaskUseCase mMvpInteractor, DeleteTasksUseCase deleteTasksUseCase,  TaskMpper taskMapper) {
        super(mMvpInteractor,taskMapper, deleteTasksUseCase);
    }

    @Override
    public void UpdateTask(TaskView taskView) {
              SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy   HH/mm");
        dialog = UpdateDialog.NewInstance(taskView.getTaskTitle(),taskView.getTaskDescripion(), false, taskView.getRepeatBody()==null?"One Time":taskView.getRepeatBody()
                ,dateFormat.format(taskView.getDateTime()), taskView.getRepeatBody()==null);
        this.taskView = taskView;
        dialog.show(getmMvpView().getManager(), "update_dialog");
    }

    @Override
    public void handleCompleteOrActive(TaskView taskView) {
        taskView.setComplete(true);
        try {
            updateUseCase.execute(new UpdateObserver(), new Task[]{mpper.from(taskView)});
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    class UpdateObserver extends DisposableCompletableObserver{
        @Override
        public void onComplete() {
            getTasks();
        }

        @Override
        public void onError(Throwable e) {

        }
    }

    @Override
    public BaseCompletableUseCase getDelettasks() {
        return deleteuseCase;
    }
}
