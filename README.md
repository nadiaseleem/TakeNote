# TakeNote ✓

a note-taking android application designed to help users easily store, categorize, filter and search
for their notes.

# 💡 Preview

download app apk : https://www.mediafire.com/file/bix85vs0pphjitx/TakeNote.apk/file



<p align="center">
  <img src="https://user-images.githubusercontent.com/37695970/231618614-8f295eca-ef55-4095-8b75-7d2ce65ea2ee.png" width="279" height="600">
  <img src="https://user-images.githubusercontent.com/37695970/231618625-99bba668-89bb-426c-bccb-74cf838037d8.png" width="279" height="600">
  <img src="https://user-images.githubusercontent.com/37695970/231618636-35d3b597-6ebc-4748-8ffc-0366340c7e99.png" width="279" height="600">
  <img src="https://user-images.githubusercontent.com/37695970/231618645-bc463356-a37a-4fa0-ba85-3c260366f5fa.png" width="279" height="600">
  <img src="https://user-images.githubusercontent.com/37695970/231618654-1c85c715-9f9a-4588-a714-002d9464c7af.png" width="279" height="600">
  <img src="https://user-images.githubusercontent.com/37695970/231618667-110def26-c302-4803-b61e-3a38978b60df.png" width="279" height="600">


</p>

# 🌟 Libraries and technologies used

- Navigation component: to manage navigation within the app and creating a single activity that contains multiple fragments, rather than creating multiple activities.

- Room: to save data (notes) in a local database on the user's device. This can be useful for caching data or for offline functionality.

- MVVM: help in saperating the logic code from the views. This makes the app easier to maintain and update.

- Coroutines: a library for asynchronous programming in Kotlin that is used to handle asynchronous background threading, such as network requests or database operations, without blocking the main thread or causing performance issues.

- StateFlow: a library for reactive programming in Kotlin that is used to create a stream of data that can be observed and updated in real-time,it is useful for managing the state of the app's UI, since it allows changes to the data to be propagated to any observers automatically. 

- Dagger Hilt: for dependency injection to simplify the app's architecture and make it easier to manage dependencies between different components.

- view binding: to automates the process of inflating views in the app's UI instead of manually inflating views in code.
