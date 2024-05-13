package org.example.musicfx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.sound.sampled.*;

import static javax.sound.sampled.FloatControl.Type.MASTER_GAIN;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        AtomicInteger i = new AtomicInteger();
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

        Button buttonPlay = new Button("▶");
        buttonPlay.setLayoutX(520);
        buttonPlay.setLayoutY(550);
        buttonPlay.setPrefWidth(70);
        buttonPlay.setPrefHeight(40);

        Button buttonRestart = new Button("\uD83D\uDD04");
        buttonRestart.setLayoutX(280);
        buttonRestart.setLayoutY(550);
        buttonRestart.setPrefWidth(70);
        buttonRestart.setPrefHeight(40);

        Button buttonAutoRestart = new Button("OF\uD83D\uDD01");
        buttonAutoRestart.setLayoutX(170);
        buttonAutoRestart.setLayoutY(590);
        buttonAutoRestart.setPrefWidth(70);
        buttonAutoRestart.setPrefHeight(40);

        Button buttonSkip10sec = new Button(">");
        Button buttonBack10sec = new Button("<");
        Button buttonNext = new Button(">>");
        Button buttonPrevious = new Button("<<");

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

        Slider slider = new Slider();
        slider.setLayoutX(360);
        slider.setLayoutY(610);
        slider.setPrefWidth(300);
        slider.setMin(0);
        slider.setMax(100);
        slider.setValue(20);

        String fileMSC = "music_list/";
        File file[] = new File[]{new File(fileMSC + "Svardstal_-_NIGHT_DRIVE_71484724 — копия.wav"),
                      new File(fileMSC + "LXST_CXNTURY_-_Andromeda_73377724 — копия.wav"),
                      new File(fileMSC + "ZXNTURY_JXXPSINNXR_FXLLEN_WXRRIOR_-_Farewell_74640904 — копия.wav")};


        AtomicReference<AudioInputStream> audioStream = new AtomicReference<>(AudioSystem.getAudioInputStream(file[i.get()]));
        AtomicReference<Clip> clip = new AtomicReference<>(AudioSystem.getClip());
        clip.get().open(audioStream.get());

        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int volume = newValue.intValue();
            float SoundChange = ((float) volume - 50.0f) / 3;
            FloatControl gainControl = (FloatControl) clip.get().getControl(MASTER_GAIN);
            gainControl.setValue(SoundChange);
        });

        Slider slider1 = new Slider();
        slider1.setLayoutX(80);
        slider1.setLayoutY(510);
        slider1.setPrefWidth(900);
        slider1.setMin(0);
        slider1.setMax(clip.get().getMicrosecondLength());
        slider1.setValue(0);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    long currentMicrosecondPosition = clip.get().getMicrosecondPosition();
                    slider1.setValue(currentMicrosecondPosition);
                })
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


        buttonPlay.setOnAction(e -> {
            String currentText = buttonPlay.getText();
            if (currentText.equals("▶")) {
                clip.get().start();
                buttonPlay.setText("⏸");
            } else if (currentText.equals("⏸") ){
                clip.get().stop();
                buttonPlay.setText("▶");
            }
        });

        buttonNext.setOnAction(e -> {
            try {
                float SoundChange = ((FloatControl) clip.get().getControl(MASTER_GAIN)).getValue();

                clip.get().stop();
                clip.get().setMicrosecondPosition(0);

                if ((i.get() + 1) < file.length) {
                    audioStream.set(AudioSystem.getAudioInputStream(file[i.get() + 1]));
                    i.set(i.get() + 1);
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
            }
        });


        buttonPrevious.setOnAction(e -> {
            float SoundChange = ((FloatControl) clip.get().getControl(MASTER_GAIN)).getValue();
            clip.get().stop();
            clip.get().setMicrosecondPosition(0);
            try {
                if ((i.get() - 1) != -1) {
                    audioStream.set(AudioSystem.getAudioInputStream(file[i.get() - 1]));
                    i.set(i.get() + 1);
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
            }
        });

        buttonSkip10sec.setOnAction(e -> {
            clip.get().stop();
            long currentPosition = clip.get().getMicrosecondPosition();
            if ((clip.get().getMicrosecondPosition() + 10000000) >= clip.get().getMicrosecondLength()) {
                clip.get().setMicrosecondPosition(0);
            } else {
                long newPosition = currentPosition + 10000000;
                clip.get().setMicrosecondPosition(newPosition);
            }
            clip.get().start();
        });

        buttonBack10sec.setOnAction(e -> {
            clip.get().stop();
            long currentPosition = clip.get().getMicrosecondPosition();
            if(currentPosition < 10000000) {
                clip.get().setMicrosecondPosition(0);
            } else {
                long newPosition = currentPosition - 10000000;
                clip.get().setMicrosecondPosition(newPosition);
            }
            clip.get().start();
        });

        buttonAutoRestart.setOnAction(e -> {
            String currentText = buttonRestart.getText();
            if (currentText.equals("OF "+"\uD83D\uDD04")) {
                buttonAutoRestart.setText("ON "+"\uD83D\uDD04");
            } else if (currentText.equals("ON " +"\uD83D\uDD04") ){
                buttonPlay.setText("OF "+ "\uD83D\uDD04");
            }
        });



        pane.getChildren().add(slider);
        pane.getChildren().add(slider1);
        String sliderStyle = "-fx-control-inner-background: #ab4fa9;";
        String sliderStyle1 = "-fx-control-inner-background: #18cfef;";
        slider.setStyle(sliderStyle);
        slider1.setStyle(sliderStyle1);
        pane.getChildren().add(buttonRestart);
        pane.getChildren().add(buttonPlay);
        pane.getChildren().add(buttonSkip10sec);
        pane.getChildren().add(buttonBack10sec);
        pane.getChildren().add(buttonNext);
        pane.getChildren().add(buttonPrevious);
        pane.getChildren().add(buttonAutoRestart);

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

        buttonAutoRestart.setOnMouseEntered(e -> buttonAutoRestart.setStyle(buttonHoverStyle));
        buttonAutoRestart.setOnMouseExited(e -> buttonAutoRestart.setStyle("-fx-background-color: #e5dbdb;"));

        stage.setResizable(false);
        stage.setScene(scene);

        stage.show();
    }
}
