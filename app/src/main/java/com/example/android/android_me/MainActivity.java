package com.example.android.android_me;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.android.android_me.data.AndroidImageAssets;
import com.example.android.android_me.ui.AndroidMeActivity;
import com.example.android.android_me.ui.BodyPartFragment;
import com.example.android.android_me.ui.MasterListFragment;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    private boolean mTwoPanel = false;
    private int mIndex = 0;


    Intent nextPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button nextButton;
        if (findViewById(R.id.two_panel_layout) == null) {
            mTwoPanel = false;

            nextPage = new Intent(this, AndroidMeActivity.class);

            nextButton = (Button) findViewById(R.id.next_button);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(nextPage);
                }
            });

        } else {
            mTwoPanel = true;
            nextButton = (Button) findViewById(R.id.next_button);
            nextButton.setVisibility(View.GONE);

            if (savedInstanceState == null) {

                // Create a new head BodyPartFragment
                BodyPartFragment headFragment = new BodyPartFragment();

                // Set the list of image id's for the head fragment and set the position to the second image in the list
                headFragment.setImageIds(AndroidImageAssets.getHeads());
                headFragment.setListIndex(mIndex);

                // Add the fragment to its container using a FragmentManager and a Transaction
                FragmentManager fragmentManager = getSupportFragmentManager();

                fragmentManager.beginTransaction()
                        .add(R.id.head_container, headFragment)
                        .commit();

                // Create and display the body and leg BodyPartFragments

                BodyPartFragment bodyFragment = new BodyPartFragment();
                bodyFragment.setImageIds(AndroidImageAssets.getBodies());
                bodyFragment.setListIndex(mIndex);
                fragmentManager.beginTransaction()
                        .add(R.id.body_container, bodyFragment)
                        .commit();

                BodyPartFragment legFragment = new BodyPartFragment();
                legFragment.setImageIds(AndroidImageAssets.getLegs());
                legFragment.setListIndex(mIndex);
                fragmentManager.beginTransaction()
                        .add(R.id.leg_container, legFragment)
                        .commit();
            } else {
                mIndex = savedInstanceState.getInt(AndroidMeActivity.INDEX);

                // Create a new head BodyPartFragment
                BodyPartFragment headFragment = new BodyPartFragment();

                // Set the list of image id's for the head fragment and set the position to the second image in the list
                headFragment.setImageIds(AndroidImageAssets.getHeads());
                headFragment.setListIndex(mIndex);

                // Add the fragment to its container using a FragmentManager and a Transaction
                FragmentManager fragmentManager = getSupportFragmentManager();

                fragmentManager.beginTransaction()
                        .add(R.id.head_container, headFragment)
                        .commit();

                // Create and display the body and leg BodyPartFragments

                BodyPartFragment bodyFragment = new BodyPartFragment();
                bodyFragment.setImageIds(AndroidImageAssets.getBodies());
                bodyFragment.setListIndex(mIndex);
                fragmentManager.beginTransaction()
                        .add(R.id.body_container, bodyFragment)
                        .commit();

                BodyPartFragment legFragment = new BodyPartFragment();
                legFragment.setImageIds(AndroidImageAssets.getLegs());
                legFragment.setListIndex(mIndex);
                fragmentManager.beginTransaction()
                        .add(R.id.leg_container, legFragment)
                        .commit();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(AndroidMeActivity.INDEX, mIndex);

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onImageSelected(int position) {

        int section = 0;
        if (position > 23) {
            mIndex = position - 24;
            section = 2;
        } else if (position > 11) {
            mIndex = position - 12;
            section = 1;
        } else if (position >= 0) {
            mIndex = position;
            section = 0;
        }

        if (mTwoPanel) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            BodyPartFragment newFragment = new BodyPartFragment();
            switch (section) {
                case 2:
                    newFragment.setImageIds(AndroidImageAssets.getLegs());
                    newFragment.setListIndex(mIndex);
                    fragmentManager.beginTransaction()
                            .replace(R.id.leg_container, newFragment)
                            .commit();
                    break;
                case 1:

                    newFragment.setImageIds(AndroidImageAssets.getBodies());
                    newFragment.setListIndex(mIndex);
                    fragmentManager.beginTransaction()
                            .replace(R.id.body_container, newFragment)
                            .commit();
                    break;
                case 0:

                    newFragment.setImageIds(AndroidImageAssets.getHeads());
                    newFragment.setListIndex(mIndex);
                    fragmentManager.beginTransaction()
                            .replace(R.id.head_container, newFragment)
                            .commit();
            }

        } else {
            Bundle bundle = new Bundle();
            bundle.putInt(AndroidMeActivity.POSITION, position);

            nextPage.putExtras(bundle);
        }
    }
}
