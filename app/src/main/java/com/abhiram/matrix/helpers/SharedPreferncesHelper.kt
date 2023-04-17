package com.abhiram.matrix.helpers

import android.content.Context
import android.content.SharedPreferences

class SharedPreferncesHelper() {
    private lateinit var sharedPreferences : SharedPreferences
    private val SHAREDPREFERENCES = "SharedPreferences"
    private val ISFIRSTRUN = "isFirstRun"
    private val NAME = "name"
    private val MAIL = "mail"
    private val NUM = "number"
    private val ECAT = "electronicscategory"
    private val VCAT = "vehiclescategory"
    private val COUNT = "count"

    fun SharedPreferncesHelperInit(context: Context){
        this.sharedPreferences = context.getSharedPreferences(SHAREDPREFERENCES,Context.MODE_PRIVATE)
    }

    fun isFirstRun():Boolean{
        return sharedPreferences.getBoolean(ISFIRSTRUN,true)
    }

    fun setFirstRun(){
        sharedPreferences.edit().putBoolean(ISFIRSTRUN,false).commit()
    }

    fun setName(name : String){
        sharedPreferences.edit().putString(NAME,name).commit()
    }

    fun getName():String{
        return sharedPreferences.getString(NAME,null).toString()
    }

    fun setMail(mail : String){
        sharedPreferences.edit().putString(MAIL,mail).commit()
    }

    fun getMail():String{
        return sharedPreferences.getString(MAIL,null).toString()
    }
    fun setContact(number: String){
        sharedPreferences.edit().putString(NUM,number).commit()
    }

    fun getContact():String{
        return sharedPreferences.getString(NUM,null).toString()
    }

    fun getECat() : String{
        return sharedPreferences.getString(ECAT,null).toString()
    }

    fun setECat(cat : String){
        sharedPreferences.edit().putString(ECAT,cat).commit()
    }

    fun getVCat() : String{
        return sharedPreferences.getString(VCAT,null).toString()
    }

    fun setVCat(cat : String){
        sharedPreferences.edit().putString(VCAT,cat).commit()
    }

    fun setNum(count : Int){
        sharedPreferences.edit().putInt(COUNT,count).commit()
    }

    fun getNum():Int{
        return sharedPreferences.getInt(COUNT,0)
    }
}