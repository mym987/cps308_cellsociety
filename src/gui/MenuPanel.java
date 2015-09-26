package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class MenuPanel extends HBox {

	public MenuPanel(){
        // create buttons, with their associated actions
        // old style way to do set up callback (anonymous class)
        myBackButton = makeButton("BackCommand", new EventHandler<ActionEvent>() {
            @Override      
            public void handle (ActionEvent event) {       
                back();        
            }      
        });
        result.getChildren().add(myBackButton);
        // new style way to do set up callback (lambdas)
        myNextButton = makeButton("NextCommand", event -> next());
        result.getChildren().add(myNextButton);
        myHomeButton = makeButton("HomeCommand", event -> home());
        result.getChildren().add(myHomeButton);
        // if user presses button or enter in text field, load/show the URL
        EventHandler<ActionEvent> showHandler = new ShowPage();
        result.getChildren().add(makeButton("GoCommand", showHandler));
        myURLDisplay = makeInputField(40, showHandler);
        result.getChildren().add(myURLDisplay);
	}

}
