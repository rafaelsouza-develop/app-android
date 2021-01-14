package br.com.app4pets.app.util.extensions

import android.content.Context
import android.graphics.*
import android.widget.ImageView


fun ImageView.selectImage(context: Context){

}


fun Bitmap.getRoundedBitmap(): Bitmap {
    val circleBitmap =
        Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888)
    val shader = BitmapShader(this, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
    val paint = Paint()
    paint.shader = shader
    paint.isAntiAlias = true
    val c = Canvas(circleBitmap)
    c.drawCircle((this.width / 2).toFloat(),
        (this.height / 2).toFloat(), (this.width / 2).toFloat(), paint)
    return circleBitmap
}