/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.android_me.ui;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity
        implements PartsListFragment.OnPartsListSelected{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        BodyPartFragment headFragment = new BodyPartFragment();
        headFragment.setmImageIndex(1);
        BodyPartFragment bodyFragment = new BodyPartFragment();
        bodyFragment.setmImageIndex(2);
        BodyPartFragment legFragment = new BodyPartFragment();
        legFragment.setmImageIndex(1);

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.head_container, headFragment, "head_fragment");
//        fragmentTransaction.add(R.id.body_container, bodyFragment, "body_fragment");
//        fragmentTransaction.add(R.id.leg_container, legFragment, "leg_fragment");
//        fragmentTransaction.commit();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.head_container, headFragment, "head_fragment")
                .add(R.id.body_container, bodyFragment, "body_fragment")
                .add(R.id.leg_container, legFragment, "leg_fragment")
                .commit();
    }

    @Override
    public void onPartsListSelected(int position, ImageView bodyPartView) {
        if (position > 23){
            FrameLayout legLayout = (FrameLayout) findViewById(R.id.leg_container);
            ImageView legImageView = (ImageView)legLayout.findViewById(R.id.body_part_imageView);
            legImageView.setImageResource(AndroidImageAssets.getAll().get(position));
        } else if (position > 11){
            FrameLayout bodyLayout = (FrameLayout) findViewById(R.id.body_container);
            ImageView bodyImageView = (ImageView)bodyLayout.findViewById(R.id.body_part_imageView);
            bodyImageView.setImageResource(AndroidImageAssets.getAll().get(position));
        } else if (position >= 0){
            FrameLayout headLayout = (FrameLayout) findViewById(R.id.head_container);
            ImageView headImageView = (ImageView)headLayout.findViewById(R.id.body_part_imageView);
            headImageView.setImageResource(AndroidImageAssets.getAll().get(position));
        }
    }
}
