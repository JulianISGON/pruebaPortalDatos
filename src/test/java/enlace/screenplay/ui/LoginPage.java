package enlace.screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LoginPage {

    public static final Target USERNAME_FIELD =
        Target.the("campo de usuario")
            .located(By.id("user"));

    public static final Target PASSWORD_FIELD =
        Target.the("campo de contraseña")
            .located(By.id("password"));

    public static final Target LOGIN_BUTTON =
        Target.the("botón de login")
            .located(By.xpath("//button[contains(text(), 'Ingresar') or contains(text(), 'Entrar') or contains(text(), 'Login')]"));

    // Selector para login FALLIDO
    public static final Target ERROR_MESSAGE =
        Target.the("mensaje de error - usuario o contraseña incorrectos")
            .located(By.cssSelector("div.alert.alert-danger"));

    // Selector para login EXITOSO
    public static final Target DASHBOARD_TITLE =
        Target.the("título del dashboard - Aviso legal")
            .located(By.cssSelector("span.title-dashboard"));




}
