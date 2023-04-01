package com.example.flickrbrowse

import android.os.Parcelable
import android.util.Log
import androidx.versionedparcelable.VersionedParcelize
import java.io.IOException
import java.io.ObjectStreamException
import java.io.Serializable
import android.os.Parcel


class photo(
    var title: String?,
    var author: String?,
    var authorId: String?,
    var link: String?,
    var tags: String?,
    var image: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(author)
        parcel.writeString(authorId)
        parcel.writeString(link)
        parcel.writeString(tags)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<photo> {
        override fun createFromParcel(parcel: Parcel): photo {
            return photo(parcel)
        }

        override fun newArray(size: Int): Array<photo?> {
            return arrayOfNulls(size)
        }
    }
}