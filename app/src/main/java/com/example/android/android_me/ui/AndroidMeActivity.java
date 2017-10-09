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

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int section = bundle.getInt(BodyPartFragment.BODY_SECTION_NUMBER);
        int partIndex = bundle.getInt(BodyPartFragment.LIST_INDEX);
        if (partIndex >11 || partIndex < 0){
            partIndex = 0;
        }

        Toast.makeText(this, "section: " + section + " index: " + partIndex, Toast.LENGTH_SHORT).show();

        // Only create new fragments when there is no previously saved state
        if(savedInstanceState == null) {

            // TODO (5) Retrieve list index values that were sent through an intent; use them to display the desired Android-Me body part image
                // Use setListindex(int index) to set the list index for all BodyPartFragments

            Toast.makeText(this, "onCreate() called. saveInstanceState = null", Toast.LENGTH_SHORT).show();

            // Create a new head BodyPartFragment
            BodyPartFragment headFragment = new BodyPartFragment();

            // Set the list of image id's for the head fragment and set the position to the second image in the list
            headFragment.setImageIds(AndroidImageAssets.getHeads());
            headFragment.setListIndex(partIndex);

            // Add the fragment to its container using a FragmentManager and a Transaction
            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .add(R.id.head_container, headFragment)
                    .commit();

            // Create and display the body and leg BodyPartFragments

            BodyPartFragment bodyFragment = new BodyPartFragment();
            bodyFragment.setImageIds(AndroidImageAssets.getBodies());
            bodyFragment.setListIndex(partIndex);
            fragmentManager.beginTransaction()
                    .add(R.id.body_container, bodyFragment)
                    .commit();

            BodyPartFragment legFragment = new BodyPartFragment();
            legFragment.setImageIds(AndroidImageAssets.getLegs());
            legFragment.setListIndex(partIndex);
            fragmentManager.beginTransaction()
                    .add(R.id.leg_container, legFragment)
                    .commit();
        }else{
            Toast.makeText(this, "onCreate() called. saveInstanceState != null", Toast.LENGTH_SHORT).show();

            BodyPartFragment bodyPartFragment = new BodyPartFragment();
            bodyPartFragment.setListIndex(partIndex);

            switch (section){
                case 0:
                    bodyPartFragment.setImageIds(AndroidImageAssets.getHeads());
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.head_container, bodyPartFragment)
                            .commit();
                    break;
                case 1:
                    bodyPartFragment.setImageIds(AndroidImageAssets.getBodies());
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.body_container, bodyPartFragment)
                            .commit();
                    break;
                case 2:
                default:
                    bodyPartFragment.setImageIds(AndroidImageAssets.getLegs());
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.leg_container, bodyPartFragment)
                            .commit();
                    break;
            }

        }



    }
}
