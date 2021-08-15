package com.yk.marvelcomics.common

import android.widget.ImageView
import coil.load

fun ImageView.load(url: String?) {
    url?.let {
        load(uri = url)
    }
}
