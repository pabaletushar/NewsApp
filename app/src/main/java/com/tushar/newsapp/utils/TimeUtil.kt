package com.tushar.newsapp.utils

import com.tushar.newsapp.utils.AppConstant.MORNING_UPDATE_TIME
import java.util.Calendar

object TimeUtil {

    fun getInitialDelay(): Long {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, MORNING_UPDATE_TIME)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        if (calendar.timeInMillis <= System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }

        return calendar.timeInMillis - System.currentTimeMillis()
    }
}