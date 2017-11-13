package com.example.uberv.shapedrawables;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView1;
    private ImageView mImageView2;
    private ImageView mImageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView1 = findViewById(R.id.image_1);
        mImageView2 = findViewById(R.id.image_2);
        mImageView3 = findViewById(R.id.image_3);
    }

    @Override
    protected void onResume() {
        super.onResume();

        transformImages();
    }

    private void transformImages() {
        transformSecondImage();
        createThirdImage();

    }

    /**
     * Example of creating Shape Drawable using Java code
     */
    private void createThirdImage() {
        RoundRectShape roundRectShape = new RoundRectShape(new float[]{
                10, 10, 10, 10,
                10, 10, 10, 10}, null, null);
        ShapeDrawable shapeDrawable = new ShapeDrawable(roundRectShape);
        shapeDrawable.getPaint().setColor(Color.parseColor("#FFFFFF"));
        mImageView3.setBackground(shapeDrawable);
        // or you can use mImageView3.setImageDrawable(shapeDrawable);
    }

    private void transformSecondImage() {
        // We also do .mutate() to allow us update it without affecting other instances
        // Since by modifying original one, we would modify all it's occurences
        // Because android reuses drawables (e.g. by default only 1 drawable copy is in memory)
        // More info: http://www.curious-creature.com/2009/05/02/drawable-mutations/
        Drawable drawable2 = getDrawable(R.drawable.spotify_logo_blue).mutate();
        // We could also do as follows
        // since it's reused
        // Drawable drawable2 = mImageView2.getDrawable();  with .mutate() call

        // Upcast Drawable to GradientDrawable and modify it
        GradientDrawable gradient = (GradientDrawable) drawable2;

        gradient.setColor(ContextCompat.getColor(this, R.color.colorPrimary));
        gradient.setShape(GradientDrawable.OVAL);
        gradient.setStroke(12, Color.CYAN);
        mImageView2.setImageDrawable(gradient);
    }
}
