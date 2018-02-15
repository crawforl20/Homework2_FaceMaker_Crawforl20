package com.example.crawf.homework2_facemaker;

import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created on 2/14/2018.
 * @author Logan Crawford
 */

public class CrawfListener implements View.OnClickListener, SeekBar.OnSeekBarChangeListener
{
    //all seekbars present
    private ArrayList<SeekBar> allSBs = new ArrayList<SeekBar>();
    private ArrayList<RadioButton> allRBs = new ArrayList<RadioButton>();
    private ArrayList<Button> allBs = new ArrayList<Button>();
    private Face fc = new Face();

    //cctr
    public CrawfListener(SeekBar initialSB)
    {
        addSB(initialSB);
    }
    //additive methods
    public void addSB(SeekBar aSB)
    {
        allSBs.add(aSB);
    }
    public void addRB(RadioButton aRB)
    {
        allRBs.add(aRB);
    }
    public void addButton(Button anotherButton)
    {
        addButton(anotherButton);
    }

    /**
     * onClick
     *
     * Used when the user clicks on the Random Face Button
     * to randomly generate colors for eyes, hair, and skin
     * @param view
     */
    @Override
    public void onClick(View view)
    {
        //when Random Face button is clicked
        for(Button b : allBs)//iterate through the buttons, We should only have one!
        {
            if(b.getId() == R.id.randomFaceButton)
            {
                //randomize all colors
                fc.colorRandomizer();
            }
            else
            {
                //Not our Button!
                return;
            }
        }


    }

    /**
     * onRadioButtonClicked
     *
     * Used to track whether the user is modifying the color of the hair, skin, or eyes
     * @param view
     */
    public void onRadioButtonClicked(View view)
    {
        //Was a radio button checked?
        boolean checked = ((RadioButton) view).isChecked();

        //check which radio button was clicked
        switch(view.getId())
        {
            case R.id.HairSelected:
                if(checked)
                    //we will be making color changes to the hair
                    fc.hair();
                break;
            case R.id.EyesSelected:
                if(checked)
                    //we will be making color changes to the eyes
                    fc.eyes();
                break;
            case R.id.SkinSelected:
                if(checked)
                    //we will be making color changes to the skin
                    fc.skin();
                break;
        }
    }

    /**
     * onProgressChanged
     *
     * Used to track change of seekbar and to update the seekbar,
     * also to set the progress of the seekbar to the given rgb value associated.(did not complete)
     * @param seekBar
     * @param progress
     * @param userChange
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean userChange)
    {
        //initialize our placeholding SeekBars
        SeekBar redSB = null;
        SeekBar greenSB = null;
        SeekBar blueSB = null;
        for(SeekBar sb : allSBs)//iterate through the Arraylist of seekBars
        {
            if (sb.getId() == R.id.Red) {
                redSB = sb; //this is our redSeekBar
            } else if (sb.getId() == R.id.Green) {
                greenSB = sb; //this is our greenSeekBar
            } else if (sb.getId() == R.id.Blue) {
                blueSB = sb; //this is our blueSeekBar
            }
            else
            {
                //Not our SeekBar!
                return;
            }
        }
        //update the red variant
        fc.redVal = redSB.getProgress();
        //update the green variant
        fc.greenVal = greenSB.getProgress();
        //update the blue variant
        fc.blueVal = blueSB.getProgress();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar)
    {
        //no code here
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar)
    {
        //no code here
    }
}
