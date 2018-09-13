package com.devs.kero.team7.learningrxjava.ui.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.devs.kero.team7.learningrxjava.Models.TaskView;
import com.devs.kero.team7.learningrxjava.R;
import com.devs.kero.team7.learningrxjava.databinding.AlltasksCardviewBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TaskViewHolder> {
    private   Context context  ;
    private List<TaskView> taskViews ;
    private ListenerToClick listenerToClick ;

    public List<TaskView> getTaskViews() {
        return taskViews;
    }

    public TasksAdapter(Context context, List<TaskView> taskViews) {
        this.context = context;
        this.taskViews = taskViews ;
    }
    public void setListenerToClick(ListenerToClick listenerToClick) {
        this.listenerToClick = listenerToClick;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskViewHolder(LayoutInflater.from(context).inflate(R.layout.alltasks_cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.update(taskViews.get(position));

    }

    public interface ListenerToClick{
      void handleLongClicks(boolean isSelected, TaskView taskView);
      boolean  handleClicks(boolean isSelected, TaskView taskView);
    }

    @Override
    public int getItemCount() {
        return taskViews.size();
    }

    class TaskViewHolder extends  RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {
        AlltasksCardviewBinding binding  ;
        public boolean isSelected ;
        TaskView MyTaskView ;


        public TaskViewHolder(View itemView) {
            super(itemView);
            itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
            isSelected = false ;
            this.binding = AlltasksCardviewBinding.bind(itemView);
        }

        @Override
        public void onClick(View view) {
            if(listenerToClick.handleClicks(isSelected, MyTaskView)){
                if(isSelected){
                    binding.alltasksCardviewChecked.setVisibility(View.GONE);
                    isSelected = false ;
                }else {
                    binding.alltasksCardviewChecked.setVisibility(View.VISIBLE);
                    isSelected = true ;
                }
            }


            }

        @Override
        public boolean onLongClick(View view) {
            listenerToClick.handleLongClicks(isSelected, MyTaskView);
            if(isSelected){
                binding.alltasksCardviewChecked.setVisibility(View.GONE);
                isSelected = false ;
            }else {
                binding.alltasksCardviewChecked.setVisibility(View.VISIBLE);
                isSelected = true ;
            }
            return true;
        }

        public void update(TaskView taskView){
            switch (taskView.getCategorie()){
                case Blue: binding.alltasksCardviewCategorie.setImageResource(R.drawable.ic_star_blue_24dp);
                     break;
                case Yellow: binding.alltasksCardviewCategorie.setImageResource(R.drawable.ic_star_yellow_24dp);
                break;
                case Green: binding.alltasksCardviewCategorie.setImageResource(R.drawable.ic_star_green_24dp);
                break;
                case Red: binding.alltasksCardviewCategorie.setImageResource(R.drawable.ic_star_red_24dp);
                break;
                case Default:binding.alltasksCardviewCategorie.setVisibility(View.INVISIBLE);
                break;
                default:binding.alltasksCardviewCategorie.setVisibility(View.INVISIBLE);
                break;
            }
             binding.alltasksCardviewChecked.setVisibility(View.GONE);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm");
            binding.alltasksCardviewReminderdate.setText(dateFormat.format(taskView.getDateTime()));
            binding.alltasksCardviewRemindertitle.setText(taskView.getTaskTitle());
            if(taskView.getRepeatBody()==null){
                binding.alltasksCardviewRepeat.setText("One Time");
            }else if(taskView.getRepeatType().equals("Simple") && taskView.getRepeatBody().equals("Every Week")) {
//                binding.alltasksCardviewRepeat.setText("Every"+taskView.getDateTime().toString());
                //every vendredi
                binding.alltasksCardviewRepeat.setText(taskView.getRepeatBody());
            }else{
                binding.alltasksCardviewRepeat.setText(taskView.getRepeatBody());
            }
            binding.alltasksCardviewRemindertime.setText(timeformat.format(taskView.getDateTime()));
           this.MyTaskView = taskView;

        }
    }

}
