object ApplicationId {
    const val id = "com.lounah.kalley"
}

object Modules {
    const val app = ":app"
    const val coreSip = ":core:sip"
    const val coreStorage = ":core:storage"
    const val coreDi = ":core:di"
    const val featureAuth = ":feature:feature-auth"
    const val architecture = ":core:architecture"
}

object Releases {
    const val versionCode = 1
    const val versionName = "0.0.1"
}

object Versions {
    const val gradle = "3.4.2"
    const val compileSdk = 29
    const val minSdk = 21
    const val targetSdk = 29
    const val appcompat = "1.0.2"
    const val design = "1.1.0-alpha06"
    const val recyclerview = "1.0.0"
    const val ktx = "1.0.0-alpha1"
    const val ktLint = "0.24.0"
    const val kotlin = "1.3.41"
    const val rxJava = "2.1.1"
    const val rxKotlin = "2.4.0"
    const val rxRelay = "2.1.1"
    const val rxRedux = "1.0.1"
    const val retrofit = "2.6.1"
    const val gson = "2.8.6"
    const val okhttp = "4.2.1"
    const val constraintLayout = "2.0.0-beta4"
}

object Libraries {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val ktx = "androidx.core:core-ktx:${Versions.ktx}"
    const val rxJava = "io.reactivex.rxjava2:rxandroid:${Versions.rxJava}"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"
    const val rxAndroid = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    const val rxRelay = "com.jakewharton.rxrelay2:rxrelay:${Versions.rxRelay}"
    const val rxRedux = "com.freeletics.rxredux:rxredux:${Versions.rxRedux}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
}

object SupportLibraries {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val design = "com.google.android.material:material:${Versions.design}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
}
