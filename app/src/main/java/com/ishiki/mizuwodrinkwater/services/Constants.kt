package com.ishiki.mizuwodrinkwater.services

const val EXTRA_DAILY = "Daily Total"
//const val PREFS_FILENAME = "com.ishiki.mizuwodrinkwater.prefs"
const val DAILY_GOAL = "daily_goal"


//const val EXTRA_CURRENT = "Current Glasses Type"
//const val EXTRA_SET = "Set Glasses Type"

// Database Constants
const val DATABASE_VERSION: Int = 1
const val DATABASE_NAME: String = "mizuwo_drinks.db"
const val TABLE_NAME: String = "DRINKS"

// Drinks table column names
const val KEY_ID: String = "id"
const val KEY_DRINK_IMAGE: String = "drink_name"
const val KEY_DRINK_VOLUME: String = "drink_volume"
const val KEY_DRINK_TIME: String = "drink_time"
const val KEY_DRINK_GOAL: String = "drink_goal"

// State Unit Constants
const val UNIT_VOLUME = "volume"
const val UNIT_PERCENTAGE = "percentage"

const val EXTRA_GLASS = "glass"
const val EXTRA_VOLUME = "volume"
const val EXTRA_CHECK = "check"
const val EXTRA_POSITION = "position"

// Home Fragment
const val HOME_TODAY = "Vandaag"

// Shared Preferences
const val PREFS_STATE = "state"

// Reminders Fragment
const val REMINDERS_ON_OFF = "reminders_on_off"
const val REMINDERS_RADIO = "reminders_radio_button"
const val FROM_TIME = "from_time"
const val TO_TIME = "to_time"