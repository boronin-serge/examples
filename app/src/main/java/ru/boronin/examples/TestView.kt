package ru.boronin.examples

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.View

class TestView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet,
  defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

  private val TAG = "TestView"

  init {
    Log.d(TAG, "constructor")
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    Log.d(TAG, "onAttachedToWindow")
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    Log.d(TAG, "onMeasure")
  }

  override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
    super.onLayout(changed, left, top, right, bottom)
    Log.d(TAG, "onLayout")
  }

  override fun draw(canvas: Canvas?) {
    Log.d(TAG, "draw")
    super.draw(canvas)
  }

  override fun onDraw(canvas: Canvas?) {
    Log.d(TAG, "onDraw")
    super.onDraw(canvas)
  }

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    Log.d(TAG, "onDetachedFromWindow")
  }

  override fun onFinishInflate() {
    super.onFinishInflate()
    Log.d(TAG, "onFinishInflate")
  }
}
