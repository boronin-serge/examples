package ru.boronin.workmanager

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.work.*

/**
 * Created by Sergey Boronin on 09.12.2019.
 */

class WorkerUtils(private val lifecycleOwner: LifecycleOwner, context: Context) {

  private val workManager = WorkManager.getInstance(context)

  // region Simple worker

  /**
   * Simple start of the Worker
   */
  fun callSaveImageWorker() {
    // Сохранять только если заряжается
    val constraints = Constraints.Builder()
      .setRequiresCharging(true)
      .build()

    // Make request
    val saveRequest = OneTimeWorkRequestBuilder<SaveImageToFileWorker>()
      .setConstraints(constraints)
      .addTag(TAG_OUTPUT)
      .build()

    // - Дать нзвание
    // - Стратегия разрешения конфликта: замена
    // - Объект реквеста
    val continuation = workManager.beginUniqueWork(
      IMAGE_MANIPULATION_WORK_NAME,
      ExistingWorkPolicy.REPLACE,
      saveRequest
    )

    // Run!
    continuation.enqueue()
  }

  fun cancelSaveImageWorker() {
    workManager.cancelUniqueWork(IMAGE_MANIPULATION_WORK_NAME)
  }

  // endregion

  // region Chain of workers

  /**
   * Chain of workers
   */
  fun callChainOfWorkers() {
    // Чтобы иметь возможность донастроить запрос к WorkManager
    // можно создать объекта WorkContinuation, но пока
    // не вызывать на нем метод .enqueue().
    //
    // Так, например, создается цепочка вызовов
    //
    // Первый WorkerRequest - удаление старых изображений
    val cleanupRequest = OneTimeWorkRequest.from(CleanupWorker::class.java)

    var continuation = workManager
      .beginUniqueWork(
        IMAGE_MANIPULATION_WORK_NAME,
        ExistingWorkPolicy.REPLACE,
        cleanupRequest
      )

    // После очистки файлов сохранить новый (Сохранять только если заряжается)
    val constraints = Constraints.Builder()
      .setRequiresCharging(true)
      .build()

    // Make request
    val saveRequest = OneTimeWorkRequestBuilder<SaveImageToFileWorker>()
      .setConstraints(constraints)
      .addTag(TAG_OUTPUT)
      .build()

    // Add to chain second request
    continuation = continuation.then(saveRequest)

    // Run!
    continuation.enqueue()
  }

  // endregion

  // region Observe worker

  fun startObserveWorker(workerTag: String) {
    // This transformation makes sure that whenever the current work Id changes the WorkStatus
    // the UI is listening to changes
    val outputWorkInfoItems = workManager.getWorkInfosByTagLiveData(workerTag)

    outputWorkInfoItems.observe(
      lifecycleOwner,
      Observer { listOfWorkInfo ->
        // If there are no matching work info, do nothing
        if (listOfWorkInfo.isNullOrEmpty()) {
          return@Observer
        }

        // We only care about the one output status.
        // Every continuation has only one worker tagged TAG_OUTPUT
        val workInfo = listOfWorkInfo[0]

        when (workInfo.state) {
          WorkInfo.State.SUCCEEDED -> {
            val result = workInfo.outputData.getString(KEY_IMAGE_URI)
          }
          WorkInfo.State.ENQUEUED -> {}
          WorkInfo.State.RUNNING -> {}
          WorkInfo.State.FAILED -> {}
          WorkInfo.State.BLOCKED -> {}
          WorkInfo.State.CANCELLED -> {}
        }
      }
    )
  }

  // endregion
}
