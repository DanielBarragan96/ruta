package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.input.DragEvent;

import java.awt.*;
import java.util.Collections;


public class Main extends Application {

    Button button;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("RUTA");
        button = new Button("Hola");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AlertBox.display("Well","Hello there");
            }
        });

        Text text1 = new Text("cuado1");
        Text text2 = new Text("cuado2");
        Text text3 = new Text("cuado3");
        HBox titles = new HBox(20);
        VBox layout = new VBox(20);
        layout.getChildren().addAll(text1, text2);
        VBox layout2 = new VBox(20);
        layout2.getChildren().add(text3);

        //Create grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);
        grid.setGridLinesVisible(true);


        text1.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dragboard drag_board = text1.startDragAndDrop(TransferMode.ANY);

                ClipboardContent content = new ClipboardContent();
                content.putString(text1.getText());
                drag_board.setContent(content);
                event.consume();
            }
        });

        layout2.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                if(event.getGestureSource() != layout2 &&
                        event.getDragboard().hasString()){
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                event.consume();
            }
        });
        layout2.setOnDragExited(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                //TODO: find dragged item
            }
        });

        grid.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                if(event.getGestureSource() != grid &&
                        event.getDragboard().hasString()){
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                event.consume();
            }
        });

        GridPane.setConstraints(text1, 0, 0);
        GridPane.setConstraints(text2, 0, 1);
        GridPane.setConstraints(text3, 1, 0);
        grid.getChildren().addAll(text1, text2, text3);
        //titles.getChildren().addAll(grid, layout, layout2);

        Scene scene = new Scene(grid, 300,250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
