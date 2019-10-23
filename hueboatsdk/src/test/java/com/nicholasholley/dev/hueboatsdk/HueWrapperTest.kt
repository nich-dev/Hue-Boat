package com.nicholasholley.dev.hueboatsdk

import com.nicholasholley.dev.hueboatsdk.models.*
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Test wrappers around the API
 */
class HueWrapperTest {
    @Test
    fun lightWrapperToList_convertsCorrect() {
        val wrapper: HueLightWrapper = HueLightWrapper().apply {
            put("1", HueLight(type = "1"))
            put("2", HueLight(type = "2"))
        }
        val unwrapped = wrapper.toList()
        assertEquals(unwrapped.size, 2)
        unwrapped.forEach {
            assert(it.type == it.id)
        }
    }

    @Test
    fun groupWrapperToList_convertsCorrect() {
        val wrapper: HueGroupWrapper = HueGroupWrapper().apply {
            put("1", HueGroup(type = "1"))
            put("2", HueGroup(type = "2"))
        }
        val unwrapped = wrapper.toList()
        assertEquals(unwrapped.size, 2)
        unwrapped.forEach {
            assert(it.type == it.id)
        }
    }

    @Test
    fun lightStateWrapperToList_convertsCorrect() {
        val wrapper: HueLightStateWrapper = HueLightStateWrapper().apply {
            put("1", HueState(alert = "1"))
            put("2", HueState(alert = "2"))
        }
        val unwrapped = wrapper.toList()
        assertEquals(unwrapped.size, 2)
        unwrapped.forEach {
            assert(it.alert == it.id)
        }
    }

    @Test
    fun ruleWrapperToList_convertsCorrect() {
        val wrapper: HueRuleWrapper = HueRuleWrapper().apply {
            put("1", HueRule(name = "1"))
            put("2", HueRule(name = "2"))
        }
        val unwrapped = wrapper.toList()
        assertEquals(unwrapped.size, 2)
        unwrapped.forEach {
            assert(it.name == it.id)
        }
    }


    @Test
    fun sceneWrapperToList_convertsCorrect() {
        val wrapper: HueSceneWrapper = HueSceneWrapper().apply {
            put("1", HueScene(type = "1"))
            put("2", HueScene(type = "2"))
        }
        val unwrapped = wrapper.toList()
        assertEquals(unwrapped.size, 2)
        unwrapped.forEach {
            assert(it.type == it.id)
        }
    }


    @Test
    fun scheduleWrapperToList_convertsCorrect() {
        val wrapper: HueScheduleWrapper = HueScheduleWrapper().apply {
            put("1", HueSchedule(name = "1"))
            put("2", HueSchedule(name = "2"))
        }
        val unwrapped = wrapper.toList()
        assertEquals(unwrapped.size, 2)
        unwrapped.forEach {
            assert(it.name == it.id)
        }
    }


    @Test
    fun sensorWrapperToList_convertsCorrect() {
        val wrapper: HueSensorWrapper = HueSensorWrapper().apply {
            put("1", HueSensor(type = "1"))
            put("2", HueSensor(type = "2"))
        }
        val unwrapped = wrapper.toList()
        assertEquals(unwrapped.size, 2)
        unwrapped.forEach {
            assert(it.type == it.id)
        }
    }
}