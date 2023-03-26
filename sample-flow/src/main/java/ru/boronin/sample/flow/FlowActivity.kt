package ru.boronin.sample.flow

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_flow.button
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

private const val TAG = "FLOW"

class FlowActivity : AppCompatActivity() {

  lateinit var flowItem: Flow<Int>
  lateinit var flowOne: Flow<String>
  lateinit var flowTwo: Flow<String>
  val stateFlow = MutableStateFlow(0)
  val sharedFlow = MutableSharedFlow<Unit>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_flow)

    setupFlow()
    initListeners()
  }

  // region private

  /**
   * Emit items after 500 milliseconds delay.
   *
   * - flow { ... } makes flow object
   * - emit numbers from 0 to 10 at 500ms delay.
   * - to emit the number we will use emit()
   * - collect for subscribe
   */
  @ExperimentalCoroutinesApi
  private fun setupFlow() {
    flowItem = flow {
      Log.d(TAG, "Start flow")
      (0..10).forEach {
        // Emit items with 500 milliseconds delay
        delay(500)
        Log.d(TAG, "Emitting $it")
        emit(it)
      }
    }.map {
      it * it
    }.flowOn(Dispatchers.Default)

    flowOne = flowOf("Himanshu", "Amit", "Janishar").flowOn(Dispatchers.Default)
    flowTwo = flowOf("Singh", "Shekhar", "Ali").flowOn(Dispatchers.Default)
  }

  private fun initListeners() {
    button.setOnClickListener {
      combineFlows(stateFlow, sharedFlow)
      CoroutineScope(Dispatchers.Main).launch {
        flowItem.collect {
          stateFlow.emit(it)
//          sharedFlow.emit(Unit)
        }
      }
    }
  }

  /**
   * combine state and shared flows
   */
  private fun combineFlows(flow1: StateFlow<Int>, flow2: SharedFlow<Unit>) {
    CoroutineScope(Dispatchers.Main).launch {
      flow1.combine(flow2) { first, secondg ->
        "$first $secondg"
      }.collect {
        Log.d(TAG, it)
      }
    }
  }

  /**
   * zip two flows
   */
  private fun zipFlows(flow1: Flow<String>, flow2: Flow<String>) {
    CoroutineScope(Dispatchers.Main).launch {
      flow1.zip(flow2) { firstString, secondString ->
        "$firstString $secondString"
      }.collect {
        Log.d(TAG, it)
      }
    }
  }

  /**
   * flowOf operator
   */
  private fun getFlowOf(): Flow<Int> {
    return flowOf(4, 2, 5, 1, 7)
      .onEach { delay(400) }
      .flowOn(Dispatchers.Default)
  }

  /**
   * asFlow operator
   */
  private fun getAsFlow(): Flow<Int> {
    return (1..5).asFlow()
      .onEach { delay(300) }
      .flowOn(Dispatchers.Default)
  }

  /**
   * channelFlow operator
   */
  @ExperimentalCoroutinesApi
  private fun getChannelFlow(): Flow<Int> {
    return channelFlow {
      (0..10).forEach {
        send(it)
      }
    }.flowOn(Dispatchers.Default)
  }

  // endregion
}
