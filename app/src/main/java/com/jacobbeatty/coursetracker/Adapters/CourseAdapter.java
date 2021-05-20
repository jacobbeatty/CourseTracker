package com.jacobbeatty.coursetracker.Adapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jacobbeatty.coursetracker.Activities.CourseDetail;
import com.jacobbeatty.coursetracker.Activities.CreateCourse;
import com.jacobbeatty.coursetracker.DAO.TermDao;
import com.jacobbeatty.coursetracker.Entity.Course;
import com.jacobbeatty.coursetracker.Entity.Term;
import com.jacobbeatty.coursetracker.R;
import com.jacobbeatty.coursetracker.Entity.Course;
//import com.jacobbeatty.coursetracker.Activities.CourseDetail;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

//    List<Course> courses;
    private List<Course> courses;
    private List<Term> terms;
    int termID;



    public CourseAdapter(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public CourseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CourseAdapter.ViewHolder holder, int position) {
        if (courses != null){
            final Course current = courses.get(position);

            holder.courseName.setText(current.getCourseName());
            Log.d("onBind", "onBindViewHolder: working");
        }else {
            holder.courseName.setText("blank");
            Log.d("onBind", "onBindViewHolder: not working");


        }
//        Course course = courses.get(position);
//        holder.courseName.setText(courses.get(position).getCourseName());
//        holder.courseStart.setText((CharSequence) courses.get(position).getCourseStart());
//        holder.courseEnd.setText((CharSequence) courses.get(position).getCourseEnd());
//        holder.course = course;
//        holder.courseInstructorName.setText((CharSequence) courses.get(position).getInstructorName());
//        holder.courseInstructorPhone.setText((CharSequence) courses.get(position).getInstructorPhone());
//        holder.courseInstructorEmail.setText((CharSequence) courses.get(position).getInstructorEmail());
//        holder.courseSpinner.setText((CharSequence) courses.get(position).getCourseStatus());

    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView courseName;
        public TextView courseStart;
        public TextView courseEnd;
        public TextView courseNotes;
        public TextView courseInstructorName;
        public TextView courseInstructorPhone;
        public TextView courseInstructorEmail;
        public TextView courseSpinner;
        LinearLayout toDetail;
        int position;
        Course course;

        public ViewHolder(View itemView) {
            super(itemView);
            int position = getAdapterPosition();
//            final Course current = mCourse.get(position);

            courseName = itemView.findViewById(R.id.course_name);
//            courseStart = itemView.findViewById(R.id.course_start);
//            courseEnd = itemView.findViewById(R.id.course_end);
//            courseNotes = itemView.findViewById(R.id.course_note);
//            courseInstructorName = itemView.findViewById(R.id.course_instructor_name);
//            courseInstructorPhone = itemView.findViewById(R.id.course_instructor_phone);
//            courseInstructorEmail = itemView.findViewById(R.id.course_instructor_email);
//            courseSpinner = itemView.findViewById(R.id.course_status);




            toDetail = itemView.findViewById(R.id.row);
            toDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Log.d("detail","onClick:Clicked detail for " + position + " course " + course.getCourseName());
                    int position = getAdapterPosition();
                    final Course current = courses.get(position);
                    Intent intent = new Intent(v.getContext(), CourseDetail.class);
                    intent.putExtra("courseName", current.getCourseName());
                    intent.putExtra("courseStart", current.getCourseStart());
                    intent.putExtra("courseEnd", current.getCourseEnd());
                    intent.putExtra("courseId", current.getCourseID());
                    intent.putExtra("termID", current.getTermID());
                    intent.putExtra("courseInstructorName", current.getInstructorName());
                    intent.putExtra("courseInstructorPhone", current.getInstructorPhone());
                    intent.putExtra("courseInstructorEmail", current.getInstructorEmail());
                    intent.putExtra("courseStatus", current.getCourseStatus());
                    intent.putExtra("courseNote", current.getCourseNote());
                    intent.putExtra("position", position);
                    v.getContext().startActivity(new Intent(intent));

                }
            });
        }
    }
    public void setCourse(List<Course> courses) {
        this.courses = courses;
        notifyDataSetChanged();
    }
}


