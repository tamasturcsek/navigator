package navigator

import com.vaadin.data.fieldgroup.BeanFieldGroup
import com.vaadin.data.fieldgroup.FieldGroup
import com.vaadin.data.fieldgroup.PropertyId
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.Button
import com.vaadin.ui.FormLayout
import com.vaadin.ui.HorizontalLayout
import com.vaadin.ui.Label
import com.vaadin.ui.Notification
import com.vaadin.ui.PasswordField
import com.vaadin.ui.TextField
import com.vaadin.ui.themes.ValoTheme

import javax.validation.constraints.Pattern

/**
 * Created by Tamás on 2015. 09. 15..
 */
class RegisterView extends FormLayout implements View {
    private Label regLabel = new Label("Registration")

    @Pattern(regexp="^[a-zA-Z]+", message = "szar")
    @PropertyId(User.USERNAME)
    private TextField userRegField = new TextField("User")
    @PropertyId(User.PASSWORD)
    private PasswordField passwordRegField = new PasswordField("Password")
    @PropertyId(User.NAME)
    private  TextField nameRegField = new TextField("Name")
    @PropertyId(User.MAIL)
    private TextField mailRegField = new TextField("E-mail")
    private Button btReg = new Button("OK")

    private BeanFieldGroup<User> fg = new BeanFieldGroup<>(User.class);

    RegisterView( def nav, def back, def repo) {
        setSizeFull();
        regLabel.addStyleName(ValoTheme.LABEL_H1)
        addComponent(regLabel)
        addComponent(userRegField)
        addComponent(passwordRegField)
        addComponent(nameRegField)
        addComponent(mailRegField)
        HorizontalLayout hl = new HorizontalLayout()
        hl.setSpacing(true)
        hl.setWidth("20%")
        hl.addComponent(back)
        hl.addComponent(btReg)
        addComponent(hl)

        setSpacing(true)
        setMargin(true)
        userRegField.setNullRepresentation("")
        mailRegField.setNullRepresentation("")
        passwordRegField.setNullRepresentation("")
        nameRegField.setNullRepresentation("")

        fg.setItemDataSource(new User())
        fg.bindMemberFields(this)

        btReg.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                try {
                    fg.commit();
                    repo.save(fg.getItemDataSource().getBean());
                    fg.setItemDataSource(new User());
                    Notification.show("Registration was successful", Notification.Type.TRAY_NOTIFICATION);
                } catch (FieldGroup.CommitException e) {
                    e.printStackTrace();
                }
            }
        })
    }


    @Override
    void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        Notification.show("Please register", Notification.Type.TRAY_NOTIFICATION);
    }
}
