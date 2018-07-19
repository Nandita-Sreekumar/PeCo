# PeCo
Personal and Private Work Manager

## To make activity support theme

1. Globally declare following variables

#### Kotlin
```kotlin
private lateinit var themeSupport: ThemeSupport
```

#### Java
```java
private ThemeSupport themeSupport
```


2. Before `setContentView(R.layout.<activity_xml>)` in function `onCreateView()`

#### Kotlin
```kotlin
themeSupport = ThemeSupport(this) // Context of the activity

/* Set accent color and Night mode as per pref */
themeSupport.setTheme()
themeSupport.nightMode(delegate) // Object of AppCompatDelegate of the current activity

```

#### Java
```java
themeSupport = ThemeSupport(this); // Context of the activity

/* Set accent color and Night mode as per pref */
themeSupport.setTheme();
themeSupport.nightMode(getDelegate()); // Object of AppCompatDelegate of the current activity
```

## To make fragment support theme

1. Globally declare following variables

#### Kotlin
```kotlin
private lateinit var themeSupport: ThemeSupport
```

#### Java
```java
private ThemeSupport themeSupport;
```


2. Before `setContentView(R.layout.<activity_xml>)` in function `onCreate()`

#### Kotlin
```kotlin
themeSupport = ThemeSupport(this) // Context of the current Fragment

/* Set accent color and Night mode as per pref */
themeSupport = ThemeSupport(this.activity!!.applicationContext) // Current FragmentActivity Object
themeSupport.setFragTheme(this.activity!!)

```

#### Java
```java
themeSupport = ThemeSupport(this); // Context of the current Fragment

/* Set accent color and Night mode as per pref */
themeSupport = ThemeSupport(this.getActivity().getApplicationContext()); // Current FragmentActivity Object
themeSupport.setFragTheme(this.getActivity());
```
