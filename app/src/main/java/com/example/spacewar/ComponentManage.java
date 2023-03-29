package com.example.spacewar;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.Image;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Arrays;

public class ComponentManage extends GameManage{
    public enum Direction { RIGHT, LEFT };

    private Context context;
    private LinearLayout layout;
    private int drawableSrc;

    private ComponentView[] componentView;

    ComponentManage(Context _context, LinearLayout _layout, int _drawableSrc) {
        super(_context);

        this.drawableSrc = _drawableSrc;
        this.layout = _layout;
        this.context = _context;

        componentView = new ComponentView[GameFieldModel.COLUMN_SIZE];
        for(int i = 0; i < componentView.length; i++)
            componentView[i] = new ComponentView();

    }

    public void setupComponents() {
        // create component view
        for(int i = 0; i < componentView.length; i++) {
            ImageView component = setComponentView();
            layout.addView(component);
            componentView[i].view = component;
        }

        // set center position component and display it
        resetState();
        GameFieldModel.componentPosition = componentView.length / 2;
        componentView[GameFieldModel.componentPosition].state = true;
        review();
    }

    private ImageView setComponentView() {
        // create a view component
        ImageView view = new ImageView(context);
        view.setImageResource(drawableSrc);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, convertPixelsToDp(GameFieldModel.COMPONENT_SIZE));
        params.gravity = Gravity.CENTER;
        params.weight = 1;
        view.setLayoutParams(params);

        return view;
    }

    private void resetState() {
        // reset component state
        for(int i = 0; i < componentView.length; i++)
            componentView[i].state = false;
    }

    public void setupEvents(View rightBtn, View leftBtn) {
        // create onClick listener for the right button controller
        rightBtn.setOnClickListener(view -> {
            // check if the new component position is valid and update the view
            int newPos = GameFieldModel.componentPosition + 1;
            if(newPos < componentView.length) {
                GameFieldModel.componentPosition = newPos;

                resetState();
                componentView[GameFieldModel.componentPosition].state = true;
                review();
            }
        });

        // create onClick listener for the left button controller
        leftBtn.setOnClickListener(view -> {
            // check if the new component position is valid and update the view
            int newPos = GameFieldModel.componentPosition - 1;
            if(newPos >= 0) {
                GameFieldModel.componentPosition = newPos;

                resetState();
                componentView[GameFieldModel.componentPosition].state = true;
                review();
            }
        });
    }

    @Override
    protected void review() {
        for(int i = 0; i < componentView.length; i++) {
            componentView[i].view.setVisibility(componentView[i].state? View.VISIBLE : View.INVISIBLE);
        }
    }
}

class ComponentView {
    public ImageView view;
    public boolean state;
}