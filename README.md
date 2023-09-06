

## Task
The included API endpoint returns, amongst other data, an array of news stories (assets).
You are tasked with creating a Phone app that consumes the provided API and displays a list of news articles to the user, ideally in a RecycleView or GridView. Tapping a story should present the asset‚Äôs URL in a WebView (or any other better alternate)

## Requirements

* The list of articles should display at least the following fields:
-- headline
-- theAbstract
-- byLine

* Display latest article first in the list, use article's 'timeStamp'

* If there are related images available for an asset, display the smallest image available for the asset in the cell.

* Images should be loaded asynchronously and cached

* The style of cells is up to you, with necessary padding and layout.

* Use activity and/or fragments where appropriate, but should be adaptable to all Phone screen sizes/rotation.

* Comment your code so it can be understood in six month

* A good unit test coverage is expected as part of solution

* Put some Espresso / Instrument UI tests to verify UI is functional and/or cover some user flow

* Use Android Studio 4 (stable) and Kotlin language, please specify code compilation notes in your submission.

Please feel free to ask if you have any questions when interpreting this document!

## Submission Notes
* Code Compilation instructions; IDE/Plugin versions expected, dependency management
* Short description explaining architecture and logical modules its comprised of (e.g View, ViewModel...)
* Any 3rd party libraries used and rational
* Explain what each test does in comments or in document format
* Any additional features -- apart the requirements given above
* Please either send us solution in zip file or share link to your cloud version control, include above notes in submission.


## Build tools & versions used

- Jetpack Compose
- Coroutines / flows
- Manual Dependency Injection
- Clean Architecture
- MVVM Pattern
- Espresso
- FakeData instead of mockk
- Accessibility support
- Coil
- [Retrofit](https://square.github.io/retrofit/)
- Gson
- Required data models are created with the help of [Json to Kotlin convertor](https://json2kt.com/)

## Steps to run the app

Please clone the code and compile it in [Android Studio](https://developer.android.com/studio).

## What areas of the app did you focus on?

- [Manual DI](https://developer.android.com/codelabs/basic-android-kotlin-compose-add-repository#0)
- Unit test and UI test
- Compose navigation
- Support of config change
- adaptive layouts to support different form-factors.
- defining independent modules
- customizing WebView to enable back button while navigating 

## What was the reason for your focus?

- I know how to use Hilt. This was my first experience with manual dependency injection, seems easier than Hilt/Dagger
- I had to find dependencies first, then inject them to the constructor of proper classes. 
- Another challenge was TestDispatcherRule for replacing the Main dispatcher with a test dispatcher
  before a test.
- Clean architecture makes the app easier to maintain and understand. I tried to follow Clean requirements

## Steps on creating this project?

- First: Creating data, repo and simple UI
- Second: unit test
- Third: Creating composables
- Fourth: UI test
- Fifth: improvements

## What do you think is the weakest part of your project?

- I am not sure if Manual DI is a good choice, when app grows!

## References:

- DI from: https://developer.android.com/codelabs/basic-android-kotlin-compose-add-repository#0
- Navigation bundles from https://github.com/nglauber/JetpackComposePlayground/tree/master

## Nice experience:

- When I was writing unit test for sortByImageWidth() function, I found its sort algorithm is wrong and corrected it.