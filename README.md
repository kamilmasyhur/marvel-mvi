# Marvel MVI

Created Android App using Marvel API to learn about MVI (Model-View-Intent) Architecture.

![Screen Shot 2021-09-26 at 11 34 36 AM](https://user-images.githubusercontent.com/18300552/134793853-a948b77a-4eeb-4f8a-926c-f160819caf89.png)
![image](https://user-images.githubusercontent.com/18300552/134793581-21d3748d-3e72-4781-9aa3-ef0c916c7dcf.png)
![image](https://user-images.githubusercontent.com/18300552/134793861-6e7c0423-9369-4e66-83d2-1f1520836b01.png)

## What is MVI?

MVI stands for Model-View-Intent. MVI works in a very different way compared to its distant relatives, MVC, MVP or MVVM. 
The role of each MVI components is as follows:

* **Model** represents a state. Models in MVI should be immutable to ensure a unidirectional data flow between them and the other layers in your architecture.
* Like in MVP, Interfaces in MVI represent **Views**, which are then implemented in one or more Activities or Fragments.
* **Intent** represents an intention or a desire to perform an action, either by the user or the app itself. For every action, a View receives an Intent. The Presenter observes the Intent, and Models translate it into a new state.

## Getting Started

### Prerequisite

* Android Studio
  Download [here](https://developer.android.com/studio) 
* Gradle 7.0.2  
* Register Marvel API, [here](https://developer.marvel.com/).

### Built with

What we used here:
* Kotlin
* RxJava2
* Retrofit
* Coil
* Hilt

### Project Setup
1. Getting API Key from Marvel API
2. Open or create `gradle.properties` file in your root project.
3. Edit to put your API key:
```
org.gradle.jvmargs=-Xmx2048m -Dfile.encoding=UTF-8
android.useAndroidX=true
android.enableJetifier=true
kotlin.code.style=official
base_url="https://gateway.marvel.com:443/"
public_key="public_key_here"
private_key="private_key_here"
```
4. Rebuild or Re-open the project.

### How to start?

Start learning from these branches:
* `master` Full code implementation.
* [`section-1-home-page`](https://github.com/kamilmasyhur/marvel-mvi/tree/section-1-home-page) contains MVI implementation at home page. 
* [`section-2-detail-page`](https://github.com/kamilmasyhur/marvel-mvi/tree/section-2-detail-page) contains MVI implementation at detail page.

## Authors

* Ahmad Kamil Almasyhur
  [@kamilmasyhur](https://github.com/kamilmasyhur)
* Danviero Yuzwan 
  [@yuzwan19](https://github.com/Yuzwan19)
