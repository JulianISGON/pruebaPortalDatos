package enlace.screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CotizantesRCIPage {

    // Título del dashboard que confirma la carga completa
    public static final Target DASHBOARD_TITLE =
        Target.the("título del dashboard Aviso legal")
            .located(By.xpath("//span[contains(@class, 'title-dashboard') and contains(text(), 'Aviso legal')]"));

    // Selector para el menú Cotizantes RCI - excluye elementos móviles ocultos
    public static final Target MENU_COTIZANTES_RCI =
        Target.the("menú Cotizantes RCI")
            .located(By.xpath("//div[contains(@class, 'mt-2') and not(contains(@class, 'd-block d-sm-none'))]/a[contains(@class, 'sidebar-link')]/i[contains(@class, 'ri-user-3-line')]/parent::a"));

    // Título de la página de Cotizantes RCI que confirma que estamos en la página correcta
    public static final Target PAGE_TITLE_COTIZANTES_RCI =
        Target.the("título de la página Cotizantes RCI")
            .located(By.xpath("//span[contains(@class, 'title') and contains(text(), 'Cotizantes RCI')]"));

    public static final Target SELECT_DOCUMENT_TYPE =
        Target.the("selector de tipo de documento")
            .located(By.cssSelector("select[formcontrolname='documentType']"));

    public static final Target INPUT_DOCUMENT_NUMBER =
        Target.the("campo de número de documento")
            .located(By.id("documentNumber"));

    public static final Target BUTTON_SEARCH =
        Target.the("botón buscar")
            .located(By.xpath("//button[contains(@class, 'search-button') and contains(text(), 'Buscar')]"));

    public static final Target SUCCESS_ALERT =
        Target.the("alerta de consulta exitosa")
            .located(By.cssSelector(".alert-success.show-success"));

    public static final Target BUTTON_EXPORT_CSV =
        Target.the("botón exportar a CSV")
            .located(By.xpath("//button[contains(@class, 'search-button') and contains(text(), 'Exportar a CSV')]"));
}
