package com.nicholasholley.dev.hueboat.data.models

import androidx.room.Entity
import androidx.room.TypeConverters
import com.nicholasholley.dev.hueboat.data.models.converters.ActionConverter
import com.nicholasholley.dev.hueboat.data.models.converters.ConditionConverter
import com.nicholasholley.dev.hueboatsdk.data.models.HueAction
import com.nicholasholley.dev.hueboatsdk.data.models.HueCondition

@Entity
@TypeConverters(ActionConverter::class, ConditionConverter::class)
data class Rule(
        var id:             Long = 0L,
        var name:           String? = null,
        var lasttriggered:  String? = null,
        var creationtime:   String? = null,
        var owner:          String? = null,
        var status:         String? = null,
        var conditions:     List<HueCondition> = listOf(),
        var actions:        List<HueAction> = listOf()
)

abstract class RuleDao : BaseDao<Rule>


