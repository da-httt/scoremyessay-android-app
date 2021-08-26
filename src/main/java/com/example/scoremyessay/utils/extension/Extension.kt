package com.example.scoremyessay.utils.extension

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import com.example.scoremyessay.data.model.orderAttribute.type.OrderType
import com.example.scoremyessay.data.model.orders.OrderItem
import java.io.ByteArrayOutputStream

fun String.convertPriceToUI(): String{
    val sb = StringBuilder(this)

    if(sb.length > 3){
        var index = sb.length - 3
        do{
            sb.insert(index,", " )
            index -= 3
        }while (index > 0)
    }
    sb.append(" VNĐ")
    return sb.toString()
}

fun List<OrderItem>.getTeacherIdSet(): Set<Int> {
    return this.mapNotNull { it.teacher_id }.toSet()
}

fun List<OrderItem>.getOrderIdSet(): Set<Int> {
    return this.map { it.order_id }.toSet()
}

fun List<OrderType>.getNameByID(id: Int): String {
    return this.find {
        it.type_id == id
    }?.type_name ?: "No Type Found"
}

fun List<OrderItem>.getIndexByOrderID(orderId: Int): Int {
    return this.indexOf(this.find { it.order_id == orderId })
    }

fun String.convertBase64ToBitmap(): Bitmap{
    val byteString = Base64.decode(this, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(byteString,0, byteString.size)
}

fun Bitmap.encodeImage(): String {
    val baos = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, 100, baos)
    val b = baos.toByteArray()
    return Base64.encodeToString(b, Base64.DEFAULT)
}

fun Double.roundNe(decimals: Int = 2): Double = "%.${decimals}f".format(this).toDouble()