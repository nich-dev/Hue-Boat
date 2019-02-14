package com.nicholasholley.dev.hueboatsdk.models

class SuccessBody(val id: String)

class Success(val success: SuccessBody)

class SuccessWrapper : ArrayList<Success>()
