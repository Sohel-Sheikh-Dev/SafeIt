package com.example.safeit.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Password_Database")
public class Password {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "loginID")
    public String LoginID;

    @ColumnInfo(name = "websiteURL")
    public String WebsiteURL;

    @ColumnInfo(name = "password")
    public String Password;


}
