package com.lixinxin.composedemo.ui.screen.canvas

import android.graphics.LinearGradient
import android.graphics.Rect
import android.graphics.Shader
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CanvasDemo01 (){


    Canvas(modifier = Modifier.fillMaxSize()) {

        drawIntoCanvas {

            val paint = Paint()
            paint.style = PaintingStyle.Fill
            paint.color = Color.Red


            //坐标转成数学坐标
            it.scale(1f, -1f)
            it.translate(0f, -size.height)

            //距离左边屏幕距离
            val marginToLeft = 180f
            //距离屏幕下边距离
            val marginToBottom = 240f

            it.translate(marginToLeft, marginToBottom)
            // it.drawCircle(Offset(100f, 100f), 100f, paint)


            //2.平行x轴线
            val line_paint = Paint()
            line_paint.strokeWidth = 2f
            line_paint.style = PaintingStyle.Stroke
            line_paint.color = Color.Gray
            //x轴距离右边也留80距离
            val x_scaleWidth = (size.width - marginToLeft - 80f)
            val onePath = Path()
            onePath.lineTo(x_scaleWidth, 0f)
            //  it.drawPath(onePath, line_paint)

            drawXLine(x_scaleWidth, 180f, 2f, it)

            drawTextDownX(x_scaleWidth, 180f, 2f, it, line_paint)

            drawTextOfYLeft(x_scaleWidth, 180f, 2f, it)


            drawCubic(
                x_scaleWidth, 180f, 2f,
                arrayListOf(0, 1200, 700, 100, 1500, 700, 0), it
            )

        }




    }


}

private fun DrawScope.drawXLine(
    x_scaleWidth: Float,
    marginToLeft: Float,
    grid_width: Float,
    canvas: androidx.compose.ui.graphics.Canvas
) {
    var x_scaleWidth1 = x_scaleWidth
    var grid_width1 = grid_width
    val line_paint = Paint()
    line_paint.strokeWidth = 2f
    line_paint.style = PaintingStyle.Stroke
    line_paint.color = Color(188, 188, 188, 100)
    //x轴距离右边也留80距离
    x_scaleWidth1 = (size.width - marginToLeft - 80f)
    grid_width1 = x_scaleWidth1 / 6

    val onePath = Path()
    //添加从当前点到给定点的直线段
    // onePath.lineTo(x_scaleWidth1, 0f)
    // canvas.drawPath(onePath, line_paint)

    (0 until 4).forEach { index ->
        val mOnePath = Path()
        mOnePath.moveTo(0f, index * (grid_width1 - 40f))
        mOnePath.lineTo(x_scaleWidth1, (index * (grid_width1 - 40f)))
        canvas.drawPath(mOnePath, line_paint)
    }

    //  canvas.save()

    //通过平移画布绘制剩余的平行x轴线
//    (0 until 3).forEach { index ->
//        canvas.translate(0f, grid_width1 - 40f)
//        canvas.drawPath(onePath, line_paint)
//    }
    // canvas.restore()
}


fun DrawScope.drawTextDownX(
    x_scaleWidth: Float,
    marginToLeft: Float,
    grid_width: Float,
    canvas: Canvas,
    paint: Paint
) {
    var x_scaleWidth1 = x_scaleWidth
    var grid_width1 = grid_width
    x_scaleWidth1 = (size.width - marginToLeft - 80f)
    grid_width1 = x_scaleWidth1 / 6
    val text_paint = android.graphics.Paint()
    text_paint.strokeWidth = 2f
    text_paint.style = android.graphics.Paint.Style.STROKE
    text_paint.color = android.graphics.Color.GRAY
    text_paint.textSize = 19f

    val rectText = Rect()
    canvas.save()
    //将文字旋转摆正，此时坐标系y向下是正
    canvas.scale(1f, -1f)
    (0 until 7).forEach { index ->
        if (index > 0) {
            Log.e("weima?", "MyCureView: " + grid_width1)
            canvas.nativeCanvas.translate(grid_width1, 0f)
        }
        val strTx = "11.${11 + index}"
        text_paint.getTextBounds(strTx, 0, strTx.length, rectText)
        canvas.nativeCanvas.drawText(
            strTx,
            -rectText.width().toFloat() / 2,
            rectText.height().toFloat() * 2.5f,
            text_paint
        )
    }
    canvas.restore()
}

