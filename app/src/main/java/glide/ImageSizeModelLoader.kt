package com.example.hotel.glide

import android.util.Log
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.data.DataFetcher
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import com.bumptech.glide.signature.ObjectKey
import data.ImageSize
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import java.io.InputStream

class ImageSizeModelLoader(private val client:OkHttpClient):ModelLoader<ImageSize, InputStream> {
    override fun buildLoadData(
        model: ImageSize,
        width: Int,
        height: Int,
        options: Options
    ): ModelLoader.LoadData<InputStream>? {
        val key = ObjectKey(model)
        val dataFetcher = ImageSizeDataFetcher(model, client)
        return ModelLoader.LoadData(key, dataFetcher)
    }

    override fun handles(model: ImageSize): Boolean {
        return true
    }


    class ImageSizeDataFetcher(private val model: ImageSize, private val client: OkHttpClient) :
        DataFetcher<InputStream> {
        private var stream: InputStream? = null
        private var call: Call? = null
        override fun loadData(
            priority: Priority,
            callback: DataFetcher.DataCallback<in InputStream>
        ) {
            val url = model.sm
            if (url == null) {
                val error= IOException("URL is null")
                Log.e("ImageSizeModelLoader", "Error loading image: $error")
                callback.onLoadFailed(error)
                return
            }
            val request= Request.Builder().url(url).build()
            call= client.newCall(request)
            call?.enqueue(object:Callback{
                override fun onFailure(call: Call, e: java.io.IOException) {
                    Log.e("ImageSizeModelLoader", "Error loading image: $url",e)
                    callback.onLoadFailed(e)
                }

                override fun onResponse(call: Call, response: Response) {
                   if (response.isSuccessful) {
                       stream = response.body?.byteStream()
                       stream?.let {
                           callback.onDataReady(it)
                       } ?: run {
                           val error = IOException("Response body is null for: $url")
                           Log.e("ImageSizeModelLoader", "Response body is null for: $url", error)
                           callback.onLoadFailed(error)
                       }
                   }else{
                       val error = IOException("Request failed with code: ${response.code} for: $url")
                       Log.e("ImageSizeModelLoader", "Request failed with code: ${response.code} for: $url")
                       callback.onLoadFailed(error)
                   }
                }

            } )


        }

        override fun cleanup() {
            stream?.close()
        }

        override fun cancel() {
            TODO("Not yet implemented")
        }

        override fun getDataClass(): Class<InputStream> {
            return InputStream::class.java
        }

        override fun getDataSource(): DataSource {
            return DataSource.REMOTE
        }


    }

    class Factory(private val client: OkHttpClient) : ModelLoaderFactory<ImageSize, InputStream> {
        override fun build(multiFactory: MultiModelLoaderFactory): ModelLoader<ImageSize, InputStream> {
            return ImageSizeModelLoader(client)
        }

        override fun teardown() {
        }

    }
}