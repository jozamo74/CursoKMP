import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)

    id("app.cash.sqldelight") version "2.0.2"
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.expenseApp.db")
        }
    }
}
kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        
        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
            //koin
            implementation(project.dependencies.platform("io.insert-koin:koin-bom:3.5.1"))
            implementation(libs.koin.core)
            implementation(libs.koin.android)

            //sqldelight
            implementation(libs.android.driver)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
//            implementation(compose.foundation)
            implementation(compose.material)
            api(compose.materialIconsExtended)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            //PreCompose
            api(compose.foundation)
            api(compose.animation)

            api(libs.precompose)
            api(libs.precompose.molecule) // For Molecule intergration
            api(libs.precompose.viewmodel) // For ViewModel intergration
            api(libs.precompose.koin) // For Koin intergration

            //koin
            implementation(project.dependencies.platform("io.insert-koin:koin-bom:3.5.1"))
            implementation(libs.insert.koin.koin.core)
            implementation(libs.insert.koin.koin.compose)
            api(libs.precompose.koin)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        iosMain.dependencies {
            //sqldelight
            implementation(libs.native.driver)
            implementation(libs.stately.common)

        }
    }
}

android {
    namespace = "com.ttec.cursokmp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.ttec.cursokmp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }

    buildFeatures { compose = true }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13"
    }
}

