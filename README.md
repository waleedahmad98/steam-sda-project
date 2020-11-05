# Steam SDA Project
A Steam replica software designed to manage a library of games as a project for Software Design &amp; Analysis course.

### About
This project uses MongoDB Cloud as its database, storing users, game information and the user-game mappings. The store is a pre-set document in the database that contains the information of games which a user interacts with. Since the database stores JSON objects, they are converted into POJO at each fetch. For dependency inversion, there are classes which provide exclusive layer functionalities and act as getters and setters of that layer.

### Roles
* **Waleed Ahmad (18L-1282)** *Front-end GUI Logic and Database Interaction Logic
* **Zulfiqar Chaudhry (18L-1037)** *Business Layer Logic and Class Logic
* **Daud Mazhar (18L-0919)** *Class Diagram, Front-end and Documentation
* **Sameer Ali (18L-1150)** *Business Layer Logic
