package com.omninos.gwappuser.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.omninos.gwappuser.R;

public class AvailableAdapter extends RecyclerView.Adapter<AvailableAdapter.MyViewHolder> {

    Context context;
    SelectBook selectBook;

    public interface SelectBook{
        public void Select(int position);
        public void BookLater(int position);
    }

    public AvailableAdapter(Context context, SelectBook selectBook) {
        this.context = context;
        this.selectBook = selectBook;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_available_services_layout,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Button book_button,bookLater;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_button=itemView.findViewById(R.id.book_button);
            bookLater=itemView.findViewById(R.id.bookLater);
            bookLater.setOnClickListener(this);
            book_button.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.book_button:
                    selectBook.Select(getLayoutPosition());
                    break;

                case R.id.bookLater:
                    selectBook.BookLater(getLayoutPosition());
                    break;

            }
        }
    }
}
