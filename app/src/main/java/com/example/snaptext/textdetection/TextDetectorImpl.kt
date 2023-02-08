package com.example.snaptext.textdetection

import android.graphics.Bitmap
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

internal class TextDetectorImpl : TextDetector {

    override fun detectText(imageBitmap: Bitmap): Task<Text> {
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
        val image = InputImage.fromBitmap(imageBitmap, 0)
        return recognizer.process(image)
    }
}