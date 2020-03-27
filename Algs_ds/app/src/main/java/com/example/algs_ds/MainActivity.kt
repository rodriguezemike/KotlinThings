package com.example.algs_ds
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.*
import com.google.android.material.tabs.TabLayout
import kotlin.random.*


class MainActivity : AppCompatActivity() {
    private val  testList = List<Int>(100) {Random.nextInt(0,100)}

    fun setText(message:String): Unit{
        var textView = findViewById<TextView>(R.id.textField)
        textView.setText(message)
    }
    fun getCleanToken(token:String) : String{
        return token.toLowerCase().substringBefore(" ")
    }
    fun getSortMethod() : (List<Int>) -> List<Int>{
        var item = getSortString()
        when(item) {
            "bubble" -> return ::bubbleSort
            "selection" -> return ::selectionSort
            "insertion" -> return ::insertionSort
            "merge" -> return ::mergeSort
            else -> return ::mergeSort
        }
    }
    fun getSortString(): String{
        var spinner = findViewById<Spinner>(R.id.sortsList)
        var item = getCleanToken(spinner.selectedItem.toString())
        return item
    }
    fun getSortLink(sortString: String, sortLinks:List<String>): String{
        for (sortLink in sortLinks){
            if(sortLink.contains(sortString, ignoreCase = true)){
                return sortLink
            }
        }
        return sortLinks[sortLinks.size-1]
    }
    fun sortClick(view: View?): Unit{
        val func = getSortMethod()
        var sortedList = func(this.testList)
        val message = "${func.toString()} -> ${sortedList.toString()}"
        this.setText(message)
    }
    fun showLink(view: View?): Unit{
        //build intent and start activity
        var sortString = getSortString()
        var sortLinks = resources.getStringArray(R.array.sorts_wikis).toList()
        var link = getSortLink(sortString, sortLinks)
        var webIntent = Intent(Intent.ACTION_VIEW)
        webIntent.setData(
            Uri.parse(link))
        startActivity(webIntent)
    }
    fun resetClick(view: View?): Unit{
        if (view == null){
            return
        }
        this.setText(testList.toString())
    }
    fun sortTouchHandler(v:View?, m:MotionEvent): Boolean{
        var button = v as Button
        var touched:Boolean = false
        if(m.buttonState == MotionEvent.ACTION_DOWN){
            if (button.isActivated()){
                touched = true
                button.isActivated = false
            }
            else{
                sortClick(v)
                button.isActivated = true
                touched = true
            }
        }else if(m.buttonState == MotionEvent.ACTION_UP){
            touched = true
            Toast.makeText(v.context,"Toasty up",Toast.LENGTH_LONG)
        }
        else if(m.buttonState == MotionEvent.ACTION_MOVE){
            button.isActivated = false
            button.setBackgroundColor(Color.parseColor("#FF0AG20"))
            touched = true
        }
        return touched
    }

    fun setupSortButton(){
        var button = findViewById<Button>(R.id.sortButton)
        //button.setOnClickListener(::sortClick)
        button.setOnTouchListener(::sortTouchHandler)
    }

    fun setupSortSpinner(){
        var spinner = findViewById<Spinner>(R.id.sortsList)
        val adapter = ArrayAdapter.createFromResource(this, R.array.sorts,
            android.R.layout.simple_spinner_dropdown_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    fun setupResultsTextField(){
        var results = findViewById<TextView>(R.id.textField)
        results.setText(this.testList.toString())
    }
    /**
    fun populateTabs(){
        //var tabView = findViewById<TabLayout>(R.id.implementations)
        for (i in 1..10){
            tabView.addTab(tabView.newTab().setText("" +i))
        }
        //var adapter = PlansPagerAdapter(supportFragmentManager, tabView.tabCount)
    }
    */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setupSortButton()
        this.setupSortSpinner()
        this.setupResultsTextField()
    }
}