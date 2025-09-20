package com.mpt.rua_java.presentation.util;

import static org.junit.jupiter.api.Assertions.*;

import android.graphics.Bitmap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests unitarios para QRCodeGenerator
 * Verifica la funcionalidad de generación de códigos QR funcionales
 */
public class QRCodeGeneratorTest {

    private QRCodeGenerator qrGenerator;

    @BeforeEach
    void setUp() {
        qrGenerator = new QRCodeGenerator();
    }

    @Test
    void generateQRCode_shouldReturnBitmap_whenValidDataProvided() {
        // Given
        String testData = "TEST DATA FOR QR";
        int width = 200;
        int height = 200;

        // When
        Bitmap result = qrGenerator.generateQRCode(testData, width, height);

        // Then
        assertNotNull(result, "QR code should be generated successfully");
        assertEquals(width, result.getWidth(), "Width should match requested size");
        assertEquals(height, result.getHeight(), "Height should match requested size");
    }

    @Test
    void generateContactQR_shouldReturnBitmap_whenValidVCardDataProvided() {
        // Given
        String vCardData = "BEGIN:VCARD\n" +
                "VERSION:3.0\n" +
                "FN:John Doe\n" +
                "EMAIL:john@example.com\n" +
                "TEL:+1234567890\n" +
                "END:VCARD";

        // When
        Bitmap result = qrGenerator.generateContactQR(vCardData);

        // Then
        assertNotNull(result, "Contact QR should be generated successfully");
        assertEquals(512, result.getWidth(), "Default width should be 512");
        assertEquals(512, result.getHeight(), "Default height should be 512");
    }

    @Test
    void generateContactQR_withCustomSize_shouldReturnCorrectSize() {
        // Given
        String vCardData = "BEGIN:VCARD\nVERSION:3.0\nFN:Test User\nEND:VCARD";
        int customSize = 300;

        // When
        Bitmap result = qrGenerator.generateContactQR(vCardData, customSize);

        // Then
        assertNotNull(result, "Custom size QR should be generated");
        assertEquals(customSize, result.getWidth(), "Width should match custom size");
        assertEquals(customSize, result.getHeight(), "Height should match custom size");
    }

    @Test
    void generateQRCode_shouldHandleEmptyData() {
        // Given
        String emptyData = "";

        // When
        Bitmap result = qrGenerator.generateQRCode(emptyData, 100, 100);

        // Then
        // ZXing puede manejar datos vacíos, así que debería retornar un bitmap válido
        assertNotNull(result, "Should handle empty data gracefully");
    }
}