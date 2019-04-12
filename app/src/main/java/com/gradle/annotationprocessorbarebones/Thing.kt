package com.gradle.annotationprocessorbarebones

import javax.inject.Inject

class Thing @Inject constructor(
    val string: String
)
