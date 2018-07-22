package com.nicholasholley.dev.hueboatsdk.data.access.definitions

import com.nicholasholley.dev.hueboatsdk.data.access.BaseData
import com.nicholasholley.dev.hueboatsdk.data.models.HueBridge
import com.nicholasholley.dev.hueboatsdk.util.w

class BridgeDataAccess : BaseData<HueBridge>, RealmDataMixin<HueBridge>() {
    override val clazz: Class<HueBridge> = HueBridge::class.java

}