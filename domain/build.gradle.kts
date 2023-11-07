plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

kotlin {
    jvmToolchain(8)
}

dependencies{
    val retrofitVersion = "2.9.0"

    implementation ("com.squareup.retrofit2:converter-gson:$retrofitVersion")
}