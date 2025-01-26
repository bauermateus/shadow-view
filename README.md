# ShadowView

<br>

A simple Android library that draw shadows of any color around child views.

<br>
<br>

# Gradle setup
``` gradle
repositories {
    maven { 
        setUrl("https://jitpack.io")
    }
}

dependencies {

implementation 'com.github.bauermateus:shadow-view:0.2'
    
}
```

# Usage

ShadowView acts like a FrameLayout, so it´s preferred if you place only one View inside each ShadowView. CardView is recommended if you want to use cornerRadius more easily. Example:

``` xml
<com.mbs.shadow_view.ShadowView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:radius="12dp"
        app:shadowAlpha="200"
        app:shadowColor="#FF5722"
        app:shadowPosition="center"
        app:shadowSpread="3"
        tools:ignore="SpeakableTextPresentCheck">

        <com.google.android.material.card.MaterialCardView
            android:layout_width="100dp"
            android:layout_height="100dp"
            android:src="@color/black"
            app:cardBackgroundColor="@color/black"
            app:cardCornerRadius="12dp"
            app:strokeColor="#FF5722"
            app:strokeWidth="1dp" />

    </com.mbs.shadow_view.ShadowView>
```

<br>

results in: 

<br>

<img src="https://github.com/bauermateus/shadow-view/assets/11887846/0d31cfae-8f1a-40f1-8795-c528573f6b89" width="300">

