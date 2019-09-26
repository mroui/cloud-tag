<p align="center">
  <img src="./src/Assets/Images/icon.png" alt="Cloud Tag logo icon" width="100"/>
</p>
<h1 align="center">
	Cloud Tag
</h1>
> This is an desktop application that allows to create a cloud picture of words found on a chosen website.

## Table of contents
* [Introduction](#introduction)
* [Features](#features)
* [Technologies](#technologies)
* [Screenshots](#screenshots)
* [Setup](#setup)
* [License](#license)

## Introduction
**Cloud Tag** is an application that helps create **your own cloud of words picture**.
</br></br>
**All you need** is a **website URL** that will extract the *words and tags*, and a **picture** that will be *base* of your cloud.
</br></br>
It is available to **customize the settings** according to your preferences, like: *colors*, *words amount* and *length*, *font size* or *space between tags*.
</br></br>
**The great convenience** is to include words that you do not want to use.
</br></br>
The main advantage is a **modern design** of application with **frameless style**. The whole looks **neat and minimalistic**.
</br></br>
It is an **desktop, window application** created in **Java** using **JavaFX** with **JSoup**, **Kumo** and **SLF4J** libraries. Application was tested on *Windows 10 64bit*.

*Project was made for passing the subject in college, but it was so interesting, that I willingly developed it with additional properties.*

## Features
The main purpose is **creating a cloud tag**. 
* Modern, minimalistic design with frameless style
* Retrieve words from any website URL
* Customize settings like: words amount and length, space between them, font size and colors.
* Five-colored tags on clouds
* Set your own base image
* Ignore unwanted words
* Save cloud result

## Technologies
* Java 1.8
* JavaFX
* IntelliJ IDEA 2019.2.2
* [Kumo 1.23](https://github.com/kennycason/kumo)
* [JSoup 1.12.1](https://jsoup.org/)
* SLF4J Api&Simple jar 1.6.1, [here](https://mvnrepository.com/artifact/org.slf4j/slf4j-api/1.6.1) and [here](https://mvnrepository.com/artifact/org.slf4j/slf4j-simple/1.6.1)

## Screenshots
<p align="center">
  <img src="https://i.ibb.co/CPXHhkd/screen1cloudtag.png" alt="Cloud tag page1-2"/>
</p>
<p align="center">
  <img src="https://i.ibb.co/rkMt4x1/screen2cloudtag.png" alt="Cloud tag page3-4"/>
</p>
<p align="center">
  <img src="https://i.ibb.co/GWCByw6/screen3cloudtag.png" alt="Cloud tag page5"/>
</p>

## Setup
* [Install JDK SE 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Install Intellij IDEA](https://www.jetbrains.com/idea/download/)
* [Download Kumo 1.23 or higher jar](https://mvnrepository.com/artifact/com.kennycason/kumo)
* [Download JSoup 1.12.1 or higher jar] (https://jsoup.org/download)
* [Download SLF4J Api 1.6.1 or higher jar] (https://mvnrepository.com/artifact/org.slf4j/slf4j-api)
* [Download SLF4J Simple 1.6.1 or higher jar] (https://mvnrepository.com/artifact/org.slf4j/slf4j-simple)
* Clone repository
```
#clone repository cloud-tag
git clone https://github.com/mroui/cloud-tag.git
```
* Enter the main folder `cloud-tag`
* Create new folder `lib`
* Copy all jars into `lib` folder
* Import project by IntelliJ IDEA
* Add jars in IntelliJ IDEA:
    ..* Click `File` from the toolbar
    ..* Project Structure `(CTRL + SHIFT + ALT + S on Windows/Linux)`
    ..* Select Modules at the left panel
    ..* Dependencies tab
    ..* `+` -> JARs or directories
* Run an application

## License
This project is licensed under the terms of the MIT license.