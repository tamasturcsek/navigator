package navigator

import com.vaadin.data.fieldgroup.PropertyId
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.*
import com.vaadin.ui.themes.ValoTheme

/**
 * Created by Tamás on 2015. 09. 15..
 */

class LoginView extends FormLayout implements View{
    private Label loginLabel = new Label("Login")
    private TextField userLoginField = new TextField("User")
    private PasswordField passwordLoginField = new PasswordField("Password")
    private Button btLogin = new Button("OK")

    public LoginView(def nav, def reg, def repo) {
        setSizeFull();
        loginLabel.addStyleName(ValoTheme.LABEL_H1)
        addComponent(loginLabel)
        addComponent(userLoginField)
        addComponent(passwordLoginField)
        HorizontalLayout hl = new HorizontalLayout()
        hl.setSpacing(true)
        hl.setWidth("20%")
        hl.addComponent(reg)
        hl.addComponent(btLogin)
        addComponent(hl)

        setSpacing(true)
        setMargin(true)
        userLoginField.setNullRepresentation("")
        passwordLoginField.setNullRepresentation("")

        btLogin.addClickListener(new Button.ClickListener() {
            @Override
            void buttonClick(Button.ClickEvent clickEvent) {
                def u = repo.findByUsername(userLoginField.getValue())
                if(u.isEmpty()) {
                    Notification.show("Failed to login","User does not exist", Notification.Type.ERROR_MESSAGE)
                    userLoginField.clear()
                    passwordLoginField.clear()
                }
                else {
                    if((u.get(0).password).equals(passwordLoginField.getValue()))
                        nav.navigateTo(NavigatorUI.PORTALVIEW)
                    else
                        Notification.show("Failed to login","Password is wrong", Notification.Type.ERROR_MESSAGE)
                        passwordLoginField.clear()
                }
            }
        })

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification.show("Welcome on my Portal", Notification.Type.TRAY_NOTIFICATION);
    }
}
