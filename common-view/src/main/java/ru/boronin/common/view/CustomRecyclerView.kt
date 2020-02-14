package ru.boronin.common.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.core.content.withStyledAttributes
import androidx.recyclerview.widget.RecyclerView
import ru.boronin.common.utils.DEFAULT_FLOAT

/**
 * Created by Sergey Boronin on 13.02.2020.
 */
class CustomRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var maskPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG).apply {
        xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }

    private var cornerTopLeftRadius = 0f
    private var cornerTopRightRadius = 0f
    private var cornerBottomLeftRadius = 0f
    private var cornerBottomRightRadius = 0f

    private lateinit var maskBitmap: Bitmap
    private lateinit var offscreenBitmap: Bitmap
    private lateinit var offscreenCanvas: Canvas

    init {
        context.withStyledAttributes(attrs, R.styleable.CustomViewPager) {
            cornerTopLeftRadius = getDimension(R.styleable.CustomRecyclerView_topLeftCornerRadius, DEFAULT_FLOAT)
            cornerTopRightRadius = getDimension(R.styleable.CustomRecyclerView_topRightCornerRadius, DEFAULT_FLOAT)
            cornerBottomLeftRadius = getDimension(R.styleable.CustomRecyclerView_bottomLeftCornerRadius, DEFAULT_FLOAT)
            cornerBottomRightRadius = getDimension(R.styleable.CustomRecyclerView_bottomRightCornerRadius, DEFAULT_FLOAT)
        }
        setWillNotDraw(false)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        offscreenBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        offscreenCanvas = Canvas(offscreenBitmap)
    }

    override fun draw(canvas: Canvas) {
        super.draw(offscreenCanvas)
        if (::maskBitmap.isInitialized.not()) {
            maskBitmap = createMask(width, height)
        }
        offscreenCanvas.drawBitmap(maskBitmap, 0f, 0f, maskPaint)
        canvas.drawBitmap(offscreenBitmap, 0f, 0f, paint)
    }

    private fun createMask(width: Int, height: Int): Bitmap {
        val mask = Bitmap.createBitmap(width, height, Bitmap.Config.ALPHA_8)
        val canvas = Canvas(mask)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = Color.WHITE
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)

        val corners = floatArrayOf(
            cornerTopLeftRadius, cornerTopLeftRadius,
            cornerTopRightRadius, cornerTopRightRadius,
            cornerBottomLeftRadius, cornerBottomLeftRadius,
            cornerBottomRightRadius, cornerBottomRightRadius
        )

        val path = Path()
        path.addRoundRect(RectF(0f, 0f, width.toFloat(), height.toFloat()), corners, Path.Direction.CW)

        canvas.drawPath(path, paint)
        return mask
    }

}