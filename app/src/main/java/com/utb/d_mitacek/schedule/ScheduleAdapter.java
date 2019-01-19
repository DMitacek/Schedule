package com.utb.d_mitacek.schedule;

        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import org.w3c.dom.Text;

        import java.util.ArrayList;


public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {
    private ArrayList<ScheduleItem> mExampleList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ScheduleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView name;
        public TextView city;
        public TextView date;
        public TextView time;


        public ScheduleViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.textView);
            city = itemView.findViewById(R.id.textView2);
            date = itemView.findViewById(R.id.textView3);
            time = itemView.findViewById(R.id.textView4);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });


        }
    }

    public ScheduleAdapter(ArrayList<ScheduleItem> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public ScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_item, parent, false);
        ScheduleViewHolder evh = new ScheduleViewHolder(v, mListener);
        return evh;
    }



    @Override
    public void onBindViewHolder(ScheduleViewHolder holder, int position) {
        ScheduleItem currentItem = mExampleList.get(position);

        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.name.setText(currentItem.getName());
        holder.city.setText(currentItem.getCity());
        holder.date.setText(currentItem.getDate());
        holder.time.setText(currentItem.getTime());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public ScheduleItem getItem(int position)
    {
        return mExampleList.get(position);
    }

}