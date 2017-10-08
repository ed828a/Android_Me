package com.example.android.android_me.ui;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.List;

/**
 * Created by Edward on 10/8/2017.
 */

public class PartsListFragment extends Fragment {

    private int[] mImageIds;
    private OnPartsListSelected mListener;

    public PartsListFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnPartsListSelected) {
            mListener = (OnPartsListSelected) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement OnPartsListSelected.");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parts_list, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        Context activity = getActivity();
        recyclerView.setLayoutManager(new GridLayoutManager(activity, 3));
        recyclerView.setAdapter(new PartsListAdatper(activity));

        return view;
    }

    class PartsListAdatper extends RecyclerView.Adapter<ViewHolder>{
        private LayoutInflater mLayoutinflater;


        public PartsListAdatper(Context context) {
            mLayoutinflater = LayoutInflater.from(context);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mLayoutinflater.inflate(R.layout.fragment_body_part, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            int imageResId = AndroidImageAssets.getAll().get(position);
            holder.mImageView.setImageResource(imageResId);
            holder.mImageView.setTag(position);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = (int) view.getTag();
                    ImageView imageView = (ImageView)view.findViewById(R.id.body_part_imageView);
                    mListener.onPartsListSelected(position, imageView);
                    Toast.makeText(getActivity(),
                            "you Clicked View position = " + position,
                            Toast.LENGTH_SHORT).show();
                }
            });

        }

        @Override
        public int getItemCount() {
            return AndroidImageAssets.getAll().size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            mImageView = (ImageView)itemView.findViewById(R.id.body_part_imageView);
        }

        private void setImageView(int imageResId){
            mImageView.setImageResource(imageResId);
        }
    }

    public interface OnPartsListSelected{
        void onPartsListSelected(int position, ImageView bodyPartView);
    }
}
