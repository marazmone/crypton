package com.marazmone.crypton.android.presentation.usecase

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.marazmone.crypton.android.presentation.broadcast.AlarmReceiver
import com.marazmone.crypton.android.presentation.broadcast.ReceiverName
import com.marazmone.crypton.domain.usecase.reminder.DailyReminderStartedGetUseCase
import com.marazmone.crypton.domain.usecase.reminder.DailyReminderStartedSaveUseCase
import com.marazmone.crypton.utils.secToMs
import java.time.LocalDateTime
import java.time.ZoneOffset

private const val DayStartWorker = 1L
private const val HourStartWorker = 9
private const val MinuteStartWorker = 0
private const val SecondStartWorker = 0

class DailyRateCheckAlarmStartUseCase(
    private val context: Context,
    private val dailyReminderStartedGetUseCase: DailyReminderStartedGetUseCase,
    private val dailyReminderStartedSaveUseCase: DailyReminderStartedSaveUseCase,
) {

    suspend fun execute() {
        if (dailyReminderStartedGetUseCase.execute()) return
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.action = ReceiverName.DAILY_REMINDER
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.setRepeating(
            AlarmManager.RTC,
            getInitialSecondsDelayInterval(),
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
        dailyReminderStartedSaveUseCase.execute(true)
    }

    /**
     * Find the interval in seconds between
     * current time and the 9:00 AM of the next day
     */
    private fun getInitialSecondsDelayInterval(): Long {
        val todayDate: LocalDateTime = LocalDateTime.now()
        val nextDayDate: LocalDateTime = todayDate.plusDays(DayStartWorker)
            .withHour(HourStartWorker)
            .withMinute(MinuteStartWorker)
            .withSecond(SecondStartWorker)

        return nextDayDate.toEpochSecond(ZoneOffset.UTC).secToMs
    }
}
