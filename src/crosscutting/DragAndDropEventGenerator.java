package crosscutting;

import javafx.event.EventHandler;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
/*
 * This class is responsible for creating the drag and drop targets.
 * Adapted from https://docs.oracle.com/javafx/2/drag_drop/jfxpub-drag_drop.htm
 * 
 */
public class DragAndDropEventGenerator 
{
	public static void DragAndDropCreator(Text source, Text target)
	{

        source.setOnDragDetected(new EventHandler<MouseEvent>() 
		{
		    public void handle(MouseEvent event) 
		    {
		    	// Detect a dragging movement and starts
		    	// the drag-and-drop gesture
		        Dragboard db = source.startDragAndDrop(TransferMode.ANY);
		        
		        // Allow a string to be put on a dragboard
		        ClipboardContent content = new ClipboardContent();
		        content.putString(source.getText());
		        db.setContent(content);
		        
		        event.consume();
		    }
		});

        source.setOnDragDone(new EventHandler<DragEvent>() 
        {
            public void handle(DragEvent event) 
            {
            	// Detect that the drag-and-drop gesture ended and
            	// if the data is correct it clears it
                if (event.getTransferMode() == TransferMode.MOVE) 
                {
                    source.setText("__________");
                }
                event.consume();
            }
        });
        

        target.setOnDragOver(new EventHandler<DragEvent>() 
		{
		    public void handle(DragEvent event) 
		    {
		        // Data is dragged over the target area. It's
		        // accepted only if it's not dragged from the same node 
		        // and if it has a string data
		        if (event.getGestureSource() != target &&
		                event.getDragboard().hasString()) 
		        {
		            // Allow both copying and moving
		            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		        }
		        
		        event.consume();
		    }
		});

		target.setOnDragEntered(new EventHandler<DragEvent>() 
		{
		    public void handle(DragEvent event) 
		    {
		    	// Detect that the drag-and-drop gesture entered the target area
		    	// and also show to the user that it is an actual gesture target 
		         if (event.getGestureSource() != target &&
		                 event.getDragboard().hasString()) 
		         {
		             target.setFill(Color.GREEN);
		         }
		                
		         event.consume();
		    }
		});

		target.setOnDragExited(new EventHandler<DragEvent>() 
		{
		    public void handle(DragEvent event) 
		    {
		        // If the mouse moves away, remove any graphical cue
		        target.setFill(Color.BLACK);

		        event.consume();
		    }
		});

		target.setOnDragDropped(new EventHandler<DragEvent>() 
		{
		    public void handle(DragEvent event) 
		    {
		        // The data is dropped by the user. If there is 
		        // a string data on dragboard, read it and use it
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasString()) 
		        {
		           target.setText(db.getString());
		           success = true;
		        }
		        
		        // Let the source know whether the string   
		        // was successfully transferred and used 
		        event.setDropCompleted(success);
		        
		        event.consume();
		     }
		});
	}
	
}

