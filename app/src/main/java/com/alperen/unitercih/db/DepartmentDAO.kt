package com.alperen.unitercih.db

import androidx.room.*

/**
 * Created by Alperen on 4.11.2021.
 */
@Dao
interface DepartmentDAO {
    @Insert
    fun insertDepartment(vararg departments: Department)

    @Delete
    fun deleteDepartment(department: Department)

    @Query("SELECT * FROM departments")
    fun getAllDepartments(): MutableList<Department>

    @Query("SELECT * FROM departments WHERE is_memorize_good = :field")
    fun is_memorize_good(field: Boolean): MutableList<Department>

    @Query("SELECT * FROM departments WHERE is_human_releations_good = :field")
    fun is_human_releations_good(field: Boolean): MutableList<Department>

    @Query("SELECT * FROM departments WHERE is_arithmetic_good = :field")
    fun is_arithmetic_good(field: Boolean): MutableList<Department>

    @Query("SELECT * FROM departments WHERE is_complex_system_good = :field")
    fun is_complex_system_good(field: Boolean): MutableList<Department>

    @Query("SELECT * FROM departments WHERE is_interested_money = :field")
    fun is_interested_money(field: Boolean): MutableList<Department>

    @Query("SELECT * FROM departments WHERE is_interested_tech_devices = :field")
    fun is_interested_tech_devices(field: Boolean): MutableList<Department>

    @Query("SELECT * FROM departments WHERE is_good_drawer = :field")
    fun is_good_drawer(field: Boolean): MutableList<Department>

    @Query("SELECT * FROM departments WHERE is_likes_books_and_movies = :field")
    fun is_likes_books_and_movies(field: Boolean): MutableList<Department>

    @Query("SELECT * FROM departments WHERE is_good_teacher = :field")
    fun is_good_teacher(field: Boolean): MutableList<Department>

    @Query("SELECT * FROM departments WHERE is_good_listener = :field")
    fun is_good_listener(field: Boolean): MutableList<Department>

    @Query("SELECT * FROM departments WHERE is_interested_crafting = :field")
    fun is_interested_crafting(field: Boolean): MutableList<Department>

    @Query("SELECT * FROM departments WHERE is_likes_learn_brilliant_knowledge = :field")
    fun is_likes_learn_brilliant_knowledge(field: Boolean): MutableList<Department>

    @Query("SELECT * FROM departments WHERE is_hands_dont_vibrate = :field")
    fun is_hands_dont_vibrate(field: Boolean): MutableList<Department>

    @Query("SELECT * FROM departments WHERE is_foreign_language_easy = :field")
    fun is_foreign_language_easy(field: Boolean): MutableList<Department>

    @Query("SELECT * FROM departments WHERE is_interested_medical = :field")
    fun is_interested_medical(field: Boolean): MutableList<Department>

    @Query("SELECT * FROM departments WHERE is_handle_crisis = :field")
    fun is_handle_crisis(field: Boolean): MutableList<Department>

    @Query("SELECT * FROM departments WHERE is_likes_office = :field")
    fun is_likes_office(field: Boolean): MutableList<Department>

    @Query("SELECT * FROM departments WHERE is_good_at_foods = :field")
    fun is_good_at_foods(field: Boolean): MutableList<Department>

    @Query("SELECT * FROM departments WHERE is_wants_goverment_job = :field")
    fun is_wants_goverment_job(field: Boolean): MutableList<Department>

    @Query("SELECT * FROM departments WHERE is_eligible_for_manager = :field")
    fun is_eligible_for_manager(field: Boolean): MutableList<Department>

    @Query("SELECT * FROM departments WHERE is_wants_difference = :field")
    fun is_wants_difference(field: Boolean): MutableList<Department>

    @Query("SELECT * FROM departments WHERE is_emotional_quotient_dominant = :field")
    fun is_emotional_quotient_dominant(field: Boolean): MutableList<Department>

    @Query("SELECT * FROM departments WHERE is_likes_programming = :field")
    fun is_likes_programming(field: Boolean): MutableList<Department>

    @Query("SELECT * FROM departments WHERE is_likes_helping = :field")
    fun is_likes_helping(field: Boolean): MutableList<Department>

    @Query("SELECT * FROM departments WHERE is_likes_work_alone = :field")
    fun is_likes_work_alone(field: Boolean): MutableList<Department>
}