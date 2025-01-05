package com.example.captureit.commonHelpers

import coil3.ImageLoader
import coil3.PlatformContext
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.request.CachePolicy
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.example.captureit.utils.Constants.DISK_CACHE_FILE_NAME
import com.example.captureit.utils.Constants.DISK_CACHE_MAX_SIZE_PERCENT
import com.example.captureit.utils.Constants.MEMORY_CACHE_MAX_SIZE_PERCENT
import okio.FileSystem

fun getAsyncImageLoader(context: PlatformContext): ImageLoader {
    return ImageLoader.Builder(context)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .memoryCache {
            MemoryCache.Builder()
                .maxSizePercent(context, MEMORY_CACHE_MAX_SIZE_PERCENT)
                .strongReferencesEnabled(true)
                .build()
        }
        .diskCachePolicy(CachePolicy.ENABLED)
        .diskCache {
            DiskCache.Builder()
                .maxSizePercent(DISK_CACHE_MAX_SIZE_PERCENT)
                .directory(FileSystem.SYSTEM_TEMPORARY_DIRECTORY/ DISK_CACHE_FILE_NAME)
                .build()
        }
        .logger(DebugLogger())
        .crossfade(true)
        .build()
}