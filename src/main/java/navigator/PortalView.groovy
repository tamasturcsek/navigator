package navigator

import com.vaadin.annotations.DesignRoot
import com.vaadin.navigator.Navigator
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.server.ThemeResource
import com.vaadin.ui.Button
import com.vaadin.ui.Embedded
import com.vaadin.ui.Label
import com.vaadin.ui.Notification
import com.vaadin.ui.Panel
import com.vaadin.ui.VerticalLayout

/**
 * Created by Tamás on 2015. 09. 16..
 */
class PortalView extends VerticalLayout implements View {
    Navigator nav;

    VerticalLayout menuContent = new VerticalLayout();


    PortalView(def nav) {
        this.nav = nav;
        menuContent.addComponent(new Button("nemuitem1",
                new ButtonListener("menuitem1")));
        menuContent.addComponent(new Button("Logout", new Button.ClickListener() {
            @Override
            void buttonClick(Button.ClickEvent clickEvent) {
                nav.navigateTo("")
            }
        }))


        addComponent(menuContent)
    }

    // Menu navigation button listener
    class ButtonListener implements Button.ClickListener {
        String menuitem;
        public ButtonListener(String menuitem) {
            this.menuitem = menuitem;
        }
        @Override
        public void buttonClick(Button.ClickEvent event) {
            // Navigate to a specific state
            nav.navigateTo(NavigatorUI.PORTALVIEW + "/" + menuitem);
        }
    }


   @Override
    void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        Notification.show("Login successful", Notification.Type.TRAY_NOTIFICATION)
    }
}
