package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.List;

/**
 * Created by Edward on 10/8/2017.
 */

public class BodyPartFragment extends Fragment {
    private List<Integer> mImageIds;
    private int mImageIndex;

    public void setmImageIds(List<Integer> mImageIds) {
        this.mImageIds = mImageIds;
    }

    public void setmImageIndex(int mImageIndex) {
        this.mImageIndex = mImageIndex;
    }

    public BodyPartFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_body_part, container, false);
        ImageView mImageView = (ImageView) view.findViewById(R.id.body_part_imageView);

        mImageIds = null;

        int containerId = container.getId();
        switch (containerId) {
            case R.id.head_container:
                mImageIds = AndroidImageAssets.getHeads();
                break;
            case R.id.body_container:
                mImageIds = AndroidImageAssets.getBodies();
                break;
            case R.id.leg_container:
                mImageIds = AndroidImageAssets.getLegs();
                break;
            default:
                throw new IllegalArgumentException("The container is in a wrong type.");
        }

        if (mImageIds != null) {
            mImageView.setImageResource(mImageIds.get(mImageIndex));
        }

        return view;
    }
}

