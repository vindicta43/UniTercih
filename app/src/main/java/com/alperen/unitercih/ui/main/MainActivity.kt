package com.alperen.unitercih.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.alperen.unitercih.R
import com.alperen.unitercih.databinding.ActivityMainBinding
import com.alperen.unitercih.db.Department
import com.alperen.unitercih.db.DepartmentDatabase
import com.alperen.unitercih.ui.detail.DetailActivity
import com.alperen.unitercih.utils.Constants

class MainActivity : AppCompatActivity() {
    private lateinit var dbRef: DepartmentDatabase
    private lateinit var departmentList: MutableList<Department>
    private lateinit var questionList: ArrayList<String>
    private lateinit var ruleList: ArrayList<String>
    private lateinit var binding: ActivityMainBinding
    private var surveyText = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        with(binding) {
            dbRef = DepartmentDatabase.getInstance(root.context)!!

            // Fill db for first time
            fillDb(dbRef)
            btnMale.isChecked = true

            etName.addTextChangedListener {
                if (it.isNullOrEmpty()) {
                    nameLayout.error = "Lütfen isminizi giriniz"
                    nameLayout.isErrorEnabled = true
                } else {
                    nameLayout.isErrorEnabled = false
                }
            }

            btnStart.setOnClickListener {
                if (!etName.text.isNullOrEmpty()) {
                    nameLayout.isErrorEnabled = false

                    if (btnMale.isChecked) {
                        fillList(etName.text, btnMale.text)
                    } else {
                        fillList(etName.text, btnFemale.text)
                    }

                } else {
                    nameLayout.error = "Lütfen isminizi giriniz"
                    nameLayout.isErrorEnabled = true
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btnDetailPage -> {
                val intent = Intent(this, DetailActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }

    private fun fillList(name: Editable?, gender: CharSequence) {
        // All lists
        departmentList = dbRef.departmentDAO().getAllDepartments()

        questionList = arrayListOf()
        questionList.clear()
        Constants.QUESTION_LIST.forEach {
            questionList.add(it)
        }

        ruleList = arrayListOf()
        ruleList.clear()
        Constants.RULE_LIST.forEach {
            ruleList.add(it)
        }

        // Clear survey text
        surveyText = ""
        binding.tvSurvey.text = ""

        startSurvey(name, gender)
    }

    private fun startSurvey(name: Editable?, gender: CharSequence) {
        val randomNum = (0 until questionList.size).random()

        Log.e("tercih", "girdi")
        // Strings
        val questionText = questionList[randomNum]
        val yes = resources.getText(R.string.yes).toString()
        val no = resources.getText(R.string.no).toString()
        val ok = resources.getText(R.string.ok).toString()

        val questionDialog = AlertDialog.Builder(this).create()
        questionDialog.apply {
            setMessage(questionText)
            setCancelable(false)
            setButton(AlertDialog.BUTTON_POSITIVE, yes) { _, _ ->
                // Update text
                surveyText += "${questionList[randomNum]} => $yes\n"
                binding.tvSurvey.text = surveyText
                Log.e("metin", surveyText)

                questionMapper(ruleList[randomNum], true)
                questionList.removeAt(randomNum)
                ruleList.removeAt(randomNum)

                when {
                    // Result found
                    departmentList.size == 1 -> {
                        Log.e("tercih", departmentList[0].departmentName)
                        showResult(name, gender, departmentList[0].departmentName)
                        questionDialog.dismiss()
                    }
                    // No result found
                    departmentList.isNullOrEmpty() -> {
                        Log.e("tercih", "sonuc yok")
                        questionDialog.dismiss()

                        AlertDialog.Builder(this@MainActivity)
                            .setMessage("Sonuç bulunamadı")
                            .setPositiveButton(ok) { _, _ -> }
                            .show()
                    }
                    // Continue asking
                    else -> {
                        Log.e("tercih", "${departmentList.size}")
                        startSurvey(name, gender)
                    }
                }
            }
            setButton(AlertDialog.BUTTON_NEGATIVE, no) { _, _ ->
                // Update text
                surveyText += "${questionList[randomNum]} => $no\n"
                binding.tvSurvey.text = surveyText
                Log.e("metin", surveyText)

                questionMapper(ruleList[randomNum], false)
                questionList.removeAt(randomNum)
                ruleList.removeAt(randomNum)

                when {
                    // Result found
                    departmentList.size == 1 -> {
                        Log.e("tercih", departmentList[0].departmentName)
                        showResult(name, gender, departmentList[0].departmentName)
                        questionDialog.dismiss()
                    }
                    // No result found
                    departmentList.isNullOrEmpty() -> {
                        Log.e("tercih", "sonuc yok")
                        questionDialog.dismiss()

                        AlertDialog.Builder(this@MainActivity)
                            .setMessage("Sonuç bulunamadı")
                            .setPositiveButton(ok) { _, _ -> }
                            .show()
                    }
                    // Continue asking
                    else -> {
                        Log.e("tercih", "${departmentList.size}")
                        startSurvey(name, gender)
                    }
                }
            }
            show()
        }
    }

    // Show result as Alert Dialog
    private fun showResult(name: Editable?, gender: CharSequence, departmentName: String) {
        if (gender == binding.btnFemale.text) {
            surveyText += "$name hanım sizin için uygun bölüm: $departmentName\n"
            binding.tvSurvey.text = surveyText
            Log.e("metin", surveyText)

            AlertDialog.Builder(this)
                .setMessage("$name hanım sizin için uygun bölüm: $departmentName")
                .setPositiveButton("Tamam") { _, _ -> }
                .show()
        } else {
            surveyText += "$name bey sizin için uygun bölüm: $departmentName\n"
            binding.tvSurvey.text = surveyText
            Log.e("metin", surveyText)

            AlertDialog.Builder(this)
                .setMessage("$name bey sizin için uygun bölüm: $departmentName")
                .setPositiveButton("Tamam") { _, _ -> }
                .show()
        }
    }

    // If app opened for fist time or all records was deleted
    private fun fillDb(dbRef: DepartmentDatabase?) {
        val departments = dbRef?.departmentDAO()?.getAllDepartments()
        if (departments.isNullOrEmpty()) {
            Constants.CONSTANT_DEPARTMENTS.forEach {
                dbRef?.departmentDAO()?.insertDepartment(it)
            }
        }
    }

    // Match questions with database queries
    private fun questionMapper(rule: String, field: Boolean) {
        when (rule) {
            Constants.RULE_1 -> {
                val tempQuery = dbRef.departmentDAO().is_memorize_good(field)
                removeFromList(tempQuery)
            }
            Constants.RULE_2 -> {
                val tempQuery = dbRef.departmentDAO().is_human_releations_good(field)
                removeFromList(tempQuery)
            }
            Constants.RULE_3 -> {
                val tempQuery = dbRef.departmentDAO().is_arithmetic_good(field)
                removeFromList(tempQuery)
            }
            Constants.RULE_4 -> {
                val tempQuery = dbRef.departmentDAO().is_complex_system_good(field)
                removeFromList(tempQuery)
            }
            Constants.RULE_5 -> {
                val tempQuery = dbRef.departmentDAO().is_interested_money(field)
                removeFromList(tempQuery)
            }
            Constants.RULE_6 -> {
                val tempQuery = dbRef.departmentDAO().is_interested_tech_devices(field)
                removeFromList(tempQuery)
            }
            Constants.RULE_7 -> {
                val tempQuery = dbRef.departmentDAO().is_good_drawer(field)
                removeFromList(tempQuery)
            }
            Constants.RULE_8 -> {
                val tempQuery = dbRef.departmentDAO().is_likes_books_and_movies(field)
                removeFromList(tempQuery)
            }
            Constants.RULE_9 -> {
                val tempQuery = dbRef.departmentDAO().is_good_teacher(field)
                removeFromList(tempQuery)
            }
            Constants.RULE_10 -> {
                val tempQuery = dbRef.departmentDAO().is_likes_books_and_movies(field)
                removeFromList(tempQuery)
            }
            Constants.RULE_11 -> {
                val tempQuery = dbRef.departmentDAO().is_interested_crafting(field)
                removeFromList(tempQuery)
            }
            Constants.RULE_12 -> {
                val tempQuery = dbRef.departmentDAO().is_likes_learn_brilliant_knowledge(field)
                removeFromList(tempQuery)
            }
            Constants.RULE_13 -> {
                val tempQuery = dbRef.departmentDAO().is_hands_dont_vibrate(field)
                removeFromList(tempQuery)
            }
            Constants.RULE_14 -> {
                val tempQuery = dbRef.departmentDAO().is_foreign_language_easy(field)
                removeFromList(tempQuery)
            }
            Constants.RULE_15 -> {
                val tempQuery = dbRef.departmentDAO().is_interested_medical(field)
                removeFromList(tempQuery)
            }
            Constants.RULE_16 -> {
                val tempQuery = dbRef.departmentDAO().is_handle_crisis(field)
                removeFromList(tempQuery)
            }
            Constants.RULE_17 -> {
                val tempQuery = dbRef.departmentDAO().is_likes_office(field)
                removeFromList(tempQuery)
            }
            Constants.RULE_18 -> {
                val tempQuery = dbRef.departmentDAO().is_good_at_foods(field)
                removeFromList(tempQuery)
            }
            Constants.RULE_19 -> {
                val tempQuery = dbRef.departmentDAO().is_wants_goverment_job(field)
                removeFromList(tempQuery)
            }
            Constants.RULE_20 -> {
                val tempQuery = dbRef.departmentDAO().is_eligible_for_manager(field)
                removeFromList(tempQuery)
            }
            Constants.RULE_21 -> {
                val tempQuery = dbRef.departmentDAO().is_wants_difference(field)
                removeFromList(tempQuery)
            }
            Constants.RULE_22 -> {
                val tempQuery = dbRef.departmentDAO().is_emotional_quotient_dominant(field)
                removeFromList(tempQuery)
            }
            Constants.RULE_23 -> {
                val tempQuery = dbRef.departmentDAO().is_likes_programming(field)
                removeFromList(tempQuery)
            }
            Constants.RULE_24 -> {
                val tempQuery = dbRef.departmentDAO().is_likes_helping(field)
                removeFromList(tempQuery)
            }
            else -> {
                val tempQuery = dbRef.departmentDAO().is_likes_work_alone(field)
                removeFromList(tempQuery)
            }
        }
    }

    // Iterator for departmentList
    private fun removeFromList(tempQuery: MutableList<Department>) {
        val iterator = departmentList.iterator()
        iterator.forEach {
            if (!tempQuery.contains(it) && departmentList.size != 1)
                iterator.remove()
        }
    }
}