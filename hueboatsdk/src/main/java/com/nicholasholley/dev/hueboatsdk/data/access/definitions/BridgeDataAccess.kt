package com.nicholasholley.dev.hueboatsdk.data.access.definitions

import com.nicholasholley.dev.hueboatsdk.data.access.BaseData
import com.nicholasholley.dev.hueboatsdk.data.models.HueBridge

class BridgeDataAccess : BaseData<HueBridge>, RealmDataBase<HueBridge>() {
    override val clazz: Class<HueBridge> = HueBridge::class.java

}