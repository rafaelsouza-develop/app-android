package br.com.app4pets.app.util.extensions

import android.app.DatePickerDialog
import android.content.Context
import android.widget.EditText
import java.lang.String.valueOf
import java.util.*


fun EditText.showDatePicker(context: Context) {



    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)

    val dpd = DatePickerDialog(
        context,
        DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->


            var dayString: String = if(dayOfMonth<10){
                "0$dayOfMonth"
            }else{
                dayOfMonth.toString()
            }

            var mounthString: String = if(monthOfYear<10){
                "0${monthOfYear+1}"
            }else{
                (monthOfYear+1).toString()
            }


            val data = dayString + "/" + mounthString + "/" + valueOf(year)
            this.setText(data)
        },
        year,
        month,
        day
    )
    dpd.show()


}

