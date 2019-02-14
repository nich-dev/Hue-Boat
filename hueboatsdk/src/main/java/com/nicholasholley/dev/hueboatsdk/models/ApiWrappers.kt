package com.nicholasholley.dev.hueboatsdk.models

class HueGroupWrapper : HashMap<String, HueGroup>() {
    fun toList(): List<HueGroup> {
        val list = mutableListOf<HueGroup>()
        this.forEach { entry ->
            list.add(entry.value.apply { id = entry.key })
        }
        return list
    }
}

class HueLightWrapper : HashMap<String, HueLight>() {
    fun toList(): List<HueLight> {
        val list = mutableListOf<HueLight>()
        this.forEach { entry ->
            list.add(entry.value.apply { id = entry.key })
        }
        return list
    }
}

class HueLightStateWrapper : HashMap<String, HueState>() {
    fun toList(): List<HueState> {
        val list = mutableListOf<HueState>()
        this.forEach { entry ->
            list.add(entry.value.apply { id = entry.key })
        }
        return list
    }
}

class HueRuleWrapper : HashMap<String, HueRule>() {
    fun toList(): List<HueRule> {
        val list = mutableListOf<HueRule>()
        this.forEach { entry ->
            list.add(entry.value.apply { id = entry.key })
        }
        return list
    }
}

class HueSceneWrapper : HashMap<String, HueScene>() {
    fun toList(): List<HueScene> {
        val list = mutableListOf<HueScene>()
        this.forEach { entry ->
            list.add(entry.value.apply { id = entry.key })
        }
        return list
    }
}

class HueScheduleWrapper : HashMap<String, HueSchedule>() {
    fun toList(): List<HueSchedule> {
        val list = mutableListOf<HueSchedule>()
        this.forEach { entry ->
            list.add(entry.value.apply { id = entry.key })
        }
        return list
    }
}

class HueSensorWrapper : HashMap<String, HueSensor>() {
    fun toList(): List<HueSensor> {
        val list = mutableListOf<HueSensor>()
        this.forEach { entry ->
            list.add(entry.value.apply { id = entry.key })
        }
        return list
    }
}

class HueCommandWrapper : HashMap<String, Any>()

class HueActionWrapper : HashMap<String, Any>()
