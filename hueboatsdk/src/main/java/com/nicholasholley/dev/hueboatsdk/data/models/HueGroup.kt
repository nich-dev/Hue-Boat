package com.nicholasholley.dev.hueboatsdk.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class HueGroup(
        @PrimaryKey var id: Long = 0L,
        @Expose     var name:       String? = null,
        @Expose     var type:       String? = null,
        @SerializedName("class")
        @Expose     var category:   String? = null,
        @Expose     var modelid:    String? = null,
        @Expose     var uniqueid:   String? = null,
        @Expose     var action:     HueAction? = null,
        @SerializedName("lights")
        @Expose     var lightIds:   RealmList<String> = RealmList(),
                    var lights:     RealmList<HueLight> = RealmList()
): RealmObject() {
    companion object {
        const val CATEGORY_LIVING = "Living room"
        const val CATEGORY_KITCHEN = "Kitchen"
        const val CATEGORY_DINING = "Dining"
        const val CATEGORY_BEDROOM = "Bedroom"
        const val CATEGORY_KIDS = "Kids bedroom"
        const val CATEGORY_BATHROOM = "Bathroom"
        const val CATEGORY_NURSERY = "Nursery"
        const val CATEGORY_RECREATION = "Recreation"
        const val CATEGORY_OFFICE = "Office"
        const val CATEGORY_GYM = "Gym"
        const val CATEGORY_HALLWAY = "Hallway"
        const val CATEGORY_TOILET = "Toilet"
        const val CATEGORY_FRONTDOOR = "Front door"
        const val CATEGORY_GARAGE = "Garage"
        const val CATEGORY_TERRACE = "Terrace"
        const val CATEGORY_GARDEN = "Garden"
        const val CATEGORY_DRIVEWAY = "Driveway"
        const val CATEGORY_CARPORT = "Carport"
        const val CATEGORY_OTHER = "Other"
    }
}