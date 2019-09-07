//package com.ishiki.mizuwodrinkwater.model
//
//import android.os.Parcel
//import android.os.Parcelable
//
//// Use this when you want to use different glass sizes
//@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
//class DrinksOld(val image: String, val volume: String, val unit: String) : Parcelable {
//
//    constructor(parcel: Parcel) : this(
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString()
//    )
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeString(image)
//        parcel.writeString(volume)
//        parcel.writeString(unit)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<Drinks> {
//        override fun createFromParcel(parcel: Parcel): Drinks {
//            return Drinks(parcel)
//        }
//
//        override fun newArray(size: Int): Array<Drinks?> {
//            return arrayOfNulls(size)
//        }
//    }
//
//}