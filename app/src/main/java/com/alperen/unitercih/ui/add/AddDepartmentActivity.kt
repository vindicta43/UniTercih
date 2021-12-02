package com.alperen.unitercih.ui.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.alperen.unitercih.R
import com.alperen.unitercih.databinding.ActivityAddDepartmentBinding
import com.alperen.unitercih.db.Department
import com.alperen.unitercih.db.DepartmentDatabase
import com.alperen.unitercih.utils.Constants

class AddDepartmentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddDepartmentBinding
    private lateinit var textViewArray: ArrayList<TextView>
    private lateinit var btnYesArray: ArrayList<RadioButton>
    private lateinit var btnNoArray: ArrayList<RadioButton>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_add_department
        )

        initArrayList()
        initView()
    }

    private fun initView() {
        with(binding) {
            val questionList = Constants.QUESTION_LIST.iterator()

            textViewArray.forEach {
                it.text = questionList.next()
            }

            btnYesArray.forEach {
                it.isChecked = true
            }

            btnAddDepartment.setOnClickListener {
                if (!etDepartmentName.text.isNullOrEmpty()) {
                    addDepartmentDb(etDepartmentName.text)
                } else {
                    AlertDialog.Builder(root.context)
                        .setMessage(R.string.missing_department_name)
                        .setPositiveButton(R.string.ok) { _, _ -> }
                }
            }
        }
    }

    private fun addDepartmentDb(departmentName: Editable) {
        val dbRef = DepartmentDatabase.getInstance(this)!!
        val newDepartment = newDepartmentBuilder(departmentName)
        dbRef.departmentDAO().insertDepartment(newDepartment)

        AlertDialog.Builder(this)
            .setMessage(R.string.add_department_success)
            .setPositiveButton(R.string.ok) { _, _ -> }
    }

    private fun newDepartmentBuilder(departmentName: Editable): Department {
        val department = Department()
        department.departmentName = departmentName.toString()
        department.isMemoriseGood = btnYesArray[0].isChecked
        department.isHumanReleationsGood = btnYesArray[1].isChecked
        department.isArithmeticGood = btnYesArray[2].isChecked
        department.isComplexSystemGood = btnYesArray[3].isChecked
        department.isInterestedMoney = btnYesArray[4].isChecked
        department.isInterestedTechDevices = btnYesArray[5].isChecked
        department.isGoodDrawer = btnYesArray[6].isChecked
        department.isLikesBooksAndMovies = btnYesArray[7].isChecked
        department.isGoodTeacher = btnYesArray[8].isChecked
        department.isGoodListener = btnYesArray[9].isChecked
        department.isInterestedCrafting = btnYesArray[10].isChecked
        department.isLikesLearnBrilliantKnowledge = btnYesArray[11].isChecked
        department.isHandsDontVibrate = btnYesArray[12].isChecked
        department.isForeignLanguageEasy = btnYesArray[13].isChecked
        department.isInterestedMedical = btnYesArray[14].isChecked
        department.isHandleCrisis = btnYesArray[15].isChecked
        department.isLikesOffice = btnYesArray[16].isChecked
        department.isGoodAtFoods = btnYesArray[17].isChecked
        department.isWantsGovermentJob = btnYesArray[18].isChecked
        department.isEligibleForManager = btnYesArray[19].isChecked
        department.isWantsDifference = btnYesArray[20].isChecked
        department.isEmotionalQuotientDominant = btnYesArray[21].isChecked
        department.isLikesProgramming = btnYesArray[22].isChecked
        department.isLikesHelping = btnYesArray[23].isChecked
        department.isLikesWorkAlone = btnYesArray[24].isChecked

        return department
    }

    private fun initArrayList() {
        with(binding) {
            textViewArray = arrayListOf(
                tv1,
                tv2,
                tv3,
                tv4,
                tv5,
                tv6,
                tv7,
                tv8,
                tv9,
                tv10,
                tv11,
                tv12,
                tv13,
                tv14,
                tv15,
                tv16,
                tv17,
                tv18,
                tv19,
                tv20,
                tv21,
                tv22,
                tv23,
                tv24,
                tv25,
            )
            btnYesArray = arrayListOf(
                btnYes1,
                btnYes2,
                btnYes3,
                btnYes4,
                btnYes5,
                btnYes6,
                btnYes7,
                btnYes8,
                btnYes9,
                btnYes10,
                btnYes11,
                btnYes12,
                btnYes13,
                btnYes14,
                btnYes15,
                btnYes16,
                btnYes17,
                btnYes18,
                btnYes19,
                btnYes20,
                btnYes21,
                btnYes22,
                btnYes23,
                btnYes24,
                btnYes25,
            )
            btnNoArray = arrayListOf(
                btnNo1,
                btnNo2,
                btnNo3,
                btnNo4,
                btnNo5,
                btnNo6,
                btnNo7,
                btnNo8,
                btnNo9,
                btnNo10,
                btnNo11,
                btnNo12,
                btnNo13,
                btnNo14,
                btnNo15,
                btnNo16,
                btnNo17,
                btnNo18,
                btnNo19,
                btnNo20,
                btnNo21,
                btnNo22,
                btnNo23,
                btnNo24,
                btnNo25,
            )
        }
    }
}