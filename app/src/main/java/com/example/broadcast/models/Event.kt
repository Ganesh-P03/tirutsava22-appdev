package com.example.broadcast.models

import android.os.Parcel
import android.os.Parcelable

data class Event (

    val event_name:String="",
    val description:String="",
    val time:String="",
    val type:String="",


):Parcelable {
    constructor(parcel: Parcel) : this(

        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!

    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

        parcel.writeString(event_name)
        parcel.writeString(description)
        parcel.writeString(time)
        parcel.writeString(type)

    }

    override fun describeContents(): Int=0

    companion object CREATOR : Parcelable.Creator<Event> {
        override fun createFromParcel(parcel: Parcel): Event {
            return Event(parcel)
        }

        override fun newArray(size: Int): Array<Event?> {
            return arrayOfNulls(size)
        }
    }
}