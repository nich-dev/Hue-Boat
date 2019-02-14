package com.nicholasholley.dev.hueboatsdk.network.api

internal object ApiPaths {
    object Base {
        const val USERNAME_REPLACEMENT = "username"
        const val ID_REPLACEMENT = "id"
        const val BASE_PATH = "/api/{$USERNAME_REPLACEMENT}"
    }

    object Groups {
        const val ALL = "${Base.BASE_PATH}/groups"
        const val NEW = "$ALL/new"
        const val SINGLE = "$ALL/{${Base.ID_REPLACEMENT}}"
        const val SINGLE_STATE = "$ALL/{${Base.ID_REPLACEMENT}}/state"
    }

    object Lights {
        const val ALL = "${Base.BASE_PATH}/lights"
        const val NEW = "$ALL/new"
        const val SINGLE = "$ALL/{${Base.ID_REPLACEMENT}}"
        const val SINGLE_STATE = "$ALL/{${Base.ID_REPLACEMENT}}/state"
    }

    object Rules {
        const val ALL = "${Base.BASE_PATH}/rules"
        const val SINGLE = "$ALL/{${Base.ID_REPLACEMENT}}"
    }

    object Scene {
        const val ALL = "${Base.BASE_PATH}/scenes"
        const val SINGLE = "$ALL/{${Base.ID_REPLACEMENT}}"
        const val STATE = "$SINGLE/lightstates/{${Base.ID_REPLACEMENT}}"
    }

    object Schedule {
        const val ALL = "${Base.BASE_PATH}/schedules"
        const val SINGLE = "$ALL/{${Base.ID_REPLACEMENT}}"
    }

    object Sensor {
        const val SENSORS_ALL = "${Base.BASE_PATH}/sensors"
        const val SENSORS_NEW = "$SENSORS_ALL/new"
        const val SENSORS_SINGLE = "$SENSORS_ALL/{${Base.ID_REPLACEMENT}}"
        const val SENSORS_CONFIG = "$SENSORS_SINGLE/config"
        const val SENSORS_STATE = "$SENSORS_SINGLE/state"
    }
}