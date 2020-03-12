package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.input.DragEvent;
import utils.Freight;

import java.awt.*;
import java.util.ArrayList;
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

        //Items
        Freight freight1 = new Freight(true, "Glez","Buga1");
        Freight freight2 = new Freight(false, "Glez","Buga2");
        Freight freight3 = new Freight(true, "Glez","Buga3");
        freight1.addItem("3Mod");
        freight1.addItem("3Mon");
        freight1.addItem("1Algo");

        ArrayList<Freight> freights = new ArrayList<Freight>();
        freights.add(freight1);
        freights.add(freight2);
        freights.add(freight3);


        //Create grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20,20,10,10));
        grid.setVgap(8);
        grid.setHgap(10);
        grid.setGridLinesVisible(true);

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
        grid.setOnDragExited(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                //TODO: find dragged item
            }
        });

        //Add items to grid
        GridPane.setColumnSpan(new Text(),7);
        for(int i = 0; i < 7; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(100));
            grid.getRowConstraints().add(new RowConstraints(100));
        }

        VBox box1 = new VBox(new Text(freight1.toString()));
        box1.setStyle("-fx-background-color: " +
                (freight1.isIn() ? "rgba(0, 255, 0, 0.3);"
                    : "rgba(255, 0, 0, 0.3);"));
        box1.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dragboard drag_board = box1.startDragAndDrop(TransferMode.ANY);

                ClipboardContent content = new ClipboardContent();
                String msg = box1.toString();
                content.putString(msg);
                drag_board.setContent(content);
                event.consume();
            }
        });

        VBox box2 = new VBox(new Text(freight2.toString()));
        box1.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dragboard drag_board = box2.startDragAndDrop(TransferMode.ANY);

                ClipboardContent content = new ClipboardContent();
                String msg = box2.toString();
                content.putString(msg);
                drag_board.setContent(content);
                event.consume();
            }
        });

        VBox box3 = new VBox(new Text(freight3.toString()));
        box1.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dragboard drag_board = box3.startDragAndDrop(TransferMode.ANY);

                ClipboardContent content = new ClipboardContent();
                String msg = box3.toString();
                content.putString(msg);
                drag_board.setContent(content);
                event.consume();
            }
        });

        //Add VBoxes to grid
        grid.add(box1, 0, 0);
        grid.add(box2, 0, 1);
        grid.add(box3, 1, 0);




        Scene scene = new Scene(grid, 800,800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
