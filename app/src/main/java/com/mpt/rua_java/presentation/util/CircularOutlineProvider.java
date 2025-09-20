package com.mpt.rua_java.presentation.util;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;

/**
 * Proveedor de contorno circular para ImageView
 * Solución nativa para crear imágenes circulares sin dependencias externas
 */
public class CircularOutlineProvider extends ViewOutlineProvider {

    @Override
    public void getOutline(View view, Outline outline) {
        int diameter = Math.min(view.getWidth(), view.getHeight());
        outline.setOval(0, 0, diameter, diameter);
    }
}
