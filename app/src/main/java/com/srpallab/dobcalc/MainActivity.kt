package com.srpallab.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var textViewSelectedDate : TextView? = null
    private var textViewAgeInMinutes : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSelectDate : Button = findViewById(R.id.btnSelectDate)
        textViewSelectedDate = findViewById(R.id.textViewSelectedDate)
        textViewAgeInMinutes = findViewById(R.id.tvAgeInMinutes)
        btnSelectDate.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(
            this,
            {
                view, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedDateText = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                textViewSelectedDate?.text = selectedDateText
                val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = simpleDateFormat.parse(selectedDateText)
                theDate?.let {
                    val selectedDateInMinute = theDate.time / 60000
                    val currentDateInMillis = simpleDateFormat.parse(
                        simpleDateFormat.format(System.currentTimeMillis())
                    )
                    currentDateInMillis?.let {
                        val currentTimeInMinutes = currentDateInMillis.time  / 60000
                        val differenceInMinutes = currentTimeInMinutes - selectedDateInMinute
                        textViewAgeInMinutes?.text = differenceInMinutes.toString()
                    }
                }


            },
            year,
            month,
            day
        )
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis() - 86400000
        datePickerDialog.show()
    }
}