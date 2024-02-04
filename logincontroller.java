//package controller;
//
//import java.util.List;
//
//import org.zkoss.zk.ui.ComponentNotFoundException;
//import org.zkoss.zk.ui.select.SelectorComposer;
//import org.zkoss.zk.ui.select.annotation.Listen;
//import org.zkoss.zk.ui.select.annotation.Wire;
//import org.zkoss.zul.Label;
//import org.zkoss.zul.Messagebox;
//import org.zkoss.zul.Textbox;
//import org.zkoss.zul.Window;
//
//import javapart.Loginpage;
//import javapart.Loginpojo;
//
//public class logincontroller extends SelectorComposer<Window>{
//    @Wire
//    private Textbox usernameTextbox, passwordTextbox;
//    @Wire
//    private Label login;
//
//    @Listen("onClick=#login")
//    public void loginpage() {
//        Loginpage p = new Loginpage();
//        String username = usernameTextbox.getValue();
//        List<Loginpojo> check = p.fetchlogindetails(username);
//        if (!check.isEmpty()) {
//            // Username is correct, display an alert message
//            Messagebox.show("Welcome, " + username + "!");
//        } else {
//            // Username is incorrect, display an error message
//            Messagebox.show("Incorrect username!", "Error", Messagebox.OK, Messagebox.ERROR);
//        }
//    }
//}
//
package controller;

import org.zkoss.jsp.zul.NavitemTag;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;


import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import javapart.Loginpage;

public class logincontroller extends SelectorComposer<Window> {
	@Wire
	private Button Q;
    @Wire
    private Textbox usernameTextbox, passwordTextbox;
    @Wire
    private Label i1;
   // private Navitem contactNavItem;
    @Listen("onClick=#Q")
    public void loginpage() {
        String username = usernameTextbox.getValue();
        String password = passwordTextbox.getValue();
        Loginpage loginPage = new Loginpage();
        if (loginPage.authenticateUser(username, password)) {
            // Authentication successful
          //  Messagebox.show("Welcome, " + username + "!");
        	
            Executions.sendRedirect("home.zul");
           // contactNavItem.setLabel(username);
            i1.setValue("Hello World!");
            i1.setValue("Welcome, " + username + "!");
        } else {
            // Authentication failed
            Messagebox.show("Incorrect username or password!", "Error", Messagebox.OK, Messagebox.ERROR);
        }
    }
}
	
