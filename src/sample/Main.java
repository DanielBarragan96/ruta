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

        //Items
        Freight freight1 = new Freight(true, "Glez","Buga1");
        Freight freight2 = new Freight(false, "Glez","Buga2");
        Freight freight3 = new Freight(true, "Glez","Buga3");
        freight1.addItem("3Mod");
        freight1.addItem("3Mon");
        freight1.addItem("1Algo");

        //Store items
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
        GridPane.setColumnSpan(new Text(),7);
        for(int i = 0; i < 7; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(100));
            grid.getRowConstraints().add(new RowConstraints(100));
        }

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
        int i = 0, j = 0;
        for(Freight freight : freights) {
            Text text1 = new Text(freight.toString());
            text1.setFill(Color.WHITE);
            VBox box1 = new VBox(text1);
            box1.setStyle("-fx-background-color: " +
                    (freight.isIn() ? "rgba(0, 150, 0, 1);"
                            : "rgba(150, 50, 0, 1);"));

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

            //Add VBoxes to grid
            grid.add(box1, j, i++);
        }

        //Create scene and stage
        Scene scene = new Scene(grid, 800,800, Color.BLACK);
        primaryStage.setTitle("RUTA");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
