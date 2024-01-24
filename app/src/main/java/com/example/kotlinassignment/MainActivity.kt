package com.example.kotlinassignment

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.FrameLayout
import android.widget.SeekBar
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.text.TextWatcher
import android.text.Editable
import android.view.View
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    private lateinit var redSeekBar: SeekBar
    private lateinit var greenSeekBar: SeekBar
    private lateinit var blueSeekBar: SeekBar
    private lateinit var resetBtn: Button
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var redSwitch: Switch
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var greenSwitch: Switch
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var blueSwitch: Switch
    private lateinit var textView: TextView
    private lateinit var redBox: EditText
    private lateinit var greenBox: EditText
    private lateinit var blueBox: EditText
    private var redSaveState = 0
    private var greenSaveState = 0
    private var blueSaveState = 0
    private var redHex = 0
    private var greenHex = 0
    private var blueHex = 0
    private var hexColor = "#FFFFFF"
    private lateinit var frameLayout: FrameLayout
    private lateinit var currentFocusedElement : View
    private lateinit var rootView : ConstraintLayout
    @SuppressLint("MissingInflatedId", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        redSeekBar = findViewById(R.id.seek_red)
        greenSeekBar = findViewById(R.id.seek_green)
        blueSeekBar = findViewById(R.id.seek_blue)
        redSeekBar.min = 0
        redSeekBar.max = 255
        greenSeekBar.min = 0
        greenSeekBar.max = 255
        blueSeekBar.min = 0
        blueSeekBar.max = 255


        redSwitch = findViewById(R.id.switch_red)
        greenSwitch = findViewById(R.id.switch_green)
        blueSwitch = findViewById(R.id.switch_blue)

        redBox = findViewById(R.id.redValBox)
        greenBox = findViewById(R.id.greenValBox)
        blueBox = findViewById(R.id.blueValBox)
        redBox.setText("0.00")
        greenBox.setText("0.00")
        blueBox.setText("0.00")
        textView = findViewById(R.id.color_palete)
        resetBtn = findViewById(R.id.reset_btn)
        frameLayout = findViewById(R.id.frameLayId)
        rootView = findViewById(R.id.rootView)

//        rootView.setOnTouchListener { v, event ->
//            if (event.action == MotionEvent.ACTION_DOWN) {
//                if (currentFocusedElement.isFocused) {
//                    currentFocusedElement.clearFocus()
//                }
//            }
//            false
//        }


        fun rgbToHex(red: Int, green: Int, blue: Int): String {
            // Ensure that the RGB values are within the valid range (0-255)
            val validRed = red.coerceIn(0, 255)
            val validGreen = green.coerceIn(0, 255)
            val validBlue = blue.coerceIn(0, 255)

            // Convert each component to a two-digit hexadecimal representation
            val redHex = validRed.toString(16).padStart(2, '0')
            val greenHex = validGreen.toString(16).padStart(2, '0')
            val blueHex = validBlue.toString(16).padStart(2, '0')

            // Concatenate the components and return the hex color code
            return "#$redHex$greenHex$blueHex"
        }

        resetBtn.setOnClickListener{
            redSeekBar.progress = 0
            greenSeekBar.progress = 0
            blueSeekBar.progress = 0
            Log.d("RedSeeker ProgressBar", redSeekBar.progress.toString())
        }

        redSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // Update the progress value in the TextView
//                progressTextView.text = "Progress: $progress%"
                Log.d("ProgressBar",progress.toString())
                Log.d("redSwitch.isChecked",redSwitch.isChecked.toString())

                redHex = progress.toString().toInt()
                Log.d("redHex",redHex.toString())
                redBox.setText(String.format("%.2f",(redHex.toFloat()/255)))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Called when the user starts touching the SeekBar
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Called when the user stops touching the SeekBar
            }
        })
        greenSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // Update the progress value in the TextView
//                progressTextView.text = "Progress: $progress%"
                Log.d("ProgressBar",progress.toString())
                Log.d("greenSwitch.isChecked",greenSwitch.isChecked.toString())
                greenHex = if(!greenSwitch.isChecked){
                    0
                } else{
                    progress.toString().toInt()
                }

                Log.d("greenHex",greenHex.toString())
//
                greenBox.setText(String.format("%.2f",(greenHex.toFloat()/255)))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Called when the user starts touching the SeekBar
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Called when the user stops touching the SeekBar
            }
        })
        blueSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // Update the progress value in the TextView
