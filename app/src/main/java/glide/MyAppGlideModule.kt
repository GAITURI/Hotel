package com.example.hotel.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import data.ImageSize
import okhttp3.OkHttpClient
import java.io.InputStream

@GlideModule
class MyAppGlideModule: AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) { super.applyOptions(context, builder)
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)
        val client= OkHttpClient()
        registry.append(ImageSize::class.java, InputStream::class.java, ImageSizeModelLoader.Factory(client))
    }
}