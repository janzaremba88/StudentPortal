package com.example.studentportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener {


    public List<Portal> mPortals;
    private PortalAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFloatingActionButton;
    public static final String EXTRA_ADDPORTAL = "AddPortal";
    public static final String EXTRA_PORTAL = "Portal";
    public static final int REQUESTCODE = 1234;
    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.portalRecycler);
        mPortals = new ArrayList<>();
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mFloatingActionButton = findViewById(R.id.floatingActionButton);
        mRecyclerView.addOnItemTouchListener(this);
        if (mPortals.isEmpty()){
            mPortals.add(new Portal("hva", "https:/www.hva.nl"));
        }
        updateUI();

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddPortal.class);
                startActivityForResult(intent, REQUESTCODE);
            }
        });

        mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    private void updateUI() {
        if (mAdapter == null) {
            mAdapter = new PortalAdapter(mPortals);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        int mAdapterPosition = recyclerView.getChildAdapterPosition(child);
        if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
            Intent intent = new Intent(MainActivity.this, PortalActivity.class);
            intent.putExtra(EXTRA_PORTAL, mPortals.get(mAdapterPosition));
            startActivity(intent);
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUESTCODE) {
            if (resultCode == RESULT_OK) {
                Portal newPortal = data.getParcelableExtra(MainActivity.EXTRA_ADDPORTAL);
                mPortals.add(newPortal);
                updateUI();
            }
        }
    }
}
