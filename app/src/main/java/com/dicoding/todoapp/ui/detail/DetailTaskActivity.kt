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
        val titleInput = findViewById<TextInputEditText>(R.id.detail_ed_title)
        val descriptionInput = findViewById<TextInputEditText>(R.id.detail_ed_description)
        val dueDate = findViewById<TextInputEditText>(R.id.detail_ed_due_date)
        val deleteTaskButton = findViewById<Button>(R.id.btn_delete_task)
        val idTask = intent.getIntExtra(TASK_ID, 0)

        val VWFactory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, VWFactory).get(DetailTaskViewModel::class.java)

        viewModel.apply{
            setTaskId(idTask)

            taskDelete.observe(this@DetailTaskActivity)
            { event ->
                val savedContent = event.getContentIfNotHandled()
                savedContent?.let {
                    if (it) {
                        viewModel.task.removeObservers(this@DetailTaskActivity)
                        finish()
                    }
                }
            }

            task.observe(this@DetailTaskActivity) {
                titleInput.text = Editable.Factory.getInstance().newEditable(it.title)
                descriptionInput.text = Editable.Factory.getInstance().newEditable(it.description)
                dueDate.text = Editable.Factory.getInstance()
                    .newEditable(DateConverter.convertMillisToString(it.dueDateMillis))
            }

            showToast.observe(this@DetailTaskActivity)
            { event ->
                val msg = event.getContentIfNotHandled()
                msg?.let{
                    Toast.makeText(this@DetailTaskActivity, it, Toast.LENGTH_SHORT).show()
                }
            }

        }

        deleteTaskButton.setOnClickListener {
            viewModel.deleteTask()

        }

    }
}