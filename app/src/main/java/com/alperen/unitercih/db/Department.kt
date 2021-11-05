package com.alperen.unitercih.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alperen.unitercih.utils.Constants

/**
 * Created by Alperen on 4.11.2021.
 */
@Entity(tableName = "departments")
data class Department(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int,

    // Bolum adi
    @ColumnInfo(name = "department_name")
    var departmentName: String,

    // 1- Ezber yetenegim iyidir
    @ColumnInfo(name = Constants.RULE_1)
    var isMemoriseGood: Boolean,

    // 2- Insan iliskilerinde iyiyimdir
    @ColumnInfo(name = Constants.RULE_2)
    var isHumanReleationsGood : Boolean,

    // 3- Sayisal yeteneklerime guvenirim
    @ColumnInfo(name = Constants.RULE_3)
    var isArithmeticGood : Boolean,

    // 4- Karmasik sistemler kurabilecegime inaniyorum
    @ColumnInfo(name = Constants.RULE_4)
    var isComplexSystemGood : Boolean,

    // 5- Para ve parayla ilgili seylerle ilgilenmekten hoslanirim
    @ColumnInfo(name = Constants.RULE_5)
    var isInterestedMoney : Boolean,

    // 6- Teknolojik cihazlara ilgim var
    @ColumnInfo(name = Constants.RULE_6)
    var isInterestedTechDevices: Boolean,

    // 7- İyi bir cizerim
    @ColumnInfo(name = Constants.RULE_7)
    var isGoodDrawer : Boolean,

    // 8- Kitap okumayi, film ve dizi izlemeyi severim
    @ColumnInfo(name = Constants.RULE_8)
    var isLikesBooksAndMovies : Boolean,

    // 9- Bildigim bir konuyu diger insanlara rahatlikla ogretebiliyorum
    @ColumnInfo(name = Constants.RULE_9)
    var isGoodTeacher : Boolean,

    // 10- Iyi bir dinleyiciyimdir
    @ColumnInfo(name = Constants.RULE_10)
    var isGoodListener : Boolean,

    // 11- Yeni bir seyler olusturmayi, yapmayi severim
    @ColumnInfo(name = Constants.RULE_11)
    var isInterestedCrafting : Boolean,

    // 12- Ufkumu acan bilgiler ogrenmek beni mutlu ediyor
    @ColumnInfo(name = Constants.RULE_12)
    var isLikesLearnBrilliantKnowledge : Boolean,

    // 13- Ellerim titremez/cok az titrer
    @ColumnInfo(name = Constants.RULE_13)
    var isHandsDontVibrate : Boolean,

    // 14- Yabanci dil benim icin bir problem degil
    @ColumnInfo(name = Constants.RULE_14)
    var isForeignLanguageEasy : Boolean,

    // 15- Saglik sektorune ilgim var
    @ColumnInfo(name = Constants.RULE_15)
    var isInterestedMedical : Boolean,

    // 16- Kriz yonetimi yapabilirim
    @ColumnInfo(name = Constants.RULE_16)
    var isHandleCrisis : Boolean,

    // 17- Bir sahada calismak yerine ofiste veya kapali alanda calismayi tercih ederim
    @ColumnInfo(name = Constants.RULE_17)
    var isLikesOffice : Boolean,

    // 18- Yemeklerle ilgili bilgimin diger insanlardan daha iyi oldugunu dusunuyorum
    @ColumnInfo(name = Constants.RULE_18)
    var isGoodAtFoods : Boolean,

    // 19- Devlet bunyesinde bir is istiyorum
    @ColumnInfo(name = Constants.RULE_19)
    var isWantsGovermentJob : Boolean,

    // 20- Bircok calisanin muduru olmaya uygunum
    @ColumnInfo(name = Constants.RULE_20)
    var isEligibleForManager : Boolean,

    // 21- Ayni seyi defalarca yapmaktan sikilan, yenilik arayan biriyim
    @ColumnInfo(name = Constants.RULE_21)
    var isWantsDifference : Boolean,

    // 22- Duygusal zekam, mantıksal zekama göre daha ön plandadır
    @ColumnInfo(name = Constants.RULE_22)
    var isEmotionalQuotientDominant : Boolean,

    // 23- Programlamaya ilgim var veya seviyorum
    @ColumnInfo(name = Constants.RULE_23)
    var isLikesProgramming : Boolean,

    // 24- Zor durumdakilere yardim etmeyi severim
    @ColumnInfo(name = Constants.RULE_24)
    var isLikesHelping : Boolean,

    // 25- Bir ekip yerine tek basima calismayi tercih ederim
    @ColumnInfo(name = Constants.RULE_25)
    var isLikesWorkAlone : Boolean,
)
