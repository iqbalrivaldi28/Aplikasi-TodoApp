package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID
import com.google.android.material.textfield.TextInputEditText

class DetailTaskActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //TODO 11 : Show detail task and implement delete action
        val inputTitle = findViewById<TextInputEditText>(R.id.detail_ed_title)
        val inputDesc = findViewById<TextInputEditText>(R.id.detail_ed_description)
        val inputDueDate = findViewById<TextInputEditText>(R.id.detail_ed_due_date)
        val deleteTask = findViewById<Button>(R.id.btn_delete_task)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory).get(DetailTaskViewModel::class.java)

        val taskId = intent.getIntExtra(TASK_ID, 0)
        viewModel.setTaskId(taskId)

        viewModel.task.observe(this@DetailTaskActivity) {
            if (it != null)
            {
                inputTitle.text = Editable.Factory.getInstance().newEditable(it.title)
                inputDesc.text = Editable.Factory.getInstance().newEditable(it.description)

                inputDueDate.text = Editable.Factory
                    .getInstance()
                    .newEditable(DateConverter.convertMillisToString(it.dueDateMillis))
            }
        }

        deleteTask.setOnClickListener {
            viewModel.deleteTask()
            Toast.makeText(this, "Berhasil di hapus", Toast.LENGTH_SHORT).show()
            finish()
        }

    }
}