//                progressTextView.text = "Progress: $progress%"
                Log.d("ProgressBar",progress.toString())
                Log.d("blueSwitch.isChecked",blueSwitch.isChecked.toString())
                blueHex = if(!blueSwitch.isChecked){
                    0
                } else{
                    progress.toString().toInt()
                }
                Log.d("blueHex",(blueHex/255).toFloat().toString())
                blueBox.setText(String.format("%.2f",(blueHex.toFloat()/255)))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Called when the user starts touching the SeekBar
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Called when the user stops touching the SeekBar
            }
        })

        redSwitch.setOnClickListener{
            if (!redSwitch.isChecked) {
//                redHex = 0
//                hexColor = rgbToHex(red = 0, green = greenHex, blue = blueHex)
                Log.d("hexColor",hexColor)
                redSaveState = redSeekBar.progress
                redSeekBar.progress = 0
                redSeekBar.isEnabled = false
                redBox.isEnabled = false
//                textView.setBackgroundColor(Color.parseColor("$hexColor"))
            }
            else{
                redSeekBar.isEnabled = true
                redBox.isEnabled = true
                redSeekBar.progress = redSaveState
//                hexColor = rgbToHex(red = redHex, green = greenHex, blue = blueHex)
            }
        }
        greenSwitch.setOnClickListener{
            if (!greenSwitch.isChecked) {
                Log.d("hexColor",hexColor)
                greenSaveState = greenSeekBar.progress
                greenSeekBar.progress = 0
                greenSeekBar.isEnabled = false
                greenBox.isEnabled = false
//                textView.setBackgroundColor(Color.parseColor("$hexColor"))
            }
            else{
                greenSeekBar.isEnabled = true
                greenBox.isEnabled = true
                greenSeekBar.progress = greenSaveState
            }
        }
        blueSwitch.setOnClickListener{
            if (!blueSwitch.isChecked) {
                Log.d("hexColor",hexColor)
                blueSaveState = blueSeekBar.progress
                blueSeekBar.progress = 0
                blueSeekBar.isEnabled = false
                blueBox.isEnabled = false
                textView.setBackgroundColor(Color.parseColor("$hexColor"))
            }
            else{
                blueSeekBar.isEnabled = true
                blueBox.isEnabled = true
                blueSeekBar.progress = blueSaveState
            }
        }

        redBox.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                redSeekBar.progress = redHex.toInt()
                currentFocusedElement = redBox
            }
        }
        greenBox.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                greenSeekBar.progress = greenHex.toInt()
            }
        }
        blueBox.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                blueSeekBar.progress = blueHex.toInt()
            }
        }

        redBox.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called before the text is changed.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // This method is called when the text is being changed.
            }

            override fun afterTextChanged(s: Editable?) {
                Log.i("S val==",s.toString())
                redHex = if(s.toString() == ""){
                    0
                } else if(s.toString().toFloat()>1){
                    1
                } else {
                    (s.toString().toFloat() * 255).toInt()
                }
                Log.i("S redHex==",redHex.toString())

                hexColor = rgbToHex(red = redHex, green = greenHex, blue = blueHex)
                Log.d("hexColor",hexColor)

                textView.setBackgroundColor(Color.parseColor("$hexColor"))

            }
        })
        greenBox.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called before the text is changed.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // This method is called when the text is being changed.
            }

            override fun afterTextChanged(s: Editable?) {
                Log.i("S val==",s.toString())
                greenHex = if(s.toString() == ""){
                    0
                } else if(s.toString().toFloat()>1){
                    1
                } else{
                    (s.toString().toFloat()*255).toInt()
                }
//                greenHex = (s.toString().toFloat()*255).toInt()
                hexColor = rgbToHex(red = redHex, green = greenHex, blue = blueHex)
                Log.d("hexColor",hexColor)

                textView.setBackgroundColor(Color.parseColor("$hexColor"))

            }
        })
        blueBox.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called before the text is changed.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // This method is called when the text is being changed.
            }

            override fun afterTextChanged(s: Editable?) {
                Log.i("S val==",s.toString())
                blueHex = if(s.toString() == ""){
                    0
                } else if(s.toString().toFloat()>1){
                    1
                } else{
                    (s.toString().toFloat()*255).toInt()
                }
//                greenHex = (s.toString().toFloat()*255).toInt()
                hexColor = rgbToHex(red = redHex, green = greenHex, blue = blueHex)
                Log.d("hexColor",hexColor)

                textView.setBackgroundColor(Color.parseColor("$hexColor"))

            }
        })
    }


}