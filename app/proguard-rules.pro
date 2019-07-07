# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-keepattributes Exceptions, InnerClasses, Signature, Deprecated, SourceFile, LineNumberTable, *Annotation*, EnclosingMethod

# koin
-keep public class * extends java.lang.Exception
-keepclassmembernames class kotlinx.** {
    volatile <fields>;
}

-keep class com.google.android.gms.vision.** { *; }
-keep interface com.google.android.gms.vision.** { *; }
-keep interface com.google.android.gms.common.** { *; }

-keepclasseswithmembernames class * {
    native <methods>;
}

#https://github.com/zhihu/Matisse
-dontwarn com.squareup.picasso.**
-keep class com.squareup.picasso.** {*;}
-dontwarn com.zhihu.matisse.engine.impl.**
-keep class com.zhihu.matisse.engine.impl.** {*;}
-dontwarn com.bumptech.glide.**
-keep class com.bumptech.glide.** {*;}
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-keep class org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement {*;}
#https://github.com/zhihu/Matisse

# OkHttp3
-dontwarn okhttp3.internal.platform.ConscryptPlatform
-dontwarn org.codehaus.mojo.animal_sniffer.*
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

-dontwarn ren.yale.android.retrofitcachelibrx2.**
-keep class ren.yale.android.retrofitcachelibrx2.** { *; }
-keepclasseswithmembernames class  retrofit2.adapter.rxjava2.BodyObservable { *; }
-keepclasseswithmembernames class  retrofit2.adapter.rxjava2.ResultObservable { *; }
-keepclasseswithmembernames class  retrofit2.adapter.rxjava2.CallEnqueueObservable { *; }
-keepclasseswithmembernames class  retrofit2.adapter.rxjava2.CallExecuteObservable { *; }
-keepclasseswithmembernames class retrofit2.Retrofit { *; }
-keepclasseswithmembernames class retrofit2.ServiceMethod { *; }
-keepclasseswithmembernames class retrofit2.OkHttpCall { *; }
-dontwarn javax.annotation.**
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

#renderscript
-keepclasseswithmembernames class * {
native <methods>;
}
-keep class androidx.renderscript.** { *; }

# Gson specific classes
-dontwarn sun.misc.**
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Google Play Services library
-keep class * extends java.util.ListResourceBundle {
    protected Object[][] getContents();
}
-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    public static final *** NULL;
}
-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
}
-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

# enum
-keepclassmembers enum * { *; }

# Logback and slf4j-api
-dontwarn ch.qos.logback.core.net.*
-dontwarn org.slf4j.**
-keep class ch.qos.** { *; }
-keep class org.slf4j.** { *; }

# Okio
-dontwarn okio.**

# Joda-Time
-dontwarn org.joda.time.**
-keep class org.joda.time.** { *; }

-keep class com.google.android.gms.ads.identifier.** { *; }
