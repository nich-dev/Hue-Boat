package com.nicholasholley.dev.hueboat.data.models

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.nicholasholley.dev.hueboat.data.models.converters.ActionListConverter
import com.nicholasholley.dev.hueboat.data.models.converters.ConditionListConverter
import com.nicholasholley.dev.hueboatsdk.models.HueAction
import com.nicholasholley.dev.hueboatsdk.models.HueCondition

@Entity
@TypeConverters(ActionListConverter::class, ConditionListConverter::class)
data class Rule(
        @PrimaryKey
        var id:             String,
        var name:           String? = null,
        var lasttriggered:  String? = null,
        var creationtime:   String? = null,
        var owner:          String? = null,
        var status:         String? = null,
        var conditions:     List<HueCondition> = listOf(),
        var actions:        List<HueAction> = listOf()
)

@Dao
abstract class RuleDao : BaseDao<Rule>
