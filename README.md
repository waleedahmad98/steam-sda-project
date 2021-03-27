# Steam SDA Project
A Steam replica software designed to manage a library of games as a project for Software Design &amp; Analysis course.

### About The Database
This project uses MongoDB Cloud as its database, storing users, game information and the user-game mappings. The store is a pre-set document in the database that contains the information of games which a user interacts with. Since the database stores JSON objects, they are mapped into POJO at each fetch with respect to each class. For dependency inversion, there are classes which provide exclusive layer functionalities and act as getters and setters of that layer. For the text database, we used .json files that store information similar to the other database. These JSON objects are serialized/deserialized as well into Java Objects using Google's GSON library.

### Use Cases
* ViewWallet
* BuyGame
* DownloadGame
* LaunchGame
* RemoveGame
* ViewStore
* ViewLibrary
* GetGameInfo
* CreateAccount
* UpdateWallet
* BrowseGames
* DisplayUserInfo
