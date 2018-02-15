package com.example.crawf.homework2_facemaker;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;

import java.util.Random;

/**
 * Created on 2/12/2018.
 * @author Logan Crawford
 */

public class Face extends AppCompatActivity
{
    private int skinColor; //required
    private int eyeColor; //req
    private int hairColor; //req
    private int hairStyle; //I did not incorporate the spinner or different hair styles, Had complications.
    protected int redVal; //integer value used for storing the red seekbar prog
    protected int greenVal; //integer value used for storing the green seekbar prog
    protected int blueVal; //integer value used for storing the blue seekbar prog
    private Paint skinPaint; //paint used for the skin
    private Paint eyePaint; //paint used for the iris of the eyes
    private Paint hairPaint; //paint used for the single hair afro style
    private Paint Whites = new Paint(Color.WHITE); //color the whites of the eye white.
    private Paint pupil = new Paint(Color.BLACK); //color the pupils black.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //retrieval of all views
        SeekBar redSeekBar = findViewById(R.id.Red);
        SeekBar greenSeekBar = findViewById(R.id.Green);
        SeekBar blueSeekBar = findViewById(R.id.Blue);
        RadioButton hairRB = findViewById(R.id.HairSelected);
        RadioButton eyeRB = findViewById(R.id.EyesSelected);
        RadioButton skinRB = findViewById(R.id.SkinSelected);

        CrawfListener myCrawfListener = new CrawfListener(redSeekBar);
        myCrawfListener.addSB(greenSeekBar);
        myCrawfListener.addSB(blueSeekBar);
        myCrawfListener.addRB(hairRB);
        myCrawfListener.addRB(eyeRB);
        myCrawfListener.addRB(skinRB);
        //register listener with the random face button
        Button randomFace = findViewById(R.id.randomFaceButton);
        randomFace.setOnClickListener(myCrawfListener);
        //register listener with the radio buttons
        hairRB.setOnClickListener(myCrawfListener);
        eyeRB.setOnClickListener(myCrawfListener);
        skinRB.setOnClickListener(myCrawfListener);
        //register listener with the seekBars
        redSeekBar.setOnSeekBarChangeListener(myCrawfListener);
        greenSeekBar.setOnSeekBarChangeListener(myCrawfListener);
        blueSeekBar.setOnSeekBarChangeListener(myCrawfListener);
        //initialize the paint objects
        hairPaint = new Paint();
        eyePaint = new Paint();
        skinPaint = new Paint();
    }

    public int randomize()
    {
        int rgb; //create a new int rgb

        Random rand = new Random(); //create a random object named rand
        rgb = rand.nextInt(255); //set rgb to some random integer val between 0-255

        return rgb; //return rgb
    }
    //assigns color values in relation to seekBar progress
    public void skin()
    {
        //Relate the seekbars values to the new skin color
        skinColor = Color.rgb(redVal, greenVal, blueVal);
    }
    public void eyes()
    {
        //Relate the seekbars vals to the new eye color
        eyeColor = Color.rgb(redVal, greenVal, blueVal);
    }
    public void hair()
    {
        //Relate the seekbars vals to the new hair color
        hairColor = Color.rgb(redVal, greenVal, blueVal);
    }

    //randomly generate a color for the skin, eye, and hair
    public void colorRandomizer()
    {
        //Used: https://stackoverflow.com/questions/17750506/define-a-custom-color-variable
        //to understand the relation of an integer to a color
        skinColor = Color.rgb(randomize(), randomize(), randomize());
        eyeColor = Color.rgb(randomize(), randomize(), randomize());
        hairColor = Color.rgb(randomize(), randomize(), randomize());
    }

    public void onDraw(Canvas canvas)
    {
        //Used: https://developer.android.com/reference/android/graphics/Paint.html
        //to understand how an integer storing a color got converted/used-in into a paint object
        hairPaint.setColor(hairColor);
        eyePaint.setColor(eyeColor);
        skinPaint.setColor(skinColor);

        //draw hair
        canvas.drawCircle(175.0f, 175.0f, 50.0f, hairPaint);
        //draw face
        canvas.drawCircle(250.0f, 250.0f, 100.0f, skinPaint);
        //draw left eye from outside in, include whites, iris, and pupil
        canvas.drawCircle(200.0f, 200.0f, 20.0f, Whites);
        canvas.drawCircle(200.0f, 200.0f, 10.0f, eyePaint);
        canvas.drawCircle(200.0f, 200.0f, 3.0f, pupil);
        //draw right eye from outside in, include whites, iris, and pupil
        canvas.drawCircle(300.0f, 200.0f, 20.0f, Whites);
        canvas.drawCircle(300.0f, 200.0f, 10.0f, eyePaint);
        canvas.drawCircle(300.0f, 200.0f, 3.0f, pupil);

    }

}
