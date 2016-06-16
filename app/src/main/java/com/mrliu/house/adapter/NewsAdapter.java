package com.mrliu.house.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mrliu.house.R;
import com.mrliu.house.bean.NewsBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Mr.Liu on 2016/5/25.
 */
public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SPE_TYPE = 0;
    private static final int NOL_TYPE = 1;
    private static final int IMG_TYPE = 2;

    private Context context;
    private final LayoutInflater inflater;
    private List<NewsBean> mData;

    private OnItemClickListener onItemClickListener;

    public NewsAdapter(Context context, List<NewsBean> data, OnItemClickListener onItemClickListener) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mData = data;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        public void onItemClickListener(int position);
    }

    @Override
    public int getItemViewType(int position) {
        int type = mData.get(position).getType();
        Log.e("123", type+"");
        if (type == 100) {
            return SPE_TYPE;
        } else if (type == 0) {
            return NOL_TYPE;
        } else if (type == 1) {
            return IMG_TYPE;
        }


        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == SPE_TYPE) {
            return new SpeHolder(inflater.inflate(R.layout.item_special, null));
        } else if (viewType == NOL_TYPE) {
            return new NolmalHolder(inflater.inflate(R.layout.item_nolmal, null));
        } else {
            return new ImgHolder(inflater.inflate(R.layout.item_img, null));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        int itemType = getItemViewType(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClickListener(position);
            }
        });

        switch (itemType){
            case SPE_TYPE:

                SpeHolder speHolder = (SpeHolder) holder;

                Picasso.with(context).load(mData.get(position).getImg_url()).into(speHolder.img_spe);
                speHolder.txt_spe.setText(mData.get(position).getTitle());

                break;
            case NOL_TYPE:

                NolmalHolder nolmalHolder = (NolmalHolder) holder;

                nolmalHolder.con_nol.setText(mData.get(position).getSummary());
                nolmalHolder.tit_nol.setText(mData.get(position).getTitle());

                Picasso.with(context).load(mData.get(position).getImg_url()).into(nolmalHolder.img_nol);

                break;
            case IMG_TYPE:

                ImgHolder imgHolder = (ImgHolder) holder;

                imgHolder.tit_img.setText(mData.get(position).getTitle());

                Picasso.with(context).load(mData.get(position).getImg_url()).into(imgHolder.img_img)
                ;
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class SpeHolder extends RecyclerView.ViewHolder {

        private TextView txt_spe;
        private ImageView img_spe;

        public SpeHolder(View itemView) {
            super(itemView);

            txt_spe = (TextView) itemView.findViewById(R.id.txt_special);
            img_spe = (ImageView) itemView.findViewById(R.id.img_special);
        }
    }

    class NolmalHolder extends RecyclerView.ViewHolder {

        private TextView tit_nol;
        private TextView con_nol;
        private ImageView img_nol;


        public NolmalHolder(View itemView) {
            super(itemView);

            tit_nol = (TextView) itemView.findViewById(R.id.title_nolmal);
            con_nol = (TextView) itemView.findViewById(R.id.content_nolmal);
            img_nol = (ImageView) itemView.findViewById(R.id.img_nolmal);
        }
    }

    class ImgHolder extends RecyclerView.ViewHolder {

        private TextView tit_img;
        private ImageView img_img;

        public ImgHolder(View itemView) {
            super(itemView);
            tit_img = (TextView) itemView.findViewById(R.id.title_img);
            img_img = (ImageView) itemView.findViewById(R.id.img_img);

        }
    }
}
