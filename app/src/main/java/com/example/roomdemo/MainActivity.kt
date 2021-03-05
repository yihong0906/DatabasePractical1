package com.example.roomdemo

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.roomdemo.data.Student
import com.example.roomdemo.data.StudentDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAdd:Button=findViewById(R.id.add_button)

        btnAdd.setOnClickListener(){
            val newRec: Student = Student(0,"John","RIT")

            CoroutineScope(IO).launch {
                val dao = StudentDB.getDatabase(application).studentDao()
                dao.addStudent(newRec)
            }
        }
        val btnGet:Button=findViewById(R.id.getBtn)
        btnGet.setOnClickListener()
        {
            CoroutineScope(Main).launch {
                var name:String=""
                val studentDAO =StudentDB.getDatabase(application).studentDao()
                val studentList :Array<Student> = studentDAO.getStudent()

                if(studentList!=null)
                {
                    for (student:Student in studentList)
                    {
                        name+=student.name + "\n"
                    }
                }
                val textView:TextView=findViewById(R.id.textView)
                textView.setText(name)

            }
        }
    }
}