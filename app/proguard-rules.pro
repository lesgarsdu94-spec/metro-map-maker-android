# Add project specific ProGuard rules here.
# You can control the optimization level using the rules inside this file.

# 1. Jetpack Compose & Kotlin Specific Rules
-keepclassmembers class * {
    @androidx.compose.runtime.Composable *** *(...);
}

# Prevents shrinking of Compose runtime internals
-keep class androidx.compose.runtime.** { *; }

# 2. Retain Essential Android & Architecture Components
-keepclassmembers class * extends androidx.lifecycle.ViewModel {
    <init>(...);
}

# 3. Maintain Line Numbers for Debugging/Crash Logs
# This keeps stack traces readable if the app crashes in a release build, 
# while still obfuscating your actual business logic/package naming.
-keepattributes SourceFile,LineNumberTable

# 4. Suppress warnings from common external dependencies if they pop up
-dontwarn kotlin.**
-dontwarn androidx.**
