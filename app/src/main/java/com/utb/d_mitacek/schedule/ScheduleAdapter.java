package com.utb.d_mitacek.schedule;

        import android.graphics.BitmapFactory;
        import android.graphics.drawable.Drawable;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import java.io.InputStream;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.util.ArrayList;

        import com.bumptech.glide.Glide;
        import com.bumptech.glide.annotation.GlideModule;
        import com.bumptech.glide.module.AppGlideModule;

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

        public TextView teplotaAktualni;
        public TextView teplotaMinimalni;
        public TextView teplotaMaximalni;


        public ScheduleViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.textView);
            city = itemView.findViewById(R.id.textView2);
            date = itemView.findViewById(R.id.textView3);
            time = itemView.findViewById(R.id.textView4);
            teplotaAktualni = itemView.findViewById(R.id.textView5TeplotaAktualniHodnota);
            teplotaMinimalni = itemView.findViewById(R.id.textView5TeplotaMinHodnota);
            teplotaMaximalni = itemView.findViewById(R.id.textView5TeplotaMaxHodnota);


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
        Glide.with(holder.mImageView).load(currentItem.getImageResource()).into(holder.mImageView);

        holder.name.setText(currentItem.getName());
        holder.city.setText(currentItem.getCity());
        holder.date.setText(currentItem.getDate());
        holder.time.setText(currentItem.getTime());
        holder.teplotaAktualni.setText(currentItem.getTeplota()[0]);
        holder.teplotaMinimalni.setText(currentItem.getTeplota()[1]);
        holder.teplotaMaximalni.setText(currentItem.getTeplota()[2]);
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public ScheduleItem getItem(int position)
    {
        return mExampleList.get(position);
    }

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }
    public boolean loadImageFromURL(String fileUrl,ImageView iv){
        try {

            URL myFileUrl = new URL (fileUrl);
            HttpURLConnection conn =
                    (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();

            InputStream is = conn.getInputStream();
            iv.setImageBitmap(BitmapFactory.decodeStream(is));

            return true;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


}