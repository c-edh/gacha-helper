# gacha-helper

developers: 
* **Neha Choudhry**
* **Corey Edh**
* **Dhominic Abenes**
* **Sarah Dueltgen**

required software: 
* [x] Android Studio
* [x] Github
* [x] Firebase
* [x] Java

What is in the app:
* Homescreen
* Login and Sign Up
* Create Build
* Level Up
* Farm Artifact

## Classes explain
* CreateBuild: This pulls data about artifacts and their stat chances, it uses this data to create the user a build
* FarmArtifact: This pulls data about each artifact and their stats, and runs calculations on how many resin (how many tries it has to loop through) to get the user's desire result
* 

## Using the App
* Make an account with Sign Up, make sure the password is longer than 6 characters
and that you enter in a valid email
* If you already have an account, then press login and enter your login
* After you login/sign up you will be presented to the menu navigator, using this you can navigate to the following:
	* Farm Artifact
	* Create Build
	* View Saved Build
	* Level Up
	* Recommend
* Farm Artifact: this allows you to know how many Resin it takes to get an Artifact with a certain stat.
to use it. Press on the artifact you want to farm, then stats will show up to the right, press one of those stats (you will not get a visual that it's selected but it is selected. Then once you select you stat you press Farm button. This will run the calculations and you get to see the Resin amount above the button.
* Create Build: This allows you to create a build and upload to Firebase. Once you are on Create a build press the artifacts you want, the limit is 5. You get to see what Artifacts were selected and what stats were chosen with it above the create build. Once you select all the stats you want, you press create build this sends the build in firebase and stores it.

## Walkthroughs
<img src='gacha_homescreen.png' title='Homescreen' alt='Homescreen' />

<img src='gacha_login.png' title='Login Page' alt="Login page" />

<img src='gacha_signup.png' title='Sign Up Page' alt='Sign Up page' />

<img src='createbuild.gif' title='Create Build' alt='Showing the function of Create Build' />

<img src='FarmArtifact.gif' title='Artifact Farm Demo' alt='showing how to use artifact farm' />
