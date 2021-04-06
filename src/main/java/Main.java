import controllers.MainMenuController;
import models.viewmodels.MainMenuViewModel;
import views.MainMenuView;

public class Main {
    public static void main(String[] args) {
        var model = new MainMenuViewModel();
        var view = new MainMenuView();
        new MainMenuController(model, view);

        System.out.println("Hello World!");
    }
}
