package mglowinski.library.adapters;

/**
 * Created by macglo on 25.09.17.
 */

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mglowinski.library.R;
import mglowinski.library.model.Borrow;

public class BorrowBooksAdapter extends RecyclerView.Adapter<BorrowBooksAdapter.UserViewHolder> {

    private List<Borrow> borrowList;
    String dateReturn;

    public BorrowBooksAdapter(List<Borrow> borrowList) {
        this.borrowList = borrowList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_borrow_book_item, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.textViewTitle.setText(borrowList.get(position).getBook().getBook_title());
        holder.textViewAuthor.setText(borrowList.get(position).getBook().getBook_author());
        holder.textViewDateBorrow.setText(borrowList.get(position).getDate_borrow());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date date = simpleDateFormat.parse(borrowList.get(position).getDate_borrow());
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MONTH, 3);
            date = cal.getTime();
            dateReturn = simpleDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.textViewDateReturn.setText(dateReturn);
    }

    @Override
    public int getItemCount() {
        return borrowList.size();
    }


    /**
     * ViewHolder class
     */
    public class UserViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView textViewTitle;
        AppCompatTextView textViewAuthor;
        AppCompatTextView textViewDateBorrow;
        AppCompatTextView textViewDateReturn;

        public UserViewHolder(View view) {
            super(view);
            textViewTitle = view.findViewById(R.id.textViewTitle);
            textViewAuthor = view.findViewById(R.id.textViewAuthor);
            textViewDateBorrow = view.findViewById(R.id.textViewDateBorrow);
            textViewDateReturn = view.findViewById(R.id.textViewDateReturn);
        }
    }


}