package com.example.android.android_me.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.List;

/**
 * Created by Edward on 10/9/2017.
 */

public class MasterListFragment extends Fragment {

    private List<Integer> mImageIds;
    private OnImageClickListener mCallback = null;

    public MasterListFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnImageClickListener) {
            mCallback = (OnImageClickListener) context;
        } else {
            throw new ClassCastException("OnImageClickListener must be implemented by " + context.toString());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_master_list, container, false);

        Context activity = getActivity();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.master_list_recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(new RecyclerMasterListAdapter());

        mImageIds = AndroidImageAssets.getAll();

        return view;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView partImageView;

        private ViewHolder(View itemView) {
            super(itemView);

            partImageView = (ImageView) itemView.findViewById(R.id.body_part_image_view);
        }
    }

    private class RecyclerMasterListAdapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_body_part, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {

            // Define the layout parameters
            holder.partImageView.setAdjustViewBounds(true);
            holder.partImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            holder.partImageView.setPadding(8, 8, 8, 8);

            holder.partImageView.setImageResource(mImageIds.get(position));

            holder.partImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getAdapterPosition();
                    mCallback.onImageSelected(position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mImageIds.size();
        }
    }

    public interface OnImageClickListener {
        void onImageSelected(int position);
    }
}
