package com.devs.kero.team7.learningrxjava.presenter;

import android.widget.Toast;

import com.devs.kero.team7.domain.UseCases.BaseCompletableUseCase;
import com.devs.kero.team7.domain.UseCases.BaseUseCase;
import com.devs.kero.team7.domain.UseCases.DeleteActiveTaskUseCase;
import com.devs.kero.team7.domain.UseCases.DeleteTasksUseCase;
import com.devs.kero.team7.domain.entities.Task;
import com.devs.kero.team7.learningrxjava.Models.TaskView;
import com.devs.kero.team7.learningrxjava.Utils.TaskMpper;
import com.devs.kero.team7.learningrxjava.base.BaseView;
import com.devs.kero.team7.learningrxjava.base.Presenter;
import com.devs.kero.team7.learningrxjava.contract.MainScreenContract;
import com.devs.kero.team7.learningrxjava.ui.adapters.TasksAdapter;
import com.devs.kero.team7.learningrxjava.ui.dialog.UpdateDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;


public abstract class MainScreenPresenter<I extends BaseUseCase , V extends MainScreenContract.View> extends Presenter<I, V>
                                                implements MainScreenContract.Presenter
                                                , TasksAdapter.ListenerToClick, UpdateDialog.UpdateClickListener {

    TaskMpper mpper ;
    private boolean IsSelectionTime;
    private int NumberOfSelected;
    private List<TaskView > taskViewsToDelete ;
    private  DeleteTasksUseCase deleteTasksUseCase ;
    private TasksAdapter adapter ;
    public abstract BaseCompletableUseCase getDelettasks();
    public abstract void UpdateTask(TaskView taskView) ;
    public abstract void handleCompleteOrActive(TaskView taskView);
    int Size ;
    UpdateDialog  dialog ;
    TaskView  taskView ;


    public MainScreenPresenter(I mMvpInteractor, TaskMpper mpper, DeleteTasksUseCase deleteTasksUseCase) {
        super(mMvpInteractor);
        this.mpper = mpper;
        this.deleteTasksUseCase = deleteTasksUseCase;
        NumberOfSelected=0 ;
        IsSelectionTime = false ;
    }


    @Override
    public void getTasks() {
      getmMvpInteractor().execute(new TasksObserver(),  new Void[]{null, null});
    }

    @Override
    public void showTaks(List<TaskView> tasks) {
        adapter = new TasksAdapter(getmMvpView().getContexte(), tasks);
        adapter.setListenerToClick(this);
        if(tasks!=null){
            if(tasks.isEmpty()){
                getmMvpView().showTasks(adapter);
                getmMvpView().showNoTasks();
                Size=0;
            }else  {
                Size=tasks.size();
                getmMvpView().showTasks(adapter);
            }

        }else{
            getmMvpView().showNoTasks();
            Size=0 ;
        }


    }

    @Override
    public void handleLongClicks(boolean isSelected, TaskView taskView) {
        if(IsSelectionTime && isSelected){
            taskViewsToDelete.remove(taskView);
            NumberOfSelected--;
            changeLabel(NumberOfSelected);
            if(NumberOfSelected==0){
                IsSelectionTime =false ;
                getmMvpView().showNotDeleteTime();
                taskViewsToDelete =null ;

            }

        }else if(!IsSelectionTime){
            taskViewsToDelete = new ArrayList<>();
            taskViewsToDelete.add(taskView);
            NumberOfSelected++ ;
            IsSelectionTime = true  ;
            getmMvpView().showDeleteTime();
            changeLabel(1);
        }else if(IsSelectionTime && !isSelected){
            taskViewsToDelete.add(taskView);
            NumberOfSelected++ ;
            changeLabel(NumberOfSelected);


        }

    }

    @Override
    public boolean handleClicks(boolean isSelected, TaskView taskView) {
        if(IsSelectionTime){
            if(isSelected){
               taskViewsToDelete.remove(taskView);
               NumberOfSelected-- ;
               changeLabel(NumberOfSelected);
               if(NumberOfSelected==0){
                   getmMvpView().showNotDeleteTime();
                  taskViewsToDelete=null;

                  IsSelectionTime=false  ;
                  return  true;
               }
            }else{
                taskViewsToDelete.add(taskView);
                NumberOfSelected++ ;
                changeLabel(NumberOfSelected);
            }

        }else {
            UpdateTask(taskView);
        }
      return IsSelectionTime ;
    }
    @Override
    public void changeLabel(int number) {
        if(number==0){
            getmMvpView().changeLabel("Reminder Clone");
        }else {
            getmMvpView().changeLabel(String.valueOf(number)+ " Selected ");
        }
    }

    @Override
    public void OnBackPressed() {
         NumberOfSelected =0 ;
         IsSelectionTime = false ;
         List<TaskView> taskViews = adapter.getTaskViews() ;
         adapter = new TasksAdapter(getmMvpView().getContexte(), taskViews);
         adapter.setListenerToClick(this);
         getmMvpView().showTasks(adapter);
         changeLabel(0);
         Size = taskViews.size();

    }

    @Override
    public void DeleteTask() {
        getmMvpView().showConfirmationDialog();
    }

    class DeleteObserver extends DisposableCompletableObserver{
        @Override
        public void onComplete() {
            getmMvpView().Toast("deleted");
            NumberOfSelected =0 ;
            IsSelectionTime = false ;
            changeLabel(0);
            taskViewsToDelete = null ;
            getTasks();
            getmMvpView().showNotDeleteTime();
            getTasks();
//            deleteTasksUseCase.dispose();
//            getDelettasks().dispose();
        }

        @Override
        public void onError(Throwable e) {

        }
    }
    class TasksObserver extends DisposableObserver<List<Task>> {
        @Override
        public void onNext(List<Task> tasks) {
            List<TaskView>
                    tasks1 = new ArrayList<>();
            for (Task ta :
                    tasks) {
                tasks1.add(mpper.to(ta));
            }
            showTaks(tasks1);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {
              getmMvpInteractor().dispose();
        }

    }

    @Override
    public void onButtonClicked(int which) {
        switch (which){
            case 0:            List<Task> tasksView = new ArrayList<>();
                try {
                    tasksView.add(mpper.from(taskView));
                    deleteTasksUseCase.execute(new DeleteObserver(),tasksView );
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case 1:SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            SimpleDateFormat e = new SimpleDateFormat("dd/MM/yyyy");
                getmMvpView().goToUpdateTask(taskView.getTaskId(), taskView.getTaskTitle(), taskView.getTaskDescripion()
                        , dateFormat.format(taskView.getDateTime()), taskView.getRepeatType(), taskView.getRepeatBody(), taskView.getAdvancedRemind()
                ,e.format(taskView.getEndDate()), taskView.getCategorie());
               break;
            case 2: handleCompleteOrActive(taskView);
            break;
        }
        dialog.dismiss();
        dialog = null ;
    }

    @Override
    public void onSkipTurnClicked() {

    }

    @Override
    public void handleDialogClicks(boolean Ok) {
        if(Ok){
            List<Task> tasks = new ArrayList<>();
            for (TaskView ta: taskViewsToDelete
                    ) {
                try {
                    tasks.add(mpper.from(ta));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            if(NumberOfSelected==Size){
                getDelettasks().execute(new DeleteObserver(), new Object[]{null, null});
                NumberOfSelected =0 ;
                IsSelectionTime = false ;
                Size = 0;
            }else {
                deleteTasksUseCase.execute(new DeleteObserver(), tasks);
                Size = Size -NumberOfSelected ;

            }

        }
        getmMvpView().DisableConfirmationDialog();
    }
}
