package application;

public class WebController {
    public void printId(Object object) {
        if (org.w3c.dom.html.HTMLElement.class.isAssignableFrom(object.getClass())) {
            org.w3c.dom.html.HTMLElement it = (org.w3c.dom.html.HTMLElement) object;
            System.out.println("Id is " + it.getId());
        }
    }
}