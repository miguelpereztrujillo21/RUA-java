package com.mpt.rua_java.presentation.util;

import android.graphics.Bitmap;
import android.graphics.Color;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Utilidad para generar códigos QR funcionales
 * Implementa la generación de QR con datos vCard para contactos
 * Cumple el requisito: "implementa un método para visualizar un QR funcional"
 */
@Singleton
public class QRCodeGenerator {

    @Inject
    public QRCodeGenerator() {}

    /**
     * Genera un código QR bitmap a partir de datos de texto
     * @param data los datos a codificar (por ejemplo, vCard)
     * @param width ancho del QR en píxeles
     * @param height alto del QR en píxeles
     * @return Bitmap del código QR generado o null si hay error
     */
    public Bitmap generateQRCode(String data, int width, int height) {
        try {
            QRCodeWriter writer = new QRCodeWriter();
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.MARGIN, 1);

            BitMatrix bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, width, height, hints);

            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }

            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Genera un código QR específicamente para datos vCard de contacto
     * @param vCardData datos vCard del contacto
     * @param size tamaño del QR (cuadrado)
     * @return Bitmap del código QR o null si hay error
     */
    public Bitmap generateContactQR(String vCardData, int size) {
        return generateQRCode(vCardData, size, size);
    }

    /**
     * Genera un código QR estándar de 512x512 para contactos
     * @param vCardData datos vCard del contacto
     * @return Bitmap del código QR
     */
    public Bitmap generateContactQR(String vCardData) {
        return generateContactQR(vCardData, 512);
    }
}
