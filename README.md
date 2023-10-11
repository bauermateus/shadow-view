# ShadowView
\
A simple Android library that applies shadows of any color to views and allows easy manipulation of edges.
ComplexView now supports shadow positioning starting from v1.1

\
\
\
\
\
\
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
results in: 
    
