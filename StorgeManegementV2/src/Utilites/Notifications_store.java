package Utilites;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author danah
 */
public class Notifications_store {

    public Notifications_store() {
    }

    
    

    public void saved(String statement) {
        Notifications notificationbuilder = Notifications.create()
                .title("saved")
                .text("تم إضافة " + statement + "بنجاح  ")
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("clicked on notifications");
                    }
                });
        notificationbuilder.showConfirm();
    }

    public void deleted(String statement) {
        Notifications notificationbuilder = Notifications.create()
                .title("delete")
                .text("تمت عملية الحذف  " + statement + " بنجاح")
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("clicked on notifications");
                    }
                });
        notificationbuilder.showConfirm();
    }

    public void updated(String statement) {
        Notifications notificationbuilder = Notifications.create()
                .title("Update")
                .text("تمت عملية التعديل " + statement + " بنجاح")
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("clicked on notifications");
                    }
                });
        notificationbuilder.showConfirm();
    }
    
     public void error(String statement) {
        Notifications notificationbuilder = Notifications.create()
                .title("error")
                .text("حدث خطأ في " + statement + "الرجاء إعادة المحاولة   ")
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("clicked on notifications");
                    }
                });
        notificationbuilder.showError();
    }
     
     
        public void warning(String statement) {
        Notifications notificationbuilder = Notifications.create()
                .title("warning")
                .text("احذر " + statement + "    الرجاء إعادة المحاولة")
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("clicked on notifications");
                    }
                });
        notificationbuilder.showWarning();
    }
     
     
    
    
}
