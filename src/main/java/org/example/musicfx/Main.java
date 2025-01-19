package org.example.musicfx;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import static javax.sound.sampled.FloatControl.Type.MASTER_GAIN;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import static org.example.musicfx.ButtonsAndSliders.addMusicButton;
import static org.example.musicfx.ButtonsAndSliders.buttonAutoRestart;
import static org.example.musicfx.ButtonsAndSliders.buttonBack10sec;
import static org.example.musicfx.ButtonsAndSliders.buttonNext;
import static org.example.musicfx.ButtonsAndSliders.buttonPlay;
import static org.example.musicfx.ButtonsAndSliders.buttonPrevious;
import static org.example.musicfx.ButtonsAndSliders.buttonRestart;
import static org.example.musicfx.ButtonsAndSliders.buttonSkip10sec;
import static org.example.musicfx.ButtonsAndSliders.buttonSound;
import static org.example.musicfx.ButtonsAndSliders.clearMusicListButton;
import static org.example.musicfx.ButtonsAndSliders.slider;
import static org.example.musicfx.ButtonsAndSliders.slider1;
import static org.example.musicfx.MusicList.file;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    
    @Override
    public void start(Stage stage) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        Text text = new Text();
        AtomicInteger i = new AtomicInteger();
        AtomicInteger AutoPlay = new AtomicInteger();
        Pane pane = new Pane();
        Image image = new Image(getClass().getResource("/org/example/musicfx/Zamok — копия.jpeg").toExternalForm());
        ImageView imageView = new ImageView(image);

        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        double newWidth = 1080;
        double newHeight = 656;
        Image icon = new Image(getClass().getResourceAsStream("/org/example/musicfx/kq2s6pmuUSjJsbQmbH9PS_yi9tF4u476CEURYpge-G5uMopqtvlw0DB3NdHOsI0I2hbxc2JR.jpg"));
        stage.getIcons().addAll(icon);
        stage.setTitle("NMD_MUSIC");
        imageView.setFitWidth(newWidth);
        imageView.setFitHeight(newHeight);
        pane.setBackground(new Background(backgroundImage));

        buttonPlay.setLayoutX(520);
        buttonPlay.setLayoutY(550);
        buttonPlay.setPrefWidth(70);
        buttonPlay.setPrefHeight(40);

        buttonRestart.setLayoutX(280);
        buttonRestart.setLayoutY(550);
        buttonRestart.setPrefWidth(70);
        buttonRestart.setPrefHeight(40);

        buttonAutoRestart.setLayoutX(170);
        buttonAutoRestart.setLayoutY(590);
        buttonAutoRestart.setPrefWidth(100);
        buttonAutoRestart.setPrefHeight(40);
        buttonAutoRestart.setStyle("-fx-background-color: #de136e;");

        buttonSkip10sec.setPrefWidth(70);
        buttonSkip10sec.setPrefHeight(40);
        buttonSkip10sec.setLayoutY(550);
        buttonSkip10sec.setLayoutX(600);

        buttonBack10sec.setPrefWidth(70);
        buttonBack10sec.setPrefHeight(40);
        buttonBack10sec.setLayoutY(550);
        buttonBack10sec.setLayoutX(440);

        buttonNext.setPrefWidth(70);
        buttonNext.setPrefHeight(40);
        buttonNext.setLayoutY(550);
        buttonNext.setLayoutX(680);

        buttonPrevious.setPrefWidth(70);
        buttonPrevious.setPrefHeight(40);
        buttonPrevious.setLayoutY(550);
        buttonPrevious.setLayoutX(360);

        slider.setLayoutX(360);
        slider.setLayoutY(610);
        slider.setPrefWidth(300);
        slider.setMin(0);
        slider.setMax(100);
        slider.setValue(40);
        String sliderStyle = "-fx-control-inner-background: #ab4fa9;";
        slider.setStyle(sliderStyle);
                                            

        buttonSound.setLayoutX(290);
        buttonSound.setLayoutY(600);
        buttonSound.setPrefWidth(55);
        buttonSound.setPrefHeight(31);
        buttonSound.setStyle("-fx-background-color: #1ade13;");

        slider1.setLayoutX(80);
        slider1.setLayoutY(510);
        slider1.setPrefWidth(900);
        slider1.setMin(0);
        slider1.setValue(0);
        String sliderStyle1 = "-fx-control-inner-background: #18cfef;";
        slider1.setStyle(sliderStyle1);

        addMusicButton.setLayoutX(130);
        addMusicButton.setLayoutY(540);
        addMusicButton.setPrefWidth(140);
        addMusicButton.setPrefHeight(40);
        addMusicButton.setStyle("-fx-background-color:rgb(152, 20, 240);");


        clearMusicListButton.setLayoutX(760);
        clearMusicListButton.setLayoutY(550);
        clearMusicListButton.setPrefWidth(100);
        clearMusicListButton.setPrefHeight(40);
        clearMusicListButton.setStyle("-fx-background-color:rgb(54, 108, 223);");

        AtomicReference<AudioInputStream> audioStream = new AtomicReference<>(AudioSystem.getAudioInputStream(file.get(i.get())));
        AtomicReference<Clip> clip = new AtomicReference<>(AudioSystem.getClip());
        clip.get().open(audioStream.get());

        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (slider.getValue() >= 30) {
                float SoundChange = ((float) oldValue.intValue() - 60.0f) / 3;
                FloatControl gainControl = (FloatControl) clip.get().getControl(MASTER_GAIN);
                gainControl.setValue(SoundChange);
                buttonSound.setStyle("-fx-background-color: #1ade13;");
                buttonSound.setText("\uD83D\uDD0A");
            } else if (slider.getValue() == 0) {
                float SoundChange = ((float) oldValue.intValue() - 80.0f);
                FloatControl gainControl = (FloatControl) clip.get().getControl(MASTER_GAIN);
                gainControl.setValue(SoundChange);
                buttonSound.setStyle("-fx-background-color: #de136e;");
                buttonSound.setText("\uD83D\uDD0A");
            } else  {
                float SoundChange = ((float) newValue.intValue() - 70.0f) / 3;
                FloatControl gainControl = (FloatControl) clip.get().getControl(MASTER_GAIN);
                gainControl.setValue(SoundChange);
                buttonSound.setStyle("-fx-background-color: #1ade13;");
                buttonSound.setText("\uD83D\uDD0A");
            }
        });

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.01), event -> extracted(text, i, AutoPlay, audioStream, clip))
                        );
                
                        text.setText(String.valueOf(file.get(i.get())));
                        text.setStyle("-fx-font-size: 25px;");
                
                        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(2), text);
                        text.setTranslateY(490);
                        text.setTranslateX(200);
                        translateTransition.setByX(50);
                        translateTransition.setCycleCount(Timeline.INDEFINITE);
                        translateTransition.setAutoReverse(true);
                        translateTransition.play();
                        timeline.setCycleCount(Timeline.INDEFINITE);
                        timeline.play();
                
                        pane.getChildren().add(text);
                
                        buttonPlay.setOnAction(e -> {
                            String currentText = buttonPlay.getText();
                            if (currentText.equals("▶")) {
                                clip.get().start();
                                buttonPlay.setText("⏸");
                            } else if (currentText.equals("⏸") ){
                                clip.get().stop();
                                buttonPlay.setText("▶");
                            } slider1.setMax(clip.get().getMicrosecondLength());
                        });
                
                        buttonNext.setOnAction(e -> extracted2(text, i, audioStream, clip));
                                                buttonPrevious.setOnAction(e -> {
                                                    float SoundChange = ((float) slider.getValue() - 60.0f) / 3;
                                                    clip.get().stop();
                                                    clip.get().setMicrosecondPosition(0);
                                                    buttonSound.setText("\uD83D\uDD0A");
                                                    buttonSound.setStyle("-fx-background-color: #1ade13;");
                                                    try {
                                                        if ((i.get() - 1) != -1) {
                                                            audioStream.set(AudioSystem.getAudioInputStream(file.get(i.get() - 1)));
                                                            i.set(i.get() - 1);
                                                            clip.set(AudioSystem.getClip());
                                                            clip.get().open(audioStream.get());
                                        
                                                            FloatControl gainControl = (FloatControl) clip.get().getControl(MASTER_GAIN);
                                                            gainControl.setValue(SoundChange);
                                                            text.setText(String.valueOf(file.get(i.get())));
                                                            clip.get().start();
                                                        } else {
                                                            text.setText(String.valueOf(file.get(i.get())));
                                                            clip.get().start();
                                                        }
                                                        slider1.setMax(clip.get().getMicrosecondLength());
                                                    } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
                                                        ex.printStackTrace();
                                                    } buttonPlay.setText("⏸");
                                                });
                                                buttonSkip10sec.setOnAction(e -> {
                                                    clip.get().stop();
                                                    long currentPosition = clip.get().getMicrosecondPosition();
                                                    if ((clip.get().getMicrosecondPosition() + 5000000) >= clip.get().getMicrosecondLength()) {
                                                        clip.get().setMicrosecondPosition(0);
                                                        slider1.setMax(clip.get().getMicrosecondLength());
                                                    } else {
                                                        long newPosition = currentPosition + 5000000;
                                                        clip.get().setMicrosecondPosition(newPosition);
                                                        slider1.setMax(clip.get().getMicrosecondLength());
                                                    }
                                                    text.setText(String.valueOf(file.get(i.get())));
                                                    clip.get().start();
                                                    buttonPlay.setText("⏸");
                                                });
                                        
                                                buttonBack10sec.setOnAction(e -> {
                                                    clip.get().stop();
                                                    long currentPosition = clip.get().getMicrosecondPosition();
                                                    if(currentPosition < 5000000) {
                                                        clip.get().setMicrosecondPosition(0);
                                                    } else {
                                                        long newPosition = currentPosition - 5000000;
                                                        clip.get().setMicrosecondPosition(newPosition);
                                                    }
                                                    slider1.setMax(clip.get().getMicrosecondLength());
                                                    text.setText(String.valueOf(file.get(i.get())));
                                                    clip.get().start();
                                                    buttonPlay.setText("⏸");
                                                });
                                        
                                                buttonRestart.setOnAction(e -> {
                                                    clip.get().stop();
                                                    clip.get().setMicrosecondPosition(0);
                                                    clip.get().start();
                                                    slider1.setMax(clip.get().getMicrosecondLength());
                                                    buttonPlay.setText("⏸");
                                                });
                                        
                                                buttonAutoRestart.setOnAction(e -> {
                                                    String buttonAutoRestartText = buttonAutoRestart.getText();
                                                    if (buttonAutoRestartText.equals("OFF "+"AutoPlay")) {
                                                        buttonAutoRestart.setStyle("-fx-background-color: #23de13;");
                                                        buttonAutoRestart.setText("ON(1) "+"AutoPlay");
                                                        AutoPlay.set(1);
                                                    } else if (buttonAutoRestartText.equals("ON(1) "+"AutoPlay")) {
                                                        buttonAutoRestart.setStyle("-fx-background-color: #08eed8;");
                                                        buttonAutoRestart.setText("ON(2) "+"AutoPlay");
                                                        AutoPlay.set(2);
                                                    } else if (buttonAutoRestartText.equals("ON(2) "+"AutoPlay")) {
                                                        buttonAutoRestart.setStyle("-fx-background-color: #de136e;");
                                                        buttonAutoRestart.setText("OFF "+"AutoPlay");
                                                        AutoPlay.set(0);
                                                    }
                                                    slider1.setMax(clip.get().getMicrosecondLength());
                                                });
                                        
                                                buttonSound.setOnAction(e -> {
                                                    String buttonSoundText = buttonSound.getText();
                                                    if (buttonSoundText.equals("\uD83D\uDD0A")) {
                                                        float SoundChange = -80.0f;
                                                        FloatControl gainControl = (FloatControl) clip.get().getControl(MASTER_GAIN);
                                                        gainControl.setValue(SoundChange);
                                                        buttonSound.setStyle("-fx-background-color: #de136e;");
                                                        buttonSound.setText("\uD83D\uDD08");
                                                    } else if (buttonSoundText.equals("\uD83D\uDD08")) {
                                                        float SoundChange = ((float) slider.getValue() - 60.0f) / 3;
                                                        FloatControl gainControl = (FloatControl) clip.get().getControl(MASTER_GAIN);
                                                        gainControl.setValue(SoundChange);
                                                        buttonSound.setStyle("-fx-background-color: #1ade13;");
                                                        buttonSound.setText("\uD83D\uDD0A");
                                                    }
                                                });
                                        
                                                addMusicButton.setOnAction(e -> {
                                                    FileChooser fileChooser = new FileChooser();
                                                    fileChooser.setTitle("select file .wav format");
                                                    fileChooser.getExtensionFilters().add(
                                                            new FileChooser.ExtensionFilter("WAV files", "*.wav")
                                                    );
                                                    File selectedFile = fileChooser.showOpenDialog(stage);
                                                    MusicList.addMusicWav(selectedFile);


                                                    float SoundChange = ((float) slider.getValue() - 60.0f) / 3;
                                                    clip.get().stop();
                                                    clip.get().setMicrosecondPosition(0);
                                                    buttonSound.setText("\uD83D\uDD0A");
                                                    buttonSound.setStyle("-fx-background-color: #1ade13;");
                                                    try {
                                                        if (i.get() != 0) {
                                                            audioStream.set(AudioSystem.getAudioInputStream(file.get(file.size() - 1)));
                                                            i.set(file.size() - 1);
                                                            clip.set(AudioSystem.getClip());
                                                            clip.get().open(audioStream.get());
                                        
                                                            FloatControl gainControl = (FloatControl) clip.get().getControl(MASTER_GAIN);
                                                            gainControl.setValue(SoundChange);
                                                            text.setText(String.valueOf(file.get(i.get())));
                                                            clip.get().start();
                                                        } slider1.setMax(clip.get().getMicrosecondLength());
                                                    } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
                                                        ex.printStackTrace();
                                                    } buttonPlay.setText("⏸");
                                                });

                                                clearMusicListButton.setOnAction(e -> {
                                                    MusicList.clearMusicList(i);
                                                    i.getAndSet(0);
                                                    clearMusicListButton.setStyle("-fx-background-color:rgb(9, 243, 9);");
                                                    clearMusicListButton.setText("Clean!");
                                                });

                                                pane.getChildren().addAll(slider, slider1, 
                                                buttonRestart, buttonPlay, buttonSkip10sec, buttonBack10sec, buttonNext, buttonPrevious, buttonAutoRestart,
                                                buttonSound, addMusicButton, clearMusicListButton);

                                                Scene scene = new Scene(pane, 1072, 656);
                                                String buttonHoverStyle = "-fx-background-color: #20f39d; -fx-text-fill: #000000;";
                                                buttonPlay.setOnMouseEntered(e -> buttonPlay.setStyle(buttonHoverStyle));
                                                buttonPlay.setOnMouseExited(e -> buttonPlay.setStyle("-fx-background-color: #e5dbdb;"));
                                        
                                                buttonSkip10sec.setOnMouseEntered(e -> buttonSkip10sec.setStyle(buttonHoverStyle));
                                                buttonSkip10sec.setOnMouseExited(e -> buttonSkip10sec.setStyle("-fx-background-color: #e5dbdb;"));
                                        
                                                buttonBack10sec.setOnMouseEntered(e -> buttonBack10sec.setStyle(buttonHoverStyle));
                                                buttonBack10sec.setOnMouseExited(e -> buttonBack10sec.setStyle("-fx-background-color: #e5dbdb;"));
                                        
                                                buttonNext.setOnMouseEntered(e -> buttonNext.setStyle(buttonHoverStyle));
                                                buttonNext.setOnMouseExited(e -> buttonNext.setStyle("-fx-background-color: #e5dbdb;"));
                                        
                                                buttonPrevious.setOnMouseEntered(e -> buttonPrevious.setStyle(buttonHoverStyle));
                                                buttonPrevious.setOnMouseExited(e -> buttonPrevious.setStyle("-fx-background-color: #e5dbdb;"));
                                        
                                                buttonRestart.setOnMouseEntered(e -> buttonRestart.setStyle(buttonHoverStyle));
                                                buttonRestart.setOnMouseExited(e -> buttonRestart.setStyle("-fx-background-color: #e5dbdb;"));
                                                buttonAutoRestart.setOnMouseExited(e -> buttonAutoRestart.setStyle(buttonAutoRestart.getStyle()));

                                                addMusicButton.setOnMouseEntered(e -> {
                                                    addMusicButton.setStyle(buttonHoverStyle);
                                                    addMusicButton.setText("add music file .wav");
                                                });
                                                addMusicButton.setOnMouseExited(e ->  {
                                                addMusicButton.setStyle("-fx-background-color:rgb(152, 20, 240);");
                                                addMusicButton.setText("add music file .wav");
                                                });

                                                clearMusicListButton.setOnMouseEntered(e -> {
                                                    clearMusicListButton.setStyle(buttonHoverStyle);
                                                    clearMusicListButton.setText("Clear music list");
                                                });
                                                clearMusicListButton.setOnMouseExited(e -> {
                                                    clearMusicListButton.setStyle("-fx-background-color:rgb(54, 108, 223);");
                                                    clearMusicListButton.setText("Clear music list");
                                                });
                                        
                                                stage.setResizable(false);
                                                stage.setScene(scene);
                                                stage.show();
                                            }
                        
                        
                            private void extracted2(Text text, AtomicInteger i, AtomicReference<AudioInputStream> audioStream,
                                    AtomicReference<Clip> clip) {
                                try {
                                    float SoundChange = ((float) slider.getValue() - 60.0f) / 3;
                                    
                                    clip.get().stop();
                                    clip.get().setMicrosecondPosition(0);
                                    buttonSound.setText("\uD83D\uDD0A");
                                    buttonSound.setStyle("-fx-background-color: #1ade13;");
                                    
                                    if ((i.get() + 1) < file.size()) {
                                        audioStream.set(AudioSystem.getAudioInputStream(file.get(i.get() + 1)));
                                        i.set(i.get() + 1);
                                        clip.set(AudioSystem.getClip());
                                        clip.get().open(audioStream.get());
                                    
                                        FloatControl gainControl = (FloatControl) clip.get().getControl(MASTER_GAIN);
                                        gainControl.setValue(SoundChange);
                                        clip.get().start();
                                        text.setText(String.valueOf(file.get(i.get())));
                                    } else {
                                        clip.get().start();
                                        text.setText(String.valueOf(file.get(i.get())));
                                    } slider1.setMax(clip.get().getMicrosecondLength());
                                } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
                                    ex.printStackTrace();
                                } buttonPlay.setText("⏸");
                            }
                
                
                    private void extracted(Text text, AtomicInteger i, AtomicInteger AutoPlay,
                            AtomicReference<AudioInputStream> audioStream, AtomicReference<Clip> clip) {
                        long currentMicrosecondPosition = clip.get().getMicrosecondPosition();
                        slider1.setValue(currentMicrosecondPosition);
                        if ((clip.get().getMicrosecondPosition() + 80000 >= clip.get().getMicrosecondLength()) && (AutoPlay.get() == 1)) {
                            try {
                                float SoundChange = ((float) slider.getValue() - 60.0f) / 3;
                                clip.get().stop();
                                clip.get().setMicrosecondPosition(0);
                
                                if ((i.get() + 1) < file.size() - 1) {
                                    audioStream.set(AudioSystem.getAudioInputStream(file.get(i.get() + 1)));
                                    i.set(i.get() + 1);
                                    clip.set(AudioSystem.getClip());
                                    clip.get().open(audioStream.get());
                
                                    FloatControl gainControl = (FloatControl) clip.get().getControl(MASTER_GAIN);
                                    gainControl.setValue(SoundChange);
                
                                    clip.get().start();
                                } else if (i.get() == file.size() - 1) {
                                    clip.get().stop();
                                    i.set(0);
                                    audioStream.set(AudioSystem.getAudioInputStream(file.get(i.get())));
                                    clip.set(AudioSystem.getClip());
                                    clip.get().open(audioStream.get());
                
                                    FloatControl gainControl = (FloatControl) clip.get().getControl(MASTER_GAIN);
                                    gainControl.setValue(SoundChange);
                                    clip.get().start();
                                } else {
                                    clip.get().start();
                                }
                            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
                                ex.printStackTrace();
                            } text.setText(String.valueOf(file.get(i.get())));
                
                        } else if ((clip.get().getMicrosecondPosition() + 80000 >= clip.get().getMicrosecondLength()) && (AutoPlay.get() == 2)) {
                                clip.get().stop();
                                clip.get().setMicrosecondPosition(0);
                                clip.get().start();
                            } text.setText(String.valueOf(file.get(i.get())));
                    }
}
