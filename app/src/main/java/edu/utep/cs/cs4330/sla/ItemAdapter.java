package edu.utep.cs.cs4330.sla;
import android.content.Context;
//import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.List;

public class ItemAdapter extends ArrayAdapter<Course> {
    private static final String TAG = ItemAdapter.class.getSimpleName();

    //private List<Person> personList;
    private List<Course> courseList;
    protected TextView nameDisplay;
    protected TextView instructorDisplay;
    protected TextView periodDisplay;
    protected Button alert;


    ItemAdapter(Context context, int resource, List<Course> objects) {
        super(context, resource, objects);
        //       super(context, resource, objects);
        courseList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        //Person currentPerson = personList.get(position);
        Course currentItem = courseList.get(position);

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.customlayout, parent, false);
        }
//        ImageView imageView =listItemView.findViewById(R.id.itemImage);
        nameDisplay = listItemView.findViewById(R.id.nameDisplay);
        instructorDisplay = listItemView.findViewById(R.id.instructorDisplay);
        periodDisplay = listItemView.findViewById(R.id.periodDisplay);
       // newPrice = listItemView.findViewById(R.id.newPriceDisplay);
//        imageView.setImageResource(R.drawable.iphone);

        nameDisplay.setText(courseList.get(position).getCourseName());
        instructorDisplay.setText(courseList.get(position).getInstructor().getName());
        periodDisplay.setText(courseList.get(position).getPeriod());
        //newPrice.setText("New Price: $" + itemList.get(position).getNewPrice());

//        TextView nameTextView = (TextView) listItemView.findViewById(R.id.name_text_view);
//        nameTextView.setText(currentItem.getName());
//
//        TextView ageTextView = (TextView) listItemView.findViewById(R.id.age_text_view);
//        ageTextView.setText(current.getAge());


        return listItemView;
    }
}
