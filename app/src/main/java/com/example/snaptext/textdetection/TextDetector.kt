package com.example.snaptext.textdetection

import android.graphics.Bitmap
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.text.Text

internal interface TextDetector {

    fun detectText(imageBitmap: Bitmap): Task<Text>
}