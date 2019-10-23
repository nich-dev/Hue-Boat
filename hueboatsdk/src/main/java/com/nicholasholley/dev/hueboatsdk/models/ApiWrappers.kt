package com.nicholasholley.dev.hueboatsdk.models

class HueGroupWrapper : HashMap<String, HueGroup>() {
    fun toList(): List<HueGroup> =
        this.map { entry ->
            entry.value.apply { id = entry.key }
        }
    }

class HueLightWrapper : HashMap<String, HueLight>() {
    fun toList(): List<HueLight> =
        this.map { entry ->
            entry.value.apply { id = entry.key }
        }
    }

class HueLightStateWrapper : HashMap<String, HueState>() {
    fun toList(): List<HueState> =
        this.map { entry ->
            entry.value.apply { id = entry.key }
        }
    }

class HueRuleWrapper : HashMap<String, HueRule>() {
    fun toList(): List<HueRule> =
        this.map { entry ->
            entry.value.apply { id = entry.key }
        }
    }

class HueSceneWrapper : HashMap<String, HueScene>() {
    fun toList(): List<HueScene> =
        this.map { entry ->
            entry.value.apply { id = entry.key }
        }
    }

class HueScheduleWrapper : HashMap<String, HueSchedule>() {
    fun toList(): List<HueSchedule> =
        this.map { entry ->
            entry.value.apply { id = entry.key }
        }
    }

class HueSensorWrapper : HashMap<String, HueSensor>() {
    fun toList(): List<HueSensor> =
        this.map { entry ->
            entry.value.apply { id = entry.key }
        }
    }

class HueCommandWrapper : HashMap<String, Any>()

class HueActionWrapper : HashMap<String, Any>()
