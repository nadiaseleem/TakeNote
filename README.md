# EasyFood

an android application that simplifies the cooking process by presenting users with a diverse
selection of food options from various categories. The app provides comprehensive instructions for
preparing the selected dish, accompanied by a helpful video tutorial.

# 💡 Preview

download app apk : https://www.mediafire.com/file/r27yhrexk7203iw/EasyFood.apk/file

<p align="center">
  <img src="https://user-images.githubusercontent.com/37695970/231596964-bf7bbc1c-00f7-4c2e-b80e-b67efa89e5cb.png" width="279" height="600">
  <img src="https://user-images.githubusercontent.com/37695970/231597100-84ccf5e4-9673-494a-a350-34466a76232f.png" width="279" height="600">
  <img src="https://user-images.githubusercontent.com/37695970/231596982-3c17e5bf-903e-4c46-98d9-e25d4a3ba79e.png" width="279" height="600">
  <img src="https://user-images.githubusercontent.com/37695970/231597109-19e8eb98-3a04-4f89-b54c-d72e635847f7.png" width="279" height="600">
  <img src="https://user-images.githubusercontent.com/37695970/231596994-4a4f19f3-63e8-4326-b69b-220c93c0eba8.png" width="279" height="600">
  <img src="https://user-images.githubusercontent.com/37695970/231597011-bb0c5693-4453-4a9c-9c3a-bb73bdad4546.png" width="279" height="600">
  <img src="https://user-images.githubusercontent.com/37695970/231597051-36f6e0fb-11ce-4e60-babe-9b5e7e05d1d9.png" width="279" height="600">
  <img src="https://user-images.githubusercontent.com/37695970/231597083-e430dd86-1a83-4807-bcf8-e61552950c46.png" width="279" height="600">

</p>

# 🌟 Libraries and technologies used

- Navigation component : one activity contains multiple fragments instead of creating multiple
  activites.
- Retrofit : making HTTP connection with the rest API and convert meal json file to Kotlin/Java
  object.
- Room : Save meals in local database.
- MVVM & LiveData : Saperate logic code from views and save the state in case the screen
  configuration changes.
- RxKotlin : for asynchrounos background threading.
- Dagger Hilt: for dependency injection.
- view binding : instead of inflating views manually view binding will take care of that.
- Glide : Catch images and load them in imageView.