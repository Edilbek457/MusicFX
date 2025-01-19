# MusicFX 🎵

MusicFX is a JavaFX application for playing music and managing a track list. This project demonstrates functionality for handling and playing music.

---

## 📋 Table of Contents

- Features
- Requirements
- Installation
- Controls

---

## ✨ Features

- Playback of audio files in .wav format.
- Music player controls: play, pause, rewind, and repeat.
- Adding custom tracks via the user interface.
- Retaining the last played track when clearing the list.

---

## 💻 Requirements

Before installation, make sure you have the following:

- Java Development Kit (JDK) 21 or higher.
- Apache Maven 3.9+.
- A development environment (e.g., IntelliJ IDEA or Visual Studio Code).
- An internet connection to download Maven dependencies.

---

## 🚀 Installation

1. **Clone the repository**
    git clone https://github.com/Edilbek457/MusicFX.git
    cd MusicFX

2. **Build the project**
    Use Maven to download dependencies and compile the project:
    
    mvn clean install

3. **Verify dependencies**
    Ensure all dependencies are properly resolved:
    
    mvn dependency:resolve

4. **Run the application**
   
   mvn javafx:run 


🎛️ Controls
1. Play and Pause
        ▶️ Play / ⏸️ Pause the current track.
2. Skip and Rewind by 10 Seconds
        < Rewind 10 seconds / > Skip forward 10 seconds.
3. Previous and Next Track
        << Go to the previous track / >> Go to the next track.
4. Add New Track
        Add a .wav music file to the track list.
5. Clear Music List
        Remove all tracks from the list while retaining the last played track.
6. Restart
        🔄 Restart the current track.
7. Sound On/Off
        🔈 Mute / 🔊 Unmute the sound.
8. Volume Control
        Adjust volume using the pink slider.
9. Track Duration
        Observe the track duration and current position through the blue slider.
10. AutoPlay Modes
        OFF AutoPlay: Disable autoplay.
        ON(1) AutoPlay: Enable autoplay for the current track.
        ON(2) AutoPlay: Enable autoplay to play all tracks sequentially.