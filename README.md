# Structure

The app is organized using clean architecture and 4 modules: domain (pure kotlin), data,
presentation and app.

# Libraries

The app uses Kotlin flow, view models, view binding, live data, koin (service locator) for
dependency injection via constructors, retrofit with moshi and room.

# Implementation details

The app is not single activity with fragments and androidx navigation because it was built
supporting tablets and phones following the single/multi-activity with fragments technique.

# Other libraries

This app is extremely simple and doesn't include many libraries/techniques. I would like to mention
some interesting ones that I have used and I will include in the future, in this or other projects:
feature modules, coroutines, stateflow, compose, hilt, mockk, detekt, spotless, barista or spek.