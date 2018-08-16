package com.nicholasholley.dev.hueboatsdk

import com.nicholasholley.dev.hueboatsdk.util.realm.BoatModule
import io.realm.Realm
import io.realm.RealmConfiguration

class Boat {
    companion object {
        private var realmConfiguration: RealmConfiguration =
                RealmConfiguration.Builder().apply {
                    deleteRealmIfMigrationNeeded()
                    name(CONFIG_NAME)
                    modules(BoatModule())
                }.build()
        private const val CONFIG_NAME = "com.nicholasholley.dev.hueboatsdk"

        @JvmStatic fun getRealmInstance(): Realm = Realm.getInstance(realmConfiguration)
    }
}