private fun DrawScope.drawTextOfYLeft(
    x_scaleWidth: Float,
    marginToLeft: Float,
    grid_width: Float,
    canvas: Canvas
) {
    var x_scaleWidth1 = x_scaleWidth
    var grid_width1 = grid_width
    val text_paint = android.graphics.Paint()
    text_paint.strokeWidth = 2f
    text_paint.style = android.graphics.Paint.Style.STROKE
    text_paint.color = android.graphics.Color.GRAY
    text_paint.textSize = 19f

    x_scaleWidth1 = (size.width - marginToLeft - 80f)
    grid_width1 = x_scaleWidth1 / 6

    val rectText = Rect()
    canvas.save()
    //将文字旋转摆正，此时坐标系y向下是正
    (0 until 4).forEach { index ->
        if (index > 0) {
            canvas.translate(0f, grid_width1 - 40f)
        }
        var strTx = ""
        if (index == 0) {
            strTx = "${index}"
        } else if (index == 1) {
            strTx = "${500}"
        } else if (index == 2) {
            strTx = "1k"
        } else {
            strTx = "1.5k"
        }

        canvas.save()
        canvas.scale(1f, -1f)
        text_paint.getTextBounds(strTx, 0, strTx.length, rectText)
        canvas.nativeCanvas.drawText(
            strTx,
            -rectText.width().toFloat() - 42f,
            rectText.height().toFloat() / 2,
            text_paint
        )
        canvas.restore()
    }
    canvas.restore()
}


private fun DrawScope.drawCubic(
    x_scaleWidth: Float,
    marginToLeft: Float,
    grid_width: Float,
    dataList: ArrayList<Int>,
    canvas: Canvas
) {
    var x_scaleWidth1 = x_scaleWidth
    var grid_width1 = grid_width
    x_scaleWidth1 = (size.width - marginToLeft - 80f)
    grid_width1 = x_scaleWidth1 / 6
    val text_paint = android.graphics.Paint()
    text_paint.strokeWidth = 2f
    text_paint.style = android.graphics.Paint.Style.FILL
    text_paint.color = android.graphics.Color.argb(100, 111, 111, 111)

    val caves_path = android.graphics.Path()
    //500=grid_width-40 每个单位的长度的=像素长度
    val danweiY = (grid_width1 - 40) / 500
    val danweiX = (grid_width1)
    val linearGradient = LinearGradient(
        0f, 1500 * danweiY,
        0f,
        0f,
        android.graphics.Color.argb(255, 229, 160, 144),
        android.graphics.Color.argb(255, 251, 244, 240),
        Shader.TileMode.CLAMP
    )
    text_paint.shader = linearGradient

    caves_path.moveTo(0f,0f)

    for (index in 0 until dataList.size - 1) {
        val xMoveDistance = 20
        val yMoveDistance = 40

        if (dataList[index] == dataList[index + 1]) {
            caves_path.lineTo(danweiX * (index + 1), 0f)
        } else if (dataList[index] < dataList[index + 1]) {//y1<y2情况
            val centerX = (grid_width1 * index + grid_width1 * (1 + index)) / 2
            val centerY =
                (dataList[index].toFloat() * danweiY + dataList[index + 1].toFloat() * danweiY) / 2
            val controX0 = (grid_width1 * index + centerX) / 2
            val controY0 = (dataList[index].toFloat() * danweiY + centerY) / 2
            val controX1 = (centerX + grid_width1 * (1 + index)) / 2
            val controY1 = (centerY + dataList[index + 1].toFloat() * danweiY) / 2
            caves_path.cubicTo(
                controX0 + xMoveDistance,
                controY0 - yMoveDistance,
                controX1 - xMoveDistance,
                controY1 + yMoveDistance,
                grid_width1 * (1 + index),
                dataList[index + 1].toFloat() * danweiY
            )
        } else {
            val centerX = (grid_width1 * index + grid_width1 * (1 + index)) / 2
            val centerY =
                (dataList[index].toFloat() * danweiY + dataList[index + 1].toFloat() * danweiY) / 2
            val controX0 = (grid_width1 * index + centerX) / 2
            val controY0 = (dataList[index].toFloat() * danweiY + centerY) / 2
            val controX1 = (centerX + grid_width1 * (1 + index)) / 2
            val controY1 = (centerY + dataList[index + 1].toFloat() * danweiY) / 2
            caves_path.cubicTo(
                controX0 + xMoveDistance,
                controY0 + yMoveDistance,
                controX1 - xMoveDistance,
                controY1 - yMoveDistance,
                grid_width1 * (1 + index),
                dataList[index + 1].toFloat() * danweiY
            )

        }
    }
    canvas.nativeCanvas.drawCircle(0f, 0f, 10f, text_paint)
    //绘制闭合渐变曲线
    canvas.nativeCanvas.drawPath(caves_path, text_paint)
    val line_paint = android.graphics.Paint()
    line_paint.strokeWidth = 3f
    line_paint.style = android.graphics.Paint.Style.STROKE
    line_paint.color = android.graphics.Color.argb(255, 212, 100, 77)
    //绘制外环红色线
    canvas.nativeCanvas.drawPath(caves_path, line_paint)
    line_paint.style = android.graphics.Paint.Style.FILL
    //画圈。
    for (index in 0 until dataList.size) {
        canvas.nativeCanvas.drawCircle(
            grid_width1 * index,
            danweiY * dataList[index],
            8f,
            line_paint
        )
    }
}



@Composable
@Preview
fun CanvasDemo01Preview() {
    CanvasDemo01()
}