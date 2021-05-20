package com.jacobbeatty.coursetracker.Adapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jacobbeatty.coursetracker.Activities.AssessmentDetail;
import com.jacobbeatty.coursetracker.Activities.CourseDetail;
import com.jacobbeatty.coursetracker.Entity.Assessment;
import com.jacobbeatty.coursetracker.Entity.Course;
import com.jacobbeatty.coursetracker.Entity.Term;
import com.jacobbeatty.coursetracker.R;

import java.util.List;

//import com.jacobbeatty.coursetracker.Activities.CourseDetail;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.ViewHolder> {

//    List<Course> courses;
    private List<Assessment> assessments;
    private List<Term> terms;
    int termID;



    public AssessmentAdapter(List<Assessment> assessments) {
        this.assessments = assessments;
    }

    @Override
    public AssessmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.assessment_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AssessmentAdapter.ViewHolder holder, int position) {
        if (assessments != null){
            final Assessment current = assessments.get(position);

            holder.assessmentName.setText(current.getAssessmentName());
            Log.d("onBind", "onBindViewHolder: working");
        }else {
            holder.assessmentName.setText("blank");
            Log.d("onBind", "onBindViewHolder: not working");


        }
//        Assessment assessment = assessments.get(position);
//        holder.assessmentName.setText(assessments.get(position).getAssessmentName());
//        holder.assessmentStart.setText((CharSequence) assessments.get(position).getAssessmentStart());
//        holder.assessmentEnd.setText((CharSequence) assessments.get(position).getAssessmentEnd());
//        holder.assessment = assessment;
//        holder.assessmentInstructorName.setText((CharSequence) assessments.get(position).getInstructorName());
//        holder.assessmentInstructorPhone.setText((CharSequence) assessments.get(position).getInstructorPhone());
//        holder.assessmentInstructorEmail.setText((CharSequence) assessments.get(position).getInstructorEmail());
//        holder.assessmentSpinner.setText((CharSequence) assessments.get(position).getAssessmentStatus());

    }

    @Override
    public int getItemCount() {
        return assessments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView assessmentName;
        public TextView assessmentStart;
        public TextView assessmentEnd;
        public TextView assessmentNotes;
        public TextView assessmentInstructorName;
        public TextView assessmentInstructorPhone;
        public TextView assessmentInstructorEmail;
        public TextView assessmentSpinner;
        LinearLayout toDetail;
        int position;
        Assessment assessment;

        public ViewHolder(View itemView) {
            super(itemView);
            int position = getAdapterPosition();
//            final Assessment current = mAssessment.get(position);

            assessmentName = itemView.findViewById(R.id.assessment_name);
            assessmentStart = itemView.findViewById(R.id.assessment_start);
            assessmentEnd = itemView.findViewById(R.id.assessment_end);
            assessmentSpinner = itemView.findViewById(R.id.assessment_status);




            toDetail = itemView.findViewById(R.id.row);
            toDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Log.d("detail","onClick:Clicked detail for " + position + " assessment " + assessment.getAssessmentName());
                    int position = getAdapterPosition();
                    final Assessment current = assessments.get(position);
                    Intent intent = new Intent(v.getContext(), AssessmentDetail.class);
                    intent.putExtra("assessmentName", current.getAssessmentName());
                    intent.putExtra("assessmentStart", current.getAssessmentStart());
                    intent.putExtra("assessmentEnd", current.getAssessmentEnd());
                    intent.putExtra("assessmentID", current.getAssessmentID());
                    intent.putExtra("courseID", current.getCourseID());
                    intent.putExtra("assessmentStatus", current.getAssessmentType());
                    intent.putExtra("position", position);
                    v.getContext().startActivity(new Intent(intent));

                }
            });
        }
    }
    public void setAssessment(List<Assessment> assessments) {
        this.assessments = assessments;
        notifyDataSetChanged();
    }
}


