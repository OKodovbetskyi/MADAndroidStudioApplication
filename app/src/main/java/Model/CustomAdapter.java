package Model;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.coursworkmadandroidstudio.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class CustomAdapter extends
        RecyclerView.Adapter<CustomAdapter.ViewHolder> implements View.OnClickListener{
    // ... view holder defined above...
    // Store a member variable for the contacts
    private List<BookDiary> books;
    private SelectListener listener;

    // Pass in the contact array into the constructor
    public CustomAdapter(List<BookDiary> listofBooks, SelectListener listener) {
        books = listofBooks;
        this.listener = listener;
    }
    @Override
    public void onClick(View view) {

    }


    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public TextView dateTextView;
        public FloatingActionButton messageButton;
        public LinearLayout cardView;
        public Intent viewDiaryBook;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            cardView = itemView.findViewById(R.id.main_container);
            nameTextView = (TextView) itemView.findViewById(R.id.contact_name);
            dateTextView = (TextView) itemView.findViewById(R.id.dateReadDisplay);
            messageButton = (FloatingActionButton) itemView.findViewById(R.id.message_button);


        }

    }
    // ... constructor and member variables

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_book_card, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // Get the data model based on position
        BookDiary contact = books.get(position);
        TextView  textView = holder.nameTextView;
            // Set item views based on your views and data model
            TextView date = holder.dateTextView;
            textView.setText(contact.getTitleName());
            date.setText(contact.getDateRead());
            FloatingActionButton button = holder.messageButton;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        listener.onRemoveClicked(contact);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    notifyItemRemoved(position);
                }
            });
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(books.get(position));
                }
            });


    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return books.size();
    }
}