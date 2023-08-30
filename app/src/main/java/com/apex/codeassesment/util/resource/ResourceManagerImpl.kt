package com.apex.codeassesment.util.resource

import android.content.Context
class ResourceManagerImpl(
    private val context: Context
) : ResourceManager {

    override fun getString(res: Int): String {
        return context.getString(res)
    }
}
