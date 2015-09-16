package navigator

import com.vaadin.annotations.Theme
import com.vaadin.data.fieldgroup.BeanFieldGroup
import com.vaadin.data.fieldgroup.FieldGroup
import com.vaadin.data.fieldgroup.PropertyId
import com.vaadin.data.util.BeanItemContainer
import com.vaadin.event.LayoutEvents
import com.vaadin.navigator.Navigator
import com.vaadin.server.VaadinRequest
import com.vaadin.spring.annotation.SpringUI
import com.vaadin.ui.*
import com.vaadin.ui.themes.ValoTheme
import org.springframework.beans.factory.annotation.Autowired

@SpringUI
@Theme("valo")
public class NavigatorUI extends UI {

    private HorizontalLayout hlBack = new HorizontalLayout();
    private Label lbBack = new Label("back")

    private HorizontalLayout hlRegister = new HorizontalLayout();
    private Label lbRegister = new Label("Registration")
    private Button btLogin = new Button("OK")


    @Autowired
    private UserRepo repo;

    Navigator navigator;
    protected static final String REGISTERVIEW = "registration";
    protected static final String PORTALVIEW = "portal";

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        // Create a navigator to control the views
        navigator = new Navigator(this, this);

        lbRegister.addStyleName(ValoTheme.LABEL_SMALL)
        hlRegister.addComponent(lbRegister)


        lbBack.addStyleName(ValoTheme.LABEL_SMALL)
        hlBack.addComponent(lbBack)

        // Create and register the views
        navigator.addView("", new LoginView(this.navigator, hlRegister, repo));
        navigator.addView(REGISTERVIEW, new RegisterView(this.navigator, hlBack, repo));
        navigator.addView(PORTALVIEW,new PortalView(this.navigator))

        hlRegister.addListener( new LayoutEvents.LayoutClickListener() {
            @Override
            void layoutClick(LayoutEvents.LayoutClickEvent layoutClickEvent) {
                navigator.navigateTo(REGISTERVIEW);
            }
        });

        hlBack.addListener( new LayoutEvents.LayoutClickListener() {
            @Override
            void layoutClick(LayoutEvents.LayoutClickEvent layoutClickEvent) {
                navigator.navigateTo("");
            }
        });

        getPage().setTitle("Portal")

    }


}
