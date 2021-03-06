package com.madrex.edvora.utils

import android.content.Context
import android.graphics.*
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import java.security.MessageDigest


class CircleTransform(context: Context?) : BitmapTransformation() {

    override fun transform(
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap {
        return circleCrop(pool, toTransform)!!
    }

    val id: String
        get() = javaClass.name

    companion object {
        private fun circleCrop(pool: BitmapPool, source: Bitmap?): Bitmap? {
            if (source == null) return null
            val size = Math.min(source.width, source.height)
            val x = (source.width - size) / 2
            val y = (source.height - size) / 2
            val squared = Bitmap.createBitmap(source, x, y, size, size)
            var result: Bitmap? = pool[size, size, Bitmap.Config.ARGB_8888]
            if (result == null) {
                result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
            }
            val canvas = Canvas(result!!)
            val paint = Paint()
            paint.setShader(
                BitmapShader(
                    squared,
                    Shader.TileMode.CLAMP,
                    Shader.TileMode.CLAMP
                )
            )
            paint.setAntiAlias(true)
            val r = size / 2f
            canvas.drawCircle(r, r, r, paint)
            return result
        }
    }
    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        print("")
    }
}