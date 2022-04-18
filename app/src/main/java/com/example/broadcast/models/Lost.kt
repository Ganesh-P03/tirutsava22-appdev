package com.example.broadcast.models

import android.os.Parcel
import android.os.Parcelable
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter.writeString

data class Lost (
    val lost_image:String="",
    val lost_name:String ="",
    val lost_desc:String ="",
    val lost_time:String="",
    val lost_place:String="",
    val contact_num:Long=0,
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readLong()!!
    ) {
    }

    override fun describeContents(): Int =0

    override fun writeToParcel(dest: Parcel, flags: Int)= with(dest)  {
        writeString(lost_image)
        writeString(lost_name)
        writeString(lost_desc)
        writeString(lost_time)
        writeString(lost_place)
        writeLong(contact_num)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Lost> = object : Parcelable.Creator<Lost> {
            override fun createFromParcel(source: Parcel): Lost = Lost(source)
            override fun newArray(size: Int): Array<Lost?> = arrayOfNulls(size)
        }
    }
}