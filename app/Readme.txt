1. This is my output for the assignment. The output includes:
- .apk file, you can install on your phone to play around with it
- The source code which was created by Android Studio 2.1.1

2. Project info:
- My approach for this project is using MVP pattern (model-view-presenter).
This will be very useful later when we write automation testing using some great Testing frameworks (e.g: Espresso)
- In this project, I use some libraries:
+ Android support design (Material Design) for Recycler View
+ Retrolambda for some Java 8 source code
+ Butterknife for injection
+ Piccasso for image loading and catching
+ Retrofit for HTTP communication
+ Rxjava and RxAndroid for applying Reactive
+ PhotoView for image view zooming

3. Notes:
- Texts in this app is customized using Bariol Bold font
- I am using Android Studio 2.1.1, so please download and use the same version
- Some code in this project use Java 8, please make sure you installed Java 8 on your PC
- When click on apiManager category, the app will load 20 photos (~ page),
every time user scrolls to bottom of the list, the app will load next 20 photos